FROM tomcat:8.5

RUN cd /usr/local/

RUN apt-get -q update --fix-missing
RUN apt-get install -q -y software-properties-common wget

COPY scsb-javadoc.war /usr/local/tomcat/webapps/scsb-javadoc.war

CMD ["catalina.sh", "run"]

RUN rm -rf /var/lib/apt/lists; rm -rf /tmp/*; apt-get autoremove -y