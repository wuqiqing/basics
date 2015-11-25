package cn.itcast.storemanager.service;

import cn.itcast.storemanager.dao.GenericDAO;
import cn.itcast.storemanager.domain.Userinfo;

import java.util.List;

//用户的业务层
//继承父类：为了代码复用
//实现接口：为了满足业务规则
public class UserServiceImpl extends BaseService implements UserService {
    //注入dao
    private GenericDAO<Userinfo, String> userDAO;

    public void setUserDAO(GenericDAO<Userinfo, String> userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Userinfo login(Userinfo user) {
        //调用dao层
        List<Userinfo> list = userDAO.findByNamedQuery("Userinfo.findUserForLogin", user.getName(), user.getPassword());
        return list.isEmpty() ? null : list.get(0);
    }

}
