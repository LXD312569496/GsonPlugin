plugins {
    id("java-gradle-plugin")
    id("maven-publish")
    id("com.gradle.plugin-publish") version "0.14.0"
    `kotlin-dsl`
}

group = "io.github.lxd312569496"
version = "1.0"

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(gradleApi())
    implementation("com.android.tools.build:gradle-api:7.2.0")
    implementation("commons-io:commons-io:2.6")
    implementation("commons-codec:commons-codec:1.15")
    implementation("org.ow2.asm:asm-commons:9.2")
    implementation("org.ow2.asm:asm-tree:9.2")
}


pluginBundle{
    website = "https://github.com/LXD312569496/GsonPlugin"
    vcsUrl = "https://github.com/LXD312569496/GsonPlugin"
    tags = listOf("gson")
}

gradlePlugin{
    plugins{
        create("com.donggua.GsonPlugin"){
            id = "io.github.LXD312569496.GsonPlugin"
            displayName = "GsonPlugin"
            description = "A Plugin that can hook fromJson method,show cost time"
            implementationClass = "com.example.gson.GsonParserPlugin"
        }
    }
}

////
//////发布到maven仓库
publishing{
    publications {
    }
    repositories{
        //发布到本地
        //Gradle将默认使用USER_HOME/.m2/repository作为本地Maven仓库位置。
        mavenLocal()

        //发布到网络

    }
}

sourceSets{
    main{
    }
}