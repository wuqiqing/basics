package cn.itcast.storemanager.service;

import cn.itcast.storemanager.dao.GenericDAO;
import cn.itcast.storemanager.domain.Store;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

//仓库的业务实现
public class StoreServiceImpl extends BaseService implements StoreService {
    //注入dao
    private GenericDAO<Store, String> storeDAO;

    public void setStoreDAO(GenericDAO<Store, String> storeDAO) {
        this.storeDAO = storeDAO;
    }

    @Override
    @CacheEvict(value = "storemanager", allEntries = true)//清除缓存
    public void saveStore(Store store) {
        //调用dao
        storeDAO.save(store);
    }

    @Override
    @Cacheable("storemanager")//缓存生效，使用自定义缓存策略
    public List<Store> findAllStoreList() {
        return storeDAO.findAll(Store.class);
    }

    @Override
    @CacheEvict(value = "storemanager", allEntries = true)//清除缓存
    public void deleteStoreById(Store store) {
        storeDAO.delete(store);
    }

    @Override

    public Store findStoreById(String id) {
        return storeDAO.findById(Store.class, id);
    }

    @Override
    @CacheEvict(value = "storemanager", allEntries = true)//清除缓存
    public void updateStore(Store store) {
        storeDAO.update(store);
    }


}
