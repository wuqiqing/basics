package cn.itcast.storemanager.domain;

/**
 * History entity. @author MyEclipse Persistence Tools
 */

public class History implements java.io.Serializable {

    // Fields

    private String id;
    private Goods goods;
    private String datetime;
    private String type;
    private Double amount;
    private Double remain;
    private String user;

    // Constructors

    /**
     * default constructor
     */
    public History() {
    }

    /**
     * full constructor
     */
    public History(Goods goods, String datetime, String type, Double amount,
                   Double remain, String user) {
        this.goods = goods;
        this.datetime = datetime;
        this.type = type;
        this.amount = amount;
        this.remain = remain;
        this.user = user;
    }

    // Property accessors

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Goods getGoods() {
        return this.goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getDatetime() {
        return this.datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getRemain() {
        return this.remain;
    }

    public void setRemain(Double remain) {
        this.remain = remain;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}