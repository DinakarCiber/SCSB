FROM recapscsb/scsb-base:latest
MAINTAINER HTC ReCAP Support "recap-support@htcindia.com"

ARG TAG
ENV envTag="$TAG"

# Create config directory
RUN mkdir /opt/cas-config

# Copy config files
COPY thekeystore /opt/cas-config/thekeystore
COPY run-cas.sh /opt/cas-config/run-cas.sh
COPY application.yml /opt/cas-config/application.yml

COPY shellBuild.sh /opt/
RUN chmod 750 /opt/shellBuild.sh
RUN cd /opt && ls -l && ./shellBuild.sh ${envTag}

EXPOSE 8080 10443

CMD ["sh","/opt/cas-config/run-cas.sh"]
