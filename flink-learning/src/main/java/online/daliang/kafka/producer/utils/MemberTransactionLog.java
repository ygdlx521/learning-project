package online.daliang.kafka.producer.utils;

import online.daliang.kafka.producer.model.MemberTransaction;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class MemberTransactionLog {

    public static MemberTransaction generateLog() {
        MemberTransaction memPaymoney = new MemberTransaction();
        Random random = new Random();
        DecimalFormat df = new DecimalFormat("0.00");
        double money = random.nextDouble() * 1000;
        memPaymoney.setPaymoney(df.format(money));
        memPaymoney.setDt(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now().minusDays(8)));
        memPaymoney.setDn("webA");
        memPaymoney.setSiteid(String.valueOf(random.nextInt(5)));
        memPaymoney.setVip_id(String.valueOf(random.nextInt(5)));
        memPaymoney.setUid(String.valueOf(random.nextInt(5000000)));
//            String registerdata = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//                    .format(RondomDate.randomDate("2019-06-30 10:00:00", "2019-06-30 11:00:00"));
        memPaymoney.setCreatetime(String.valueOf(System.currentTimeMillis()));
        return memPaymoney;
    }
}
