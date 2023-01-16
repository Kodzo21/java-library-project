package Repository.BookCopy;

import Entity.BookCopiesEntity;
import Entity.UsersEntity;

import java.util.Set;

public interface IBookCopyRepository {
    void addBookCopies(int bookID,int publisherID,int amount);
    Set<BookCopiesEntity> findBookCopiesByUser(int userID);
}
