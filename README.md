# 🚀 Algorithm Study (SSAFY 15기 서울 10반)

> **매주 금요일 점심**, 오프라인 코드 리뷰를 통해 함께 성장하는 알고리즘 스터디입니다.  
> 서로의 코드를 깊이 있게 뜯어보며 '더 나은 풀이'를 고민합니다.

<br>

## 📊 주차별 풀이 현황

<!-- STATS_TABLE_START -->
| 월 | 주차 | 장지인 | 김세민 | 이경호 | 임성혁 |
| :---: | :---: | :---: | :---: | :---: | :---: |
<!-- STATS_TABLE_END -->

<br>

## 📅 운영 방식

- **일시**: 매주 금요일 점심시간 (오프라인)
- **방식**: 각자 정해진 문제를 풀고 자동으로 정리된 후 코드 리뷰합니다.
- **분량**: 매주 정해진 알고리즘 테마의 **골드 4~5 수준** 문제 2개

<br>

## 🚀 사용 방법 (자동화)

### 1. 브랜치별 작업
- **김세민**: `ksm` 브랜치
- **장지인**: `jji` 브랜치
- **이경호**: `lkh` 브랜치
- **임성혁**: `ish` 브랜치

### 2. 파일 추가
```bash
# 각자의 브랜치로 체크아웃
git checkout ksm  # 또는 jji, lkh, ish

# workspace/이름/thisWeek/ 폴더 안에 Java 파일 작성
# 예: workspace/김세민/thisWeek/BOJ_1234.java 파일 생성 및 코드 작성

# 파일 추가 및 커밋
git add workspace/김세민/thisWeek/
git commit -m "[BOJ] 1234 문제명 - 김세민"
git push origin ksm
```

### 3. 자동 처리
GitHub Actions가 자동으로:
- ✅ `workspace/이름/월/주차/` 폴더로 복사 (예: `workspace/김세민/01/week5/`)
- ✅ `weeklyCode/주차/` 폴더로 복사 (파일명에 이름 포함)
- ✅ `thisWeek` 폴더 비우기
- ✅ main 브랜치에 자동 merge

### 4. 코드 리뷰
- `weeklyCode/week5/` 폴더에서 모든 스터디원의 코드를 한눈에 확인
- 같은 문제에 대한 다양한 풀이 비교

<br>

## 📂 폴더 구조

```
AlgoStudy_S10_basic/
├── workspace/
│   ├── 김세민/
│   │   ├── thisWeek/          # 여기에 이번 주 풀이 추가
│   │   ├── 01/
│   │   │   └── week5/         # 자동 정리된 파일
│   │   └── 02/
│   │       └── week1/
│   ├── 장지인/
│   ├── 이경호/
│   └── 임성혁/
└── weeklyCode/                # 모든 스터디원 코드 모음
    ├── week1/
    │   ├── BOJ_1234_김세민.java
    │   ├── BOJ_1234_장지인.java
    │   └── ...
    └── week5/
```

<br>

## 💬 커밋 컨벤션

### Commit Message
> `[platform] 문제번호 문제명 - 이름`

**예시:**
- `[BOJ] 1158 요세푸스 문제 - 김세민`
- `[PGS] 43162 네트워크 - 장지인`

<br>

## 🛠️ 스터디 멤버

- 장지인 (jji)
- 김세민 (ksm)
- 이경호 (lkh)
- 임성혁 (ish)
