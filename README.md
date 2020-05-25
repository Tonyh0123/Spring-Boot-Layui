#大学生创业服务平台-系统部署手册
## 感谢Radom7
##写在前面：
>本系统的底层框架是基于Spring Boot + Mybatis的组合，如果你已经获得了该系统的源码，并打算部署上自己的电脑运行，那么我便默认你对Spring Boot和Mybatis是熟悉的。如果不熟悉，请自行学习Spring Boot以及Mybatis。
本系统使用的开发工具建议使用IDEA，如果对IDEA不熟悉，可以尝试学习，时间成本不会太高。
##系统部署：
>Spting Boot项目的部署十分简便，要想成功部署并运行，需要以下几个条件：
> * ###将源码导入IDEA。

---

> * ###正确配置Mysql数据库的连接：
>> 保证Mysql版本一致：本系统使用的Mysql版本为5.1.45版本，如果你的Mysql版本并非5.1.45，则需要将pom.xml中的Mysql版本给替换成你所需要的版本。

---

> * ###正确配置配置和数据库连接：
>> 找到application.yml文件，把图1-2中url、username、password正确配置即可。

---

> * ###正确配置系统下的静态资源路径；
>> * ① 如果你的电脑硬盘有一个F盘，并且F盘的根目录下建立有“caseImages”、“noticeFile”和“identifyConfirmFile”这三个文件夹，则可以省略掉这一步的配置。
>> * ② 如果没有，则需修改MyWebConfig.java类,将相关路径修改成你本地指定路径即可。
>> * ③ 如果你在部署成功之后使用上传功能时系统出现了文件上传错误的问题，那一定是路径配置的问题，请仔细查看所涉及到的上传的java类以及js文件，将代码中所有涉及到的“F:/**”修改成你配置的路径即可。
>> * ④ 为了省去不必要的麻烦，如果有条件，请尽量满足条件①，如果没有F盘，也请在其他盘中建立名字相同的三个文件夹。

---

> * ###建立好对应的数据库以及数据表：
>>数据库结构及数据sql文件路径如下：

    ..\src\main\resources\sql\graproject.sql
>> 使用数据库管理工具Navicat运行此文件，创建好数据库以及数据表。

---

> * ###正确配置smtp服务器：
>>系统有涉及到简单邮件的发送，Smtp建议使用163邮箱。在application.yml中进行配置。

---

> * ###系统管理员账号：
    用户名：admin
    密码：000000
>>其他角色类型的用户可自行注册/申请。

---

##写在最后：
>要是想把本系统部署在自己的本机，请务必认真仔细地关注代码中的路径问题，如果系统运行出错，有很大可能是路径不正确，所以再次强调：关注代码中的路径。
这些问题在部署中可能不会出现，要在实际运行使用中才有可能发现，请务必认真对系统的功能进行全面使用测试。
