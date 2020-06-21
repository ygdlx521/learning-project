package online.daliang.flink.streaming.config

object GlobalConfig {

  val HBASE_ZOOKEEPER_QUORUM = "node00,node01,node02"
  val HBASE_ZOOKEEPER_PROPERTY_CLIENTPORT = "2181"

  val BOOTSTRAP_SERVERS = "node00:9092,node01:9092,node02:9092"
  val ACKS = "-1"

}
