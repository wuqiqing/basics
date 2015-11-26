package test;

/**
 * Created by 常晓虎  on 2015/11/26.
 * 描述：
 */
public class Demo {
    public static void main(String[] args) {
        int i = 1;
        Boolean aBoolean = demo2(i);
        System.out.println(aBoolean);
    }

    public static Boolean demo2(int i) {
        return i == 1 ? true : false;
    }
}
