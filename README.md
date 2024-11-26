## 安装

Project 的 settings.gradle.ktx 添加仓库
> Kotlin DSL
```kotlin
dependencyResolutionManagement {
    repositories {
        // ...
        maven("https://jitpack.io")
    }
}
```

Module 的 build.gradle.ktx 添加依赖框架
> Kotlin DSL
```gradle
dependencies {
   compileOnly("com.github.Xiaolangs:sweet_plugins:1.0.13")
}
```

## 使用
* ###  编写入口类，如：
```kotlin
class InitHooker : BaseIHookXposedInit() {
    override fun PackageParam.load() {
         ///这里面就可以开始的插件之旅
    }
}
```
* ###  最后在`main`下新建`assets`目录，在里面创建`sweet_init`文件，文件里面就可以添加我们刚刚的类了，如：
```
com.sweet.plugins.InitHooker
```

## 插件资源注入
```kotlin
///进行资源注入
 curPluginsHelper?.injectPluginAppResources()
```

在使用以下功能之前，为防止资源 ID 互相冲突，你需要在当前项目的 `build.gradle.ktx` 中修改资源 ID。

> Kotlin DSL

```kotlin
android {
    androidResources.additionalParameters += listOf("--allow-reserved-package-id", "--package-id", "0x65")
}
```
## APIs
* ### CoreStorage
```
CoreStorage.getWxId()    : 获取微信号
CoreStorage.getWxOldId() : 获取原始微信号
CoreStorage.getPhone()   : 获取手机号
CoreStorage.getSex()     : 获取性别
CoreStorage.getNickName(): 获取昵称
CoreStorage.getSign()    : 获取个性签名
```
* ### AvatarStorage
```
AvatarStorage.setAvatar() : ImageView 设置头像
AvatarStorage.getAvatar() : 获取头像
AvatarStorage.getAvatar() : 获取头像
```
