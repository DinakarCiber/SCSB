FROM recapscsb/scsb-base:latest
MAINTAINER HTC ReCAP Support "recap-support@htcindia.com"

ARG TAG
ENV envTag="$TAG"
COPY shellBuild.sh /opt/
RUN cd /opt && ls -l && ./shellBuild.sh ${envTag}

EXPOSE 9096

ENTRYPOINT java -jar -Denvironment=$ENV /opt/scsb-batch-scheduler-0.0.1-SNAPSHOT.jar && bash
