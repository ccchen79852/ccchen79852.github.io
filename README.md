# 拾光博客（Vue + Spring Boot + MySQL）

## 本地启动

1. 启动 MySQL：`C:\Program Files\MySQL\MySQL Server 8.4\bin\mysqld.exe --defaults-file="mysql\my.ini"`
2. PowerShell 设置密码：`$env:DB_PASSWORD='（你的本地 MySQL 密码）'`
3. 后端：`cd backend; mvn spring-boot:run`
4. 前端：`cd frontend; npm install; npm run dev`

访问 `http://localhost:5173`。前端会代理 `/api` 到 Spring Boot 的 `8080` 端口。
