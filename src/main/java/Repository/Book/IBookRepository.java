package Repository.Book;

import Entity.AuthorsEntity;
import Entity.BooksEntity;
import Entity.UsersEntity;

import java.util.List;

public interface IBookRepository {

    void addBook(BooksEntity book);
    BooksEntity findByID(int ID);
    List<BooksEntity> findByAuthor(AuthorsEntity author);
    List<BooksEntity> findByTitle(String title);
}
