package Service.Books;

public interface IBookReservationService {
    void reserveBook(int id);
    void returnBook(int id);
}
