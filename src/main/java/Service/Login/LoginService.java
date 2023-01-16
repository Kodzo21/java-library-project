package Service.Login;

import Entity.UsersEntity;
import EntityManager.EntityManagerSingleton;
import Repository.User.IUserRepository;
import Repository.User.UserRepository;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class LoginService implements ILoginService {

    @Override
    public UsersEntity login(String login, String password) {
        IUserRepository userRepository
                = new UserRepository(EntityManagerSingleton.getInstance().getEntityManager());
        UsersEntity user = userRepository.getUserByLogin(login);
        if (user != null) {
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
            if (result.verified) {
                System.out.println("Zalogowano");
                return user;
            } else {
                System.out.println("Nieprawidlowe haslo");
                return null;
            }
        } else {
            System.out.println("Nie znaleziono uzytkownika - zarejestruj sie");
            return null;
        }
    }
}
