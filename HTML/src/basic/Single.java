package basic;

/**
 * Created by 常晓虎  on 2015/11/20.
 * 描述：单例设计模式
 * 1.私有化构造函数
 * 2.创建私有对象
 * 3.通过get方法获取
 */

/**
 * 饿汉式
 */
public class Single {
    private static Single single = new Single();

    private Single() {
    }

    public static Single getSingle() {
        return single;
    }
}

class SingleDemo {
    public static void main(String[] args) {
        Single single1 = Single.getSingle();
        Single single2 = Single.getSingle();
        Single single3 = Single.getSingle();
        System.out.println(single1);
        System.out.println(single2);
        System.out.println(single3);
    }
}


/**
 * 懒汉式
 */
class Single2 {
    private static Single2 single2 = null;

    private Single2() {
    }

    public static Single2 getSingle2() {
        if (single2 == null) {
            single2 = new Single2();
        }
        return single2;
    }
}

class Single2Demo {
    public static void main(String[] args) {
        Single2 single1 = Single2.getSingle2();
        Single2 single2 = Single2.getSingle2();
        Single2 single3 = Single2.getSingle2();
        System.out.println(single1);
        System.out.println(single2);
        System.out.println(single3);

    }
}

