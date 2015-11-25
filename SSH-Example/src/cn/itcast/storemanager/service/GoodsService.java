package cn.itcast.storemanager.service;

import cn.itcast.storemanager.domain.Goods;
import cn.itcast.storemanager.page.Pagination;

public interface GoodsService {

    /**
     * 根据简记码查询出一个货物
     *
     * @param nm
     * @return
     */
    public Goods findByNm(String nm);

    /**
     * 根据名称模糊查询
     *
     * @param name
     * @return
     */
    public Object findByNameLike(String name);

    /**
     * 入库
     *
     * @param goods
     */
    public void saveGoods(Goods goods);

    /**
     * 分页查询
     *
     * @param pagination
     */
    public void findPageData(Pagination<Goods> pagination);


}
