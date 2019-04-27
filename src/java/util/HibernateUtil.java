package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static Configuration conf;
    private final static  SessionFactory sf;
    static {
        try{
            conf=new Configuration().configure();
            sf=conf.buildSessionFactory();
            System.out.println("配置文件加载成功。。。。。。。");
        }catch (HibernateException ex){
            throw new ExceptionInInitializerError(ex);
        }
    }
    private HibernateUtil(){}

    public static Session currentSession(){
        return sf.openSession();
    }
    public static Session current(){
        return sf.getCurrentSession();
    }
    public static void closeSession(){
        sf.getCurrentSession().close();
    }
}
