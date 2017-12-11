# Movie Fun!

Smoke Tests require server running on port 8080 by default.

## Build WAR ignoring Smoke Tests

```
$ mvn clean package -DskipTests -Dmaven.test.skip=true
```

## Run Smoke Tests against specific URL

```
$ MOVIE_FUN_URL=http://moviefun.example.com mvn test
```
```
git clean -df
./gradlew bootRun --parallel
echo "source ~/workspace/apps-movie-fun-modernization-code/.env" >> ~/.bashrc
source .env
env

./gradlew tasks
./gradlew bootRun
./gradlew build
./gradlew clean build
cf push moviefun --random-route -p build/libs/moviefun-1.1.0-SNAPSHOT.war 

logs6.papertrailapp.com:16333 

cf create-service p-mysql 100mb movies-mysql
cf bind-service moviefun movies-mysql
cf restage moviefun

cf create-service aws-s3 standard moviefun-s3
cf bind-service moviefun moviefun-s3

mvn spring-boot:run
mvn clean package -DskipTests -Dmaven.test.skip=true
cf push moviefun --random-route --no-start -p target/moviefun.war
cf push moviefun --random-route -p target/moviefun.war

cf create-user-provided-service paper-trail -l syslog-tls://logs6.papertrailapp.com:16333 

git push --set-upstream origin logging-start-2

     "access_key_id": "AKIAJXKJUHYWUAXCCOYA",
     "bucket": "cf-6dff8c99-a0d2-4f39-91f9-f70c8ef00a75",
     "region": "us-east-1",
     "secret_access_key": "7TVZSoMKcpC3GK/7PzKRNC/iCpARZQyRFi2pG5X8"


cf set-env moviefun S3_ENDPOINTURL http://s3.amazonaws.com 
cf set-env moviefun S3_ACCESSKEY AKIAJXKJUHYWUAXCCOYA
cf set-env moviefun S3_SECRETKEY 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
cf set-env moviefun S3_BUCKETNAME cf-6dff8c99-a0d2-4f39-91f9-f70c8ef00a75

cf set-env moviefun MOVIEFUN_DATASOURCES_MOVIES_URL jdbc:mysql://10.0.4.41:3306/cf_be8fc5a0_1ae6_47e2_830d_fff7332f943c
cf set-env moviefun MOVIEFUN_DATASOURCES_MOVIES_USERNAME hkmPNWrDDBIb4EWV
cf set-env moviefun MOVIEFUN_DATASOURCES_MOVIES_PASSWORD r6nhUJVkvleQ8v6P

aws s3 ls s3://cf-6dff8c99-a0d2-4f39-91f9-f70c8ef00a75
aws configure
aws s3 cp albums.csv s3://cf-6dff8c99-a0d2-4f39-91f9-f70c8ef00a75

apt search rabbitmq
/etc/init.d/rabbitmq-server start
cd /etc/init.d
sudo rabbitmq-plugins enable rabbitmq_management
http://localhost:15672/#/queues

curl -X POST http://localhost:8080/rabbit -d ""

cf create-service p.rabbitmq solo rabbit
cf bind-service moviefun rabbit
cf service rabbit
https://rmq-5471641b-58d6-4a98-acca-f4b1e8af0017.sys.longs.pal.pivotal.io


curl -X POST http://moviefun-adductive-goldcrest.apps.longs.pal.pivotal.io/rabbit -d ""

./gradlew submitReplatformingBackgroundJobsWithAmqp -PmovieFunUrl=http://moviefun-adductive-goldcrest.apps.longs.pal.pivotal.io

mvn clean package
cf push remove-session-state -p target/remove-session-state-lab.war -i 2 --random-route --no-start
cf create-service p-redis shared-vm my-redis 
cf bind-service remove-session-state my-redis
cf start remove-session-state


```
