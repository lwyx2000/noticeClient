# NoticeClient (Android / Java / JDK 1.8)

一个纯自用的消息通知 APP（Java），核心能力：
- 集成 JPush 自动初始化
- 推送消息入库 + 列表展示 + 详情查看
- 消息级别：普通/成功/警告/错误
- SQLite 本地持久化，重启不丢失
- 到达消息时系统通知（声音+震动）
- 删除单条、一键清空
- 设置页查看并复制 Registration ID
- 前台服务 + 开机自启，提升保活稳定性

## 快速使用
1. 在 `app/src/main/AndroidManifest.xml` 中替换 `JPUSH_APPKEY`。
2. 使用 Android Studio 打开项目。
3. JDK 选择 1.8 语法兼容（项目已配置 source/targetCompatibility 1.8）。
4. 安装运行后，JPush 推送自定义消息格式推荐：
   ```json
   {
     "title": "业务标题",
     "content": "业务内容",
     "level": "NORMAL|SUCCESS|WARNING|ERROR"
   }
   ```

## 结构说明
- `app/app/NoticeApplication`：JPush 初始化、拉起保活服务
- `receiver/JPushMessageReceiver`：接收推送，解析并入库，发系统通知
- `data/MessageRepository`：SQLite 数据读写
- `ui/MainActivity`：消息列表、删除、清空
- `ui/MessageDetailActivity`：详情展示
- `ui/SettingsActivity`：展示/复制 Registration ID
- `service/KeepAliveService`：前台保活服务

## 权限策略
仅保留推送通知与保活所需基础权限：
- INTERNET / ACCESS_NETWORK_STATE
- WAKE_LOCK / VIBRATE
- RECEIVE_BOOT_COMPLETED / FOREGROUND_SERVICE
- POST_NOTIFICATIONS（Android 13+）
