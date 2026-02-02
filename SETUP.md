# IntelliJ 프로젝트 설정 가이드

프로젝트를 처음 clone한 후 IntelliJ에서 열 때 다음과 같이 설정하세요.

## 1. 프로젝트 열기
```
File > Open > AlgoStudy_S10_basic 폴더 선택
```

## 2. JDK 설정 확인
- **File > Project Structure > Project**
- Project SDK: **11** 선택
- Project language level: **11**

## 3. 모듈 자동 인식
IntelliJ가 자동으로 다음 모듈들을 인식합니다:
- 김세민
- 장지인  
- 이경호
- 임성혁
- weeklyCode

만약 인식하지 못한다면:
- **File > Project Structure > Modules > + > Import Module**
- `workspace/김세민/` 폴더 선택 (다른 스터디원도 동일)

## 4. 실행 확인
- `workspace/김세민/template/Main.java` 열기
- 상단에 실행 버튼(▶️) 활성화 확인
- 실행하여 정상 동작 확인

## 5. 문제 해결
- 빨간 줄이 보이면: **File > Invalidate Caches > Invalidate and Restart**
- JDK 11이 없으면: [Oracle JDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html) 또는 [OpenJDK 11](https://adoptium.net/) 설치
