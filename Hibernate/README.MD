#快照更新
    通过sqlsessionfactory.opensession 获取到session 通过session查询数据库查询到对象，此时是持久态的，如果session。close这个
    对象变成脱管态  直接new出来的对象是瞬时态  快照更新是查询出来的持久态对象 然后对象.set属性值 直接修改然后再保存进数据库 即快照更新
#一级缓存
    一级缓存的生命周期是跟session 的生命周期一样的 session close 一级缓存消失
    一级缓存除了可以flush（刷出）之外，还可以清除（clear，evict）、重载（reflesh）。
#结论：删除操作中，删除托管对象没有级联效果，删除持久对象可以进行级联删除 cascade all（在一方 配置 cascade=”delete” ,会级联删除）
#hibernate四种检索方式
 OID检索方式：session.get(entity.class,id),session.load(entity.class,id)
 HQL检索方式：session.createQuery(hql).list()
 SQL检索方式(Native Query)：session.createSQLQuery(sql).list()
 QBC检索方式：(Query by Criteria):session.createCriteria(Entity.class).list()
    