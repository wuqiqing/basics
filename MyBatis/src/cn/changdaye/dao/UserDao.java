package cn.changdaye.dao;


import cn.changdaye.domain.User;

import java.util.List;

public interface UserDao {

    public List<User> findAllUser();

    public User findUserByID(Integer id);

    public void insertUser(User user);

    public void updateUserByID(User user);

}
