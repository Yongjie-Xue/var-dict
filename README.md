# VarDictHover - IDEA 自定义词典悬浮插件

读取本地 txt 词典文件，在 Java 标识符的快速文档弹窗中展示对应的中文释义，方便快速识别业务变量与方法的含义。

## 功能特性
- 支持变量、方法、类名的精确匹配
- 支持自定义词典文件路径，无需重新打包
- 纯本地运行，无网络请求，不泄露代码
- 全平台通用（macOS / Windows / Linux）

## 安装方式
1.  源码执行 `buildPlugin` 打包，获取 `VarDictHover-1.0.zip`
2.  打开 IDEA → Settings → Plugins → 右上角齿轮 → Install Plugin from Disk
3.  选中 zip 包安装，重启 IDEA 生效

## 词典配置
### 默认路径
插件默认读取用户主目录下的词典文件：
- macOS/Linux: `~/dictionary.txt`
- Windows: `C:\Users\你的用户名\dictionary.txt`

### 自定义路径
1.  IDEA 顶部菜单：`Help → Edit Custom VM Options...`
2.  添加一行配置，替换为你的词典绝对路径：
    -Dvardict.path=/ 你的自定义路径 /dictionary.txt
3.  保存后重启 IDEA 生效

## 词典格式
UTF-8 纯文本格式，每行一条词条，`标识符 + 空格 + 中文释义`，支持 `#` 开头的注释行：
```txt
# 患者相关
patientId 患者编号
userId 用户ID
medicalRecord 病历

# 业务相关
deptCode 科室编码
bizCode 业务编码
## 触发方式

1. 光标放在标识符上，按 `F1`（Mac 可配合 Fn）调出快速文档
2. 开启悬浮：Settings → Editor → Code Editing → 勾选 Show quick documentation on hover

## 兼容版本

- IntelliJ IDEA 2025.3 及以上（旗舰版 / 社区版均可）
- 基于经典文档 API 开发，高版本向后兼容稳定