FROM recapscsb/scsb-solr-base:latest
MAINTAINER HTC ReCAP Support "recap-support@htcindia.com"

# Get files from GIT using tag
ARG TAG
ENV envTag="$TAG"

# Create solr config directory
RUN mkdir /opt/solr-config

# Copy Solr config files
COPY managed-schema /opt/solr-config/managed-schema
COPY solrconfig.xml /opt/solr-config/solrconfig.xml

# Copy and Run shellBuild.sh file
COPY shellBuild.sh /opt/
RUN cd /opt && ls -l && ./shellBuild.sh

# Change solr logs directory.
RUN echo 'SOLR_OPTS="$SOLR_OPTS -Dsolr.log=/var/data/solr-logs"' >> /opt/solr-6.5.0/bin/solr.in.sh

#Add startup.sh
COPY startup.sh /opt/startup.sh
RUN chmod 777 /opt/startup.sh

ENTRYPOINT ["/opt/startup.sh"]