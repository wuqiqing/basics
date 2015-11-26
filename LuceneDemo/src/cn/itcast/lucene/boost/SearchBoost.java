package cn.itcast.lucene.boost;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.junit.Before;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class SearchBoost {

    public IndexSearcher indexSearcher = null;


    @Before
    public void init() throws IOException {

        indexSearcher = new IndexSearcher(DirectoryReader.open(FSDirectory.open(new File("C:\\lucenedata\\indexdata"))));

    }

    @Test
    public void testSearchBoost() throws Exception {

        String[] fields = {"title", "content", "author"};


        //可以构造一个map用来设置不同域的不同权重
        HashMap<String, Float> boosts = new HashMap<String, Float>();
        boosts.put("title", 30f);
        boosts.put("content", 10f);
        boosts.put("author", 5f);


        MultiFieldQueryParser queryParser = new MultiFieldQueryParser(fields, new IKAnalyzer(), boosts);
        Query query = queryParser.parse("redis");


        TopDocs topDocs = indexSearcher.search(query, 1);
        // 3.2 拿到"文档的id及文档的相关度得分"数组
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        // 3.3拿到"命中的文档总数"
        int totalHits = topDocs.totalHits;

        System.out.println("搜索到的文档总数为： " + totalHits);

        // 3.4 遍历scoreDocs数组，然后根据每个文档id去文档库中获取文档信息

        for (ScoreDoc scoreDoc : scoreDocs) {

            // 3.5 从scoreDoc中拿到文档id
            int docid = scoreDoc.doc;

            // 3.6根据id去文档库获取文档信息
            Document doc = indexSearcher.doc(docid);

            // 3.7将文档中的信息打印出来
            System.out.println(scoreDoc.score);
            System.out.println(doc.get("title"));
            System.out.println(doc.get("content"));
            System.out.println(doc.get("path"));
            System.out.println(doc.get("size"));

        }


    }


}
