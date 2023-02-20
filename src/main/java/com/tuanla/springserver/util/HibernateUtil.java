//package vn.telsoft.acm.util;
//
//
//
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.MetadataSources;
//import org.hibernate.boot.registry.StandardServiceRegistry;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.dialect.Dialect;
//import org.hibernate.engine.spi.SessionFactoryImplementor;
//import telsoft.lib.SystemLogger;
//
///**
// * Hibernate Utility class with a convenient method to get Session Factory
// * object.
// *
// *
// */
//public class HibernateUtil {
//    private static SessionFactory sessionFactory;
//    private static Dialect dialect;
//    static {
//        try {
//            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
//            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//            dialect=((SessionFactoryImplementor)sessionFactory).getJdbcServices().getDialect();
//        } catch (Exception ex) {
//            SystemLogger.getLogger().error(ex);
//            throw ex;
//        }
//    }
//
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//    public static Dialect getDialect() {
//        return dialect;
//    }
//
//}
