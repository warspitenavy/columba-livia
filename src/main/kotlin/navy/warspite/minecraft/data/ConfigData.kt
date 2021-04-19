package navy.warspite.minecraft.data

import kotlinx.serialization.Serializable

/**
 * Config定義
 */
object ConfigData {
    /**
     * @param server サーバー設定
     * @param master マスター設定
     * @Param players プレイヤー設定
     */
    @Serializable
    data class Config(
        val server: Server? = Server(),
        val master: Master? = Master(),
        val players: ArrayList<Player>? = arrayListOf()
    )

    /**
     * Discordサーバー(ギルド)設定
     * @param channel チャンネルID
     */
    @Serializable
    data class Server(
        val channel: String? = ""
    )

    /**
     * マスターBot設定
     * @param token Discord Botのトークン
     */
    @Serializable
    data class Master(
        val token: String? = "",
    )

    /**
     * プレイヤー設定
     * @param uuid MinecraftユーザーのUUID
     * @param token 紐付けるDiscord Botのトークン
     */
    @Serializable
    data class Player(
        val uuid: String?,
        val token: String?
    )
}