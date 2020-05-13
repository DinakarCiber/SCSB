FROM recapscsb/scsb-zookeeper-base:latest
MAINTAINER HTC ReCAP Support "recap-support@htcindia.com"

ENV ZOO_USER=zookeeper
ENV ZOO_BASE=/opt/scsb-zookeeper

ARG DISTRO_NAME=/opt/zookeeper
ENV DISTRO_NAME=$DISTRO_NAME

WORKDIR $DISTRO_NAME

ENV ZOO_USER=zookeeper \
    ZOO_CONF_DIR=$ZOO_BASE/conf \
    ZOO_DATA_DIR=$ZOO_BASE/data \
    ZOO_DATA_LOG_DIR=$ZOO_BASE/datalog \
    ZOO_PORT=2181 \
    ZOO_TICK_TIME=2000 \
    ZOO_INIT_LIMIT=5 \
    ZOO_SYNC_LIMIT=2

VOLUME ["$ZOO_DATA_DIR", "$ZOO_DATA_LOG_DIR"]

EXPOSE $ZOO_PORT 2888 3888

ENV PATH=$PATH:/$DISTRO_NAME/bin \
    ZOOCFGDIR=$ZOO_CONF_DIR

COPY docker-entrypoint.sh /
RUN chmod 777 /docker-entrypoint.sh
ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["zkServer.sh", "start-foreground"]
