package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Practice1 {
	static Scanner scan = new Scanner(System.in);
	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;

	static String url = "jdbc:mysql://localhost:3306/firm";
	static String id = "root";
	static String pass = "mysql";

	public static void main(String[] args) {

		try {
			conn = DriverManager.getConnection(url, id, pass);
			stmt = conn.createStatement();
			boolean menu = true;

			while (menu) {
				System.out.println("----------------");
				System.out.println("1. 데이터 삽입");
				System.out.println("2. 데이터 조회");
				System.out.println("3. 데이터 변경");
				System.out.println("4. 데이터 삭제");
				System.out.println("----------------");
				System.out.print("입력 >");
				int menuNum = scan.nextInt();

				switch (menuNum) {
				case 1:
					insertData();
					break;
				case 2:
					viewMenu();
					break;
				case 3:
					changeData();
					break;
				case 4:
					deleteData();
					break;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	static void insertData() {
		System.out.print("사번 >");
		int empno = scan.nextInt();
		System.out.print("이름 >");
		String ename = scan.next();
		System.out.print("직무 >");
		String job = scan.next();
		System.out.print("이거무슨번호냐 >");
		int mgr = scan.nextInt();
		System.out.print("입사일(YYYY-MM-DD) >");
		String hiredate = scan.next();
		System.out.print("급여 >");
		double sal = scan.nextDouble();
		System.out.print("모르는숫자 >");
		double comm = scan.nextDouble();
		System.out.print("부서번호 >");
		int deptno = scan.nextInt();
		String sql = "INSERT INTO emp VALUES ('" + empno + "','" + ename + "','" + job + "','" + mgr + "','" + hiredate
				+ "','" + sal + "','" + comm + "','" + deptno + "')";

		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("잘못된 접근");
			e.printStackTrace();
		}
	}

	static void viewMenu() {
		boolean view = true;
		while(view) {
			System.out.println("----------------");
			System.out.println("1. 전체조회");
			System.out.println("2. 이름검색");
			System.out.println("3. 조회종료");
			System.out.println("----------------");
			System.out.print("선택 >");
			int ans = scan.nextInt();
			switch (ans) {
			case 1:
				viewData();
				break;
			case 2:
				System.out.print("이름입력 >");
				viewName();
				break;
			case 3:
				view = false;
			}
		}
	}
	
	static void viewData() {
		String sql = "SELECT * FROM emp";
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				int mgr = rs.getInt("mgr");
				String hiredate = rs.getString("hiredate");
				double sal = rs.getDouble("sal");
				double comm = rs.getDouble("comm");
				int deptno = rs.getInt("deptno");
				System.out.printf("%d\t %s\t %s\t %d\t %s\t %f\t %f\t %d\n", empno, ename, job, mgr, hiredate, sal,
							comm, deptno);
				}
//			}
		} catch (SQLException e) {
			System.out.println("입력값을 확인하세요.");
		}
	}
	
	
	static void viewName() {
		String ansName = scan.next();
		String sql = String.format("SELECT * FROM emp WHERE ename='%s'", ansName);
		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				if(rs.getString("ename").equals(ansName)) {
					int empno = rs.getInt("empno");
					String ename = rs.getString("ename");
					String job = rs.getString("job");
					int mgr = rs.getInt("mgr");
					String hiredate = rs.getString("hiredate");
					double sal = rs.getDouble("sal");
					double comm = rs.getDouble("comm");
					int deptno = rs.getInt("deptno");
					System.out.println("-----검색 결과-----");
					System.out.printf("%d\t %s\t %s\t %d\t %s\t %f\t %f\t %d\t", empno, ename, job, mgr, hiredate, sal,
							comm, deptno);
				}
				else {
					System.out.println("직원 이름을 확인하세요.");
			} 
				}
		} catch (SQLException e) {
		}
	}

	static void changeData() {
		System.out.println("직원정보 변경");
		System.out.print("사번 >");
		int empno = scan.nextInt();
		System.out.print("바꿀 이름 >");
		String ename = scan.next();
		System.out.print("바꿀 직무 >");
		String job = scan.next();
		System.out.print("바꿀 사수번호 >");
		int mgr = scan.nextInt();
		System.out.print("바꿀 입사일(YYYY-MM-DD) >");
		String hiredate = scan.next();
		System.out.print("바꿀 급여 >");
		double sal = scan.nextDouble();
		System.out.print("바꿀 상여급 >");
		double comm = scan.nextDouble();
		System.out.print("바꿀 부서번호 >");
		int deptno = scan.nextInt();

		String sql = "UPDATE emp SET ename = '" + ename + "', job = '" + job + "', mgr = " + mgr + ", hiredate = '"
				+ hiredate + "', sal = " + sal + ", comm = " + comm + ", deptno = " + deptno + " WHERE empno = "
				+ empno;


		try {
			stmt.executeUpdate(sql);
			System.out.println("변경되었습니다.");
		} catch (SQLException e) {
			System.out.println("정보 변경을 원하는 직원의 사번을 확인하세요");
			e.printStackTrace();
		}
	}

	static void deleteData() {
		System.out.println("직원 정보 삭제");
		System.out.print("삭제할 직원 사번 >");
		int empno = scan.nextInt();
		String sql = "DELETE FROM emp WHERE empno = " + empno;
//		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
			System.out.println("삭제되었습니다.");
		} catch (SQLException e) {
			System.out.println("사번을 확인하세요.");
			e.printStackTrace();
		}

	}

}
