# AWS Secretmanager를 이용하여 환경변수 설정

`DB 아이디, 비밀번호` `secret key` 등 민감한 속성들은 프로젝트 안에 있으면 위험합니다.(다른 사람이 해당 속성을 얻어 악용할 우려)      
`github`에서 `private repository`라도 안전하지 않습니다.(모든 조직원이 시크릿 정보에 접근 가능, 추후 public 전환할 때 이전 커밋 참조)   
그래서 시크릿 속성은 따로 공개되지 않은 안전한 장소에 저장해두어야 합니다.   

## 어디에 저장해둘까?
 
![img.png](image/파일-이동.png)

프로젝트는 `intellij`와 같은 로컬 ide에서 작업되어 `github`에 저장됩니다.   
이후 특정 시점에 `github`의 코드 또는 도커 이미지를 `aws`로 옮깁니다.   
`github actions` `cd`시에 넘겨줄 수도 있고, `aws` 내부에 보관해둘 수 있습니다.

## 저장해둘 곳

1. Github Secrets   
- 안전하지만, github에 종속적입니다. 실제 사용할 곳은 aws인데, github도 정보를 알고 있어야 합니다.
2. AWS SecretManager
- 사용할 장소와 저장할 장소가 일치합니다. 별 이유가 없다면 aws에 저장해두고 사용하는 것이 바람직합니다.

## AWS SecretManager 흐름
![img.png](image/img.png)

## 구현 방법

1. `SecretManager` 의존성 주입
2. 접근할 `Secrets` 설정(spring.config.import), 
3. `Secrets 키-값` ${}로 참조
4. `SecretManager` 접근 권한이 있는 `IAM Key` 발급
   - `IAM Key`에 `SecretsManagerReadWrite` 권한이 부여되어 있어야 합니다.
5. `AWS_ACCESS_KEY_ID`, `AWS_SECRET_ACCESS_KEY` 환경 변수로 설정

## 참조

- [Spring Boot 3.0 integration with AWS Secrets Manager | Spring Cloud AWS - Visa2Learn Youtube](https://www.youtube.com/watch?v=1j028KYS4ps)
- [spring-cloud-aws 문서](https://docs.awspring.io/spring-cloud-aws/docs/3.0.0/reference/html/index.html#spring-cloud-aws-secrets-manager)
- [SpringBoot 에서 AWS Secrets manager를 이용하여 안전하게 데이터베이스를 이용하는 방법 - AWS](https://repost.aws/ko/articles/ARrbXsydIkSAqKLrWhos7GnQ/spring-boot-%EC%97%90%EC%84%9C-aws-secrets-manager%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%98%EC%97%AC-%EC%95%88%EC%A0%84%ED%95%98%EA%B2%8C-%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95)
