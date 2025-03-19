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

ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk

# Создание пользователя vscode
ARG USERNAME=vscode
ARG USER_UID=1000
ARG USER_GID=1000  # Указываем конкретное значение, а не ссылку на переменную ARG

RUN groupadd --gid $USER_GID $USERNAME \
    && useradd --uid $USER_UID --gid $USER_GID -m $USERNAME \
    && echo "$USERNAME ALL=(ALL) NOPASSWD:ALL" > /etc/sudoers.d/$USERNAME \
    && chmod 0440 /etc/sudoers.d/$USERNAME

# **ЯВНО УКАЗЫВАЕМ ПОЛЬЗОВАТЕЛЯ**
USER vscode  

# Создание рабочей директории
WORKDIR /workspace
