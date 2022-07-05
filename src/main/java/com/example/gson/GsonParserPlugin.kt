package com.example.gson

import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * User: linxiaodong
 * Date: 2022/7/1
 * Time: 4:21 PM
 */
class GsonParserPlugin:Plugin<Project> {
    override fun apply(project: Project) {
        project.logger
        println("com.example.gson.GsonParserPlugin apply")

        val androidComponents=project.extensions.getByType(AndroidComponentsExtension::class.java)

        androidComponents.beforeVariants {variantBuilder->
           val gsonExtension= project.extensions.findByName("gsonPlugin") as? GsonPluginExtension
           val variantExtension=project.objects.newInstance(GsonPluginExtension::class.java)
           variantExtension.config.set(GsonPluginConfig(enable = gsonExtension?.config?.get()?.enable?:false))
           variantBuilder.registerExtension(GsonPluginExtension::class.java,variantExtension)
        }

        androidComponents.onVariants { variant ->
            //注册Transform，控制是否需要扫描依赖库代码
            variant.instrumentation.transformClassesWith(
                GsonParserTransform::class.java,
                InstrumentationScope.ALL
            ){}

            variant.instrumentation.setAsmFramesComputationMode(
                FramesComputationMode.COMPUTE_FRAMES_FOR_INSTRUMENTED_METHODS
            )
            val gsonPluginExtension = variant.getExtension(GsonPluginExtension::class.java)
            val gsonPluginEnable=gsonPluginExtension?.config?.get()?.enable
            println("加载Gson插件配置信息 gsonPluginEnable:$gsonPluginEnable")
        }
    }
}