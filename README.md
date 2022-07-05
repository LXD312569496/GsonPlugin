## Getting started

### Add plugin
Using the plugins DSL:
```
plugins{
    id "io.github.LXD312569496.GsonPlugin"
}
```

Using legacy plugin application:
```
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "com.donggua:GsonPlugin:[latest]"
  }
}

apply plugin: "io.github.LXD312569496.GsonPlugin"
```

### When use with gson
when you use gson ,you can show some log in logcat.<br>
println the json string and the type。
```
2022-07-05 16:06:26.379 29819-29819/com.example.gsonparserplugin D/GsonRecorder: fromJson text:{"age":111,"name":"dongggua"}, type:class com.example.gsonparserplugin.MainActivity$Person}
```
