FROM eclipse-temurin:21-jdk-jammy

# Установка инструментов разработки
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    sudo \
    git \
    maven \
    gradle \
    docker.io \
    && rm -rf /var/lib/apt/lists/*

# Устанавливаем переменные окружения
ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk

# Указываем рабочую директорию
WORKDIR /workspace
