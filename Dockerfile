FROM node:24-alpine AS frontend
WORKDIR /frontend
COPY frontend/package*.json ./
RUN npm ci
COPY frontend/ ./
RUN npm run build

FROM maven:3.9.11-eclipse-temurin-21-alpine AS backend
WORKDIR /app
COPY backend/pom.xml ./pom.xml
RUN mvn dependency:go-offline
COPY backend/src ./src
COPY --from=frontend /frontend/dist ./src/main/resources/static
RUN mvn package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=backend /app/target/blog-api-0.0.1.jar app.jar
ENV UPLOAD_DIR=/data/uploads
RUN mkdir -p /data/uploads
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
