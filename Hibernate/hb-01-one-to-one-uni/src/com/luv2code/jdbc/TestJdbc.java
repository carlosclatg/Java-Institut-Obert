package com.luv2code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class TestJdbc {

	public static void main(String[] args) {
		
		String jdbcUrlString = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false&serverTimezone=UTC";
		String user= "hbstudent";
		String password = "hbstudent";

		try {
			
			System.out.println("Connecting to DB" + jdbcUrlString);
			Connection myConn = DriverManager.getConnection(jdbcUrlString, user, password);
			System.out.println("Succesful connection!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}



