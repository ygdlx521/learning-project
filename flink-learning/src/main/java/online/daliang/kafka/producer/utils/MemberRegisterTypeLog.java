package online.daliang.kafka.producer.utils;

import online.daliang.kafka.producer.model.MemberRegisterType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class MemberRegisterTypeLog {

    private static String[] webAappregUrl = new String[]{
            "http:www.webA.com/product/register/index.html", "http:www.webA.com/sale/register/index.html",
            "http:www.webA.com/product10/register/aa/index.html",
            "http:www.webA.com/hhh/wwww/index.html"};
    private static String[] webBappregUrl = new String[]{
            "http:www.webB.com/product/register/index.html", "http:www.webB.com/sale/register/index.html",
            "http:www.webB.com/hhh/wwww/index.html"};
    private static String[] webcappregUrl = new String[]{
            "http:www.webC.com/product/register/index.html", "http:www.webB.com/sale/register/index.html",
            "http:www.webC.com/product52/register/ac/index.html"};


    public static MemberRegisterType generateLog(String uid, String dn) {
        MemberRegisterType memberRegType = new MemberRegisterType();
        memberRegType.setAppkey("-");
        Random random = new Random();
        String url = "";
        int index = random.nextInt(4);
        switch (dn) {
            case "webA":
                url = webAappregUrl[index];
                break;
            case "webB":
                url = webBappregUrl[index];
                break;
            case "webC":
                url = webcappregUrl[index];
                break;
        }
        memberRegType.setAppregurl(url);
        memberRegType.setBdp_uuid("-");
//            String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//                    .format(RondomDate.randomDate("2019-06-30 10:00:00", "2019-06-30 11:00:00"));
        memberRegType.setCreatetime(String.valueOf(System.currentTimeMillis()));
        memberRegType.setDt(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now().minusDays(8)));
        memberRegType.setDn(dn);
        memberRegType.setDomain("-");
        memberRegType.setIsranreg("-");
        memberRegType.setDomain("-");
        memberRegType.setWebsiteid(String.valueOf(random.nextInt(5)));
        memberRegType.setRegsource(String.valueOf(random.nextInt(5)));
        memberRegType.setUid(uid);
        return memberRegType;
    }
}
