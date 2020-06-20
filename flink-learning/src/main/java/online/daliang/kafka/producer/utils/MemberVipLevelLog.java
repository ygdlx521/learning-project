package online.daliang.kafka.producer.utils;

import online.daliang.kafka.producer.model.MemberVipLevel;

import java.text.SimpleDateFormat;

public class MemberVipLevelLog {

    private static String[] vipLevels = new String[]{"普通会员", "白金", "银卡", "金卡", "钻石"};

    public static MemberVipLevel generateLog(String vipid) {
        MemberVipLevel memViplevel = new MemberVipLevel();
        memViplevel.setDiscountval("-");
        memViplevel.setDn("webA");
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(RondomDate.randomDate("2015-01-01 00:00:00", "2016-06-30 00:00:00"));
        String time2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(RondomDate.randomDate("2016-01-01 00:00:00", "2019-06-30 00:00:00"));
        memViplevel.setLast_modify_time("");
        memViplevel.setStart_time(time);
        memViplevel.setEnd_time(time2);
        memViplevel.setLast_modify_time(time2);
        memViplevel.setMax_free("-");
        memViplevel.setMin_free("-");
        memViplevel.setNext_level("-");
        memViplevel.setVip_id(vipid);
        memViplevel.setOperator("update");
        memViplevel.setVip_level(vipLevels[Integer.parseInt(vipid)]);
        return memViplevel;
    }
}
