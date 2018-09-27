package Prog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Program {

	static Connection conn;
	
	public static void main(String[] args) throws SQLException {
		String dbUrl = "jdbc:mysql://localhost:3306/CountryCityPeople?useSSL=false";//������� ���� ���� �����
		String username = "root"; // root
		String password = "123456"; // 123456

		conn = DriverManager.getConnection(dbUrl, username, password);
		System.out.println("Connected? " + !conn.isClosed());

		createTableCountry();
		createTableCity();
		createTablePeople();
//		addStudent();
//		for (int i = 0; i < 30; i++) {
//			addStudents(i);
//		}
//
//		
//		delStudent(4);
//		selectStudent(5);
//
//		selectStudents();
		conn.close();

	}
	
	
	private static void createTableCountry() throws SQLException {
		String dropQuery = "drop table if exists TableCountry;";//��� ��� ��������� ����, ��� ������ ��� ������� ������� ���� ������� ������� ���� ��� ���� ��������

		String query = "CREATE TABLE TableCountry("//��� ��� ��������� ����
				+ "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
				+ "Country VARCHAR(45) NOT NULL,"
//				+ "city VARCHAR(45) NOT NULL,"
				+ "population INT NOT NULL"
				+ ");";
		Statement stmt = conn.createStatement();//��������� ��� ��� ����� �� ������������ ���� ��� ����� � �� ���
		stmt.execute(dropQuery);//��������� ����
		stmt.execute(query);//��������� ����
		System.out.println("Table 'TableCountry' created");
		stmt.close();
	}

	private static void createTableCity() throws SQLException {
		String dropQuery = "drop table if exists TableCity;";//��� ��� ��������� ����, ��� ������ ��� ������� ������� ���� ������� ������� ���� ��� ���� ��������

		String query = "CREATE TABLE TableCity("//��� ��� ��������� ����
				+ "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
				+ "city VARCHAR(45) NOT NULL,"
				+ "population INT NOT NULL"
				+ ");";
		Statement stmt = conn.createStatement();//��������� ��� ��� ����� �� ������������ ���� ��� ����� � �� ���
		stmt.execute(dropQuery);//��������� ����
		stmt.execute(query);//��������� ����
		System.out.println("Table 'TableCity' created");
		stmt.close();
	}
	
	private static void createTablePeople() throws SQLException {
		String dropQuery = "drop table if exists TablePeople;";//��� ��� ��������� ����, ��� ������ ��� ������� ������� ���� ������� ������� ���� ��� ���� ��������

		String query = "CREATE TABLE TablePeople("//��� ��� ��������� ����
				+ "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
				+ "FirstName VARCHAR(45) NOT NULL,"
				+ "LastName VARCHAR(45) NOT NULL,"
				+ "age INT NOT NULL"
				+ "city VARCHAR(45) NOT NULL,"
				+ ");";
		Statement stmt = conn.createStatement();//��������� ��� ��� ����� �� ������������ ���� ��� ����� � �� ���
		stmt.execute(dropQuery);//��������� ����
		stmt.execute(query);//��������� ����
		System.out.println("Table 'TablePeople' created");
		stmt.close();
	}
	
	
	
	private static void addStudent() throws SQLException {//������ ���� � �������
		String query = "insert into student(full_name, city, age) values(?, ?, ?)";

		PreparedStatement pstmt = conn.prepareStatement(query);//��������� ��� �������� � ������� ����
		pstmt.setString(1, "Petro Donchen");
		pstmt.setString(2, "Lviv");
		pstmt.setInt(3, 26);

		pstmt.executeUpdate();
		pstmt.close();
	}

	private static void selectStudents() throws SQLException {
		String query = "select * from student;";

		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.println("id: "+rs.getInt("id") + "\t|"+
					"full name: "+rs.getString("full_name")+"\t|"+
					"City: "+rs.getString("city")+"\t|"+
					"Age: "+rs.getInt("age")
					);
		}	
	}

	private static void addStudents(int i) throws SQLException {//������ ���� � �������
		String query = "insert into student(full_name, city, age) values(?, ?, ?)";

		PreparedStatement pstmt = conn.prepareStatement(query);//��������� ��� �������� � ������� ����
		pstmt.setString(1, "Petro Donchen #" +i);
		pstmt.setString(2, "Lviv #" +i);
		pstmt.setInt(3, 26 +i);

		pstmt.executeUpdate();
		pstmt.close();
	}

	private static void selectStudent(int id) throws SQLException {//������ �� ��
		String query = "select * from student where id = ?;";

		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			System.out.println("id: "+rs.getInt("id") + "\t|"+
					"full name: "+rs.getString("full_name")+"\t|"+
					"City: "+rs.getString("city")+"\t|"+
					"Age: "+rs.getInt("age")
					);
		}
		
	}

	private static void delStudent(int id) throws SQLException {//��������� �� ��
		String query = "delete from student where id = ?;";
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
		
	}

}
