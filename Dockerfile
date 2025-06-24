# Usar uma imagem do Maven para build e do Java para execução
FROM maven:3.9-eclipse-temurin-21 AS builder

# Diretório de trabalho para o build
WORKDIR /app

# Copiar o código fonte e o arquivo pom.xml
COPY . .

# Executar o build da aplicação
RUN mvn package -DskipTests

# Segunda etapa: imagem final para execução
FROM eclipse-temurin:21-jdk-alpine

# Diretório de trabalho para execução
WORKDIR /app

# Copiar o jar gerado na etapa anterior
COPY --from=builder /app/target/pdf.for.chatgpt-0.0.1-SNAPSHOT.jar ./app.jar

# Definir a porta que será exposta
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
