#!/bin/bash

#Start container.

if [ $REPLICATION = "true" ]
then
    echo "Starting SOLR container with replication using zookeeper host"
    /opt/solr-6.5.0/bin/solr start -Dbootstrap_conf=true -h $INSTANCE_HOST -z $ZK_HOST_1:2181,$ZK_HOST_2:2181,$ZK_HOST_3:2181 -noprompt -force -f $ENV
else
    echo "Starting SOLR container with non replication"
    /opt/solr-6.5.0/bin/solr -force -f $ENV
fi