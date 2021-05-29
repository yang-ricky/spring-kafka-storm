# spring-kafka-storm
# 踩坑记
 ## org.apache.catalina.LifecycleException: Failed to start component [StandardEngine[Catalina].Standard
  * 删除 tomcat-embed-core ~/.m2/repository/org/apache/tomcat/embed
  * 重新编译