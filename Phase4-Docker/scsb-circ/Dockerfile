FROM recapscsb/scsb-base:latest
MAINTAINER HTC ReCAP Support "recap-support@htcindia.com"

ARG TAG
ENV envTag="$TAG"
COPY shellBuild.sh /opt/
RUN cd /opt && ls -l && ./shellBuild.sh ${envTag}

EXPOSE 9095

ENTRYPOINT java -jar -Denvironment=$ENV /opt/scsb-circ-0.0.1-SNAPSHOT.jar && bash
