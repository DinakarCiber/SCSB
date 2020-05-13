# Add SCSB-SOLR files
cd /opt/solr-6.5.0/bin
./solr start -force
./solr create -c recap -force
ls /opt/solr-6.5.0/server/solr/
cp /opt/solr-config/solrconfig.xml /opt/solr-6.5.0/server/solr/recap/conf/solrconfig.xml
cp /opt/solr-config/managed-schema /opt/solr-6.5.0/server/solr/recap/conf/managed-schema
mkdir /opt/solr-6.5.0/server/solr/configsets/recap_config
\
mkdir /opt/solr-6.5.0/server/solr/configsets/recap_config/conf
\
cp -R /opt/solr-6.5.0/server/solr/recap/conf /opt/solr-6.5.0/server/solr/configsets/recap_config/