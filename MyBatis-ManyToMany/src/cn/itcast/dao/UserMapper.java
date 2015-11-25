package cn.itcast.dao;

import cn.itcast.domain.User;

import java.util.List;

public interface UserMapper {

    public List<User> findUserAndItems();

    public void insertUser(User user);

}
