package net.codejava.hibernate;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * 
 * This program demonstrates using Hibernate framework to manage a
 * bidirectional one-to-many association.
 * @author www.codejava.net
 *
 */
public class StockManager {

	public static void main(String[] args) {
		// loads configuration and mappings
		Configuration configuration = new Configuration().configure();
		ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
		registry.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = registry.buildServiceRegistry();
		
		// builds a session factory from the service registry
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		// obtains the session
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Category category = new Category("Computer");
		
		Product pc = new Product("DELL PC", "Quad-core PC", 1200, category);
		
		Product laptop = new Product("MacBook", "Apple High-end laptop", 2100, category);
		
		Product phone = new Product("iPhone 5", "Apple Best-selling smartphone", 499, category);
		
		Product tablet = new Product("iPad 3", "Apple Best-selling tablet", 1099, category);
		
		Set<Product> products = new HashSet<Product>();
		products.add(pc);
		products.add(laptop);
		products.add(phone);
		products.add(tablet);
		
		category.setProducts(products);
		
		session.save(category);
		
		session.getTransaction().commit();
		session.close();		
	}
}