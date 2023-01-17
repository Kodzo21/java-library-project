package Service.Books;

import Entity.BookBorrowsEntity;
import Entity.BookCopiesEntity;
import Entity.UsersEntity;
import EntityManager.EntityManagerSingleton;
import Repository.BookCopy.BookCopyRepository;
import Repository.BookCopy.IBookCopyRepository;
import Repository.User.IUserRepository;
import Repository.User.UserRepository;

public class BookReservationService implements IBookReservationService{
    @Override
    public void reserveBook(int bookID,int userID) throws Exception {
        IUserRepository userRepository
                =new UserRepository(EntityManagerSingleton.getInstance().getEntityManager());
        IBookCopyRepository bookCopyRepository
                = new BookCopyRepository(EntityManagerSingleton.getInstance().getEntityManager());
        BookCopiesEntity bookCopy = bookCopyRepository.findFreeBookCopy(bookID);
        if(bookCopy==null)
            throw new Exception("Brak wolnej ksiazki o tym ID");
        BookBorrowsEntity bookBorrow = new BookBorrowsEntity();
        userRepository.addBookBorrow(bookBorrow,userID,bookCopy);
    }

    @Override
    public void returnBook(int bookCopyID, int userID) {
        IUserRepository userRepository
                =new UserRepository(EntityManagerSingleton.getInstance().getEntityManager());
        IBookCopyRepository bookCopyRepository
                = new BookCopyRepository(EntityManagerSingleton.getInstance().getEntityManager());

        UsersEntity user = userRepository.getUserByID(userID);
        BookCopiesEntity bookCopy = bookCopyRepository.findByID(bookCopyID);
        try {
            userRepository.updateBookBorrow(userID,bookCopy);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
