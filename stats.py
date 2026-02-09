import os
import datetime

# 스터디원 이름 (폴더명과 정확히 일치해야 함)
MEMBERS = ["장지인", "김세민", "이경호", "임성혁", "이정헌"]

def update_readme():
    # 1. 표 헤더 만들기
    table_header = "| 월 | 주차 | " + " | ".join(MEMBERS) + " |\n"
    table_divider = "| :---: | :---: | " + " | ".join([":---:" for _ in MEMBERS]) + " |\n"
    table_content = ""

    # 2. 폴더 탐색 (workspace/이름/월/주차 구조)
    workspace_path = "workspace"
    
    if not os.path.exists(workspace_path):
        return
    
    # 모든 월/주차 조합 수집
    week_data = {}
    
    for member in MEMBERS:
        member_workspace = os.path.join(workspace_path, member)
        if not os.path.exists(member_workspace):
            continue
        
        # 월 폴더 찾기 (숫자로 된 폴더만)
        months = [d for d in os.listdir(member_workspace) if os.path.isdir(os.path.join(member_workspace, d)) and d.isdigit()]
        
        for month in months:
            month_path = os.path.join(member_workspace, month)
            # week 폴더 찾기
            weeks = [d for d in os.listdir(month_path) if os.path.isdir(os.path.join(month_path, d)) and d.lower().startswith("week")]
            
            for week in weeks:
                week_key = (month, week)
                if week_key not in week_data:
                    week_data[week_key] = {}
                
                week_path = os.path.join(month_path, week)
                # .md 파일 개수 세기
                md_files = [f for f in os.listdir(week_path) if f.endswith(".md")]
                week_data[week_key][member] = len(md_files)
    
    # 월/주차별로 정렬하여 테이블 생성
    for (month, week) in sorted(week_data.keys()):
        row = f"| {month}월 | {week} |"
        
        for member in MEMBERS:
            solved_count = week_data[(month, week)].get(member, 0)
            
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
