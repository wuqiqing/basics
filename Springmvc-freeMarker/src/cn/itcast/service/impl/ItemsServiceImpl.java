package cn.itcast.service.impl;

import cn.itcast.dao.ItemsMapper;
import cn.itcast.domain.Items;
import cn.itcast.service.ItemsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemsServiceImpl implements ItemsService {

    @Resource
    private ItemsMapper itemsMapper;

    public List<Items> selectAllItems() {
        // TODO Auto-generated method stub
        return itemsMapper.selectAllItems();
    }

    public Items selectItemsByID(Integer id) {
        // TODO Auto-generated method stub
        return itemsMapper.selectByPrimaryKey(id);
    }

    public void updateItems(Items item) {
        itemsMapper.updateByPrimaryKey(item);


    }

}
