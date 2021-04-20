package navy.warspite.minecraft

import navy.warspite.minecraft.bot.Master
import navy.warspite.minecraft.bot.User
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

/**
 * Minecraftのリスナークラス
 * @author warspite.navy
 */
object McListener : Listener {
    /**
     * プレイヤーが入室したとき、Discordにメッセージを送信
     * @param e PlayerJoinEvent
     */
    @EventHandler
    private fun playerJoinEvent(e: PlayerJoinEvent) {
        Master.send("${e.player.name} joined the game.")
        User.run(e.player.uniqueId)
    }

    /**
     * プレイヤーが退室したとき、Discordにメッセージを送信
     * @param e PlayerQuitEvent
     */
    @EventHandler
    private fun playerQuitEvent(e: PlayerQuitEvent) {
        Master.send("${e.player.name} left the game.")
        User.shutdown(e.player.uniqueId)
    }

    /**
     * ゲーム内チャットをDiscordに送信する
     * @param e AsyncPlayerChatEvent
     */
    @EventHandler
    private fun onAsyncPlayerChatEvent(e: AsyncPlayerChatEvent) {
        User.send(e.player.uniqueId, e.message)
    }
}