FROM openjdk
WORKDIR usr/lib
ADD ./target/vendorService-0.0.1-SNAPSHOT.jar /usr/lib/vendorService-0.0.1-SNAPSHOT.jar
ENTRYPOINT {"java","-jar","vendorService-0.0.1-SNAPSHOT.jar"}