import Entity.BooksEntity;
import EntityManager.EntityManagerSingleton;
import StandardView.IView;
import StandardView.StandardView;
import org.apache.commons.validator.EmailValidator;

import javax.persistence.EntityTransaction;

public class Main {
    public static void main(String[] args) {
        IView view = new StandardView();
        view.menu();
    }
}
