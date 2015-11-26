package cn.itcast.dao;

import cn.itcast.domain.Items;

import java.util.List;

public interface ItemsDao {

    List<Items> selectAllItems();

    Items selectByPrimaryKey(Integer id);

    void updateByPrimaryKey(Items item);

}
