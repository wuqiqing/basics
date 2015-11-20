package basic;

/**
 * Created by 常晓虎  on 2015/11/20.
 * 描述：多线程
 */
public class DuoXianCheng extends Thread {
    @Override
    public void run() {
//        super.run();
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
    }


//    public static void main(String[] args) {
//        DuoXianCheng duoXianCheng = new DuoXianCheng();
//        duoXianCheng.start();
//    }
}


class Duo2 implements Runnable {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i + "==========");
        }
        Duo2 duo2 = new Duo2();
        new Thread(duo2).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
    }
}
