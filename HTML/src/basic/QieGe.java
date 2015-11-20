package basic;

/**
 * Created by 常晓虎  on 2015/11/20.
 * 描述：使用任意多个字符来切割字符串
 */
public class QieGe {
    public static void main(String[] args) {
        String s = "长大爷爷耶耶耶大的委屈打也也底袜打擦是大爷爷";
        String[] split = s.split("(.)\\1+");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }
    }
}
