package cn.edu.zju.isst.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CollecttedNewsSource {
    private static String url = "http://www.cst.zju.edu.cn/";
    private static String newsTypeRegex = "<h3>(.*?)</h3>";
    private static String newsTitleRegex = "<a href=\"(.*?id=([0-9]+?))\" target=\"_blank\" title=\"(.*?)\">";
    private static String newsEndRegex = "</ul>";
    private static String newsDateRegex = "<span class=\"fr\">(.+?)</span>";
    
    private static Map<String, List<Map<String, String>>> getCollectingNewsList(String[] allowedTypes) {
      //private static void getCollectingNewsList() {
        Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String, String>>>();
        List<String> allowedTypesList = Arrays.asList(allowedTypes);
        try {
            URLConnection uc = new URL(url).openConnection();
            uc.setRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.3) Gecko/20100401 Firefox/3.6.3");  
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
            String tmpStr = null;
            Pattern newsTypePattern = Pattern.compile(newsTypeRegex);
            Pattern newsTitlePattern = Pattern.compile(newsTitleRegex);
            Pattern newsEndPattern = Pattern.compile(newsEndRegex);
            Pattern newsDatePattern = Pattern.compile(newsDateRegex);
            
            List<Map<String, String>> matched = null;
            Map<String, String> prevNews = null;
            while (null != (tmpStr = in.readLine())) {
                if (null != matched) {
                    if (prevNews != null) {
                        Matcher dateMatcher = newsDatePattern.matcher(tmpStr);
                        if (dateMatcher.find()) {
                            prevNews.put("date", dateMatcher.group(1));
                        }
                    }
                    
                    Matcher titleMatcher = newsTitlePattern.matcher(tmpStr);
                    if (titleMatcher.find()) {
                        Map<String, String> oneNews = new HashMap<String, String>();
                        oneNews.put("url", titleMatcher.group(1));
                        oneNews.put("id", titleMatcher.group(2));
                        oneNews.put("title", titleMatcher.group(3));
                        matched.add(oneNews);
                        prevNews = oneNews;
                    } else {
                        Matcher endMatcher = newsEndPattern.matcher(tmpStr);
                        if (endMatcher.find()) {
                            matched = null;
                        }
                    }
                } else {
                    Matcher matcher = newsTypePattern.matcher(tmpStr);
                    if (matcher.find()) {
                        String type = matcher.group(1);
                        if (allowedTypesList.contains(type)) {
                            matched = new ArrayList<Map<String, String>>();
                            map.put(type, matched);
                            if (type.equals("新闻中心")) {
                                for (int i=0; i<10; i++) {
                                    in.readLine();
                                }
                            }
                        }
                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
    
    public static void main(String[] args) {
        Map<String, List<Map<String, String>>> map = getCollectingNewsList(new String[]{"重要通知", "学生思政", "新闻中心"});
        System.out.println(map);
    }
}