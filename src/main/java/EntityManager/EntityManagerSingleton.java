package EntityManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerSingleton {
    private static EntityManagerSingleton INSTANCE;
    private final EntityManagerFactory emf;

    private EntityManagerSingleton() {
        emf = Persistence.createEntityManagerFactory("default");
    }

    public static EntityManagerSingleton getInstance() {
        if(INSTANCE==null)
            INSTANCE = new EntityManagerSingleton();
        return INSTANCE;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void close() {
        emf.close();
        INSTANCE = null;
    }
}
