#!/bin/bash

if [ ! -f /var/lib/mysql/ibdata1 ]; then
	echo "Initialize mysql db"
	mysqld --initialize-insecure --console

	echo "Run mysql"
    	/usr/bin/mysqld_safe & sleep 10s
    	mysql_tzinfo_to_sql /usr/share/zoneinfo | mysql -uroot mysql

    	mysql -uroot < /opt/git/mysql/1_SONAR_SCHEMA_CREATION.sql
    	mysql -uroot < /opt/git/mysql/2_RECAP_SCHEMA_TABLES_CREATION.sql
        mysql -uroot < /opt/git/mysql/3_COLLECTION_GROUP_T.sql
        mysql -uroot < /opt/git/mysql/4_INSTITUTION_T.sql
        mysql -uroot < /opt/git/mysql/5_ITEM_STATUS_T.sql
        mysql -uroot < /opt/git/mysql/6_REQUEST_TYPE_T.sql
        mysql -uroot < /opt/git/mysql/7_ALLOW_CONTAINER_LINKING.sql
        mysql -uroot < /opt/git/mysql/9_CUSTOMER_CODE_T.sql
        mysql -uroot < /opt/git/mysql/12_PERMISSIONS_T.sql
        mysql -uroot < /opt/git/mysql/13_ROLES_T.sql
        mysql -uroot < /opt/git/mysql/14_ROLES_PERMISSIONS_T.sql
        mysql -uroot < /opt/git/mysql/15_USER_T.sql
        mysql -uroot < /opt/git/mysql/16_USER_ROLE_T.sql
        mysql -uroot < /opt/git/mysql/17_REQUEST_ITEM_STATUS_T.sql
        mysql -uroot < /opt/git/mysql/19_CAS_SCHEMA_CREATION.sql
        mysql -uroot < /opt/git/mysql/20_DELIVERY_RESTRICTION_CROSS_PARTNER_T.sql
        mysql -uroot < /opt/git/mysql/21_CROSS_PARTNER_MAPPING_T.sql
        mysql -uroot < /opt/git/mysql/22_BATCH_SCHEMA_CREATION.sql
        mysql -uroot < /opt/git/mysql/23_JOB_T.sql
        mysql -uroot < /opt/git/mysql/24_ACTIVEMQ_SCHEMA_CREATION.sql
        mysql -uroot < /opt/git/mysql/25_JOB_PARAM_T.sql
        mysql -uroot < /opt/git/mysql/26_JOB_PARAM_DATA_T.sql
        mysql -uroot < /opt/git/mysql/27_DELETE_TRIGGERS.sql
        	killall mysqld

            sleep 10s
else
    echo "Run Alter scripts for mysql db"
    mysqld --initialize-insecure --console

    echo "Run mysql"
        /usr/bin/mysqld_safe & sleep 10s
    	mysql_tzinfo_to_sql /usr/share/zoneinfo | mysql -uroot mysql

        mysql -uroot < /opt/git/mysql/10_RECAP_ALTER_SCRIPTS.sql


    	        killall mysqld

                sleep 10s

fi

echo 'Set Time zone'
sed -i -e 's@#--TimeZoneConfig@'"default-time-zone='America/New_York'"'@g' /usr/share/mysql/my-default.cnf
sed -i -e 's@#--TimeZoneConfig@'"default-time-zone='America/New_York'"'@g' /etc/mysql/my.cnf

echo "DB already initialized; Starting MySQL"
/usr/bin/mysqld_safe & tail -f /dev/null