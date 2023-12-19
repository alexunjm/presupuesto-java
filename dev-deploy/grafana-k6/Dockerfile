FROM ubuntu:20.04

# https://www.makeuseof.com/run-ubuntu-as-docker-container/
# docker run -ti --rm ubuntu /bin/bash
RUN apt update
RUN apt upgrade
RUN apt install gpg-agent -y
RUN gpg -k
RUN gpg --no-default-keyring --keyring /usr/share/keyrings/k6-archive-keyring.gpg --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys C5AD17C747E3415A3642D57D77C6C491D6AC1D69
RUN echo "deb [signed-by=/usr/share/keyrings/k6-archive-keyring.gpg] https://dl.k6.io/deb stable main" | tee /etc/apt/sources.list.d/k6.list
RUN apt install ca-certificates -y
RUN apt update
RUN apt install k6 -y


WORKDIR /app

# EXPOSE 6565

CMD ["k6", "new"]
