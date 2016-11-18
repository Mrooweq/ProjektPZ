package com.malinki.pz;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.malinki.pz.controller.Animal;
import com.malinki.pz.controller.User;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("Gosh, it's working!");

		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		/////
		
		SqlSession session = sqlSessionFactory.openSession();
		try {
			User user = session.selectOne("mapper.test");              
			System.out.println("lol1: " + user.getFirstName());		
			
			
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", "12:0");

			User user2 = session.selectOne("mapper.test2", params);
			System.out.println("lol2: " + user2.getFirstName());			
		} finally {
			session.close();
		}
	}

}
