package Entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@NamedQuery(name =  "AuthorsEntity.ByName",
        query = "select u from AuthorsEntity u where u.name like :name ")

@Entity
@Table(name = "authors", schema = "labhibernate", catalog = "")
public class AuthorsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idAuthor")
    private int idAuthor;
    @Basic
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy ="bookAuthorsByIdBook" )
    private Set<BooksEntity> authorBooksByIdAuthor;

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorsEntity that = (AuthorsEntity) o;

        if (idAuthor != that.idAuthor) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAuthor;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public Set<BooksEntity> authorBooksByIdAuthor() {
        return authorBooksByIdAuthor;
    }

    public void setBookAuthorsByIdBook(Set<BooksEntity> authorBooksByIdAuthor) {
        this.authorBooksByIdAuthor = authorBooksByIdAuthor;
    }
}
