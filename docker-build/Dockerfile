FROM centos:8
MAINTAINER niujiatao "niujiatao@qq.com"

# 使用yum的方式安装mysql
WORKDIR app
WORKDIR /usr/local/node/
ADD ZjAction.jar /app/ZjAction.jar
ADD node-v8.11.1-linux-x64.tar.gz /usr/local/node/
ADD pm2-2.10.2.tar.gz /usr/local/node/node-v8.11.1-linux-x64/lib/node_modules/
RUN chown root:root /usr/local/node -R \
    && ln -s /usr/local/node/node-v8.11.1-linux-x64/bin/node /usr/local/bin/node \
    && ln -s /usr/local/node/node-v8.11.1-linux-x64/lib/node_modules/pm2/bin/pm2 /usr/local/bin/pm2
ADD pm2.config.js /app/pm2.config.js
ADD jdk-8u162-linux-x64.rpm /app/jdk-8u162-linux-x64.rpm
RUN rpm -ivh /app/jdk-8u162-linux-x64.rpm
EXPOSE 8088
ENTRYPOINT ["pm2","start","/app/pm2.config.js"]
