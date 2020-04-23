# sso单点登录
# 跨域、spring、jwt
找到三个系统模块下OrgApplication.java文件，分别都run as->1java application  

位置如下：
sso_cqu\cas_cqu\src\main\java\cas\OrgApplication.java  

sso_cqu\sport_cqu\src\main\java\sport\OrgApplication.java  

sso_cqu\jwc_cqu\src\main\java\jwc\OrgApplication.java

系统A：sport_cqu    访问入口：http://localhost:8081/sport  

系统B：jwc_cqu       访问入口：http://localhost:8082/jwc  

访问控制中心：cas_cqu
用户名：yyf   密码：yyf
用户名：fnzs   密码：fnzs


在登陆期间：
访问http://localhost:8081/sport、http://localhost:8081/sportvisit可以直接进入http://localhost:8081/sportvisit来访问系统A页面

访问http://localhost:8082/jwc、http://localhost:8082/jwcvisit可以直接进入http://localhost:8082/jwcvisit来访问系统A页面

未登陆期间：
访问http://localhost:8081/sport会跳转到中心登录

访问http://localhost:8081/sportvisit显示已退出登录

访问http://localhost:8082/jwc会跳转到中心登录

访问http://localhost:8082/jwcvisit显示已退出登录
