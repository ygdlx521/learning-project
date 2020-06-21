package online.daliang.flink.streaming.model

case class MemberVipLevel(vip_id: Int, vip_level: String, start_time: String,
                           end_time: String, last_modify_time: String, max_free: String,
                           min_free: String, next_level: String, operator: String, dn: String)
