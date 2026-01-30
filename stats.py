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
        lines = f.readlines()

    with open(readme_path, "w", encoding="utf-8") as f:
        is_inside_table = False
        found_marker = False
        
        for line in lines:
            if "" in line:
                f.write(line)
                f.write(table_header)
                f.write(table_divider)
                f.write(table_content)
                is_inside_table = True
                found_marker = True
            elif "" in line:
                is_inside_table = False
                f.write(line)
            elif not is_inside_table:
                f.write(line)
        
        # 만약 주석(Marker)이 없으면 파일 맨 끝에 표를 추가함 (안전장치)
        if not found_marker:
            f.write("\n\n## 📊 주차별 풀이 현황\n")
            f.write("\n")
            f.write(table_header)
            f.write(table_divider)
            f.write(table_content)
            f.write("\n")

if __name__ == "__main__":
    update_readme()
