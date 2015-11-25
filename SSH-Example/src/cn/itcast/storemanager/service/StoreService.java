package cn.itcast.storemanager.service;

import cn.itcast.storemanager.domain.Store;

import java.util.List;

public interface StoreService {

    /**
     * 保存仓库
     *
     * @param store
     */
    public void saveStore(Store store);

    /**
     * 查询所有仓库的列表
     */
    public List<Store> findAllStoreList();

    /**
     * 根据id来删除仓库
     *
     * @param store
     */
    public void deleteStoreById(Store store);

    /**
     * 根据id查询仓库
     *
     * @param id
     * @return
     */
    public Store findStoreById(String id);

    /**
     * 更新仓库
     *
     * @param model
     */
    public void updateStore(Store store);

}
