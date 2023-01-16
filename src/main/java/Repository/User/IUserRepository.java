package Repository.User;

import Entity.BookCopiesEntity;
import Entity.UsersEntity;

import java.util.List;
import java.util.Set;

public interface IUserRepository {
    UsersEntity getUserByID(int ID);
    void addUser(UsersEntity user);
    UsersEntity getUserByLogin(String login);
    Set<BookCopiesEntity> getUserBookCopies(int ID);
}
