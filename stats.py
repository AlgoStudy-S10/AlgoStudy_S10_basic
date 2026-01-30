import os
import datetime

# 스터디원 이름 (폴더명과 정확히 일치해야 함)
MEMBERS = ["장지인", "김세민", "이경호", "임성혁"]

def update_readme():
    # 1. 표 헤더 만들기
    table_header = "| 월 | 주차 | " + " | ".join(MEMBERS) + " |\n"
    table_divider = "| :---: | :---: | " + " | ".join([":---:" for _ in MEMBERS]) + " |\n"
    table_content = ""

    # 2. 폴더 탐색 (월/주차/이름 구조)
    # 현재 위치의 모든 폴더 중 숫자로 된 폴더(예: 02, 03)만 찾음
    months = sorted([d for d in os.listdir('.') if os.path.isdir(d) and d.isdigit()])

    for month in months:
        # 해당 월 폴더 안에서 'week'로 시작하는 폴더 찾기
        month_path = os.path.join(month)
        weeks = sorted([d for d in os.listdir(month_path) if os.path.isdir(os.path.join(month_path, d)) and d.lower().startswith("week")])
        
        for week in weeks:
            row = f"| {month}월 | {week} |"
            
            for member in MEMBERS:
                member_path = os.path.join(month, week, member)
                solved_count = 0
                
                # 멤버 폴더가 있고, 그 안에 .java 파일이 몇 개인지 확인
                if os.path.exists(member_path):
                    files = [f for f in os.listdir(member_path) if f.endswith(".java")]
                    solved_count = len(files)
                
                # 이모지로 상태 표시 (2문제 이상=💯, 1문제=✅, 0문제=➖)
                if solved_count >= 2:
                    row += f" 💯 ({solved_count}) |"
                elif solved_count > 0:
                    row += f" ✅ ({solved_count}) |"
                else:
                    row += " ➖ |"
            
            table_content += row + "\n"

    # 3. README.md 업데이트
    readme_path = "README.md"
    
    # README가 없으면 생성
    if not os.path.exists(readme_path):
        with open(readme_path, "w", encoding="utf-8") as f:
            f.write("# Algorithm Study\n\n")

    with open(readme_path, "r", encoding="utf-8") as f:
        content = f.read()

    # HTML 주석 마커 사이의 내용을 새 테이블로 교체
    start_marker = "<!-- STATS_TABLE_START -->"
    end_marker = "<!-- STATS_TABLE_END -->"
    
    if start_marker in content and end_marker in content:
        # 마커 사이의 내용을 교체
        start_idx = content.find(start_marker) + len(start_marker)
        end_idx = content.find(end_marker)
        
        new_content = (
            content[:start_idx] + "\n" +
            table_header +
            table_divider +
            table_content +
            content[end_idx:]
        )
        
        with open(readme_path, "w", encoding="utf-8") as f:
            f.write(new_content)
    else:
        # 마커가 없으면 파일 맨 끝에 추가
        with open(readme_path, "a", encoding="utf-8") as f:
            f.write("\n\n## 📊 주차별 풀이 현황\n\n")
            f.write(start_marker + "\n")
            f.write(table_header)
            f.write(table_divider)
            f.write(table_content)
            f.write(end_marker + "\n")

if __name__ == "__main__":
    update_readme()
