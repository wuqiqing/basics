package changdaye.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//hibernate����������
public class HibernateUtils {
    private static SessionFactory sessionFactory;

    static {
        //��ʼ��
        sessionFactory = new Configuration().configure().buildSessionFactory();

        //Ҫ��������رյ�ʱ����ùرչ����ķ���:ʹ������ʱ�ĹرյĹ��ӷ���
        Runtime.getRuntime().addShutdownHook(new Thread() {
            //һ������д������������Դ
            @Override
            public void run() {
                //�رչ���
                sessionFactory.close();
                System.out.println("session�����Ѿ��رգ�");
            }
        });

    }

    //�õ�session����
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    //�õ� �Ự
    public static Session openSession() {
        return sessionFactory.openSession();
    }

}
