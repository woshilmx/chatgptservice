FROM maven:3.8.1-jdk-8-slim as builder

MAINTAINER lmx

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn package -DskipTests

# 声明环境变量，这样容器就可以在运行时访问它们
ENV OPENAI_API_KEY=你的API_KEY
ENV LANG C.UTF-8
# ENV LC_ALL C.UTF-8
# Run the web service on container startup.
ENTRYPOINT ["java","-Dfile.encoding=UTF-8","-jar","/app/target/ChatGptShellService-0.0.1-SNAPSHOT.jar"]