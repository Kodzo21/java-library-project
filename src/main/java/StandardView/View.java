package StandardView;

import Entity.UsersEntity;

import java.util.Scanner;

public class View {

    Scanner scanner = new Scanner(System.in);


    public void menu(){
        boolean shouldBreak = true;
        while(shouldBreak){
            switch (loginOrRegister()) {
                case 1 -> doLogin();
                case 2 -> doRegister();
                case 0 -> shouldBreak = false;
                default -> System.out.println("Nieprawidlowa opcja - sprobuj ponownie");
            }


        }
    }

    private int loginOrRegister(){
        int choice;
        System.out.println("Wybierz opcje:");
        System.out.println("1 - Logowanie");
        System.out.println("2 - Rejestracja");
        System.out.println("0 - Wyjscie");
        choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    private void doLogin(){
        String login,password;

        System.out.println("Podaj login:");
        login = scanner.nextLine();
        System.out.println("Podaj haslo:");
        password= scanner.nextLine();

//        UsersEntity user  = userS.login(login,password);
//        if(user != null) {
//            NoteService noteS = new NoteService(notesO, user);
//            UserMenu userMenu = new UserMenu(noteS);
//            userMenu.menu();
//        }
    }

    private void doRegister(){
        System.out.println("Podaj login:");
        String login = scanner.nextLine();
        System.out.println("Podaj haslo:");
        String password = scanner.nextLine();
        System.out.println("Podaj imie:");
        String name = scanner.nextLine();
        System.out.println("Podaj nazwisko");
        String surname = scanner.nextLine();

        //userS.register(login,password,name,surname);
    }

}
