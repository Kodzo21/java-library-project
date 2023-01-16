package Repository.Author;

import Entity.AuthorsEntity;

public interface IAuthorRepository {
    void addAuthor(AuthorsEntity author);
    AuthorsEntity findByID(int ID);
    AuthorsEntity findByName(String name);

}
