package StandardView;

import Entity.BookCopiesEntity;
import Entity.BooksEntity;
import Service.Books.BookReservationService;
import Service.Books.BookSearchService;
import Service.Books.IBookReservationService;
import Service.Books.IBookSearchService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserView implements IView{

    private Scanner scanner = new Scanner(System.in);
    private IBookSearchService bookSearchService = new BookSearchService();
    private IBookReservationService bookReservationService = new BookReservationService();
    private int userID;

    public UserView(int userID) {
        this.userID = userID;
    }

    @Override
    public void menu() {
        boolean shouldBreak = true;
        while (shouldBreak) {
            System.out.println("Wybierz opcje:");
            System.out.println("1 - wyswietl wypozyczone ksiazki");
            System.out.println("2 - wyszukaj ksiazke");
            System.out.println("3 - wyszukaj ksiazki po autorze");
            System.out.println("4 - wypozycz ksiazke");
            System.out.println("5 - zwroc ksiazke");
            System.out.println("0 - wyloguj");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> doShowBorrowedBooks();
                    case 2 -> doFindBookByTitle();
                    case 3 -> doFindBookByAuthorName();
                    case 4 -> doReserveBook();
                    case 5 -> doReturnBook();
                    case 0 -> shouldBreak = false;
                    default -> System.out.println("Nieprawidlowa opcja - sprobuj ponownie");
                }
            }catch (InputMismatchException e){
                System.out.println("Nieprawidlowa opcja - sprobuj ponownie");
            }
        }
    }

    private void doReturnBook() {
        System.out.println("Podaj numer kopii ksiazki:");
        try {
            int bookCopyID = scanner.nextInt();
            scanner.nextLine();
            bookReservationService.returnBook(bookCopyID,userID);
            System.out.println("Ksiazka zwrocona");
        }catch (Exception e){
            System.out.println("Blad podczas zwracania ksiazki");
        }
    }

    private void doShowBorrowedBooks(){
        IBookSearchService bookSearchService = new BookSearchService();
        List<BookCopiesEntity> books =  bookSearchService.borrowedBooks(userID);
        if (books.isEmpty())
            System.out.println("Brak wypozyczonych ksiazek");
        else books.forEach(b -> {
            if (b.isFree()==(byte)0) System.out.println(b);
        });
    }

    private void doFindBookByTitle(){
        System.out.println("Podaj tytul ksiazki:");
        String title = scanner.nextLine();
        List<BooksEntity> books =  bookSearchService.findByTitle(title);
        if (books==null)
            System.out.println("Nie znaleziono pasujacych ksiazek");
        else books.forEach(System.out::println);
    }

    private void doFindBookByAuthorName(){
        System.out.println("Podaj autora ksiazki:");
        String authorName = scanner.nextLine();
        List<BooksEntity> books = bookSearchService.findByAuthor(authorName);
        if (books==null)
            System.out.println("Nie znaleziono pasujacych ksiazek");
        else books.forEach(System.out::println);
    }

    private void doReserveBook(){
        System.out.println("Podaj id ksiazki, ktora chcesz wypozyczyc");
        try {
            int bookID = scanner.nextInt();
            scanner.nextLine();
            bookReservationService.reserveBook(bookID, userID);
            System.out.println("Ksiazka zarezerwowana");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
