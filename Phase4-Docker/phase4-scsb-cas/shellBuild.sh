
echo "Parameter = $1"
PROJ_NAME="Phase4-SCSB-CAS"
PROJ_DIR="/opt/Phase4-SCSB-CAS"
PROJ_DIR_TAG=""
VAR_SLASH="/"

PROJ_DIR_TAG=$PROJ_DIR$VAR_SLASH$PROJ_NAME;

# echo $PROJ_DIR_TAG
mkdir $PROJ_NAME
cd $PROJ_DIR

# echo Cloning poject
git clone https://github.com/ResearchCollectionsAndPreservation/Phase4-SCSB-CAS.git

# echo $PROJ_DIR_TAG
cd $PROJ_DIR_TAG
if [ ! -z $1 ] ;then
    git checkout tags/$1
fi
ls -l
pwd

# echo Copy application config file
cp /opt/cas-config/application.yml $PROJ_DIR_TAG/etc/cas/config/application.yml

# echo Preparing config files
mkdir -p /etc/cas \
    && mkdir -p /etc/cas/services \
    && mkdir -p /etc/cas/config \
    && mkdir -p $PROJ_DIR_TAG/bin \
    && cp -f $PROJ_DIR_TAG/etc/cas/config/*.* /etc/cas/config;

# echo Copy keystore file
cp /opt/cas-config/thekeystore /etc/cas/

# echo Listing project directory
ls $PROJ_DIR_TAG/

# echo Change permissions
chmod -R 750 $PROJ_DIR_TAG/bin \
    && chmod 750 $PROJ_DIR_TAG/mvnw \
    && chmod 750 $PROJ_DIR_TAG/build.sh;

# echo Building application
./mvnw clean package -T 10