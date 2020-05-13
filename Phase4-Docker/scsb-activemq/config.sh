if [ ! -z $1 ] ;then
    echo "inside if condition..."
    cd activemq/bin
    ls -la
    rm /opt/activemq/bin/env
    echo "removed env"
    mv /opt/activemq/bin/env-container /opt/activemq/bin/env
    echo "renamed env-container to env"
fi