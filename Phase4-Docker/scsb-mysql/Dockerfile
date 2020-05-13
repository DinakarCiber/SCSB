FROM mysql:5.7.14
MAINTAINER HTC ReCAP Support "recap-support@htcindia.com"

#Set Terminal
ENV TERM=xterm

#Set EST Timezone
ENV TZ=America/New_York
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY startup.sh /opt/startup.sh
RUN chmod 777 /opt/startup.sh

#Add my.cnf
COPY my.cnf /usr/share/mysql/my-default.cnf
COPY my.cnf /etc/mysql/my.cnf

COPY 1_SONAR_SCHEMA_CREATION.sql /opt/git/mysql/1_SONAR_SCHEMA_CREATION.sql
COPY 2_RECAP_SCHEMA_TABLES_CREATION.sql /opt/git/mysql/2_RECAP_SCHEMA_TABLES_CREATION.sql
COPY 3_COLLECTION_GROUP_T.sql /opt/git/mysql/3_COLLECTION_GROUP_T.sql
COPY 4_INSTITUTION_T.sql /opt/git/mysql/4_INSTITUTION_T.sql
COPY 5_ITEM_STATUS_T.sql /opt/git/mysql/5_ITEM_STATUS_T.sql
COPY 6_REQUEST_TYPE_T.sql /opt/git/mysql/6_REQUEST_TYPE_T.sql
COPY 7_ALLOW_CONTAINER_LINKING.sql /opt/git/mysql/7_ALLOW_CONTAINER_LINKING.sql
COPY 9_CUSTOMER_CODE_T.sql /opt/git/mysql/9_CUSTOMER_CODE_T.sql
COPY 10_RECAP_ALTER_SCRIPTS.sql /opt/git/mysql/10_RECAP_ALTER_SCRIPTS.sql
COPY 11_ONGOING_TABLES_CREATION.sql /opt/git/mysql/11_ONGOING_TABLES_CREATION.sql
COPY 12_PERMISSIONS_T.sql /opt/git/mysql/12_PERMISSIONS_T.sql
COPY 13_ROLES_T.sql /opt/git/mysql/13_ROLES_T.sql
COPY 14_ROLES_PERMISSIONS_T.sql /opt/git/mysql/14_ROLES_PERMISSIONS_T.sql
COPY 15_USER_T.sql /opt/git/mysql/15_USER_T.sql
COPY 16_USER_ROLE_T.sql /opt/git/mysql/16_USER_ROLE_T.sql
COPY 17_REQUEST_ITEM_STATUS_T.sql /opt/git/mysql/17_REQUEST_ITEM_STATUS_T.sql
COPY 18_RECAP_VIEW_CREATION.sql /opt/git/mysql/18_RECAP_VIEW_CREATION.sql
COPY 19_CAS_SCHEMA_CREATION.sql /opt/git/mysql/19_CAS_SCHEMA_CREATION.sql
COPY 20_DELIVERY_RESTRICTION_CROSS_PARTNER_T.sql /opt/git/mysql/20_DELIVERY_RESTRICTION_CROSS_PARTNER_T.sql
COPY 21_CROSS_PARTNER_MAPPING_T.sql /opt/git/mysql/21_CROSS_PARTNER_MAPPING_T.sql
COPY 22_BATCH_SCHEMA_CREATION.sql /opt/git/mysql/22_BATCH_SCHEMA_CREATION.sql
COPY 23_JOB_T.sql /opt/git/mysql/23_JOB_T.sql
COPY 24_ACTIVEMQ_SCHEMA_CREATION.sql /opt/git/mysql/24_ACTIVEMQ_SCHEMA_CREATION.sql
COPY 25_JOB_PARAM_T.sql /opt/git/mysql/25_JOB_PARAM_T.sql
COPY 26_JOB_PARAM_DATA_T.sql /opt/git/mysql/26_JOB_PARAM_DATA_T.sql
COPY 27_DELETE_TRIGGERS.sql /opt/git/mysql/27_DELETE_TRIGGERS.sql

ENTRYPOINT ["/opt/startup.sh"]


