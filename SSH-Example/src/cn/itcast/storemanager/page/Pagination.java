package cn.itcast.storemanager.page;

import java.util.List;
import java.util.Map;
import java.util.Set;

//分页bean（pojo）
//主要用来存储页面传过来的参数和查询的结果：把分页bean当成一个盒子
public class Pagination<T> {
    //客户端会传过来哪些条件
    private int page = 1;//页码（用户点击的）
    private int pageSize = 3;//每页最大查询的记录数;(用户可以自定义每页查询的最大记录数，这里没这个功能)
    //	private Goods goods=model;
    private Map<String, String[]> parameterMap;//用map来封装页面传过来的所有业务参数（封装简单,要考虑类型，最好一致）


    //服务端可以返回哪些数据
    private List<T> pageDataList;//分页数据列表（从数据库查询出来，给页面循环显示用的）
    private long totalPage;//总页码数（给页面显示，计算工具条）
    private long totalCount;//总记录数（从数据库查询出来的，计算总页码数）
    //放置两个属性：起始页码和结束页码
    private long begin;//起始页码
    private long end;//结束页码

    //分页用的:计算
//	private int firstResult;//起始索引
//	private int maxResults;//最大查询出来的记录数
    public int getFirstResult() {
        return (page - 1) * pageSize;
    }

    public int getMaxResults() {//最大查询出来的记录数
        return pageSize;//直接返回最大查询的每页记录数
    }

    public long getBegin() {
        return begin;
    }

    public void setBegin(long begin) {
        this.begin = begin;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    //根据前台返回的参数，进行拼接参数字符串，给前台的分页url使用
    public String getParameterUrl() {
        //将参数拆开，拼接成&nm=aaa&name=...
        String parameterUrl = "";

        Set<String> keySet = parameterMap.keySet();
        for (String key : keySet) {
            String[] values = parameterMap.get(key);
            //去除page参数
            if (values != null && !key.equals("page")) {
                parameterUrl += "&" + key + "=" + values[0];
            }
        }
        return parameterUrl;
    }

    //getter和setter
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, String[]> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, String[]> parameterMap) {
        this.parameterMap = parameterMap;
    }

    public List<T> getPageDataList() {
        return pageDataList;
    }

    public void setPageDataList(List<T> pageDataList) {
        this.pageDataList = pageDataList;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        //顺便计算一下总页面数
        totalPage = (totalCount + pageSize - 1) / pageSize;

        //计算页面的页码中“显示”的起始页码和结束页码
        //一般显示的页码叫好的效果是最多显示10个页码
        //算法是前5后4，不足补10
        //计算显示的起始页码（根据当前页码计算）：当前页码-5
        begin = page - 5;
        if (begin < 1) {//页码修复
            begin = 1;
        }
        //计算显示的结束页码（根据开始页码计算）：开始页码+9
        end = begin + 9;
        if (end > totalPage) {//页码修复
            end = totalPage;
        }
        //起始页面重新计算（根据结束页码计算）：结束页码-9
        begin = end - 9;
        if (begin < 1) {
            begin = 1;
        }
        System.out.println(begin + "和" + end);
        this.totalCount = totalCount;
    }


}
