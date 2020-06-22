package br.com.projeto.estoque.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConectionJDBC {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("estoqueTecFour");
		
		public EntityManager getConnection() {return emf.createEntityManager();}
		
		public Connection getConnectionJDBC() {
	        try {
	            return DriverManager.getConnection(

	                    "jdbc:mysql://localhost:3306/estoquetecfour?serverTimezone=GMT-3", "root", "root");

//	                    "jdbc:mysql://localhost:3306/estoquetecfour?serverTimezone=GMT-3", "root", "root");

	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
}
