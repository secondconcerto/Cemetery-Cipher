
package pl.polsl.cementarycipher.oliwia.mlonek.listeners;

import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


/**
 * Web application lifecycle listener.
 * @author Oliwia Mlonek
 * @version 5.0
 */
@WebListener
public class DbConnectionInitializer implements ServletContextListener  {

    /**
    * Notification that the web application initialization process is starting. 
    * @param sce notification about changes to the servlet context of a web application
    */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Here you can store the connection with the db in the servlet context
        // and then read it in your servlets using getAttribute method
        sce.getServletContext().setAttribute("DbCon", 
                Persistence.createEntityManagerFactory("LabPU").createEntityManager());
    }
    
    /**
    * Notification that the servlet context is about to be shut down.
    * @param sce notification about changes to the servlet context of a web application
    */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
