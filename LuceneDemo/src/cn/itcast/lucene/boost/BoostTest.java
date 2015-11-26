package cn.itcast.lucene.boost;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Before;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class BoostTest {

    private IndexWriter indexWriter = null;
    private Directory directory = null;
    private IndexWriterConfig conf = null;
    private Analyzer analyzer = null;


    @Before
    public void init() throws Exception {

        directory = FSDirectory.open(new File("C:/lucenedata/indexdata"));
        analyzer = new IKAnalyzer();

        conf = new IndexWriterConfig(Version.LATEST, analyzer);

        indexWriter = new IndexWriter(directory, conf);

    }


    /**
     * 想索引库中增添文档
     *
     * @throws IOException
     */
    @Test
    public void addDocumentToIndexData() throws IOException {

        StringField title = new StringField("title", "电商网站优化策略", Store.YES);

        TextField content = new TextField("content", "redis是一种非常有用的优化工具，号称瑞士军刀", Store.YES);
        //可以针对一篇文档的指定域提升权重
        content.setBoost(20);


        TextField author = new TextField("author", "无敌小涛", Store.YES);


        Document doc = new Document();

        doc.add(title);
        doc.add(content);
        doc.add(author);

        indexWriter.addDocument(doc);

        indexWriter.commit();

        indexWriter.close();

    }


}
