<h1>:clap: JDBC_CLI</h1>

모든 입력은 Enter로 함  

0. 필드 소개  
    * 사번 : (int) 4자리  
    * 직원이름 : (String)  
    * 직무 : (String)  
    * 사수사번 : (int) 4자리  
    * 입사일 : (String) (YYYY-MM-DD)  
    * 연봉 : (int)   
    * 부서번호 : (int) 2자리  
1. 데이터 삽입  
    -  1번 > 각 필드 입력 > 완료  
2. 데이터 조회  
    -  전체 조회  
        + 2번 > 1번  
        + 2번 > 2번 > 직원 이름 입력 (입력 오류시 조회 메뉴로 돌아감)  
3. 데이터 변경  
 **사번을 기준으로** 변경함  
    - 3번 > 변경할 **사번**입력 > 각 필드 입력 > 완료  
4. 데이터 삭제  
 **사번을 기준으로** 삭제함
    - 4번 > 삭제할 **사번** 입력
  

# :clap:JDBC_GUI  
0. 필드 소개  
    * 사번 : (int) 4자리  
    * 직원이름 : (String)  
    * 직무 : (String)  
    * 사수사번 : (int) 4자리  
    * 입사일 : (String) (YYYY-MM-DD)  
    * 연봉 : (int)   
    * 부서번호 : (int) 2자리  
1. 전체 조회  
    * 모든 데이터가 출력됨  
2. 사번 검색  
    * 사번 필드(첫번째)에 사번 입력 > '사번검색' 버튼 클릭  
3. 직원 추가
    * '전체 조회' 버튼으로 각 필드를 초기화 > 새로 추가할 직원의 정보값을 각 필드에 입력 > '직원추가' 버튼 클릭  
4. 직원 정보 변경  
    * **사번 검색**후 각 필드에 입력된 기존 값을 수정 > '변경' 버튼 클릭  
5. 직원 정보 삭제  
    * **사번 검색**후 검색된 직원 내용 확인 > '삭제' 버튼 클릭  
