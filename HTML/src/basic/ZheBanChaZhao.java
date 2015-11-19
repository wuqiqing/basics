package basic;

/**
 * Created by 常晓虎  on 2015/11/19.
 * 描述：二分查找 折半查找 数组必须是有序的
 */
public class ZheBanChaZhao {

    public static void main(String[] args) {
        int[] num = {1, 2, 3, 4, 5};
        int key = 2;
        int sear = search(key, num);
        System.out.println(sear);
    }

    public static int search(int key, int[] num) {

        int start = 0;
        int end = num.length - 1;
        int mid = (start + end) / 2;
        while (key != num[mid]) {
            if (start > end) {
                return -1;
            }
            if (key > num[mid]) {
                start = mid + 1;
                mid = (start + end) / 2;

            } else if (key < num[mid]) {
                end = mid - 1;
                mid = (start + end) / 2;
            }

        }
        return mid;
    }
}
