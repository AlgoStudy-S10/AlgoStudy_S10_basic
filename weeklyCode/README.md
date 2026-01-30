# Weekly Code Collection

이 폴더에는 모든 스터디원의 코드가 주차별로 모여있습니다.

## 구조
```
weeklyCode/
├── week1/
│   ├── BOJ_1234_김세민.java
│   ├── BOJ_1234_장지인.java
│   ├── BOJ_1234_이경호.java
│   ├── BOJ_1234_임성혁.java
│   └── ...
├── week2/
└── ...
```

## 특징
- 각 파일명에 스터디원 이름이 포함되어 있어 한눈에 구분 가능
- 같은 문제에 대한 다양한 풀이를 비교하기 쉬움
- 코드 리뷰 시 참고용으로 활용

## 자동 생성
각 브랜치(ksm, jji, ish, lkh)에 커밋하면 GitHub Actions가 자동으로:
1. `workspace/이름/주차/` 폴더의 파일을 복사
2. 파일명에 이름을 추가 (예: `BOJ_1234.java` → `BOJ_1234_김세민.java`)
3. `weeklyCode/주차/` 폴더에 저장
