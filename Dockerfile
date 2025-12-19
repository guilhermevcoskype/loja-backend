# ---- STAGE 1: build ----
FROM maven:3.9.9-eclipse-temurin-17 AS builder
WORKDIR /app

# Copia pom.xml e baixa dependências (cache eficiente)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o código fonte e empacota o projeto
COPY src ./src
RUN mvn clean package -DskipTests

# ---- STAGE 2: runtime ----
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copia o JAR gerado do estágio anterior
COPY --from=builder /app/target/*.jar app.jar

# Define variáveis de ambiente (para sobrepor configs do application.properties)
ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/loja_e_commerce?useSSL=false&serverTimezone=UTC
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=3e4e5e6
ENV SERVER_PORT=8080
ENV JWT_SECRET=12345678
ENV JWT_ISSUER="API LOJA"

# Expõe a porta da aplicação
EXPOSE 8080

# Comando padrão de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]
