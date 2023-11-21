# Define a imagem base
FROM openjdk:17

# Copia o arquivo JAR do seu projeto para dentro do container
COPY target/loja-backend-0.0.1-SNAPSHOT.jar /app/loja-backend-0.0.1-SNAPSHOT.jar

# Define o diretório de trabalho
WORKDIR /app

# Instala o MySQL
# RUN apt-get update && apt-get install -y mysql-server

# Expõe a porta do seu projeto
# EXPOSE 8080

# Define o comando de inicialização do seu projeto
CMD ["java", "-jar", "loja-backend-0.0.1-SNAPSHOT.jar"]