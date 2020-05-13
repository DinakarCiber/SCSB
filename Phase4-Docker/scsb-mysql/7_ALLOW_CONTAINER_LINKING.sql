use mysql;
GRANT ALL ON *.* to recap@'%' IDENTIFIED BY 'recap';
GRANT ALL ON sonar.* to sonar@'%' IDENTIFIED BY 'sonar';
FLUSH PRIVILEGES;
