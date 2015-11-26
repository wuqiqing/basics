#集群部署
    zookeeper : Zookeeper 是 Google 的 Chubby的开源实现，是 Hadoop 的分布式协调服务  用于solr云的集群服务的
    keepalive : 两天机器 主机器 从机器之间建立心跳连接 如果1台机器挂了 keepalive自动切换到第二台机器上
    Nginx ： 反向代理服务器 做负载均衡用的
    lvs ： 带虚拟ip的 nginx  两台nginx 不够用的时候用