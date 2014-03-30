package cn.edu.zju.isst.collection;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSTCollectionSource extends AbstractURLConnectionSource<CollectionEntity> {
    private static final String url = "http://www.cst.zju.edu.cn/";
    private static final String charset = "utf-8";
    private static final String typeRegex = "<h3>(.*?)</h3>";
    private static final String titleRegex = "<a href=\"(.*?id=([0-9]+?))\" target=\"_blank\" title=\"(.*?)\">";
    private static final String dateRegex = "<div class=\"detailed_ly\">.+([0-9]{4}-[0-9]{2}-[0-9]{2}).+</div>";
    private static final String contentStartRegex = "<div class=\"vid_wz\">(.+)?";
    private static final String contentEndRegex = "^</div>$";
    
    
    private String[] allowedTypes;
    private Pattern typePattern;
    private Pattern titlePattern;
    private Pattern datePattern;
    private Pattern contentStartPattern;
    private Pattern contentEndPattern;
    
    public CSTCollectionSource(String[] allowedTypes) {
        this.allowedTypes = allowedTypes;
        typePattern = Pattern.compile(typeRegex);
        titlePattern = Pattern.compile(titleRegex);
        datePattern = Pattern.compile(dateRegex);
        contentStartPattern = Pattern.compile(contentStartRegex);
        contentEndPattern = Pattern.compile(contentEndRegex);
    }
    
    public List<CollectionEntity> collectList() {
        List<CollectionEntity> list = new ArrayList<CollectionEntity>();
        List<String> allowedTypesList = Arrays.asList(allowedTypes);
        try {
            BufferedReader in = read(url, charset);
            String line = null;
            Matcher matcher = null;
            String type = null;
            while (null != (line = in.readLine())) {
                matcher = typePattern.matcher(line);
                if (matcher.find()) {
                    type = matcher.group(1);
                    if (type.equals("新闻中心")) {
                        for (int i=0; i<10; i++) {
                            in.readLine();
                        }
                    }
                } else if (null != type && allowedTypesList.contains(type)) {
                    matcher = titlePattern.matcher(line);
                    if (matcher.find()) {
                        CollectionEntity entity = new CollectionEntity();
                        entity.setId(Integer.parseInt(matcher.group(2)));
                        entity.setTitle(matcher.group(3).trim());
                        entity.setUrl(url + matcher.group(1));
                        list.add(entity);
                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void parseDetail(CollectionEntity entity) {
        try {
            BufferedReader in = read(entity.getUrl(), charset);
            String line = null;
            Matcher matcher = null;
            StringBuilder content = new StringBuilder("");
            boolean inContentArea = false;
            
            while (null != (line = in.readLine())) {
                if (inContentArea) {
                    matcher = contentEndPattern.matcher(line);
                    if (matcher.find()) {
                        break;
                    } else {
                        content.append(line).append("\n");
                    }
                } else {
                    matcher = datePattern.matcher(line);
                    if (matcher.find()) {
                        Date postTime;
                        try {
                            postTime = new SimpleDateFormat("yyyy-MM-dd").parse(matcher.group(1));
                        } catch (ParseException e) {
                            postTime = new Date();
                        }
                        entity.setPostTime(postTime);
                    } else {
                        matcher = contentStartPattern.matcher(line);
                        if (matcher.find()) {
                            inContentArea = true;
                            content.append(matcher.group(1)).append("\n");
                        }
                    }
                }
            }
            
            entity.setContent(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<CollectionEntity> collect() {
        List<CollectionEntity> list = collectList();
        
        for (CollectionEntity entity : list) {
            parseDetail(entity);
        }
        
        return list;
    }
    
    public static void main(String[] args) {
        /*CSTCollectionSource source = new CSTCollectionSource(new String[] {});
        CollectionEntity entity = new CollectionEntity();
        entity.setUrl("http://www.cst.zju.edu.cn/index.php?c=Index&a=detail&catid=126&id=1632");
        source.parseDetail(entity);
        System.out.println(entity);
        */
    }
}