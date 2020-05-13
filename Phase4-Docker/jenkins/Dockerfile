FROM recapscsb/scsb-base:latest
MAINTAINER HTC ReCAP Support "recap-support@htcindia.com"

RUN wget -q -O -  http://pkg.jenkins.io/debian/jenkins.io.key | apt-key add - && \
sh -c 'echo deb http://pkg.jenkins-ci.org/debian binary/ > /etc/apt/sources.list.d/jenkins.list' && \
apt-get update && \
apt-get install -q -y jenkins



RUN service jenkins start && sleep 10 && cd /var/lib/jenkins/plugins && wget http://updates.jenkins-ci.org/download/plugins/git-client/1.19.6/git-client.hpi && \
wget http://updates.jenkins-ci.org/download/plugins/git/2.4.4/git.hpi && \
wget http://updates.jenkins-ci.org/download/plugins/scm-api/1.2/scm-api.hpi && \
wget http://updates.jenkins-ci.org/download/plugins/junit/1.13/junit.hpi && \
wget http://updates.jenkins-ci.org/download/plugins/script-security/1.20/script-security.hpi && \
wget http://updates.jenkins-ci.org/download/plugins/matrix-project/1.7.1/matrix-project.hpi && \
wget http://updates.jenkins-ci.org/download/plugins/windows-slaves/1.1/windows-slaves.hpi && \
wget http://updates.jenkins-ci.org/download/plugins/icon-shim/2.0.3/icon-shim.hpi && \
wget http://updates.jenkins-ci.org/download/plugins/matrix-auth/1.4/matrix-auth.hpi && \
wget http://updates.jenkins-ci.org/download/plugins/javadoc/1.4/javadoc.hpi && \
wget http://updates.jenkins-ci.org/download/plugins/antisamy-markup-formatter/1.5/antisamy-markup-formatter.hpi && \
wget http://updates.jenkins-ci.org/download/plugins/mailer/1.17/mailer.hpi && \
wget http://updates.jenkins-ci.org/download/plugins/maven-plugin/2.13/maven-plugin.hpi && \
wget http://updates.jenkins-ci.org/download/plugins/cobertura/1.9.8/cobertura.hpi

ENV JAVA_HOME /opt/jdk1.8.0_51
ENV PATH $JAVA_HOME/bin:$PATH

RUN echo ${JAVA_HOME}

#Intall Gradle Plugin
RUN service jenkins start && sleep 10 && cd /var/lib/jenkins/plugins && wget https://updates.jenkins-ci.org/download/plugins/gradle/1.24/gradle.hpi

#Install SSH Plugin
RUN service jenkins start && sleep 10 && cd /var/lib/jenkins/plugins && wget https://updates.jenkins-ci.org/download/plugins/ssh/2.4/ssh.hpi

#Install git
RUN apt-get install -q -y git

#Install Gradle
ENV GRADLE_VERSION 2.6

RUN mkdir /opt/gradle
RUN cd /opt/gradle && \
curl -sLO https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-all.zip && \
unzip gradle-${GRADLE_VERSION}-all.zip


ENV GRADLE_HOME /opt/gradle/gradle-2.6
ENV PATH $PATH:$GRADLE_HOME/bin

#Set EST Timezone
RUN ln -sf /usr/share/zoneinfo/America/New_York /etc/localtime

EXPOSE 8080:8080

ENTRYPOINT service jenkins start && tail -f /dev/null
