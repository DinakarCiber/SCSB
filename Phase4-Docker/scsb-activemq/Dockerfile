FROM recapscsb/scsb-activemq-base:latest
MAINTAINER HTC ReCAP Support "recap-support@htcindia.com"

COPY activemq.xml /opt/activemq/conf
COPY log4j.properties /opt/activemq/conf
COPY jetty-realm.properties /opt/activemq/conf
COPY activemq.sh /opt/activemq/bin
COPY env /opt/activemq/bin
COPY env-container /opt/activemq/bin
COPY config.sh /opt

#Add Download MYSQL Driver
RUN curl http://central.maven.org/maven2/mysql/mysql-connector-java/6.0.6/mysql-connector-java-6.0.6.jar -o /opt/activemq/lib/mysql-connector-java-6.0.6.jar

ARG ENVVAR
ENV envVar="$ENVVAR"
RUN cd /opt && ls -l && ./config.sh ${envVar}

#Add startup.sh
COPY startup.sh /opt/startup.sh
RUN chmod 777 /opt/startup.sh

ENTRYPOINT ["/opt/startup.sh"]
