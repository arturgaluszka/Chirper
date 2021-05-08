#!/usr/bin/env bash

cd /home/ec2-user/server
sudo /usr/lib/jvm/java-11-amazon-corretto.x86_64/bin/java -jar \
  -Dserver.port=80 \
  *.jar > /dev/null 2> /dev/null < /dev/null &

#sudo /usr/bin/java -jar -Dserver.port=80 \
#    *.jar > /dev/null 2> /dev/null < /dev/null &