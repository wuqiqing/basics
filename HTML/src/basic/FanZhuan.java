package basic;

/**
 * Created by 常晓虎  on 2015/11/20.
 * 描述：stringbuffer 反转
 */
public class FanZhuan {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("常大爷");
        StringBuffer reverse = stringBuffer.reverse();
        System.out.println(reverse.toString());
    }
}
