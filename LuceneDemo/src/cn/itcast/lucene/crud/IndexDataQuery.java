package cn.itcast.lucene.crud;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.junit.Before;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

/**
 * lucene支持的各种查询方式
 *
 * @author
 */
public class IndexDataQuery {

    public IndexSearcher indexSearcher = null;


    @Before
    public void init() throws IOException {

        indexSearcher = new IndexSearcher(DirectoryReader.open(FSDirectory.open(new File("C:\\lucenedata\\indexdata"))));

    }


    /**
     * 测试各种查询条件
     *
     * @throws IOException
     * @throws Exception
     */
    @Test
    public void testAdvancedQuery() throws Exception {

        Term term = new Term("content", "spring");

        //1、最基本的查询条件
        TermQuery query1 = new TermQuery(term);

        //2、数字范围查询条件  参数1、搜索域  参数2、范围的最小值   参数3、范围的最大值  参数4：是否包含最小值   参数5：是否包含最大值
        NumericRangeQuery<Long> query2 = NumericRangeQuery.newLongRange("size", 100L, 5000L, true, false);

        //3、布尔查询——实质是多条件的组合查询

        BooleanQuery booleanQuery = new BooleanQuery();

        booleanQuery.add(query1, Occur.MUST);
        booleanQuery.add(query2, Occur.MUST_NOT);

        //4、matchall查询
        MatchAllDocsQuery query3 = new MatchAllDocsQuery();


        /**
         * 以上的各种查询条件其实都是lucene底层查询语法的封装
         */
/*		System.out.println(query1);
        System.out.println(query2);
		System.out.println(booleanQuery);
		System.out.println(query3);*/
        /**

         content:spring
         size:[100 TO 5000}
         +content:spring -size:[100 TO 5000}
         *:*

         如果我们要直接使用查询语法来搜索，就需要用到queryparser来对语法进行解析得到各种查询条件query

         */


//--------------------------------------------------------------------------		


        //使用查询解析器来解析语法生成查询条件
        //lucene支持的常用查询与法
        /**
         *
         * 		 content:spring
         size:[100 TO 5000}
         +content:spring -size:[100 TO 5000}
         *:*
         */


        //1、构造一个查询条件解析器   参数1：默认搜索域     参数2：解析查询语句时所用的分词器
        QueryParser queryParser = new QueryParser("title", new IKAnalyzer());
        //2、传递一个用lucene查询语法表示的字符串给parser去解析
        Query queryYufa1 = queryParser.parse("content:spring.txt");
        System.out.println(queryYufa1);
        //title:spring.txt title:spring title:txt

        Query queryYufa2 = queryParser.parse("size:[2000 TO 4000}");
        System.out.println(queryYufa2);


        /**
         * 多个域的查询解析器
         */

        String[] fields = {"title", "content", "author"};
        //参数1： 默认搜索域（多个）   参数2：用来解析查询短语的分词器
        MultiFieldQueryParser queryParser2 = new MultiFieldQueryParser(fields, new IKAnalyzer());

        //可以给一个精确的查询语法来解析成查询条件对象query
        Query query4 = queryParser2.parse("title:spring +content:springmvc");

        //也可以只给一个查询短语，让它自己去对默认搜索域进行解析
        Query query5 = queryParser2.parse("小屌丝");

        System.out.println("query4---- " + query4);
        System.out.println("query5---- " + query5);
        /**
         query4---- title:spring +content:springmvc
         query5---- (title:小 title:屌 title:丝) (content:小 content:屌 content:丝) (author:小 author:屌 author:丝)

         */


        TopDocs topDocs = indexSearcher.search(queryYufa2, 10);

        int totalHits = topDocs.totalHits;
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        System.out.println("总命中数： " + totalHits);

        for (ScoreDoc scoreDoc : scoreDocs) {

            Document doc = indexSearcher.doc(scoreDoc.doc);

            System.out.println(doc.get("title"));
            System.out.println(doc.get("content"));
            System.out.println(doc.get("path"));
            System.out.println(doc.get("size"));


        }


    }


}
