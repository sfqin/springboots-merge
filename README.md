# springboots-merge
springboot（稳定版 2.1.3.RELEASE） 整合其它组件

1. 整合 redis-5.0.4
2. 整合 elasticsearch-6.5.4  默认 application.properties 和 esController 已经注释防止项目启动报错
  1) 查看集群状态: localhost:9200/_cat/health?v
  2) 查看索引列表: localhost:9200/_cat/indices?v
  3) 查看某个索引库结构：localhost:9200/blog
  4) 查看某个对象：localhost:9200/blog/article/1
  5) javaAPI: https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.3/index.html
3. 整合 apache-activemq-5.15.7
4. 整合 rocketmq-all-4.4.0
  1) rocketmq 安装完成后默认启动需要的内存很多 需要修改 bin 目录下 runbroker.sh 和 runserver.sh 脚本，需要将 JAVA_OPT="${JAVA_OPT} -server 后的参数调小
  2) 使用 rocketmq-console 的坑, 在 github 上 clone 了 rocketmq 的源码后, 进入对应目录使用 maven 命令进行编译，各种jar依赖找不到，解决方案，修改pom文件，将版本号进行对应，修改 application.properties , 修改完成后再进行打包使用
