# Git Hooks 설정

## 설치 방법
프로젝트를 clone한 후, 다음 명령어를 실행하세요:

```bash
git config core.hooksPath .githooks
```

## pre-commit Hook 동작 방식

1. `thisWeek/template/Main.java`에서 문제를 풉니다
2. 완성된 코드를 `thisWeek/BOJ_1234.java` 형식으로 복사합니다
3. `git add`와 `git commit` 실행
4. **자동으로** `template/Main.java`가 초기 상태로 리셋됩니다

## 주의사항
- template 폴더 밖의 `.java` 파일을 커밋할 때만 템플릿이 초기화됩니다
- 각 팀원은 clone 후 반드시 hook 설정 명령어를 실행해야 합니다
