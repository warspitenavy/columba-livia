package navy.warspite.minecraft

import navy.warspite.minecraft.bot.Master
import navy.warspite.minecraft.bot.Server
import navy.warspite.minecraft.command.CommandRegister
import org.bukkit.plugin.java.JavaPlugin

/**
 * メインクラス
 * @author warspite.navy
 */
class Main : JavaPlugin() {
    /** メインクラスのインスタンス変数 */
    companion object {
        lateinit var instance: JavaPlugin
        private set
    }

    /** プラグイン読み込み */
    override fun onEnable() {
        super.onEnable()
        instance = this
        Config.loadConfig()

        if (Config.validity) {
            Master.run()
            server.pluginManager.registerEvents(McListener, this)
        }
        getCommand("clivia")?.setExecutor(CommandRegister)
    }

    /** プラグイン停止 */
    override fun onDisable() {
        super.onDisable()
        Server.powerOff()
    }
}