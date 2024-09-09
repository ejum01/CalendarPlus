# Updated version of README.md with markdown syntax for API documentation and removal of SQL schema

final_readme_content = """

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
- **프레임워크**: Spring Boot 3.x
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

# Writing the improved final README.md content back to the file

with open(readme_path, 'w') as file:
file.write(final_readme_content)

final_readme_content # Displaying the updated README.md content