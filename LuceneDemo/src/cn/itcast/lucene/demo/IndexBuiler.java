package cn.itcast.lucene.demo;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * 用来从原始文件中抽取信息建立索引库
 *
 * @author
 */
public class IndexBuiler {

    private IndexWriter indexWriter = null;

    /**
     * 从原始数据中抽取信息建立索引
     *
     * @throws IOException
     */
    @Test
    public void testBuildIndex() throws IOException {


        /**
         * 1、从原始数据中抽取信息
         */

        // 1.1获取原始文件
        File sourceDir = new File("C:/lucenedata/searchsource");

        File[] sourceFiles = sourceDir.listFiles();

        String title;
        String content;
        String path;
        long size;

        for (File file : sourceFiles) {

            // 抽取到文件名
            title = file.getName();
            // 抽取到文件内容
            content = FileUtils.readFileToString(file);
            // 抽取文件路径
            path = file.getPath();
            // 抽取文件大小
            size = FileUtils.sizeOf(file);

            /**
             * 2、将信息封装成lucene标准域
             */

            // 将标题信息封装成StringField域对象 1、自定义的搜索域名称 2、域中封装的信息 3、是否需要存储到文档库
            StringField titleField = new StringField("title", title, Store.YES);

            // 将文件内容信息封装成TextField域对象
            TextField contentField = new TextField("content", content, Store.NO);

            // 将文件路径信息封装成StringField域对象
            StringField pathField = new StringField("path", path, Store.YES);

            // 将文件大小信息封装成LongField域对象
            LongField sizeField = new LongField("size", size, Store.YES);

            /**
             * 3、将封装好的域创建成统一的document对象
             */

            // 新建一个空的文档对象
            Document doc = new Document();

            // 将前面封装好的域对象添加到文档对象中
            doc.add(titleField);
            doc.add(contentField);
            doc.add(pathField);
            doc.add(sizeField);

            /**
             * 4、将document交给lucene去建立索引库和文档信息库
             */

            // 可以将索引数据放到内存中，速度快，但是内存空间有限，而且容易丢失
            // Directory directory = new RAMDirectory();
            FSDirectory directory = FSDirectory.open(new File("C:/lucenedata/indexdata"));

            Version version = Version.LATEST;
            Analyzer analyzer = new StandardAnalyzer();
            // 构造一个配置参数对象 1、指定支持的lucene库版本 2、指定建立索引时所用的分词器
            IndexWriterConfig conf = new IndexWriterConfig(version, analyzer);

            // 1、我们要建立的索引库所存放的磁盘(内存)路径 2、lucene建立索引时需要的一些配置参数
            indexWriter = new IndexWriter(directory, conf);

            // 2、将之前构造好的document交给indexwriter去建立索引数据
            // addDocument方法做了两件事：a、从doc的各个域中分析出关键词，写入索引库 b、将doc中需要存储的域添加到文档信息库
            indexWriter.addDocument(doc);

            // 3、提交执行
            indexWriter.commit();

            // 4、 关闭indexWriter资源
            indexWriter.close();

        }

    }

}
