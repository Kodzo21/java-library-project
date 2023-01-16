package Repository.Author;

import Entity.AuthorsEntity;
import Entity.BooksEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class AuthorRepository implements IAuthorRepository{

    private  final EntityManager em;

    public AuthorRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void addAuthor(AuthorsEntity author) {
        EntityTransaction tr = em.getTransaction();

        try{
            tr.begin();
            em.persist(author);
            tr.commit();
        }catch (PersistenceException e){
            e.printStackTrace();
        }finally {
            if (tr.isActive())
                tr.rollback();
        }
    }

    @Override
    public AuthorsEntity findByID(int ID) {
        return em.find(AuthorsEntity.class,ID);
    }

    @Override
    public AuthorsEntity findByName(String name) {
        TypedQuery<AuthorsEntity> authorNames = em.createNamedQuery("AuthorsEntity.ByName",AuthorsEntity.class);
        authorNames.setParameter("name",name);
        List<AuthorsEntity> authors = authorNames.getResultList();
        if (authors!=null)
            return authors.get(0);
        else return null;

    }
}
