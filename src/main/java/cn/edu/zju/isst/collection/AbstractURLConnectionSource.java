package cn.edu.zju.isst.collection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

abstract class AbstractURLConnectionSource<T extends CollectionEntity> implements CollectionSource<T> {
    protected URLConnection connect(String url) throws IOException {
        URLConnection uc = new URL(url).openConnection();
        uc.setRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.3) Gecko/20100401 Firefox/3.6.3"); 
        return uc;
    }
    
    protected BufferedReader read(String url, String charset) throws IOException {
        return new BufferedReader(new InputStreamReader(connect(url).getInputStream(), charset));
    }
}