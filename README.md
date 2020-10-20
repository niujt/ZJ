# ZJ
# 智能组卷系统  
### 页面采用spring的thymeleaf  
### 技术栈springboot+springmvc+mybatis+pageHepler（分页插件）  

### 项目启动
```shell 
bash start.sh
```
## 下面项目部分截图 
--- 
### 首页  
![管理员-首页](imgs/12.png)  
---
### 新增试卷-自动组卷  
![新增试卷-自动组卷](imgs/1.png)  
---  
### 新增试卷-人工组卷  
![新增试卷-人工组卷](imgs/13.png)    
---
### 作业列表  
![作业列表](imgs/2.png)
---
### 学生-评价和建议      
![学生-评价和建议](imgs/3.png) 
---
### 学生-作业查看     
![老师-作业查看](imgs/4.png)   
### 老师-作业批改    
![老师-作业批改](imgs/5.png)  
---
![老师-作业批改](imgs/6.png)  
---
![老师-作业批改](imgs/7.png)  
---
### 考卷管理   
![考卷管理](imgs/8.png)  
---
### 考卷答案
![考卷答案](imgs/9.png)  
----
### 登录
![登录](imgs/10.png)  
---
![登录](imgs/14.png)  
---
### 注册
![注册](imgs/11.png)   

## FAQ
项目启动报错，不存在XXXX表     
需要修改mysql的配置文件，忽略表大小写   
在[mysqld]节点下，加入一行： lower_case_table_names=1

   
