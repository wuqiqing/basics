package cn.itcast.service;

import cn.itcast.domain.Items;

import java.util.List;

public interface ItemsService {

    public List<Items> selectAllItems();

    public Items selectItemsByID(Integer id);

    public void updateItems(Items item);

}
