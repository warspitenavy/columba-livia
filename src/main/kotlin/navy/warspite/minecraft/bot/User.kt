package navy.warspite.minecraft.bot

import navy.warspite.minecraft.Config
import navy.warspite.minecraft.Main
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.util.*

/**
 * プレイヤーBotクラス
 * @author warspite.navy
 */
object User {
    val users = linkedMapOf<UUID, JDA>()

    /**
     * プレイヤーBotを起動する
     * @param uuid プレイヤーのUUID
     */
    fun run(uuid: UUID) {
        if (users[uuid] != null) return
        val token = Config.config.players?.find { it.uuid == "$uuid" }?.token ?: return
        JDABuilder.createDefault(token).addEventListeners(object : ListenerAdapter() {
            override fun onReady(event: ReadyEvent) {
                super.onReady(event)
                Master.update()
                users[uuid] = event.jda
            }
        }).build()
    }

    /**
     * プレイヤーBotを停止する
     * @param uuid プレイヤーのUUID
     */
    fun shutdown(uuid: UUID) {
        users[uuid]?.also {
            it.shutdownNow()
            users.remove(uuid)
        }
    }

    /**
     * ログインしているユーザーのBotを起動する
     */
    fun runOnlinePlayer() {
        val players = Main.instance.server.onlinePlayers.map { it.uniqueId }
        players.forEach { this.run(it) }
    }

    /**
     * ユーザーBotでメッセージを送信する
     * @param uuid プレイヤーUUID
     * @param message メッセージ
     */
    fun send(uuid: UUID, message: String) {
        users[uuid]?.let { Server.send(it, message) }
    }
}