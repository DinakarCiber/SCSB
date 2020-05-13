#!/bin/bash

echo "Start container."
echo "Updating"

    sed -i -e 's@memory_argument@'"$MEM_ARG"'@g' /opt/activemq/bin/env
    sed -i -e 's@max_frame_size@'"$MAX_FRAME_SIZE"'@g' /opt/activemq/conf/activemq.xml


if [ $DB_PERSISTANCE = "true" ]
then
    echo "Starting ActiveMQ with MySQL db Persistance"
    sed -i -e 's@<!--ReplaceStorageType-->@''<jdbcPersistenceAdapter dataSource="#mysql-ds"/>''@g' /opt/activemq/conf/activemq.xml

    echo "Replace db url"
    sed -i -e 's@replace_your_db_url@'"$DB_URL"'@g' /opt/activemq/conf/activemq.xml

    echo "Replace db username"
    sed -i -e 's@replace_your_db_username@'"$DB_USERNAME"'@g' /opt/activemq/conf/activemq.xml

    echo "Replace db password"
    sed -i -e 's@replace_your_db_password@'"$DB_PASSWORD"'@g' /opt/activemq/conf/activemq.xml
else
    echo "Starting ActiveMQ with Kaha db Persistance"
    sed -i -e 's@<!--ReplaceStorageType-->@''<kahaDB directory="${activemq.data}/kahadb"/>''@g' /opt/activemq/conf/activemq.xml
fi

if [ $UPDATE_INSTANCE_HOST = "true" ]
then
    echo "Update Instance Host"
    sed -i -e 's@scsbactivemqhost@'"$INSTANCE_HOST"'@g' /opt/activemq/bin/env
fi

sed -i -e 's@#--replace_user_infos@'"$USERS"'@g' /opt/activemq/conf/jetty-realm.properties

#Start container.
/opt/activemq/bin/activemq console