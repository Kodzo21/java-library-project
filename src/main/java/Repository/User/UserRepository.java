package Repository.User;

import Entity.BookCopiesEntity;
import Entity.UsersEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

public class UserRepository implements IUserRepository {
    private EntityManager em;

    public UserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public UsersEntity getUserByID(int ID) {
        return em.find(UsersEntity.class, ID);
    }

    @Override
    public void addUser(UsersEntity user) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(user);
            et.commit();
        }catch(PersistenceException e){
            e.printStackTrace();
        }finally {
            if(et.isActive())
                et.rollback();
        }
    }

    @Override
    public UsersEntity getUserByLogin(String login) {
        TypedQuery<UsersEntity> userLogin = em.createNamedQuery("UsersEntity.ByLogin",UsersEntity.class);
        userLogin.setParameter(1,login);
        List<UsersEntity> usersEntityList = userLogin.getResultList();
        if (!usersEntityList.isEmpty())
            return usersEntityList.get(0);
        else return null;
    }

    @Override
    public Set<BookCopiesEntity> getUserBookCopies(int ID) {
        UsersEntity user = em.find(UsersEntity.class,ID);
        return user.getBookCopiesByUser();
    }

}
