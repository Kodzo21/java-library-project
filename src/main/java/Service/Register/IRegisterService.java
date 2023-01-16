package Service.Register;

import org.apache.commons.validator.ValidatorException;

public interface IRegisterService {

    public void registerUser(String login,String password, String firstName,String lastName, String email) throws ValidatorException;

}
