package online.daliang.kafka.producer.utils;

import online.daliang.kafka.producer.model.Advertisement;

public class AdvertisementLog {

    public static Advertisement generateLog(String adid) {
        Advertisement basead = new Advertisement();
        basead.setAdid(adid);
        basead.setAdname("注册弹窗广告" + adid);
        basead.setDn("webA");
        return basead;
    }
    
}
