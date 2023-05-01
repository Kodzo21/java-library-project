package Repository.BookCopy;

import Entity.BookCopiesEntity;
import Entity.BooksEntity;
import Entity.UsersEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Set;

public class BookCopyRepository implements IBookCopyRepository {
    private EntityManager em;

    public BookCopyRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void addBookCopies(int bookID, int publisherID, int amount) {
        EntityTransaction tr = em.getTransaction();

        try {
            tr.begin();
            for (int i = 0; i < amount; i++) {
                BookCopiesEntity tmp = new BookCopiesEntity();
                tmp.setBookId(bookID);
                tmp.setPublisherId(publisherID);
                em.persist(tmp);
            }
            tr.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            if (tr.isActive())
                tr.rollback();
        }
    }

    @Override
    public BookCopiesEntity findByID(int id) {
        return em.find(BookCopiesEntity.class, id);
    }

    @Override
    public List<BookCopiesEntity> findBookCopiesByUser(int userID) {
        UsersEntity user = em.find(UsersEntity.class, userID);
        return user.getBookCopiesByUser();
    }

    @Override
    public BookCopiesEntity findFreeBookCopy(int bookID) {
        BooksEntity book = em.find(BooksEntity.class,bookID);
        if(book!=null) {
            List<BookCopiesEntity> books = book.getBookCopiesByIdBook();
            for (BookCopiesEntity bookCopy : books
            ) {
                if (bookCopy.isFree() == (byte) 1)
                    return bookCopy;
            }
        }
        return null;
    }
}
