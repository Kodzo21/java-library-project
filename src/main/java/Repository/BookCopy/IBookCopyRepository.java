package Repository.BookCopy;

import Entity.BookCopiesEntity;

import java.util.List;
import java.util.Set;

public interface IBookCopyRepository {
    void addBookCopies(int bookID,int publisherID,int amount);
    BookCopiesEntity findByID(int id);
    List<BookCopiesEntity> findBookCopiesByUser(int userID);
    BookCopiesEntity findFreeBookCopy(int bookID);
}
