
<img 
src="https://capsule-render.vercel.app/api?type=wave&color=auto&reversal=true&height=300&section=header&text=PrivateStay&desc=Be01-2nd-2Team&textBg=true&fontSize=90&fontColor=ffee00&animation=fadeIn"
/>

# 🏡 PrivateStay
Be01 - 2nd - 2Team

## 📖 프로젝트 소개
'Private Stay'는 개인 숙박시설 예약을 위한 프로그램입니다. 이 프로그램은 예약 과정을 자동화하여 간소화하고, 
전용 숙박 예약을 통해 프라이버시와 프리미엄 경험을 제공하며, 지역 숙박 사업과 협력을 통해 COVID-19 이후 지역 경제를 활성화하는 것을 목표로 합니다


## 🪪 팀원 및 역할 분담
- 정수민 : <strong>팀장</strong>, User 테이블 담당, Reservation 구현
- 김정민 : Reservation 파트 담당,
- 윤채영 : Product, Stock 파트 담당, Reservation 구현
- 임성현 : Business, Company 테이블 담당, 

## ✔️ 프로젝트 주요 기능
1) 사용자 관리
  	- USER(손님) 계정 / BUSINESS(사업자) 계정 및 관리자와 같은 다양한 사용자 역할 및 액션이 포함되어 있음
2) 사업장 관리
	  - BUSINESS(사업자)는 사업장 관리를 통해 새 사업장을 추가할 수 있으며 특정 사업장을 선택해 관리할 수 있음
3) 숙박 예약
	  - USER(손님) 예약
	  - USER는 이용 가능한 상품(숙소)을 찾고 세부 정보를 확인하여 선호에 따라 예약할 수 있음
4) 상품 관리
	  - BUSINESS(사업자)는 상품(숙소) 추가, 상품 정보 및 상품 예약 현황을 업데이트 할 수 있음
5) 예약 확인
	  - USER는 본인의 예약 목록과 현황을 확인할 수 있음
6) 프로필 관리
	  - USER(손님) 계정 / BUSINESS(사업자) 계정에서 본인의 프로필을 확인 및 수정할 수 있음
7) 메이븐을 이용한 빌드/배포


## 💻 개발 환경 및 API
| Frontend |
|:---------------------:|
| ![HTML5](https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white) ![CSS3](https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white) ![JavaScript](https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black) ![Bootstrap](https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white) ![Thymeleaf](https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white) |

| Backend |
|:--------------------:|
| ![MariaDB](https://img.shields.io/badge/mariaDB-003545?style=for-the-badge&logo=mariaDB&logoColor=white) ![Java](https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white) ![Spring](https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white) ![Spring Boot](https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white) ![STS](https://img.shields.io/badge/STS-6DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![Apache Maven](https://img.shields.io/badge/apachemaven-C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white) ![Lombok](https://img.shields.io/badge/lombok-C70D2C.svg?style=for-the-badge&logo=lombok&logoColor=white) ![REST API](https://img.shields.io/badge/RESTapi-1997B5?style=for-the-badge&logo=java&logoColor=white) |


| Collaboration Tools |
|:-------------------:|
| ![Git](https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white) ![GitHub](https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white)  ![Slack](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white) |



## 📒 요구사항 명세서 
|1. 시스템 사용자||
|:--:|:--:|
|개인회원과 사업자가 다른 화면 사용|회원가입 및 로그인 기능|
|개인 정보 관리|숙박시설 검색 및 예약 기능|
|예약 정보 관리 (확정, 취소)|내 예약 현황 조회|
|사업자 회원가입 및 로그인 기능|사업체 정보 등록 및 관리|
|숙소(상품) 등록 및 관리|재고 관리 시스템 연동|
|예약 현황 조회 및 관리|캘린더 기능 사용|

|2. 숙박시설 관리|비고|
|:--:|:--:|
|숙소 정보 등록 및 수정|(이름, 가격, 위치, 설명 등)|
|예약 가능 날짜 및 가격 설정|(사업자가 설정 및 수정 가능)|
|재고 관리 시스템 연동|(예약 가능 여부 관리(볼드체))|
|예약 현황 대시보드|(캘린더 기능 사용)|

|3. 예약 및 결제 시스템|비고|
|:--:|:--:|
|예약 프로세스 관리|(검색, 선택, 예약, 취소)|
|예약 변경 및 취소 정책 설정|(CRUD 기능 사용)|

## 🛠️ 기능 명세서
참조: [기능명세서](https://github.com/beyond-sw-camp/be01-2nd-2Team-PrivateStay/blob/main/Documents/2_PrivateStay_%EA%B8%B0%EB%8A%A5%EB%AA%85%EC%84%B8%EC%84%9C.xlsx)

## 💾 DB 설계
참조: [설계 문서 및 DataBase 설계 스크립트 파일(DDL)
](https://github.com/beyond-sw-camp/be01-2nd-2Team-PrivateStay/blob/main/Documents/3_PrivateStay_%EC%84%A4%EA%B3%84%20%EB%AC%B8%EC%84%9C%20%EB%B0%8F%20DataBase%20%EC%84%A4%EA%B3%84%20%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8%20%ED%8C%8C%EC%9D%BC(DDL).pdf)

## 📐 계층 구조 설계



# 회고
