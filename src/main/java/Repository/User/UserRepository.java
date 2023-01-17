package Repository.User;

import Entity.BookBorrowsEntity;
import Entity.BookCopiesEntity;
import Entity.UsersEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.Date;
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
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            if (et.isActive())
                et.rollback();
        }
    }

    @Override
    public UsersEntity getUserByLogin(String login) {
        TypedQuery<UsersEntity> userLogin = em.createNamedQuery("UsersEntity.ByLogin", UsersEntity.class);
        userLogin.setParameter(1, login);
        List<UsersEntity> usersEntityList = userLogin.getResultList();
        if (!usersEntityList.isEmpty())
            return usersEntityList.get(0);
        else return null;
    }

    @Override
    public Set<BookCopiesEntity> getUserBookCopies(int ID) {
        UsersEntity user = em.find(UsersEntity.class, ID);
        return user.getBookCopiesByUser();
    }

    @Override
    public void addBookBorrow(BookBorrowsEntity bookBorrow, int userID,BookCopiesEntity bookCopy) {
        UsersEntity user = em.find(UsersEntity.class, userID);
        EntityTransaction tr = em.getTransaction();

        try {
            tr.begin();
            bookCopy.setFree((byte) 0);
            user.getBookCopiesByUser().add(bookCopy);
            user.getBookBorrowsByIdUser().add(bookBorrow);
            em.merge(user);
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (tr.isActive())
                tr.rollback();
        }
    }

    @Override
    public void updateBookBorrow(int userID, BookCopiesEntity bookCopy) throws Exception {
        EntityTransaction tr = em.getTransaction();
        TypedQuery<BookBorrowsEntity> query = em.createNamedQuery("BookBorrows.NotReturned", BookBorrowsEntity.class);
        query.setParameter("userID",userID);
        query.setParameter("bookCopyID",bookCopy.getIdCopy());
        List<BookBorrowsEntity> bookBorrowsList = query.getResultList();
        BookBorrowsEntity bookBorrow;
        if (!bookBorrowsList.isEmpty())
             bookBorrow= bookBorrowsList.get(0);
        else throw  new Exception("Nie posiadasz wypozyczonej ksiazki o tym id");
        try {
            tr.begin();
            bookCopy.setFree((byte) 1);
            bookBorrow.setReturnDate(new Timestamp(new Date().getTime()));
            em.merge(bookCopy);
            em.merge(bookBorrow);
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (tr.isActive())
                tr.rollback();
        }
    }

}
