#lucene 步骤
    提取索引域的信息 比如 标题  作者 价格等  
        需要建立索引的 比如标题 index = true 
        需要分词的     比如标题 分词
        需要存储到文档信息库的  比如商品描述信息
    然后建立索引库跟文档信息库
    然后根据配置就相应存储进去了  id是lucene负责维护的不需要自己管理
    
#lucene 跟solr 的对比是 solr封装了lucene 不需要自己创建索引库 文档信息库等 solr是独立的服务