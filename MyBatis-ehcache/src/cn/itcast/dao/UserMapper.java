package cn.itcast.dao;

import cn.itcast.domain.User;

import java.util.List;

public interface UserMapper {

    public List<User> findUserLazyLoading(Integer userId);

    public User findUserByID(Integer id);

}
