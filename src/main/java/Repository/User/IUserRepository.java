package Repository.User;

import Entity.BookBorrowsEntity;
import Entity.BookCopiesEntity;
import Entity.UsersEntity;

import java.util.Set;

public interface IUserRepository {
    UsersEntity getUserByID(int ID);
    void addUser(UsersEntity user);
    UsersEntity getUserByLogin(String login);
    Set<BookCopiesEntity> getUserBookCopies(int ID);
    void addBookBorrow(BookBorrowsEntity bookBorrow, int userID,BookCopiesEntity bookCopy);
    void updateBookBorrow(int userID,BookCopiesEntity bookCopy) throws Exception;
}
