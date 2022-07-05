package com.example.gson

import org.gradle.api.provider.Property

/**
 * User: linxiaodong
 * Date: 2022/7/4
 * Time: 8:04 PM
 * 自定义插件扩展，使插件可以自定义配置
 */
interface GsonPluginExtension {

    abstract val config: Property<GsonPluginConfig>

}

data class GsonPluginConfig(
    val enable: Boolean = false
)