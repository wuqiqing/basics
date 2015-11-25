package cn.itcast.storemanager.domain;

import org.apache.struts2.json.annotations.JSON;

import java.util.HashSet;
import java.util.Set;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */

public class Goods implements java.io.Serializable {

    // Fields

    private String id;
    private Store store;
    private String name;
    private String nm;
    private String unit;
    private Double amount;
    private Set histories = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Goods() {
    }

    /**
     * full constructor
     */
    public Goods(Store store, String name, String nm, String unit,
                 Double amount, Set histories) {
        this.store = store;
        this.name = name;
        this.nm = nm;
        this.unit = unit;
        this.amount = amount;
        this.histories = histories;
    }


    //增加方法用来转换json用的,json插件会自动将getter方法的名字转换为json的key
    //label是下拉显示的
    public String getLabel() {
        return store.getName() + "的" + name;//会被转换为json的值
    }

    //value是选中后填充到input中的值
    public String getValue() {
        return name;
    }


    // Property accessors

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Store getStore() {
        return this.store;
    }

    public void setStore(Store store) {
        this.store = store;
    }


    //	@JSON(name="label")//更改插件转换成json的key的值，缺点：name这个key就没了。
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNm() {
        return this.nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @JSON(serialize = false)//排除该关联属性转换json
    public Set getHistories() {
        return this.histories;
    }

    public void setHistories(Set histories) {
        this.histories = histories;
    }

}