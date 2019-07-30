# springboot-demo

### 1. 建项目添加依赖

建议每个工程添加以下依赖库
```
- spring-boot-starter-data-jpa 数据库的对象关系映射库
- spring-boot-starter-test 单元测试
- lombok 简化数据的get和set函数编写
- fastjson 处理json的库
- swagger2 api文档，做接口测试也很必须
```

### 项目的代码目录结构

Project目录结构
```
main
    - java
    - resources
        - sql 创建表的记录，方便测试建表
        - static 静态文件，前端文件
        - application.properties 工程的配置
test
```

java/com.xxx代码的目录结构
```
config - 项目配置
    - SwaggerConfig 接口文档的配置
entity - 通用的数据结构
dal - 数据库表对应的数据结构
dao - dal中的数据操作
dto - 传输层的数据结构
service - 数据操作和逻辑
controller - 对外http接口层
util - 通用的工具函数
Application 启动入口mian函数
```
