package Repository.Book;

import Entity.AuthorsEntity;
import Entity.BooksEntity;
import Entity.UsersEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

public class BookRepository implements IBookRepository{

    private final EntityManager em;

    public BookRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void addBook(BooksEntity book ) {
        EntityTransaction tr = em.getTransaction();

        try{
            tr.begin();
            em.persist(book);
            tr.commit();
        }catch (PersistenceException e){
            e.printStackTrace();
        }finally {
            if (tr.isActive())
                tr.rollback();
        }

    }

    @Override
    public BooksEntity findByID(int ID) {
        return em.find(BooksEntity.class,ID);
    }

    @Override
    public List<BooksEntity> findByAuthor(AuthorsEntity author) {
        Set<BooksEntity> books = author.authorBooksByIdAuthor();
        return books.stream().toList();
    }

    @Override
    public List<BooksEntity> findByTitle(String title) {
        TypedQuery<BooksEntity> bookTitles = em.createNamedQuery("BooksEntity.ByTitle",BooksEntity.class);
        bookTitles.setParameter("name",'%'+title+'%');
        return bookTitles.getResultList();
    }


}
