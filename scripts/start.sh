export $(cat /home/ec2-user/.env | xargs) && nohup java -jar build/libs/momentree-0.0.1-SNAPSHOT.jar > application.log 2>&1 &
