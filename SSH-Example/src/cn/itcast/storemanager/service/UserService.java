package cn.itcast.storemanager.service;

import cn.itcast.storemanager.domain.Userinfo;

public interface UserService {

    /**
     * 登录
     *
     * @param user
     * @return
     */
    public Userinfo login(Userinfo user);

}
