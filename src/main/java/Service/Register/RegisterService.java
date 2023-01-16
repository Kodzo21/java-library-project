package Service.Register;

import Entity.UsersEntity;
import EntityManager.EntityManagerSingleton;
import Repository.User.IUserRepository;
import Repository.User.UserRepository;
import at.favre.lib.crypto.bcrypt.BCrypt;
import org.apache.commons.validator.EmailValidator;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorException;

public class RegisterService implements IRegisterService{
    @Override
    public void registerUser(String login, String password, String firstName, String lastName, String email) throws ValidatorException {
        if(!EmailValidator.getInstance().isValid(email))
            throw new ValidatorException("Nieprawidlowy adres email");
        IUserRepository userRepository = new UserRepository(EntityManagerSingleton.getInstance().getEntityManager());
        UsersEntity user = new UsersEntity();
        user.setLogin(login);
        user.setPassword(BCrypt.withDefaults().hashToString(12,password.toCharArray()));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        userRepository.addUser(user);


    }

}
