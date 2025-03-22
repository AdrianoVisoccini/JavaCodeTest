FROM eclipse-temurin:21-jdk-jammy

# Install development tools
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    sudo \
    git \
    maven \
    gradle \
    docker.io \
    && rm -rf /var/lib/apt/lists/*

ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk

# Создание пользователя vscode без переменных
RUN groupadd --gid 1001 vscode && \
    useradd --uid 1001 --gid 1001 -m vscode --shell /bin/bash && \
    echo "vscode ALL=(ALL) NOPASSWD:ALL" > /etc/sudoers.d/vscode && \
    chmod 0440 /etc/sudoers.d/vscode

# Переключаемся на пользователя vscode
USER vscode

# Устанавливаем правильные права на папку
WORKDIR /workspace
RUN sudo chown -R vscode:vscode /workspace
