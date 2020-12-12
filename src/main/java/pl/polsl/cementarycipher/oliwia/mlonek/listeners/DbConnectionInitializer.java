/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.listeners;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


/**
 * Web application lifecycle listener.
 *
 * @author Tomasz
 */
@WebListener
public class DbConnectionInitializer implements ServletContextListener  {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Here you can store the connection with the db in the servlet context
        // and then read it in your servlets using getAttribute method
        sce.getServletContext().setAttribute("DbCon", 
                Persistence.createEntityManagerFactory("LabPU").createEntityManager());
    }
    
//    public  EntityManager getEntityManager() {
//        EntityManager em = (EntityManager) getServletContext().getAttribute("DbCon");
//		if(em == null){
//			EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jpa-example");
//			return emFactory.createEntityManager();
//		}
//		return entityManager;
//	}

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
