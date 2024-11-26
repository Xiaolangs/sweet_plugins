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
* ###  申明为甜萝 module项目

在`AndroidManifest`中通过`meta-data`申明:

> - `sweetModule`: 是否甜萝插件
> - `sweetDescription`: 该模块描述

```xml
<application .....>
    <meta-data
        android:name="sweetModule"
        android:value="true" />
    <meta-data
        android:name="sweetDescription"
        android:value="描述" />
</application>
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
* ### PluginsUtils
```
PluginsUtils.getWxClassLoader() : 获取最上层宿主ClassLoader
PluginsUtils.getWxApplication() : 获取最上层宿主Applicatio
```
* ### CoreStorage
```
CoreStorage.getWxId()     : 获取微信号
CoreStorage.getWxOldId()  : 获取原始微信号
CoreStorage.getPhone()    : 获取手机号
CoreStorage.getSex()      : 获取性别
CoreStorage.getNickName() : 获取昵称
CoreStorage.getSign()     : 获取个性签名
```
* ### AvatarStorage
```
AvatarStorage.setAvatar() : ImageView 设置头像
AvatarStorage.getAvatar() : 获取头像
AvatarStorage.getAvatar() : 获取头像
```
* ### HttpUtils
```
HttpUtils.get()        : GET请求
HttpUtils.post()       : POST请求
HttpUtils.submitForm() : 表单提交
HttpUtils.download()   : 文件下载
```
* ### LogKTX
```
LogKTX.e()      : 日志打印
LogKTX.eStack() : 打印日志调用栈
```
* ### Scope 协程扩展
```
scope {
    HttpUtils.get<String>("").onSuccess { }.onFailure { }
}
```
* ### FileExt 文件扩展
```
File.smartCreateNewFile() : 创建新文件
File.smartCreateFolder()  : 创建文件夹
File.deleteFolder()       : 删除文件夹
File.md5()                : 获取文件MD5
```
