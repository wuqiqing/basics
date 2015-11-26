package cn.itcast.lucene.analyzer;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class AnalyzerTest {

    public static void main(String[] args) throws Exception {


        //1、测试标准分词器
//		StandardAnalyzer analyzer = new StandardAnalyzer();

        //中日韩分词器
//		CJKAnalyzer analyzer = new CJKAnalyzer();

//		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();

        //IK分词器
        IKAnalyzer analyzer = new IKAnalyzer();

        //2、用分词器对给定的字符串进行分词
//		TokenStream tokenStream = analyzer.tokenStream("title","传智播客是一家伟大的培训机构，她能把小屌丝变成高富帅，迎娶白富美，登上人生巅峰");
        TokenStream tokenStream = analyzer.tokenStream("title", "无敌小涛");

        //3、设置一个“指针”来对分词流进行遍历，没遍历到一个词，就会放入“指针的容器中”
        CharTermAttribute addAttribute = tokenStream.addAttribute(CharTermAttribute.class);

        //4、先将流的指针复位
        tokenStream.reset();

        //5、逐个遍历流中的关键词
        while (tokenStream.incrementToken()) {

            System.out.println(addAttribute);

        }


        tokenStream.close();

    }


}
