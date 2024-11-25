pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven("https://api.xposed.info/")
        // MavenCentral 有 2 小时缓存，若无法集成最新版本请添加此地址
        maven("https://s01.oss.sonatype.org/content/repositories/releases/")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://api.xposed.info/")
        // MavenCentral 有 2 小时缓存，若无法集成最新版本请添加此地址
        maven("https://s01.oss.sonatype.org/content/repositories/releases/")
    }
}
plugins {
    id("com.highcapable.sweetdependency") version "1.0.4"
    id("com.highcapable.sweetproperty") version "1.0.5"
}
sweetProperty {
    rootProject { all { isEnable = false } }
}
rootProject.name = "sweet_plugins"
include(":app")
include(":plugins")
include(":yukihookapi-core")
include(":yukihookapi-stub")
