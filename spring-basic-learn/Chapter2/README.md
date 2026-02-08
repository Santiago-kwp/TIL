# 내일배움캠프 - Spring 입문 주차 챕터2

---

## 1. IoC와 DI

> IoC는 **설계 원칙**, DI는 **디자인 패턴**이다.
> Spring은 DI 패턴을 사용하여 IoC 설계 원칙을 구현하고 있다.

### 1.1 의존성(Dependency)이란?

- 코드에서 하나의 객체가 다른 객체를 직접 생성하여 사용하면, 두 객체는 **강하게 결합**된다.
- 강한 결합은 요구사항 변경 시 많은 코드 수정이 불가피하다.

```java
// 강한 결합 예시: Consumer가 Chicken을 직접 생성
public class Consumer {
    void eat() {
        Chicken chicken = new Chicken();
        chicken.eat();
    }
}
```

- **Interface**를 활용하면 **약한 결합**으로 만들 수 있다.

```java
// 약한 결합 예시: Interface 다형성 활용
public class Consumer {
    void eat(Food food) {
        food.eat();
    }

    public static void main(String[] args) {
        Consumer consumer = new Consumer();
        consumer.eat(new Chicken());
        consumer.eat(new Pizza());
    }
}

interface Food {
    void eat();
}

class Chicken implements Food {
    @Override
    public void eat() { System.out.println("치킨을 먹는다."); }
}

class Pizza implements Food {
    @Override
    public void eat() { System.out.println("피자를 먹는다."); }
}
```

### 1.2 주입(Injection) 방법

> 필요로 하는 객체를 외부에서 해당 객체에 전달하는 것

#### 필드를 통한 주입

```java
Consumer consumer = new Consumer();
consumer.food = new Chicken();
consumer.eat();
```

#### Setter 메서드를 통한 주입

```java
Consumer consumer = new Consumer();
consumer.setFood(new Chicken());
consumer.eat();
```

#### 생성자를 통한 주입 (권장)

```java
Consumer consumer = new Consumer(new Chicken());
consumer.eat();
```

- 생성자 주입은 **객체의 불변성**을 지킬 수 있어 일반적으로 권장된다.

### 1.3 제어의 역전(IoC)

- **이전**: Consumer가 직접 Food를 생성 → 제어 흐름: `Consumer → Food`
- **이후**: 외부에서 만들어진 Food를 Consumer에게 전달 → 제어 흐름: `Food → Consumer` (역전)
- 결과적으로 어떤 Food든 코드 변경 없이 대응 가능하다.

| 방향 | 결합도 |
|------|--------|
| Controller → Service → Repository | 강한 결합 |
| Controller ← Service ← Repository | 약한 결합 (IoC 적용) |

---

## 2. IoC Container와 Bean

### 2.1 Spring IoC 컨테이너

- DI를 사용하려면 객체 생성이 먼저 필요하다. Spring이 이 역할을 대신 해준다.
- **Bean**: Spring이 관리하는 객체
- **Spring IoC 컨테이너**: Bean을 모아둔 컨테이너

### 2.2 Bean 등록 방법

#### @Component

- Bean으로 등록할 클래스 위에 선언한다.
- Spring 서버 시작 시 IoC 컨테이너에 Bean으로 저장된다.
- Bean 이름은 클래스명의 **앞글자를 소문자로** 변경한 것이다. (예: `MemoService` → `memoService`)

```java
@Component
public class MemoService { ... }
```

#### @ComponentScan

- Spring 서버 시작 시 지정한 패키지 및 하위 패키지에서 `@Component`가 설정된 클래스를 자동으로 Bean 등록한다.
- `@SpringBootApplication`에 의해 기본(default) 설정되어 있다.

```java
@Configuration
@ComponentScan(basePackages = "com.sparta.memo")
class BeanConfig { ... }
```

### 2.3 Bean 사용 방법 (@Autowired)

#### 필드 주입

```java
@Component
public class MemoService {
    @Autowired
    private MemoRepository memoRepository;
}
```

#### 생성자 주입 (권장)

