package com.lyw.spark.crawl;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class CrawlDemo {

    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://wwww.baidu.com");
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        } catch (Exception e) {

        }finally {

        }
    }
}
