package navy.warspite.minecraft.data

data class Messages(
    val commandReference: String = """
                    &r---------- &aColumbaLivia&r ----------
                    &r/clivia edit <&auser&r> &b#トークン削除
                    &r/clivia edit <&auser&r> <&atoken&r> &b#トークン編集
                    &r/clivia reboot &b#プラグイン再起動
                """.trimIndent(),
    val missingPlayer: String = "そのプレイヤーが見つかりません",
    val removeToken: String = "トークンを削除しました",
    val editToken: String = "トークンを編集しました"
)
