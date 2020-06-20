package online.daliang.kafka.producer.model;

public class MemberRegisterType {

    private String reflagid;
    private String uid;
    private String regsource; //注册来源 1.pc 2.mobile 3.app 4.wechat
    private String appkey;
    private String appregurl;
    private String websiteid;
    private String domain;
    private String isranreg;
    private String bdp_uuid;
    private String createtime;
    private String dt;
    private String dn;


    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getReflagid() {
        return reflagid;
    }

    public void setReflagid(String reflagid) {
        this.reflagid = reflagid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRegsource() {
        return regsource;
    }

    public void setRegsource(String regsource) {
        this.regsource = regsource;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getAppregurl() {
        return appregurl;
    }

    public void setAppregurl(String appregurl) {
        this.appregurl = appregurl;
    }

    public String getWebsiteid() {
        return websiteid;
    }

    public void setWebsiteid(String websiteid) {
        this.websiteid = websiteid;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getIsranreg() {
        return isranreg;
    }

    public void setIsranreg(String isranreg) {
        this.isranreg = isranreg;
    }

    public String getBdp_uuid() {
        return bdp_uuid;
    }

    public void setBdp_uuid(String bdp_uuid) {
        this.bdp_uuid = bdp_uuid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }
}