```java
@Component
public class MemoService {
    private final MemoRepository memoRepository;

    @Autowired
    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }
}
```

- 객체의 불변성을 확보할 수 있어 **생성자 주입이 권장**된다.

#### @Autowired 적용/생략 조건

- **적용 조건**: Spring IoC 컨테이너에 의해 관리되는 클래스에서만 가능하며, Bean 객체만 DI에 사용 가능하다.
- **생략 조건**: Spring 4.3부터, 생성자가 **1개**일 때 `@Autowired` 생략 가능하다.

### 2.4 ApplicationContext

- `BeanFactory`를 상속하여 기능을 확장한 IoC 컨테이너
- Bean을 수동으로 가져올 수 있다.

```java
@Component
public class MemoService {
    private final MemoRepository memoRepository;

    public MemoService(ApplicationContext context) {
        // 방법 1: Bean 이름으로 가져오기
        MemoRepository memoRepository = (MemoRepository) context.getBean("memoRepository");

        // 방법 2: Bean 클래스 형식으로 가져오기
        // MemoRepository memoRepository = context.getBean(MemoRepository.class);

        this.memoRepository = memoRepository;
    }
}
```

---

## 3. 영속성 컨텍스트

> Entity 객체를 효율적으로 관리하기 위해 만들어진 공간

### 3.1 영속성 컨텍스트란?

- JPA는 영속성 컨텍스트에 Entity 객체들을 저장하여 관리하면서 DB와 소통한다.
- 개발자는 직접 SQL을 작성하지 않아도 JPA를 사용하여 DB의 CRUD 작업이 가능하다.

### 3.2 EntityManager와 EntityManagerFactory

- **EntityManager**: Entity를 관리하는 관리자. 저장, 조회, 수정, 삭제를 담당한다.
- **EntityManagerFactory**: EntityManager를 생성하며, 일반적으로 DB 하나에 하나만 생성되어 애플리케이션 동작 중 사용된다.

```java
EntityManagerFactory emf = Persistence.createEntityManagerFactory("memo");
EntityManager em = emf.createEntityManager();
```

- EntityManagerFactory 생성을 위해 `/resources/META-INF/persistence.xml`에 DB 정보를 설정한다.

```xml
<persistence version="2.2" xmlns="http://xmlns.jcp.org/xml/ns/persistence">
  <persistence-unit name="memo">
    <class>com.sparta.entity.Memo</class>
    <properties>
      <property name="jakarta.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
      <property name="jakarta.persistence.jdbc.user" value="root"/>
      <property name="jakarta.persistence.jdbc.password" value="{비밀번호}"/>
      <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/memo"/>
      <property name="hibernate.hbm2ddl.auto" value="create"/>
      <property name="hibernate.show_sql" value="true"/>
      <property name="hibernate.format_sql" value="true"/>
      <property name="hibernate.use_sql_comments" value="true"/>
    </properties>
  </persistence-unit>
</persistence>
```

### 3.3 JPA 트랜잭션

> DB 데이터의 무결성과 정합성을 유지하기 위한 논리적 개념

- 여러 개의 SQL이 하나의 트랜잭션에 포함될 수 있다.
- 모든 SQL 성공 → DB에 영구 반영 (commit)
- SQL 중 하나라도 실패 → 모든 변경 되돌림 (rollback)

#### EntityTransaction 사용

```java
EntityTransaction et = em.getTransaction();
et.begin();     // 트랜잭션 시작

try {
    Memo memo = new Memo();
    memo.setId(1L);
    memo.setUsername("Robbie");
    memo.setContents("영속성 컨텍스트와 트랜잭션 이해하기");

    em.persist(memo);   // 영속성 컨텍스트에 저장
    et.commit();        // DB에 반영
} catch (Exception ex) {
    ex.printStackTrace();
    et.rollback();      // 오류 시 되돌림
} finally {
    em.close();
}
emf.close();
```

- `et.begin()`: 트랜잭션 시작
- `et.commit()`: 트랜잭션 작업을 영구적으로 DB에 반영
- `et.rollback()`: 오류 발생 시 모든 작업을 취소하고 이전 상태로 되돌림

