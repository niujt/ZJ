docker run --name mysql5.7 -e MYSQL_ROOT_PASSWORD=123456 -p 3306:3306 -d mysql:5.7
sleep 5
docker cp  sql/zj.sql mysql5.7:/root/ && docker exec -it mysql5.7 /bin/bash
# 容器内执行
# mysql -Uroot -p123456
# source /root/zj.sql;
# exit
docker run -d --name zj -p 8088:8088  jtniu/zj:v1

