package online.daliang.kafka.utils;

import online.daliang.kafka.model.Member;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class MemberLog {

    private static String[] dns = new String[]{"webA", "webB", "webC"};
    private static String[] type = new String[]{"insert", "update"};
    private static int[][] range = {{607649792, 608174079},//36.56.0.0-36.63.255.255
            {1038614528, 1039007743},//61.232.0.0-61.237.255.255
            {1783627776, 1784676351},//106.80.0.0-106.95.255.255
            {2035023872, 2035154943},//121.76.0.0-121.77.255.255
            {2078801920, 2079064063},//123.232.0.0-123.235.255.255
            {-1950089216, -1948778497},//139.196.0.0-139.215.255.255
            {-1425539072, -1425014785},//171.8.0.0-171.15.255.255
            {-1236271104, -1235419137},//182.80.0.0-182.92.255.255
            {-770113536, -768606209},//210.25.0.0-210.47.255.255
            {-569376768, -564133889}, //222.16.0.0-222.95.255.255
    };

    public static Member generateLog(String uid) {
        Member member = new Member();
        Random rand = new Random();
        member.setAd_id(rand.nextInt(10) + "");
        String birthday = new SimpleDateFormat("yyyy-MM-dd")
                .format(RondomDate.randomDate("1960-01-01 00:00:00", "2000-01-01 00:00:00"));
        member.setDt(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now().minusDays(8)));
        member.setDn(dns[0]);
        member.setUid(uid);
        member.setPassword("123456");
        member.setEmail("test@126.com");
        member.setFullname("王" + uid);
        member.setPhone("13711235451");
        member.setBirthday(birthday);
        member.setQq("10000");
        member.setUnitname("-");
        member.setMailaddr("-");
        member.setZipcode("-");
//            String registerdata = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//                    .format(RondomDate.randomDate("2019-06-30 10:00:00", "2019-06-30 11:00:00"));
        member.setRegister(String.valueOf(System.currentTimeMillis()));
        member.setMemberlevel((rand.nextInt(8) + 1) + "");
        member.setPaymoney("-");
        member.setUserip("-");
        int index = rand.nextInt(10);
        String ip = num2ip(range[index][0]
                + new Random().nextInt(range[index][1] - range[index][0]));
        member.setUserip(ip);
        member.setRegupdatetime("-");
        member.setLastlogin("-");
        member.setIconurl("-");
        return member;
    }

    public static String num2ip(int ip) {
        int[] b = new int[4];
        String x = "";

        b[0] = (int) ((ip >> 24) & 0xff);
        b[1] = (int) ((ip >> 16) & 0xff);
        b[2] = (int) ((ip >> 8) & 0xff);
        b[3] = (int) (ip & 0xff);
        x = Integer.toString(b[0]) + "." + Integer.toString(b[1]) + "."
                + Integer.toString(b[2]) + "." + Integer.toString(b[3]);

        return x;
    }
}