---

## 4. 영속성 컨텍스트의 기능

### 4.1 1차 캐시

- 영속성 컨텍스트 내부의 **캐시 저장소** (Map 자료구조)
  - **key**: `@Id`로 매핑한 식별자 값
  - **value**: Entity 객체

#### Entity 저장

```java
em.persist(memo); // 캐시 저장소에 Entity 저장
```

#### Entity 조회

- 캐시 저장소에 **해당 Id가 없는 경우**: DB에서 SELECT 조회 → 캐시 저장소에 저장 → 반환
- 캐시 저장소에 **해당 Id가 있는 경우**: DB 조회 없이 캐시 저장소에서 바로 반환

```java
Memo memo1 = em.find(Memo.class, 1); // DB에서 조회 후 캐시에 저장
Memo memo2 = em.find(Memo.class, 1); // 캐시에서 바로 반환 (DB 조회 X)
```

- 조회만 하는 경우 데이터 변경이 없으므로 **트랜잭션 없이도 조회 가능**하다.

#### 1차 캐시의 장점

- DB 조회 횟수를 줄인다.
- **객체 동일성 보장**: DB row 1개당 객체 1개 사용을 보장한다.

```java
Memo memo1 = em.find(Memo.class, 1);
Memo memo2 = em.find(Memo.class, 1);
System.out.println(memo1 == memo2); // true (동일 객체)
```

#### Entity 삭제

- 삭제할 Entity를 조회한 후 `em.remove(entity)` 호출
- Entity가 **DELETED** 상태로 전환되고, 트랜잭션 commit 후 DELETE SQL이 DB에 요청된다.

```java
Memo memo = em.find(Memo.class, 2);
em.remove(memo); // DELETED 상태로 전환 → commit 시 DELETE SQL 실행
```

### 4.2 쓰기 지연 저장소 (ActionQueue)

- JPA는 SQL을 즉시 실행하지 않고, **쓰기 지연 저장소**에 모아두었다가 **트랜잭션 commit 시 한번에** DB에 반영한다.

```java
em.persist(memo);   // INSERT SQL → 쓰기 지연 저장소에 저장
em.persist(memo2);  // INSERT SQL → 쓰기 지연 저장소에 저장

System.out.println("트랜잭션 commit 전");
et.commit();        // 이 시점에 INSERT SQL 2개가 한번에 DB에 요청됨
System.out.println("트랜잭션 commit 후");
```

### 4.3 flush()

- 영속성 컨텍스트의 변경 내용(쓰기 지연 저장소의 SQL)을 **DB에 반영**하는 메서드
- 트랜잭션 commit 시 내부적으로 `em.flush()`가 호출된다.
- 직접 호출도 가능하지만, **트랜잭션이 없으면** `TransactionRequiredException` 오류 발생

```java
em.persist(memo);

em.flush();     // 이 시점에 즉시 DB에 SQL 요청
et.commit();    // 이미 flush 되었으므로 추가 SQL 없음
```

### 4.4 변경 감지 (Dirty Checking)

- JPA는 영속성 컨텍스트에 Entity를 저장할 때 **최초 상태(LoadedState)**를 저장한다.
- 트랜잭션 commit 시 `em.flush()`가 호출되면 **현재 상태와 최초 상태를 비교**한다.
- 변경이 있다면 **Update SQL을 자동 생성**하여 DB에 반영한다.
- 별도의 `em.update()` 메서드는 존재하지 않는다.

```java
Memo memo = em.find(Memo.class, 4);   // 조회 (최초 상태 저장)

memo.setUsername("Update");            // 값 변경
memo.setContents("변경 감지 확인");

et.commit(); // flush() → 최초 상태와 비교 → Update SQL 자동 생성 및 실행
```

---

## 5. Entity의 상태

### 5.1 비영속 (Transient)

- `new` 연산자로 인스턴스화 된 Entity 객체
- 영속성 컨텍스트에 저장되지 않아 **JPA가 관리하지 않는** 상태
- 데이터를 변경해도 변경 감지가 이루어지지 않는다.

