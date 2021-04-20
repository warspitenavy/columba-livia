package navy.warspite.minecraft

import com.charleskorn.kaml.EmptyYamlDocumentException
import com.charleskorn.kaml.InvalidPropertyValueException
import com.charleskorn.kaml.UnknownPropertyException
import com.charleskorn.kaml.Yaml
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import navy.warspite.minecraft.data.ConfigData
import java.lang.Exception
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

/**
 * Configファイルに関するクラス
 * @author warspite.navy
 */
object Config {
    private val server = Main.instance.server

    /** プラグインのディレクトリパス */
    private val dir = Paths.get("./plugins/${Main.instance.name}")

    /** configファイルパス */
    private val configFile = dir.resolve("config.yml")

    /** config変数 */
    lateinit var config: ConfigData.Config

    /** configファイルの妥当性 */
    var validity = false

    /**
     * configファイルを保存する
     * @param data ConfigData.Config
     */
    fun save(data: ConfigData.Config) {
        val file = Yaml.default.encodeToString(data).split(System.lineSeparator())
        Files.write(configFile, file)
    }

    /**
     * ユーザーのトークンを編集
     * トークンがnullの場合、削除する
     * @param uuid UUID
     * @param token Botトークン
     * @return 正常終了の場合、true
     */
    fun edit(uuid: UUID, token: String? = null): Boolean {
        val data = this.config
        val user = data.players?.find { it.uuid == "$uuid" } ?: return false

        data.players.remove(user)
        if (token != null) data.players.add(ConfigData.Player("$uuid", token))
        save(data)
        return true
    }

    /**
     * 引数のconfigをバリデーション
     * @param data ConfigData.Config
     * @return 問題ない場合、true
     */
    private fun validation(data: ConfigData.Config): Boolean {
        val messages = arrayListOf<String>()

        if (data.server == null) messages.add("serverマッピングがありません")
        if (data.server?.channel.isNullOrBlank()) messages.add("チャンネルIDが未設定です")

        if (data.master == null) messages.add("masterマッピングがありません")
        if (data.master?.token.isNullOrBlank()) messages.add("マスタートークンが未設定です")

        for (message in messages) server.logger.info(message)

        return messages.isEmpty()
    }

    /**
     * configファイルを読み込む
     * 変数configに代入する
     */
    fun loadConfig() {
        initialise()
        config = try {
            Yaml.default.decodeFromString(configFile.toFile().readText())
        } catch (e: EmptyYamlDocumentException) {
            initialise(true)
            Yaml.default.decodeFromString(configFile.toFile().readText())
        } catch (e: Exception) {
            when (e) {
                is InvalidPropertyValueException, is UnknownPropertyException -> {
                    server.logger.info("configファイルに誤りがあります")
                    ConfigData.Config()
                }
                else -> throw e
            }
        }

        if (validation(config)) {
            validity = true
            server.logger.info("configファイルを読み込みました")
        }
    }

    /**
     * configファイルを生成する
     * プラグインのディレクトリが存在しない場合、ディレクトリを生成する
     * @param force 強制的にconfigを生成する
     */
    private fun initialise(force: Boolean = false) {
        if (!Files.isDirectory(dir) || Files.notExists(dir)) {
            Files.createDirectory(dir)
        }

        if (Files.notExists(configFile) || force) {
            val file = Yaml.default.encodeToString(ConfigData.Config()).split(System.lineSeparator())
            Files.write(configFile, file)
        }
    }
}