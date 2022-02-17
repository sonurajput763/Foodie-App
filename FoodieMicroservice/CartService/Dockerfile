FROM openjdk
WORKDIR usr/lib
ADD ./target/CartService-0.0.1-SNAPSHOT.jar /usr/lib/CartService-0.0.1-SNAPSHOT.jar
ENTRYPOINT {"java","-jar","CartService-0.0.1-SNAPSHOT.jar"}