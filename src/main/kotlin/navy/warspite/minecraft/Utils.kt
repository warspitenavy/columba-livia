package navy.warspite.minecraft

import org.bukkit.ChatColor

/**
 * ユーティリティクラス
 * @author warspite.navy
 */
object Utils {
    /**
     * 装飾された文字列に変換
     * @param text 変換するテキスト
     * @param prefix 文字列にプラグインのprefixを付けるオプション
     * @return 変換された文字列
     */
    fun coloured(text: String, prefix: Boolean = false): String {
        return if (prefix) {
            ChatColor.translateAlternateColorCodes(
                '&',
                "&r[&a${Main.instance.name}&r] $text"
            )
        } else {
            ChatColor.translateAlternateColorCodes('&', text)
        }
    }
}