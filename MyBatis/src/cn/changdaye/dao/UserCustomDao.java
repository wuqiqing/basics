package cn.changdaye.dao;

import cn.changdaye.domain.QueryUserVo;
import cn.changdaye.domain.User;

import java.util.List;
import java.util.Map;


public interface UserCustomDao {

    public int findUserBySexAndUsernameCount(QueryUserVo vo);

    public List<User> findUserBySexAndUsername(QueryUserVo vo);

    public List<User> findUserByMap(Map<String, Object> map);

    public List<User> findUserResultMap();

    public List<User> findUserByOr(QueryUserVo vo);

    public List<User> findUserByIn(QueryUserVo vo);


}
