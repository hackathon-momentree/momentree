export $(cat /home/ec2-user/.env | xargs) && nohub java -jar build/libs/momentree-0.0.1-SNAPSHOT.jar > application.log 2>&1 &