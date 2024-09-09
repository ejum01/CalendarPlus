# CalendarPlus

## 프로젝트 설명

**CalendarPlus**는 사용자가 일정을 관리하고 다른 사용자와 공유할 수 있는 기능을 제공하는 일정 관리 API 애플리케이션입니다.  
이 애플리케이션은 이벤트 생성, 수정, 삭제와 같은 기본적인 캘린더 기능 외에도 일정 공유 및 참석자 관리 기능을 포함합니다.

### 주요 기능

- **일정 관리**: 일정 생성, 수정, 삭제 기능 제공
- **일정 공유**: 다른 사용자에게 일정을 공유하고 초대 메일 전송
- **참석 관리**: 초대받은 사용자는 참석 여부를 '예' 또는 '아니오'로 응답할 수 있으며, 이는 DB에 기록됨

## 기술 스택

- **언어**: Java 17 이상
- **프레임워크**: Spring Boot 3.3.3
- **빌드 도구**: Gradle
- **데이터베이스**: MySQL

---

## 빌드 및 실행 방법

### 사전 요구 사항

- Java 17 이상 설치
- MySQL 데이터베이스 설정

### 빌드 및 실행

1. **저장소 클론**:

    ```bash
    git clone <https://github.com/ejum01/CalendarPlus.git>
    cd CalendarPlus
    ```

2. **종속성 설치**:

    ```bash
    ./gradlew build
    ```

3. **애플리케이션 실행**:

    ```bash
    ./gradlew bootRun
    ```

4. **데이터베이스 연결 설정**:

   `application.properties` 파일에 아래와 같이 DB 연결 정보를 설정합니다.

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/calendarplus
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=create
    ```
```mysql
*필수*
create schema Calendarplus;
use Calendarplus;
```
---

## 주요 컴포넌트 설명

- **EventController**: 일정 관련 CRUD 기능 제공
- **UserController**: 사용자 등록 및 정보 관리
- **ShareController**: 일정 공유 시 초대 이메일 전송 기능을 담당, 공유 받은 이메일 수락 또는 거절 에 따른 상태 변화

---

## API 명세

### 1. 유저 생성

- **URL**: `/api/user/signup`
- **Method**: `POST`
- **Request**:

  ```json
       {
    "username": "생성할 유저 이름",
    "password": "생성할 유저 비밀번호",
    "email": "생성할 유저 이메일"
    }
    ```

- **Response**:
- ```String
  성공 시 : 201 code "성공적으로 유저를 생성했습니다."
  실패 시 : error code "유저를 생성하지 못했습니다."
  중복 시 : 이미 존재하는 사용자입니다.
  ```

### 2. 일정 생성

- **URL**: `/api/calendar/events`
- **Method**: `POST`
    - **Request**:

        ```json
       {
        "start": "2024-09-10T08:00:00",
        "end": "2024-09-10T09:00:00",
        "title": "회의 일정",
        "description": "프로젝트 회의입니다.",
        "location": "중앙 회의실",
            "organizer": {
                "email": "ejum0713@gmail.com",
                "displayName": "ejum"
            }
        }
        ```

- **Response**:
- ```String
  성공 시 : 201 code "성공적으로 이벤트를 생성했습니다."
  실패 시 : error code "이벤트를 생성하지 못했습니다."
  ```

### 3. 일정 조회

- **URL**: `/api/calendar/events/{email}`
- **Method**: `GET`
- **Response**:

    ```json
       {
        "start": "2024-09-10T08:00:00",
        "end": "2024-09-10T09:00:00",
        "title": "회의 일정",
        "description": "프로젝트 회의입니다.",
        "location": "중앙 회의실",
            "organizer": {
                "email": "{email}",
                "displayName": "{username}"
            },
      "eventAttendees" : []
        }
    ```

### 4. 일정 수정

- **URL**: `/api/calendar/events
- **Method**: `patch`
    - **Request**:

        ```json
            {
                "id" :1,
                "title": "과제",
                "description": "과제제출..",
                "location": "중앙회의실"
            }
  
        ```
    - **Response**:

        ```String
        성공 시 이벤트 수정 성공 메세지
        실패 시 이벤트 수정 실패 메세지
        ```

### 5. 일정 삭제

