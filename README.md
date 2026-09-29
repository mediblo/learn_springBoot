# tutorial_spring
> Spring 및 백엔드와 프론트엔드의 실력을 올리기 위해 간단한 CRUD 웹을 제작하며, 모르는 부분이 있는 경우를 대비하여  
> Gemini CLI를 사용하여 해당 프로젝트의 구조를 확인 및 1:1 가정교사, 코칭, 페어 프로그래밍, 리뷰어 등의 역할을 부여하여  
> 7일에 완료를 목표로 한다.

- **IDE** : IntelliJ 2023.3.3
- **DataBase** : MySQL
- **Backend** : Java 17, Spring Boot, Spring Data JPA, Lombok, Swagger(Springdoc)
- **Frontend** : React, TypeScript, Vite

> 2일차, JPA 어노테이션에 대해 조금씩 알아가는중  
> 3일차, stock 테이블 구현, @Transactional로는 race condition을 못 막는다는 것을 알게 됨
> 

## 1일차 2026-09-24
- 계획 구성
- ERD 제작
- DB 생성
- Spring Boot + React(Vite) + MySQL 개발 환경 구축
- 민감한 DB 정보를 보호하기 위한 application.properties.example 분리 및 .gitignore 보안 처리

## 2일차 2026-09-28
- 뼈대 생성 [ 계층형 아키텍처화 ]

## 3일차 2026-09-29
- 입출고 트랜잭션
- 재고 부족 예외 처리 [ 레이스 컨디션은 못 막음 ]

