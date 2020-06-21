package online.daliang.flink.streaming.sink

import com.google.gson.Gson
import online.daliang.flink.streaming.config.GlobalConfig
import online.daliang.flink.streaming.model.{Advertisement, MemberVipLevel, TopicAndValue, WebSite}
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory, Put}
import org.apache.hadoop.hbase.util.Bytes


class HbaseSink extends RichSinkFunction[TopicAndValue] {

  var connection: Connection = _

  //打开hbase连接
  override def open(parameters: Configuration): Unit = {
    val conf = HBaseConfiguration.create()
    conf.set("hbase.zookeeper.quorum", GlobalConfig.HBASE_ZOOKEEPER_QUORUM)
    conf.set("hbase.zookeeper.property.clientPort", GlobalConfig.HBASE_ZOOKEEPER_PROPERTY_CLIENTPORT)
    connection = ConnectionFactory.createConnection(conf)
  }

  //写入hbase
  override def invoke(value: TopicAndValue, context: SinkFunction.Context[_]): Unit = {
    value.topic match {
      case "ods_website" => invokeDimWebSite("education:dim_website", value)
      case "ods_advertisement" => invokeDimAdvertisement("education:dim_advertisement", value)
      case _ => invokeDimMemberVipLevel("education:dim_member_vip_level", value)
    }
  }


  //关闭hbase连接
  override def close(): Unit = {
    super.close()
    connection.close()
  }

  def invokeDimWebSite(tableName: String, value: TopicAndValue): Unit = {
    val gson = new Gson()
    val webSite = gson.fromJson(value.value, classOf[WebSite])
    val table = connection.getTable(TableName.valueOf(tableName))
    val put = new Put(Bytes.toBytes(webSite.siteid + "_" + webSite.dn))
    put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("siteid"), Bytes.toBytes(webSite.siteid))
    put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("sitename"), Bytes.toBytes(webSite.sitename))
    put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("siteurl"), Bytes.toBytes(webSite.siteurl))
    put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("delete"), Bytes.toBytes(webSite.delete))
    put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("createtime"), Bytes.toBytes(webSite.createtime))
    put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("creator"), Bytes.toBytes(webSite.creator))
    put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("dn"), Bytes.toBytes(webSite.dn))
    table.put(put)
    table.close()
  }

  def invokeDimAdvertisement(tableName: String, value: TopicAndValue): Unit = {
    val gson = new Gson()
    val ad = gson.fromJson(value.value, classOf[Advertisement])
    val table = connection.getTable(TableName.valueOf(tableName))
    val put = new Put(Bytes.toBytes(ad.adid + "_" + ad.dn))
    put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("adid"), Bytes.toBytes(ad.adid))
    put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("adname"), Bytes.toBytes(ad.adname))
    put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("dn"), Bytes.toBytes(ad.dn))
    table.put(put)
    table.close()
  }

  def invokeDimMemberVipLevel(tableName: String, value: TopicAndValue): Unit = {
    val gson = new Gson()
    val memberVipLevel = gson.fromJson(value.value, classOf[MemberVipLevel])
    val table = connection.getTable(TableName.valueOf(tableName))
    val put = new Put(Bytes.toBytes(memberVipLevel.vip_id + "_" + memberVipLevel.dn))
    put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("vip_id"), Bytes.toBytes(memberVipLevel.vip_id))
    put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("vip_level"), Bytes.toBytes(memberVipLevel.vip_level))
    put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("start_time"), Bytes.toBytes(memberVipLevel.start_time))
    put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("end_time"), Bytes.toBytes(memberVipLevel.end_time))
    put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("last_modify_time"), Bytes.toBytes(memberVipLevel.last_modify_time))
    put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("max_free"), Bytes.toBytes(memberVipLevel.max_free))
    put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("min_free"), Bytes.toBytes(memberVipLevel.min_free))
    put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("next_level"), Bytes.toBytes(memberVipLevel.next_level))
    put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("operator"), Bytes.toBytes(memberVipLevel.operator))
    put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("dn"), Bytes.toBytes(memberVipLevel.dn))
    table.put(put)
    table.close()
  }

}
