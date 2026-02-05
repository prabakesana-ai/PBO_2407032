import {
  makeWASocket,
  useMultiFileAuthState,
  DisconnectReason
} from '@whiskeysockets/baileys'
import fs from 'fs'
import axios from 'axios'

/* ================= KONFIG ================= */
const OWNER = '6281224442601@s.whatsapp.net'
const DATA_FILE = './positions.json'
const CONFIG_FILE = './config.json'
const AUTH_FOLDER = './auth'
const CHECK_INTERVAL = 60 * 1000 // 1 menit

/* ================= INIT ================= */
if (!fs.existsSync(AUTH_FOLDER)) fs.mkdirSync(AUTH_FOLDER)
if (!fs.existsSync(DATA_FILE)) fs.writeFileSync(DATA_FILE, '[]')
if (!fs.existsSync(CONFIG_FILE)) fs.writeFileSync(CONFIG_FILE, '{}')

/* ================= GLOBAL STATE ================= */
let groupSelectionState = {
    active: false,
    list: []
}

/* ================= COIN ================= */
const COINS = {
  pepe: { name: 'Pepe', pair: 'pepe_idr', cg: 'pepe' },
  btc: { name: 'Bitcoin', pair: 'btc_idr', cg: 'bitcoin' },
  eth: { name: 'Ethereum', pair: 'eth_idr', cg: 'ethereum' },
  sol: { name: 'Solana', pair: 'sol_idr', cg: 'solana' },
  doge: { name: 'Dogecoin', pair: 'doge_idr', cg: 'dogecoin' },
  shib: { name: 'Shiba Inu', pair: 'shib_idr', cg: 'shiba-inu' },
  xrp: { name: 'XRP', pair: 'xrp_idr', cg: 'ripple' },
  link: { name: 'Chainlink', pair: 'link_idr', cg: 'chainlink' }
}

/* ================= HELPER ================= */
const load = () => JSON.parse(fs.readFileSync(DATA_FILE))
const save = d => fs.writeFileSync(DATA_FILE, JSON.stringify(d, null, 2))

const loadConfig = () => {
    try { return JSON.parse(fs.readFileSync(CONFIG_FILE)) } 
    catch { return {} }
}
const saveConfig = (d) => fs.writeFileSync(CONFIG_FILE, JSON.stringify(d, null, 2))

const rupiah = n => 'Rp ' + Number(n).toLocaleString('id-ID')
const price = n =>
  Number(n).toLocaleString('id-ID', {
    minimumFractionDigits: 2,
    maximumFractionDigits: 8
  })

/* ================= INDODAX ================= */
async function ticker(coin) {
  try {
    const pair = COINS[coin]?.pair
    if (!pair) return null

    const r = await axios.get(`https://indodax.com/api/${pair}/ticker`, {
      timeout: 10000
    })

    const t = r.data.ticker
    return {
      last: parseFloat(t.last),
      high: parseFloat(t.high),
      low: parseFloat(t.low)
    }
  } catch {
    return null
  }
}

/* ================= COINGECKO (BACKTEST) ================= */
async function history(coin, hours) {
  const id = COINS[coin]?.cg
  if (!id) return null

  const r = await axios.get(
    `https://api.coingecko.com/api/v3/coins/${id}/market_chart`,
    {
      params: {
        vs_currency: 'idr',
        days: Math.ceil(hours / 24)
      }
    }
  )
  return r.data.prices
}

