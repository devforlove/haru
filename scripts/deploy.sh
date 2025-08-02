#!/bin/bash

cd /home/ec2-user/app

# 오래된 컨테이너 정지 및 제거
docker stop my-app-container || true
docker rm my-app-container || true

# 새 이미지 빌드 및 실행
docker build -t my-app-image .
docker run -d --name my-app-container -p 8080:8080 my-app-image