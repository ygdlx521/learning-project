package online.daliang.flink.streaming.dwd

import java.lang

import com.alibaba.fastjson.JSONObject
import online.daliang.flink.streaming.model.{DwdMember, DwdMemberRegisterType}
import org.apache.flink.api.common.functions.CoGroupFunction
import org.apache.flink.util.Collector

class MemberLeftJoinMemberRegisterTypeCoGroupFunction extends CoGroupFunction[DwdMember, DwdMemberRegisterType, String]{
  override def coGroup(first: lang.Iterable[DwdMember], second: lang.Iterable[DwdMemberRegisterType], out: Collector[String]): Unit = {
    var bl = false
    val leftIterator = first.iterator()
    val rightIterator = second.iterator()
    while (leftIterator.hasNext) {
      val dwdMember = leftIterator.next()
      val jsonObject = new JSONObject()
      jsonObject.put("uid", dwdMember.uid)
      jsonObject.put("ad_id", dwdMember.ad_id)
      jsonObject.put("birthday", dwdMember.birthday)
      jsonObject.put("email", dwdMember.email)
      jsonObject.put("fullname", dwdMember.fullname)
      jsonObject.put("iconurl", dwdMember.iconurl)
      jsonObject.put("lastlogin", dwdMember.lastlogin)
      jsonObject.put("mailaddr", dwdMember.mailaddr)
      jsonObject.put("memberlevel", dwdMember.memberlevel)
      jsonObject.put("password", dwdMember.password)
      jsonObject.put("phone", dwdMember.phone)
      jsonObject.put("qq", dwdMember.qq)
      jsonObject.put("register", dwdMember.register)
      jsonObject.put("unitname", dwdMember.unitname)
      jsonObject.put("userip", dwdMember.userip)
      jsonObject.put("zipcode", dwdMember.zipcode)
      jsonObject.put("dt", dwdMember.dt)
      jsonObject.put("dn", dwdMember.dn)
      while (rightIterator.hasNext) {
        val dwdMemberRegtype = rightIterator.next()
        jsonObject.put("appkey", dwdMemberRegtype.appkey)
        jsonObject.put("appregurl", dwdMemberRegtype.appregurl)
        jsonObject.put("bdp_uuid", dwdMemberRegtype.bdp_uuid)
        jsonObject.put("createtime", dwdMemberRegtype.createtime)
        jsonObject.put("isranreg", dwdMemberRegtype.isranreg)
        jsonObject.put("regsource", dwdMemberRegtype.regsource)
        jsonObject.put("websiteid", dwdMemberRegtype.websiteid)
        bl = true
        out.collect(jsonObject.toJSONString)
      }
      if (!bl) {
        jsonObject.put("appkey", "")
        jsonObject.put("appregurl", "")
        jsonObject.put("bdp_uuid", "")
        jsonObject.put("createtime", "")
        jsonObject.put("isranreg", "")
        jsonObject.put("regsource", "")
        jsonObject.put("websiteid", "")
        out.collect(jsonObject.toJSONString)
      }
    }
  }
}
