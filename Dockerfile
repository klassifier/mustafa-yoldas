# Base image
FROM adoptopenjdk/openjdk11:alpine-jre

# Uygulama dosyalarını /app dizinine kopyalama
COPY target/poc-demo-project-0.0.1-SNAPSHOT.jar /app/poc-demo.jar

# Çalışma dizini ayarlama
WORKDIR /app

# Uygulama çalıştırma komutu
CMD ["java", "-jar", "poc-demo.jar"]
