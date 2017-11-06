### 管理员管理服务模块

    1. 提供管理员账号，管理员角色权限管理的代码库
    2. 作为被引用的代码库而不是可独立部署的微服务节点存在
    3. 因为此模块作为被引用的代码库存在，所以
        1. 本身并不是也不提供可直接运行的 SpringBootApplication
        2. 本模块需要的数据库配置由调用者指定,使用标准 spring 数据源 datasource 定义

### Spring JPA , BCryptPasswordEncoder        