## Android唯一设备号
### 方案一
#### 具体做法
 - 生成UUID
 - 保存在APP中
 - 保存在SD卡中
 - APP和SD卡相互保存，防止用户卸载APP或者删除SD卡文件
#### 代码部分截图
![Screenshot](screenshot/01.png)

#### 使用方法
![Screenshot](screenshot/02.png)

### 注意
务必在使用前判断SD卡权限，否则保存，库中没有做SD卡权限申请或判断。


