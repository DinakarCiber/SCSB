FROM recapscsb/scsb-base:latest
MAINTAINER HTC ReCAP Support "recap-support@htcindia.com"

ARG TAG
ENV envTag="$TAG"
COPY shellBuild.sh /opt/
RUN cd /opt && ls -l && ./shellBuild.sh ${envTag}

EXPOSE 9093

ENTRYPOINT java -jar -Denvironment=$ENV /opt/phase4-scsb-gateway-0.0.1-SNAPSHOT.jar && bash
