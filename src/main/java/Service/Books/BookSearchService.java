package Service.Books;

import Entity.AuthorsEntity;
import Entity.BookCopiesEntity;
import Entity.BooksEntity;
import Entity.UsersEntity;
import EntityManager.EntityManagerSingleton;
import Repository.Author.AuthorRepository;
import Repository.Author.IAuthorRepository;
import Repository.Book.BookRepository;
import Repository.Book.IBookRepository;
import Repository.BookCopy.BookCopyRepository;
import Repository.BookCopy.IBookCopyRepository;
import Repository.User.IUserRepository;
import Repository.User.UserRepository;

import java.util.List;

public class BookSearchService implements IBookSearchService {
    @Override
    public List<BooksEntity> findByTitle(String expression) {
        IBookRepository bookRepository
                = new BookRepository(EntityManagerSingleton.getInstance().getEntityManager());
        List<BooksEntity> books = bookRepository.findByTitle(expression);
        if (books == null)
            System.out.println("Nie znaleziono pasujacych ksiazek");
        return books;
    }

    @Override
    public List<BooksEntity> findByAuthor(String authorName) {
        IBookRepository bookRepository
                = new BookRepository(EntityManagerSingleton.getInstance().getEntityManager());
        IAuthorRepository authorRepository
                = new AuthorRepository(EntityManagerSingleton.getInstance().getEntityManager());
        AuthorsEntity author = authorRepository.findByName(authorName);
        if(author!=null) {
                    return bookRepository.findByAuthor(author);
        }
        else{
            System.out.println("Nie znaleziono pasujacych ksiazek");
            return null;
        }
    }

    @Override
    public List<BookCopiesEntity> borrowedBooks(int userID) {
        IBookCopyRepository bookCopyRepository
                = new BookCopyRepository(EntityManagerSingleton.getInstance().getEntityManager());
        return bookCopyRepository
                .findBookCopiesByUser(userID);
    }

//    @Override
//    public List<BookCopiesEntity> borrowedBooks(int userID) {
//        IUserRepository userRepository =
//                new UserRepository(EntityManagerSingleton.getInstance().getEntityManager());
//        return userRepository.
//                getUserByID(userID)
//                .getBookCopiesByUser()
//                .stream()
//                .toList();
//    }
}
