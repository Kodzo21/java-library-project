package Service.Login;

import Entity.UsersEntity;

public interface ILoginService {
    UsersEntity login(String login, String password);
}
