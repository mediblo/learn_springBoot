# tutorial_spring
> Spring 및 백엔드와 프론트엔드의 실력을 올리기 위해 간단한 CRUD 웹을 제작하며, 모르는 부분이 있는 경우를 대비하여  
> Gemini CLI를 사용하여 해당 프로젝트의 구조를 확인 및 1:1 가정교사, 코칭, 페어 프로그래밍 역할을 부여하여  
> 7일에 완료를 목표로 한다.

- **IDE** : IntelliJ 2023.3.3
- **DataBase** : MySQL
- **Backend** : Java 17, Spring Boot, Spring Data JPA, Lombok, Swagger(Springdoc)
- **Frontend** : React, TypeScript, Vite

## 1일차 2026-09-24
- 계획 구성
- ERD 제작
- DB 생성
- Spring Boot + React(Vite) + MySQL 개발 환경 구축
- 민감한 DB 정보를 보호하기 위한 application.properties.example 분리 및 .gitignore 보안 처리

## 2일차 2026-09-28
- 뼈대 생성 [ 계층형 아키텍처화 ]

### 📌 2일차 학습 내용 정리

#### 1. 웹 & 컨트롤러 메서드 매핑 어노테이션
| 어노테이션 | 적용 코드 | 역할 및 의미 |
| :--- | :--- | :--- |
| `@RestController` | `@RestController` | JSON 데이터 기반의 REST API 컨트롤러 선언 (`@Controller` + `@ResponseBody`) |
| `@RequestMapping` | `@RequestMapping("/api/items")` | 컨트롤러 내 모든 API의 공통 기본 URL 경로 매핑 |
| `@PostMapping` | `@PostMapping` | `POST /api/items` 매핑 (신규 자재 등록) |
| `@GetMapping` | `@GetMapping` | `GET /api/items` 매핑 (전체 자재 목록 조회) |
| `@GetMapping("/{id}")` | `@GetMapping("/{id}")` | `GET /api/items/{id}` 매핑 (특정 자재 단건 상세 조회) |
| `@PutMapping("/{id}")` | `@PutMapping("/{id}")` | `PUT /api/items/{id}` 매핑 (특정 자재 정보 전체 덮어쓰기/수정) |
| `@DeleteMapping("/{id}")` | `@DeleteMapping("/{id}")` | `DELETE /api/items/{id}` 매핑 (특정 자재 삭제) |
| `@RequestBody` | `(@RequestBody ItemRequestDto dto)` | 클라이언트가 보낸 HTTP 본문의 JSON 데이터를 자바 DTO 객체로 변환 |
| `@PathVariable` | `(@PathVariable Long id)` | URL 경로의 `{id}` 자리에 들어온 값을 자바 변수로 추출 |
| `ResponseEntity<T>` | `ResponseEntity.ok(response)` | HTTP 상태 코드(`200 OK` 등)와 응답 데이터를 함께 포장 |

#### 2. JPA & 데이터베이스 매핑 어노테이션
| 어노테이션 | 적용 코드 | 역할 및 의미 |
| :--- | :--- | :--- |
| `@Entity` | `@Entity` | DB 테이블과 1:1 매핑되는 JPA 관리 엔티티 클래스 선언 |
| `@Table` | `@Table(name = "item")` | 매핑할 DB 테이블 이름 명시 |
| `@Id` | `@Id` | 엔티티의 기본키(PK) 컬럼 선언 (참조형 `Long` 권장) |
| `@GeneratedValue` | `@GeneratedValue(strategy = IDENTITY)` | MySQL `AUTO_INCREMENT`처럼 DB가 ID를 자동 생성하도록 위임 |
| `@Column` | `@Column(nullable = false, unique = true)` | NOT NULL, UNIQUE 제약조건 및 컬럼명(`name = "alert_quantity"`) 지정 |
| `@PrePersist` | `@PrePersist` | DB에 INSERT 되기 직전에 실행되어 `createdAt`, `updatedAt` 자동 세팅 |
| `@PreUpdate` | `@PreUpdate` | DB에 UPDATE 되기 직전에 실행되어 `updatedAt` 갱신 |

#### 3. 서비스 & 트랜잭션 어노테이션
| 어노테이션 | 적용 코드 | 역할 및 의미 |
| :--- | :--- | :--- |
| `@Service` | `@Service` | 핵심 비즈니스 로직을 처리하는 스프링 빈(Bean) 등록 |
| `@Transactional` | `@Transactional` | 메서드 내 로직을 단일 트랜잭션으로 묶어 성공 시 커밋, 예외 시 자동 롤백 보장 |
| `@Transactional(readOnly = true)` | `@Transactional(readOnly = true)` | 불필요한 변경 감지를 생략하여 조회 성능 최적화 |

#### 4. 롬복 (Lombok) 어노테이션
| 어노테이션 | 적용 코드 | 역할 및 의미 |
| :--- | :--- | :--- |
| `@Getter` | `@Getter` | `private` 필드의 Getter 자동 생성 (Jackson 직렬화 및 Service 값 접근용) |
| `@NoArgsConstructor` | `@NoArgsConstructor` | 기본 생성자 자동 생성 (Jackson 역직렬화 시 빈 객체 생성에 필수) |
| `@NoArgsConstructor(access = PROTECTED)` | `@NoArgsConstructor(access = PROTECTED)` | JPA 프록시용 기본 생성자는 유지하되 외부 빈 객체(`new Item()`) 생성 방지 |
| `@RequiredArgsConstructor` | `@RequiredArgsConstructor` | `final` 필드의 생성자를 자동 생성하여 스프링 의존성 주입(DI) 처리 |

#### 5. 핵심 메서드 문법
* **`JpaRepository<Item, Long>` 상속**: `save()`, `findAll()`, `findById()`, `deleteById()` 등 기본 CRUD 메서드 자동 제공
* **Spring Data JPA 쿼리 메서드 (`existsByName`)**: 인터페이스 선언만으로 `SELECT COUNT(*) > 0` 쿼리 자동 실행
* **`Optional` 예외 처리 (`orElseThrow`)**: `findById(id).orElseThrow(...)` 형태로 데이터가 없을 때 `IllegalArgumentException` 발생
* **Stream 변환 파이프라인 (`map`)**: `items.stream().map(ItemResponseDto::new).toList()`로 `List<Item>` ➔ `List<ItemResponseDto>` 일괄 변환
* **더티 체킹 (Dirty Checking)**: 트랜잭션 내에서 `save()` 호출 없이 엔티티 필드 수정만으로 UPDATE 쿼리 자동 반영