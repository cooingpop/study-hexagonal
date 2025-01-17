# 헥사고날 아키텍처 기반 애플리케이션

### Reference Documentation
이 프로젝트는 헥사고날 아키텍처를 기반으로 구현된 뱅킹 애플리케이션입니다.
헥사고날 아키텍처는 비즈니스 로직을 중심에 두고, 외부 의존성을 분리하여 유지보수성과 테스트 용이성을 높이는 설계 방식입니다.

## **헥사고날 아키텍처의 이점**
>- 이 구조를 통해 비즈니스 로직에 집중하면서도 기술적 요구사항의 변화에 유연하게 대응할 수 있는 애플리케이션을 구축할 수 있습니다.

- 관심사의 분리: 각 계층이 명확히 구분되어 책임이 분명함
- 테스트 용이성: 각 컴포넌트를 독립적으로 테스트 가능
- 유지보수성: 핵심 비즈니스 로직이 외부 의존성으로부터 격리되어 변경이 용이
- 유연성: 기술 스택 변경 시 해당 어댑터만 수정하면 됨
- 확장성: 새로운 기능 추가 시 기존 구조를 쉽게 확장 가능
- 도메인 중심 개발: 비즈니스 로직에 집중할 수 있는 환경 제공

## 프로젝트 구조
```

com.example.study_hexagonal
├── domain
│   ├── account
│   │   ├── Account.java
│   │   └── Money.java
│   └── customer
│       ├── Customer.java
│       └── CustomerInfo.java
├── application
│   ├── account
│   │   ├── port
│   │   │   ├── in
│   │   │   │   ├── DepositUseCase.java
│   │   │   │   └── WithdrawUseCase.java
│   │   │   └── out
│   │   │       ├── LoadAccountPort.java
│   │   │       └── SaveAccountPort.java
│   │   └── usecase
│   │       ├── AccountUseCaseImpl.java
│   │       ├── DepositUseCaseImpl.java
│   │       └── WithdrawUseCaseImpl.java
│   └── customer
│       ├── port
│       │   ├── in
│       │   │   ├── CreateCustomerUseCase.java
│       │   │   └── GetCustomerUseCase.java
│       │   └── out
│       │       ├── LoadCustomerPort.java
│       │       └── SaveCustomerPort.java
│       └── usecase
│           ├── CreateCustomerUseCaseImpl.java
│           └── GetCustomerUseCaseImpl.java
└── infrastructure
    ├── adapter
    │   ├── in
    │   │   └── web
    │   │       ├── account
    │   │       │   ├── api
    │   │       │   │   ├── dto
    │   │       │   │   │   ├── AccountRequestDto.java
    │   │       │   │   │   └── AccountResponseDto.java
    │   │       │   │   ├── view
    │   │       │   │   │   └── AccountViewModel.java
    │   │       │   │   └── AccountApiController.java
    │   │       │   └── view
    │   │       │       └── AccountViewController.java
    │   │       ├── customer
    │   │       │   ├── api
    │   │       │   │   ├── dto
    │   │       │   │   │   ├── CustomerRequestDto.java
    │   │       │   │   │   └── CustomerResponseDto.java
    │   │       │   │   ├── view
    │   │       │   │   │   └── CustomerViewModel.java
    │   │       │   │   └── CustomerApiController.java
    │   │       │   └── view
    │   │       │       └── CustomerViewController.java
    │   │       └── interceptor
    │   │           ├── LoggingInterceptor.java
    │   │           └── AuthenticationInterceptor.java
    │   └── out
    │       ├── persistence
    │       │   ├── account
    │       │   │   └── AccountPersistenceAdapter.java
    │       │   └── customer
    │       │       └── CustomerPersistenceAdapter.java
    │       └── cache
    │           └── account
    │               └── AccountCacheAdapter.java
    └── config
        ├── WebConfig.java
        └── PersistenceConfig.java
        
```

## 패키지 설명

### domain
> 핵심 비즈니스 로직과 엔티티를 포함
외부 의존성 없이 순수한 비즈니스 규칙만을 표현
 
### application
> 유스케이스 구현 및 도메인 객체 조작

- port: 외부와의 인터페이스 정의
  - in: 애플리케이션으로 들어오는 요청 인터페이스
  - out: 애플리케이션에서 외부로 나가는 요청 인터페이스
- usecase: 구체적인 유스케이스 구현

### infrastructure
> 외부 시스템과의 통신 및 기술적 세부사항 구현

- adapter: 포트 구현체 및 외부 시스템과의 연동
  - in: 들어오는 요청 처리 (예: 웹 컨트롤러, API)
  - out: 외부로 나가는 요청 처리 (예: 데이터베이스 연동, 캐시)
- config: 애플리케이션 설정 클래스

### 특징
 
- 도메인 중심 설계: 핵심 비즈니스 로직이 도메인 계층에 집중
- 포트와 어댑터 패턴: 외부 시스템과의 통신을 추상화
- 의존성 역전 원칙: 내부 계층이 외부 계층에 의존하지 않음
- 유스케이스 중심: 각 비즈니스 기능이 독립적인 유스케이스로 구현


## 패키지별 의존 관계

### domain 패키지:
> 다른 패키지에 의존하지 않습니다.
순수한 Java 코드만으로 구성됩니다.

### application 패키지:
> domain 패키지에 의존합니다.
port.in과 port.out 인터페이스를 정의합니다.
usecase는 port.in 인터페이스를 구현하고, port.out 인터페이스를 사용합니다.

### infrastructure 패키지:
> domain과 application 패키지에 의존합니다.
adapter.in은 application의 port.in 인터페이스를 사용합니다.
adapter.out은 application의 port.out 인터페이스를 구현합니다.

### 의존성 주입:
> 의존성 주입은 항상 추상화(인터페이스)에 대해 이루어져야 합니다.
구체적인 구현체는 설정(config) 클래스에서 주입합니다.

 


