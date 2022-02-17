FROM openjdk
WORKDIR usr/lib
ADD ./target/FoodieService-0.0.1-SNAPSHOT.jar /usr/lib/FoodieService-0.0.1-SNAPSHOT.jar
ENTRYPOINT {"java","-jar","FoodieService-0.0.1-SNAPSHOT.jar"}