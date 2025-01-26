application.yml 보시면 환경변수로 설정되어있는 값들이 있습니다.
.env 파일 만들어서 사용하시면 됩니다.

```
DB_USERNAME= 데이터베이스 사용자 이름
DB_PASSWORD= 데이터베이스 비밀번호
KAKAO_CLIENT_ID= 카카오 API 키
```

mysql을 docker로 사용하신다면
```
docker run --name my-mysql -e MYSQL_ROOT_PASSWORD=rootpassword -e MYSQL_DATABASE=mydatabase -e MYSQL_USER=myuser -e MYSQL_PASSWORD=userpassword -p 3306:3306 -d mysql:latest

--name my-mysql: 컨테이너 이름 설정
-e MYSQL_ROOT_PASSWORD=rootpassword: 루트 비밀번호 설정
-e MYSQL_DATABASE=mydatabase: 초기 데이터베이스 이름 설정 (생략가능)
-e MYSQL_USER=myuser: 사용자 이름 설정 (생략가능)
-e MYSQL_PASSWORD=userpassword: 사용자 비밀번호 설정 (생략가능)
```

밑은 사용 예시입니다.
터미널 명령어 (이후로는 docker desktop으로 키셔도 됩니다)
```
docker run --name my-mysql -e MYSQL_ROOT_PASSWORD=password -p 3306:3306 -d mysql:latest
```

env파일
```
DB_USERNAME= root
DB_PASSWORD= password
KAKAO_CLIENT_ID= 카카오 API 키 (발급받아야함)
```

카카오 API 키 발급 방법
https://loosie.tistory.com/302
6번까지만 따라하시면 됩니다. (검수 진행 X)
