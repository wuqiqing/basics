package cn.itcast.lucene.crud;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Before;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class IndexDataCRUD {
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

        StringField title = new StringField("title", "传智是大家永远的家", Store.YES);

        TextField content = new TextField("content", "走到哪里也不要忘了妈", Store.YES);

        TextField author = new TextField("author", "无敌小涛", Store.YES);


        Document doc = new Document();

        doc.add(title);
        doc.add(content);
        doc.add(author);

        indexWriter.addDocument(doc);

        indexWriter.commit();

        indexWriter.close();

    }


    /**
     * 删除文档
     *
     * @throws Exception
     */
    @Test
    public void testDeleteDocument() throws Exception {


//		indexWriter.deleteDocuments(new Term("author","无敌"));

        //删除所有文档，清空索引库，慎用！！！
        indexWriter.deleteAll();

        indexWriter.commit();
        indexWriter.close();


    }


    /**
     * 更新文档
     *
     * @throws Exception
     */
    @Test
    public void testUpdateDocument() throws Exception {

        //先要构造一个新的文档对象
        StringField title = new StringField("title", "angelababy太无情", Store.YES);
        TextField content = new TextField("content", "你现在对我爱答不理，有一天我会让你高攀不起", Store.YES);
        TextField author = new TextField("author", "无敌小涛", Store.YES);
        Document doc = new Document();
        doc.add(title);
        doc.add(content);
        doc.add(author);

        //更新操作的实质是先删除符合条件的文档，在添加新的文档
        indexWriter.updateDocument(new Term("content", "apache"), doc);

        indexWriter.commit();
        indexWriter.close();

    }


}
