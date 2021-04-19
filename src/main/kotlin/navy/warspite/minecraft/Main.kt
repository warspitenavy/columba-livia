package navy.warspite.minecraft

import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    companion object {
        lateinit var instance: JavaPlugin
        private set
    }

    override fun onEnable() {
        super.onEnable()
        instance = this
    }

    override fun onDisable() {
        super.onDisable()
    }
}