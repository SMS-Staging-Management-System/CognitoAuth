curl https://s3.amazonaws.com/sms-cognito-auth/com/revature/CognitoAuth/0.0.1/CognitoAuth-0.0.1.jar -O CognitoAuth-0.0.1.jar
mvn install:install-file -Dfile=CognitoAuth-0.0.1.jar -DgroupId=com.revature -DartifactId=CognitoAuth -Dversion=0.0.1 -Dpackaging=jar