/* ================= BOT ================= */
async function startBot() {
  const { state, saveCreds } = await useMultiFileAuthState(AUTH_FOLDER)
  const sock = makeWASocket({ auth: state, printQRInTerminal: true })

  sock.ev.on('creds.update', saveCreds)

  sock.ev.on('connection.update', ({ connection, lastDisconnect }) => {
    if (connection === 'close') {
        const shouldReconnect = lastDisconnect?.error?.output?.statusCode !== DisconnectReason.loggedOut
        console.log('Koneksi terputus, mencoba reconnect:', shouldReconnect)
        if (shouldReconnect) {
            startBot()
        }
    } else if (connection === 'open') {
        console.log('Bot Terhubung!')
    }
  })

  /* ================= COMMAND ================= */
  sock.ev.on('messages.upsert', async ({ messages }) => {
    try {
        const m = messages[0]
        if (!m?.message) return
        if (m.key.remoteJid !== OWNER) return

        const text =
        m.message.conversation ||
        m.message.extendedTextMessage?.text ||
        ''
        
        // === LOGIKA PEMILIHAN GRUP ===
        if (groupSelectionState.active) {
            const index = parseInt(text.trim())
            if (!isNaN(index) && index > 0 && index <= groupSelectionState.list.length) {
                const selectedGroup = groupSelectionState.list[index - 1]
                
                let cfg = loadConfig()
                cfg.targetGroup = selectedGroup.id
                cfg.targetGroupName = selectedGroup.subject
                saveConfig(cfg)

                groupSelectionState.active = false
                groupSelectionState.list = []

                return sock.sendMessage(OWNER, { 
                    text: `✅ Berhasil mengatur grup target ke:\n*${selectedGroup.subject}*\n\nUpdate profit/loss akan dikirim ke grup tersebut.` 
                })
            } else if (text.toLowerCase() === 'batal') {
                groupSelectionState.active = false
                return sock.sendMessage(OWNER, { text: '❌ Pemilihan grup dibatalkan.' })
            }
        }

        if (!text.startsWith('.')) return
        const a = text.trim().split(/\s+/)
        const cmd = a[0].toLowerCase()
        let data = load()

        /* ===== MENU ===== */
        if (cmd === '.menu')
        return sock.sendMessage(OWNER, {
            text:
`📊 MENU TRADING BOT

.buy <coin> <harga_token> <modal_idr>
.posisi <coin>
.hapus <coin>

.cek <coin>
.saran <coin>
.hitung <harga_coin> <modal_idr>
.backtest <coin> <24h / 7d>

.koin
.koinku
.grup (Atur grup notifikasi)
.hapusgrup (Stop notifikasi)`
        })

        /* ===== SET GRUP ===== */
        if (cmd === '.grup') {
            const groups = await sock.groupFetchAllParticipating()
            const groupList = Object.values(groups)
            
            if (groupList.length === 0) {
                return sock.sendMessage(OWNER, { text: '⚠️ Bot belum bergabung ke grup manapun.' })
            }

            let txt = '📢 *PILIH GRUP UNTUK NOTIFIKASI*\nKirim angka (contoh: 1) untuk memilih.\n\n'
            groupList.forEach((g, i) => {
                txt += `${i + 1}. ${g.subject}\n`
            })
            txt += '\nKetik "batal" untuk membatalkan.'

            groupSelectionState.active = true
            groupSelectionState.list = groupList

            return sock.sendMessage(OWNER, { text: txt })
        }

        /* ===== HAPUS GRUP (STOP NOTIF) ===== */
        if (cmd === '.hapusgrup') {
            let cfg = loadConfig()
            if (!cfg.targetGroup) {
                return sock.sendMessage(OWNER, { text: '⚠️ Belum ada grup yang diatur sebagai penerima notifikasi.' })
            }

            const oldGroupName = cfg.targetGroupName || 'Grup Lama'
            
            // Hapus data grup dari config
            delete cfg.targetGroup
            delete cfg.targetGroupName
            saveConfig(cfg)

            return sock.sendMessage(OWNER, { 
                text: `✅ Sukses! Notifikasi ke grup *${oldGroupName}* dimatikan.\nBot tidak akan mengirim update ke sana lagi.` 
            })
        }

        /* ===== DAFTAR COIN ===== */
        if (cmd === '.koin') {
        let t = '🪙 DAFTAR COIN\n\n'
        for (const k in COINS)
            t += `• ${COINS[k].name} → ${k}\n`
        return sock.sendMessage(OWNER, { text: t })
        }

        /* ===== COIN DIMILIKI ===== */
        if (cmd === '.koinku') {
        if (!data.length)
            return sock.sendMessage(OWNER, { text: '📭 Belum ada coin' })
        let t = '💼 COIN MILIKMU\n\n'
        for (const p of data)
            t += `• ${COINS[p.coin].name} (${p.coin})\n`
        return sock.sendMessage(OWNER, { text: t })
        }

        /* ===== BUY ===== */
        if (cmd === '.buy') {
        const coin = a[1]
        const entry = parseFloat(a[2])
        const modal = parseFloat(a[3])

        if (!COINS[coin] || !entry || !modal)
            return sock.sendMessage(OWNER, {
            text: '❌ Format: .buy pepe 0.000113 100000'
            })

        data.push({
            coin,
            entry,
            modal,
            amount: modal / entry,
            high: entry,
            lastLog: 0,
            sellProfitSent: false,
            sellProtectSent: false
        })
        save(data)

        return sock.sendMessage(OWNER, {
            text:
`✅ BUY TERCATAT
Coin   : ${COINS[coin].name}
Entry  : ${price(entry)}
Modal  : ${rupiah(modal)}`
        })
        }

        /* ===== POSISI ===== */
        if (cmd === '.posisi') {
        const p = data.find(x => x.coin === a[1])
        if (!p) return

        const t = await ticker(p.coin)
        if (!t) return

        const currentVal = p.amount * t.last // Total Uang Sekarang
        const pl = currentVal - p.modal      // Selisih (Untung/Rugi)
        const persen = ((t.last - p.entry) / p.entry) * 100

        return sock.sendMessage(OWNER, {
            text:
`📌 POSISI ${p.coin.toUpperCase()}

Entry : ${price(p.entry)}
Now   : ${price(t.last)}

Modal : ${rupiah(p.modal)}
Total : ${rupiah(currentVal)}

P/L   : ${persen.toFixed(2)}%
Cuan  : ${rupiah(pl)}`
        })
        }

        /* ===== HAPUS ===== */
        if (cmd === '.hapus') {
        data = data.filter(x => x.coin !== a[1])
        save(data)
        return sock.sendMessage(OWNER, { text: '🗑 Posisi dihapus' })
        }

        /* ===== CEK ===== */
        if (cmd === '.cek') {
        const t = await ticker(a[1])
        if (!t) return
        return sock.sendMessage(OWNER, {
            text:
`📈 HARGA ${a[1].toUpperCase()}

Now  : ${price(t.last)}
Low  : ${price(t.low)}
High : ${price(t.high)}`
        })
        }

        /* ===== SARAN ===== */
        if (cmd === '.saran') {
        const t = await ticker(a[1])
        if (!t) return

        const range = t.high - t.low
        const pos = ((t.last - t.low) / range) * 100

        let rec = 'TUNGGU'
        if (pos < 30) rec = 'BUY'
        else if (pos > 80) rec = 'SELL'

        return sock.sendMessage(OWNER, {
            text:
`🤖 SARAN 24 JAM

Now  : ${price(t.last)}
Low  : ${price(t.low)}
High : ${price(t.high)}
Pos  : ${pos.toFixed(1)}%

Rekomendasi : ${rec}`
        })
        }

        /* ===== BACKTEST ===== */
        if (cmd === '.backtest') {
        const coin = a[1]
        const tf = a[2]
        let hours = tf.endsWith('h') ? parseInt(tf) :
                    tf.endsWith('d') ? parseInt(tf) * 24 : 0
        if (!hours) return

        const prices = await history(coin, hours)
        if (!prices) return

        let win = 0, loss = 0
        for (let i = 10; i < prices.length; i++) {
            if (prices[i][1] > prices[i - 1][1]) win++
            else loss++
        }

        const total = win + loss
        return sock.sendMessage(OWNER, {
            text:
`📊 BACKTEST ${coin.toUpperCase()}
Durasi : ${tf}
Data   : ${total}
Up     : ${win}
Down   : ${loss}`
        })
        }
    } catch (err) {
        console.log('Error di pesan:', err)
    }
  })

  /* ================= AUTO SIGNAL & TRACKER ================= */
  setInterval(async () => {
    // Cek apakah sock siap menerima pesan
    if (!sock?.user) return 

    try {
        const cfg = loadConfig()
        const targetGroup = cfg.targetGroup

        // Jika belum ada grup disetting, skip notif (AUTO STOP)
        if (!targetGroup) return

        let data = load()
        let changed = false

        for (const p of data) {
            const t = await ticker(p.coin)
            if (!t) continue

            if (t.last > p.high) p.high = t.last

            // HITUNG VALUE
            const currentVal = p.amount * t.last 
            const currentPL = ((t.last - p.entry) / p.entry) * 100
            const plRupiah = currentVal - p.modal

            if (typeof p.lastLog === 'undefined') p.lastLog = 0

            const diff = Math.abs(currentPL - p.lastLog)

            // LOGIKA UPDATE (Setiap 0.10% pergerakan)
            if (diff >= 0.10) {
                const emoji = currentPL >= 0 ? '🟢' : '🔴'
                const status = currentPL >= 0 ? 'PROFIT' : 'LOSS'
                const arrow = currentPL > p.lastLog ? '⬆️' : '⬇️'

                // === LOGIKA TAG MEMBER KELIPATAN 2% (2, 4, 6, 8, 10...) ===
                const floorCurrent = Math.floor(currentPL)
                const floorLast = Math.floor(p.lastLog)

                const isMultipleOfTwo = (floorCurrent % 2 === 0) && (floorCurrent >= 2)
                const isNewLevel = floorCurrent !== floorLast

                const shouldTag = isMultipleOfTwo && isNewLevel
                
                let mentions = undefined
                let footerText = ''

                if (shouldTag) {
                    try {
                        const groupMetadata = await sock.groupMetadata(targetGroup)
                        mentions = groupMetadata.participants.map(p => p.id)
                        footerText = `\n\n🎉 *TARGET ${floorCurrent}% HIT!* (Attention All Members)`
                    } catch (e) {
                        console.log('Gagal ambil peserta grup:', e.message)
                    }
                }

                // KIRIM PESAN KE GRUP
                try {
                    await sock.sendMessage(targetGroup, {
                        text:
`📢 *UPDATE SIGNAL ${p.coin.toUpperCase()}*

Status : ${status} ${arrow}
Entry  : ${price(p.entry)}
Now    : ${price(t.last)}

Modal  : ${rupiah(p.modal)}
Total  : ${rupiah(currentVal)}

P/L    : ${emoji} ${currentPL.toFixed(2)}%
Cuan   : ${rupiah(plRupiah)}

(Gerak ${diff.toFixed(2)}% dari info terakhir)${footerText}`,
                        mentions: mentions 
                    })
                    
                    p.lastLog = currentPL
                    changed = true
                } catch (sendErr) {
                    console.log(`Gagal kirim update ${p.coin} ke grup:`, sendErr.message)
                }
            }
        }

        if (changed) save(data)
    } catch (loopErr) {
        console.log('Error di loop interval:', loopErr)
    }
  }, CHECK_INTERVAL)
}

// === PENGAMAN ANTI CRASH ===
process.on('uncaughtException', function (err) {
    console.log('Caught exception: ', err)
})

process.on('unhandledRejection', (reason, p) => {
    console.log('Unhandled Rejection at:', p, 'reason:', reason)
})

startBot()