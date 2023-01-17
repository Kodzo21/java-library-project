package Service.Books;

public interface IBookReservationService {
    void reserveBook(int bookID,int userID) throws Exception;
    void returnBook(int bookCopyID, int userID);
}
