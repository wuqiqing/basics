package cn.itcast.lucene.demo;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.File;

/**
 * 查询索引数据
 *
 * @author
 */
public class IndexSearcherDemo {

    @Test
    public void testSearch() throws Exception {

        // 1、构造一个读取索引的lucene提供的工具IndexSearcher
        IndexReader reader = DirectoryReader.open(FSDirectory.open(new File("C:/lucenedata/indexdata")));
        // 1.1提供一个目录读取器给IndexSearcher
        IndexSearcher indexSearcher = new IndexSearcher(reader);

        // 2、构造一个查询条件

        // 2.1先构造一个term（就是域和关键词的组合）
//		Term term = new Term("content", "apache");
        Term term = new Term("content", "angelababy");

        // 2.2根据term构造一个查询条件对象
        Query query = new TermQuery(term);

        // 3、用IndexReader根据查询条件去索引库中查询数据

        // 3.1 先根据搜索条件获取到符合条件的"文档的id及文档的相关度得分的数组"以及"命中的文档总数"
        TopDocs topDocs = indexSearcher.search(query, 10);
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
