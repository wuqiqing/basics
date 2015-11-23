#JDBC
    设置驱动，建立连接，操作数据库，释放资源
#事务的四大特性
    ACID  原子性： 要么都成功要么都失败
    atomic 一致性：保证数据完整性一致
    隔离性：多个事务之间是互不影响的
    isolation 持久性：事务一旦提交后，sql执行的数据将永久改变
#事务的隔离级别：
#数据库内部定义了四种隔离级别，用于解决三种隔离问题
1、read uncommited 最低事务级别，允许以上三类隔离问题的发生 
2、read commint 不会发生脏读，允许不可重复读和虚读发生
3、repeatable read 不会发生脏读、不可重复读，允许虚读发生(mysql 没有效果)
4、serializable 事务最高级别，所有相同数据事务操作，都会串行进行，不存在并发，不存在三类隔离问题 
    脏读 ： 一个事务读取另一个事务未提交的数据 
    不可重复读：在一个事务先后两次读取发生数据不一致情况，第二次读取到另一个事务已经提交数据 （强调数据更新 update）
    虚读 ： 在一个事务中，第二次读取发生数据记录数的不同 ，读取到另一个事务已经提交数据 （强调数据记录变化 insert ）A 第一次读取 存在5条记B 向 A 插入一条新的记录A 第二次读取 存在6条记录 
    
#BeanHandler 和 BeanListHandler 、ScalarHandler
     BeanHandler:  将数据库的某一条记录的字段值 对应的封装到 对应的实体类javabean 属性中   返回实体对象
     BeanListHandler  数据库的多条记录对应的数据  封装到 List 集合中  集合的元素类型 和 BeanListHandler<实体类型> 泛型的类型一致.
     ScalarHandler  单值查询  获取结果集的某一列的值   例如 select  count(*)  from user   