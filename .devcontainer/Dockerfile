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

# Use non-conflicting UID/GID (1001 instead of 1000)
ARG USERNAME=vscode
ARG USER_UID=1001
ARG USER_GID=1001

# Create user and group
RUN groupadd --gid ${USER_GID} ${USERNAME} && \
    useradd --uid ${USER_UID} --gid ${USER_GID} -m ${USERNAME} --shell /bin/bash && \
    echo "${USERNAME} ALL=(ALL) NOPASSWD:ALL" > /etc/sudoers.d/${USERNAME} && \
    chmod 0440 /etc/sudoers.d/${USERNAME}

# Switch to non-root user
USER ${USERNAME}

# Configure workspace with proper ownership
WORKDIR /workspace
RUN sudo chown -R ${USERNAME}:${USERNAME} /workspace
