## Android唯一设备号
## 方案一
### 具体做法
 - 生成UUID
 - 保存在APP中
 - 保存在SD卡中
 - APP和SD卡相互保存，防止用户卸载APP或者删除SD卡文件
### 代码部分截图
![Screenshot](screenshot/01.png)

### 快速集成

#### 1.添加依赖

在项目的`build.gradle`中，需要在`allprojects`最后添加`jitpack`


```
allprojects {
    repositories {
       ...

        maven { url 'https://jitpack.io' }
    }
}
```
在APP的`build.gradle`中添加如下依赖，版本号以最新为准

```
    implementation 'com.github.zdf01082413:AndroidDeviceId:1.0'
```

#### 2.直接使用
```
     // 1. path 如需要自己设置
     val Path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS).toString() + File.separator + "AndroidDeviceId"
     CustomDeviceId.init(this).setFilePath(Path).getCustomDeviceId

     // 2. path 也可不要，因有默认位置
     CustomDeviceId.init(this)getCustomDeviceId
```
### 注意
务必在使用前判断SD卡权限，否则会直接出错，库中没有做SD卡权限申请或判断。