```java
Memo memo = new Memo(); // 비영속 상태
memo.setId(1L);
memo.setUsername("Robbie");
memo.setContents("비영속과 영속 상태");
```

### 5.2 영속 (Managed)

- `em.persist(entity)`로 영속성 컨텍스트에 저장되어 **JPA가 관리하는** 상태

```java
em.persist(memo); // 비영속 → 영속 상태로 전환
```

### 5.3 준영속 (Detached)

> 영속성 컨텍스트에서 관리되다가 분리된 상태. JPA의 어떤 기능도 사용할 수 없다.

#### detach(entity) — 특정 Entity만 분리

```java
em.detach(memo); // 특정 Entity를 준영속 상태로 전환

memo.setUsername("Update"); // 변경 감지 불가 → Update SQL 수행되지 않음
System.out.println(em.contains(memo)); // false
```

#### clear() — 영속성 컨텍스트 초기화

- 모든 Entity를 준영속 상태로 전환하지만, **영속성 컨텍스트 틀은 유지**되어 계속 사용 가능하다.

```java
em.clear(); // 영속성 컨텍스트 완전 초기화
Memo memo = em.find(Memo.class, 1); // 다시 조회하여 영속 상태로 만들 수 있음
```

#### close() — 영속성 컨텍스트 종료

- 모든 Entity를 준영속 상태로 전환하고, **영속성 컨텍스트 자체를 종료**한다.
- 종료 후 EntityManager 사용 시 `Session/EntityManager is closed` 오류 발생

```java
em.close(); // 영속성 컨텍스트 종료
em.find(Memo.class, 2); // 오류 발생!
```

#### merge(entity) — 준영속/비영속 → 영속 상태로 전환

- 전달받은 Entity를 사용하여 **새로운 영속 상태의 Entity를 반환**한다.

**동작 과정:**

1. 파라미터 Entity의 식별자 값으로 영속성 컨텍스트 조회
2. 없으면 → DB에서 조회
   - DB에 있으면 → 영속성 컨텍스트에 저장 → 전달받은 Entity 값으로 병합 → **Update SQL** (수정)
   - DB에도 없으면 → 새로 생성하여 영속성 컨텍스트에 저장 → **Insert SQL** (저장)

```java
// merge를 통한 저장
Memo memo = new Memo();
memo.setId(3L);
memo.setUsername("merge()");
Memo mergedMemo = em.merge(memo); // DB에 없으면 Insert

System.out.println(em.contains(memo));       // false (원본은 비영속 그대로)
System.out.println(em.contains(mergedMemo)); // true  (새로 생성된 영속 객체)
```

```java
// merge를 통한 수정
Memo memo = em.find(Memo.class, 3);
em.detach(memo);                  // 준영속 상태로 전환
memo.setContents("merge() 수정"); // 값 변경

Memo mergedMemo = em.merge(memo); // DB에서 조회 후 병합 → Update SQL
```

### 5.4 삭제 (Removed)

```java
em.remove(memo); // 영속 상태의 Entity를 삭제 상태로 전환 → commit 시 DELETE SQL 실행
```

---

## 6. SpringBoot의 JPA

### 6.1 JPA 설정

#### build.gradle

```groovy
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
```

#### application.properties

```properties
spring.jpa.hibernate.ddl-auto=update

spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
```

**ddl-auto 옵션:**

| 옵션 | 설명 |
|------|------|
| `create` | 기존 테이블 삭제 후 다시 생성 (DROP + CREATE) |
| `create-drop` | create와 같으나 종료 시점에 테이블 DROP |
| `update` | 변경된 부분만 반영 |
| `validate` | Entity와 테이블이 정상 매핑되었는지만 확인 |
| `none` | 아무것도 하지 않음 |

#### Memo Entity

```java
@Entity
@Getter @Setter
@Table(name = "memo")
@NoArgsConstructor
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "contents", nullable = false, length = 500)
    private String contents;

    public Memo(MemoRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }

    public void update(MemoRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}
```

