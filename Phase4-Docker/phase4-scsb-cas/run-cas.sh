echo "Parameter = $1"
PROJ_NAME="Phase4-SCSB-CAS"
PROJ_DIR="/opt/Phase4-SCSB-CAS"
PROJ_DIR_TAG=""
VAR_SLASH="/"

PROJ_DIR_TAG=$PROJ_DIR$VAR_SLASH$PROJ_NAME;

#Replace db url
sed -i -e 's@replace_your_db_host@'"$DB_HOST"'@g' $PROJ_DIR_TAG/etc/cas/config/application.yml

#Replace db username
sed -i -e 's@replace_your_db_username@'"$DB_USERNAME"'@g' $PROJ_DIR_TAG/etc/cas/config/application.yml

#Replace db password
sed -i -e 's@replace_your_db_password@'"$DB_PASSWORD"'@g' $PROJ_DIR_TAG/etc/cas/config/application.yml

cd $PROJ_DIR_TAG

# echo Building application
./mvnw clean package -T 10

# echo Listing project directory
ls

exec ./build.sh run