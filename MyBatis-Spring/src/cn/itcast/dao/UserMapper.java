package cn.itcast.dao;

import cn.itcast.domain.User;

public interface UserMapper {

    public User findUserByID(Integer id);

}
