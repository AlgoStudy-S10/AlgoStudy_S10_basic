# 장지인 풀이 폴더

## 사용법
1. `thisWeek` 폴더에 이번 주 풀이할 Java 파일을 추가합니다
2. 커밋하고 jji 브랜치에 push하면 자동으로 정리됩니다
3. GitHub Actions가 자동으로:
   - 커밋 날짜 기준으로 월/주차 계산
   - `월/주차/장지인/` 폴더로 복사
   - `weeklyCode/주차/` 폴더로 복사 (파일명에 이름 포함)
   - `thisWeek` 폴더 비우기

## 예시
```
장지인/
└── thisWeek/
    ├── BOJ_1234.java
    └── BOJ_5678.java
```

커밋 후 자동으로:
- `01/week5/장지인/BOJ_1234.java`
- `weeklyCode/week5/BOJ_1234_장지인.java`
