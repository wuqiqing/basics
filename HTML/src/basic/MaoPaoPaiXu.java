package basic;

/**
 * Created by Administrator on 2015/11/19.
 * 描述：冒泡排序
 */
public class MaoPaoPaiXu {
    public static void main(String[] args) {
        int[] num = {1, 32, 321, 43, 31, 321321};
        //打印排序前的数组
        for (int i = 0; i < num.length; i++) {
            System.out.println(num[i] + "\t");
        }

        //调用冒泡排序方法
        sort(num);
        //打印排序后的数组
        System.out.println("==================================");
        for (int i = 0; i < num.length; i++) {
            System.out.println(num[i] + "\t");
        }


    }

    public static void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

}
