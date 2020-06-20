package online.daliang.kafka.producer.utils;

import online.daliang.kafka.producer.model.WebSite;

import java.util.Random;

public class WebSiteLog {

    private static String[] siteNames = new String[]{"百度", "163", "114", "126", "谷歌"};
    private static String[] siteUrls = new String[]{"wwww.baidu.com", "www.163.com", "www.114.com",
            "www.126.com", "www.google.com"};

    public static WebSite generateLog(String siteid, String dn) {
        WebSite baseWebsite = new WebSite();
        Random rand = new Random();
        baseWebsite.setCreatetime("2000-01-01");
        baseWebsite.setDelete("0");
        baseWebsite.setSiteid(siteid);
        baseWebsite.setCreator("admin");
        baseWebsite.setDn(dn);
        int index = Integer.parseInt(siteid);
        baseWebsite.setSitename(siteNames[index]);
        baseWebsite.setSiteurl(siteUrls[index] + "/" + dn);
        return baseWebsite;
    }
}
