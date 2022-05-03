# Kenshin-Back
kenshin은 게임 genshin의 인게임 데이터를 한 눈에 확인 할 수 있는 웹사이트입니다. 
## 실행 방법
- git clone 이후 `./resource-examples` 안의 파일들을 작성후 `src/main/resources`에 .example를 지우고 추가합니다.
- `chmod +x gradlew; ./gradlew build` 명령어를 통해 프로젝트를 빌드합니다.
- `docker build . --tag kenshin-back ; docker run --rm --name kenshin-back -p 8080:8080 kenshin-back` 명령어를 통해 실행합니다.

## 기술 스택
<div>
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
  <br/>
  <img src="https://img.shields.io/badge/flyway-CC0200?style=for-the-badge&logo=flyway&logoColor=white">
  <img src="https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=PostgreSQL&logoColor=white">
  <br/>
  <img src="https://img.shields.io/badge/github%20actions-2088FF?style=for-the-badge&logo=GitHub%20Actions&logoColor=white">
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=GitHub&logoColor=white">
  <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">
  <img src="https://img.shields.io/badge/kubernetes-326CE5?style=for-the-badge&logo=kubernetes&logoColor=white">
</div>
