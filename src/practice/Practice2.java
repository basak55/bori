package practice;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Practice2 extends JFrame {
	//GUI필드
	JButton jb1 = new JButton("전체조회");
	JButton jb2 = new JButton("사번검색");
	JButton jb3 = new JButton("직원추가");
	JButton jb4 = new JButton("변경");
	JButton jb5 = new JButton("삭제");
	
	JLabel lb1 = new JLabel("사번");
	JTextField tf1 = new JTextField(6);
	JLabel lb2 = new JLabel("직원명");
	JTextField tf2 = new JTextField(6);
	JLabel lb3 = new JLabel("부서");
	JTextField tf3 = new JTextField(6);
	JLabel lb4 = new JLabel("사수사번");
	JTextField tf4 = new JTextField(6);
	JLabel lb5 = new JLabel("입사일");
	JTextField tf5 = new JTextField(6);
	JLabel lb6 = new JLabel("급여");
	JTextField tf6 = new JTextField(6);
	JLabel lb7 = new JLabel("인센티브");
	JTextField tf7 = new JTextField(6);
	JLabel lb8 = new JLabel("부서번호");
	JTextField tf8 = new JTextField(6);
	
	JTextArea ta = new JTextArea(10, 65);
	
	//jdbc필드
	Connection conn;
	Statement stmt;

	
	public Practice2() {
		//JDBC
		String url = "jdbc:mysql://localhost:3306/firm";
		String id = "root";
		String pass = "mysql";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,id,pass);
			stmt = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		//GUI구현
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(500, 400);
		this.setSize(1000, 300);
		this.setTitle("EMP");
		
		Container con = this.getContentPane();
		con.setLayout(new BorderLayout());
		
		//버튼 (판넬>컨텐트팬>윈도우)
		JPanel jp1 = new JPanel(new FlowLayout());
		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jb3);
		jp1.add(jb4);
		jp1.add(jb5);
		con.add(jp1, BorderLayout.SOUTH);
		
		//입력필드 (판넬>컨텐트팬>윈도우)
		JPanel jp2 = new JPanel(new FlowLayout());
		jp2.add(lb1); jp2.add(tf1);
		jp2.add(lb2); jp2.add(tf2);
		jp2.add(lb3); jp2.add(tf3);
		jp2.add(lb4); jp2.add(tf4);
		jp2.add(lb5); jp2.add(tf5);
		jp2.add(lb6); jp2.add(tf6);
		jp2.add(lb7); jp2.add(tf7);
		jp2.add(lb8); jp2.add(tf8);
		con.add(jp2,BorderLayout.NORTH);
		
		//출력화면 스크롤팬 (판넬>컨텐트팬>윈도우)
		JPanel jp3 =  new JPanel(new FlowLayout());
		JScrollPane scroll = new JScrollPane(ta);
		jp3.add(scroll);
		con.add(jp3, BorderLayout.CENTER);
		
		//이벤트
		jb1.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				viewData();
			}
		});
		
		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewDataName();
			}
		});
		
		jb3.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertData();
			}
		});
		
		jb4.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				updateData();
			}
		});
		
		jb5.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteData();
			}
		});
		
	}
	
	public void clearTextField() { 
		//입력창 초기화
		tf1.setText(""); tf2.setText(""); tf3.setText("");
		tf4.setText(""); tf5.setText(""); tf6.setText("");
		tf7.setText(""); tf8.setText(""); 
	}

	public void viewData() { 
		//전체조회
		String sql = "SELECT * FROM emp";
		try {
			clearTextField();
			ta.setText("");
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				int mgr = rs.getInt("mgr");
				String hiredate = rs.getString("hiredate");
				double sal = rs.getDouble("sal");
				double comm = rs.getDouble("comm");
				int deptno = rs.getInt("deptno");
				
				String print = String.format("%d\t %s\t %s\t %d\t %s\t %f\t %f\t %d\n",
						empno, ename, job, mgr, hiredate, sal, comm, deptno);
				ta.append(print);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void viewDataName() {
		//이름으로 조회
		String sql = String.format("SELECT * FROM emp WHERE empno = '%s'",tf1.getText());
		try {
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				int mgr = rs.getInt("mgr");
				String hiredate = rs.getString("hiredate");
				double sal = rs.getDouble("sal");
				double comm = rs.getDouble("comm");
				int deptno = rs.getInt("deptno");
				
				String print = String.format("%d\t %s\t %s\t %d\t %s\t %f\t %f\t %d\n",
						empno, ename, job, mgr, hiredate, sal, comm, deptno);
				ta.setText("");
				ta.append(print);
				
				tf1.setText(empno+"");
				tf2.setText(ename);
				tf3.setText(job);
				tf4.setText(mgr+"");
				tf5.setText(hiredate);
				tf6.setText(sal+"");
				tf7.setText(comm+"");
				tf8.setText(deptno+"");
				
			} else {
				clearTextField();
				ta.append("존재하지 않는 이름입니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertData() {
		String sql = String.format("INSERT INTO emp VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
					tf1.getText(),tf2.getText(),tf3.getText(),tf4.getText(),tf5.getText(),tf6.getText(),tf7.getText(),tf8.getText());
		try {
			stmt.executeUpdate(sql);
			clearTextField();
			ta.setText("추가가 완료되었습니다. 직원 정보를 조회하여 확인하세요.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateData() {
		String sql = String.format("UPDATE emp SET empno='%s', ename='%s', job='%s', mgr='%s', hiredate='%s'"
				+ ", sal='%s', comm='%s', deptno='%s' WHERE empno = '%s'", tf1.getText(),tf2.getText(),tf3.getText(),tf4.getText(),
				tf5.getText(),tf6.getText(),tf7.getText(),tf8.getText(), tf1.getText());
		try {
			stmt.executeUpdate(sql);
			clearTextField();
			ta.setText("수정이 완료되었습니다. 직원 정보를 조회하여 확인하세요.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteData() {
		String sql = String.format("DELETE FROM emp WHERE empno = ('%s')", 
				tf1.getText());
		try {
			stmt.executeUpdate(sql);
			clearTextField();
			ta.setText("삭제가 완료되었습니다. 직원 정보를 조회하여 확인하세요.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Practice2();

	}
	

}
