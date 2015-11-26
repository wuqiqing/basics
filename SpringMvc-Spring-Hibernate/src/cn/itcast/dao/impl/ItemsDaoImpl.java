package cn.itcast.dao.impl;

import cn.itcast.dao.ItemsDao;
import cn.itcast.domain.Items;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ItemsDaoImpl extends HibernateDaoSupport implements ItemsDao {

    private SessionFactory sessionFactory;

    @Resource
    public void setMySessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public List<Items> selectAllItems() {

        String sql = "select * from items";

        List<Items> list = this.getSession().createSQLQuery(sql).addEntity(Items.class).list();
        return list;
    }

    public Items selectByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    public void updateByPrimaryKey(Items item) {
        // TODO Auto-generated method stub

    }


}
