package modelo;
import java.net.URL;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			URL cfg = HibernateUtil.class.getResource("/modelo/hibernate.cfg.xml");
			Configuration configuration = new Configuration().
					configure(cfg);
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
					applySettings(configuration.getProperties());
			return configuration.buildSessionFactory(builder.build());
		}
		catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

    public static void closeConnection(){
    	sessionFactory.close();
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
}