#### EntityManager 주입

- SpringBoot 환경에서는 `EntityManagerFactory`와 `EntityManager`를 **자동으로 생성**해준다.

```java
@PersistenceContext
EntityManager em;
```

### 6.2 @Transactional

- 클래스나 메서드에 추가하면 해당 범위 내의 모든 DB 연산이 **하나의 트랜잭션**으로 묶인다.
- 정상 수행 → commit / 예외 발생 → rollback
- JPA를 사용하여 DB에 **저장, 수정, 삭제**하려면 트랜잭션 적용이 **필수**
- 조회만 하는 메서드에는 `@Transactional(readOnly = true)` 적용 권장

```java
@Transactional(readOnly = true)
public class SimpleJpaRepository<T, ID> implements JpaRepositoryImplementation<T, ID> {

    @Transactional // readOnly = false로 덮어씀
    @Override
    public <S extends T> S save(S entity) {
        if (entityInformation.isNew(entity)) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }
    }
}
```

> 테스트 코드에서 `@Transactional`을 사용하면 테스트 완료 후 자동 롤백된다.
> DB에 반영하려면 `@Rollback(value = false)`를 추가해야 한다.

### 6.3 영속성 컨텍스트와 트랜잭션의 생명주기

- 스프링 컨테이너 환경에서는 **영속성 컨텍스트와 트랜잭션의 생명주기가 일치**한다.
- 트랜잭션이 유지되는 동안 영속성 컨텍스트도 유지되며, 영속성 컨텍스트의 기능을 사용할 수 있다.

### 6.4 트랜잭션 전파

- `@Transactional`의 기본 전파 옵션은 **REQUIRED**
- **REQUIRED**: 부모 메서드에 트랜잭션이 존재하면, 자식 메서드의 트랜잭션은 부모에 합류한다.
  - 자식 메서드가 종료되어도 즉시 commit되지 않고, **부모 메서드 종료 시** 트랜잭션이 commit된다.
  - 부모 트랜잭션이 없으면 자식 메서드가 자체적으로 트랜잭션을 생성한다.

---

## 7. Spring Data JPA

### 7.1 Spring Data JPA란?

- JPA를 쉽게 사용할 수 있게 만들어놓은 **모듈**
- JPA를 추상화시킨 **Repository 인터페이스**를 제공한다.
- `JpaRepository` 인터페이스를 상속받으면, Spring이 자동으로 **SimpleJpaRepository** 클래스를 생성하고 Bean으로 등록한다.
- 구현 클래스를 직접 작성하지 않아도 JPA 기능을 사용할 수 있다.

### 7.2 사용 방법

```java
public interface MemoRepository extends JpaRepository<Memo, Long> {
    // JpaRepository<Entity 클래스, @Id의 데이터 타입>
}
```

- Spring Data JPA에 의해 **자동으로 Bean 등록**된다.
- `Memo` Entity를 추가했으므로 DB의 `memo` 테이블과 연결되어 CRUD 작업을 처리한다.

---

## 8. JPA Auditing

> 데이터의 생성/수정 시간을 자동으로 관리하는 기능

### 8.1 Timestamped 추상 클래스

```java
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Timestamped {

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt;
}
```

### 8.2 주요 애너테이션

| 애너테이션 | 설명 |
|-----------|------|
| `@MappedSuperclass` | 상속받는 Entity 클래스에서 부모의 필드를 컬럼으로 인식하게 함 |
| `@EntityListeners(AuditingEntityListener.class)` | 해당 클래스에 Auditing 기능 포함 |
| `@CreatedDate` | Entity 생성 시 시간 자동 저장 (`updatable = false` 권장) |
| `@LastModifiedDate` | Entity 변경 시 변경 시간 자동 저장 |
| `@Temporal` | 날짜 타입 매핑 (DATE / TIME / TIMESTAMP) |

### 8.3 적용 방법

1. `@SpringBootApplication`이 있는 클래스에 **`@EnableJpaAuditing`** 추가
2. Entity 클래스에서 `Timestamped`를 **상속**
