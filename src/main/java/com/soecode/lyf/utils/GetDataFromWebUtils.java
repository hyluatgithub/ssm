package com.soecode.lyf.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 网络抓取数据 工具类
 * Created by 鲁大师 on 2016/12/18.
 */
public class GetDataFromWebUtils {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 创建连接
     *
     * @param url
     */
    public Connection connectUrl(String url) {
        //TODO 改进连接方法为工厂方法
        Connection doc = Jsoup.connect(url);
        return doc;
    }

    /**
     * 获取页面Document
     *
     * @param url
     */
    public Document Document(String url) {
        Document doc = null;
        Connection connection = this.connectUrl(url);
        try {
            doc = connection.get();
        } catch (IOException e) {
            logger.error("获取页面Documentd发生异常：",e);
            e.printStackTrace();
        }finally {}
        return doc;

    }

    /**
     * 数据抽取方法
     * @param doc
     */
    public void getBookInfo(Document doc) {
        Element content = doc.body();
        Elements links = content.getElementsByClass("bang_index_list_body");
        //logger.info(bookList.outerHtml());
        int i = 0;
        for (Element link : links) {
            i++;
            logger.info(link.outerHtml());
            Elements elements = link.getElementsByClass("number");
            //logger.info(elements.outerHtml());
            //String imgs = link.select(".bang_index_list_pic").select("[src]");
            //logger.info(imgs.);
            //String linkHref = link.attr("href");
            //String linkText = link.text();
        }
        logger.info("========"+i);
    }

    public static void main(String[] args) {
        String urls = "http://bang.dangdang.com/books/";
        GetDataFromWebUtils dataFromWebUtils = new GetDataFromWebUtils();
        Document document = dataFromWebUtils.Document(urls);
        dataFromWebUtils.getBookInfo(document);
    }

}
