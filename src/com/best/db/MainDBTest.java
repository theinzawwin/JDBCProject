package com.best.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainDBTest {

	public static void main(String[] args) {
		saveStudentPrepare("Zaw Zaw", "roll-5", "0934343444");
		show();
	}
	public static void saveStudent(String name,String rollno,String phoneno) {
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_bestdb", "root", "root");
			Statement stmt=con.createStatement();
			stmt.executeUpdate("insert into student(name,rollno,phoneno) values('"+name+"','"+rollno+"','"+phoneno+"')");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static void show() {
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_bestdb", "root", "root");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from student");
			while(rs.next()) {
				System.out.println("Name :"+rs.getString("name")+" ,Roll No: "+rs.getString("rollno")+", Phone No:"+rs.getString("phoneno"));
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static void saveStudentPrepare(String name,String rollno,String phoneno) {
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_bestdb", "root", "root");
			String sql="insert into student(name,rollno,phoneno) values(?,?,?)";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, rollno);
			pstmt.setString(3, phoneno);
			pstmt.executeUpdate();
			System.out.println("Successfully saved!");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