- **URL**: `/api/calendar/events/{event_id}`
- **Method**: `delete`
    - **Response**:

        ```String
        성공 시 이벤트 삭제 성공 메세지
        실패 시 이벤트 삭제 실패 메세지
        ```

### 6. 일정 공유

- **URL**: `/events/{id}/share`
- **Method**: `POST`
- **Request**:

    ```json
    {
        "email": "user1@example.com"
    }
    ```

- **Response**:

    ```json
    {
        "message": "이벤트가 성공적으로 공유되었습니다."
    }
    ```

### 7. 일정 수락

- **URL**: `/events/attend`
- **Method**: `GET`
- **Description**: 사용자가 초대받은 이벤트에 대한 참석 여부를 응답합니다.
- **Request Parameters**:
    - `eventId` (Long): 참석할 이벤트의 ID
    - `email` (String): 참석자의 이메일
    - `response` (String): 'yes' 또는 'no'로 참석 여부 응답
- **Response**:
    - `200 OK` - 참석 응답이 성공적으로 기록된 경우
    - `500 INTERNAL_SERVER_ERROR` - 참석 응답을 기록하는 데 실패한 경우

---
---

## 느낀 점

이번 프로젝트를 진행하면서 가장 크게 느낀 점은 **설계의 중요성**이었습니다. 처음에는 기능 구현에만 집중했지만, 진행할수록 구조와 설계가 얼마나 중요한지 절실히 깨달았습니다. **명확한 요구사항 분석**과 그에 따른 **아키텍처 설계**가 부족하면, 이후 과정에서 많은 수정이 필요하게 되고, 그만큼 비효율적이 될 수 있다는 점을 몸소 체험했습니다.

특히 **일정 관리 기능**을 구현하면서 **예외 처리**에 대한 고민이 많았습니다. 사용자가 실수하거나 예상치 못한 상황이 발생할 때, 어떻게 오류를 처리하고 사용자에게 적절한 메시지를 전달할지에 대한 부분은 **서비스의 신뢰성**에도 큰 영향을 미친다는 것을 느꼈습니다. 예외 상황을 미리 예측하고, 유연하게 처리하는 방법을 고민하는 과정에서 많은 배움을 얻었습니다.

### 이번 프로젝트에서 배운 주요 사항

1. **API 설계의 중요성**  
   API가 명확하고 일관되게 설계되어야 **사용자 경험**이 향상되고, 시스템의 **유지보수**가 용이해진다는 것을 배웠습니다. 초기 설계 단계에서 API 명세를 철저하게 준비하는 것이 중요함을 깨달았습니다.

2. **테스트의 필요성**  
   단순히 코드를 작성하는 것만으로는 부족합니다. **다양한 상황**을 가정하고 그에 맞는 **테스트 케이스**를 작성하는 것이 얼마나 중요한지 이번 프로젝트를 통해 알게 되었습니다. 이러한 과정이 시스템이 예상치 못한 상황에서도 안정적으로 동작할 수 있게 해줍니다.

3. **예외 처리**  
   오류가 발생했을 때 **사용자에게 의미 있는 메시지**를 전달하는 방법과, 시스템 내부에서 오류를 **어떻게 처리할지**에 대해 깊이 고민할 수 있었습니다. 이는 사용자 신뢰성을 높이고, 시스템 안정성을 유지하는 데 중요한 역할을 한다는 것을 배웠습니다.
## 아쉬운 점
1. **시간 관리 부족**
   프로젝트를 진행하는 동안 일정 관리가 충분히 이루어지지 않아, 일부 기능을 구현하는 데 시간이 더 필요했습니다.
2. **코드 최적화 부족**
   시간 관리가 되지 않다 보니까 코드에 최적화를 더 할 수 있었을 거 같은데 더 잘 작성해볼 수 있었을텐데 같은 생각 때문에 아쉬움이 큽니다..


마지막으로, 이번 프로젝트는 저에게 **개발자로서 한 단계 더 성장**할 수 있는 좋은 기회였습니다. 더 나은 코드를 작성하고, 더 **견고한 시스템**을 만드는 방법을 고민할 수 있었고, 앞으로도 이러한 과정을 통해 **지속적으로 성장**하고 싶습니다.

---
