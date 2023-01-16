package StandardView;

import java.util.Scanner;

public class UserView implements IView{

    private Scanner scanner = new Scanner(System.in);

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
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
//                case 1 ->doCreateNote();
//                case 2 ->noteS.listAllNotes();
//                case 3 ->doShowCertainNote();
//                case 4 ->doEditNote();
//                case 5 -> doDeleteNote();
//                case 0 -> shouldBreak = false;
//                default -> System.out.println("Nieprawidlowa opcja - sprobuj ponownie");
            }

        }
    }
}
