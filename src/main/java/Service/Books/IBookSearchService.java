package Service.Books;

import Entity.BookCopiesEntity;
import Entity.BooksEntity;
import Entity.UsersEntity;

import java.util.List;

public interface IBookSearchService {
    List<BooksEntity> findByTitle(String expression);
    List<BooksEntity> findByAuthor(String authorName);
    List<BookCopiesEntity> borrowedBooks(int userID);
}
