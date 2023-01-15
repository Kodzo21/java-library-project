package Entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "authors", schema = "labhibernate", catalog = "")
public class AuthorsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idAuthor")
    private int idAuthor;
    @Basic
    @Column(name = "firstName")
    private String firstName;
    @Basic
    @Column(name = "lastName")
    private String lastName;
    @ManyToMany(mappedBy ="bookAuthorsByIdBook" )
    private Set<BooksEntity> authorBooksByIdAuthor;

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorsEntity that = (AuthorsEntity) o;

        if (idAuthor != that.idAuthor) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAuthor;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    public Set<BooksEntity> authorBooksByIdAuthor() {
        return authorBooksByIdAuthor;
    }

    public void setBookAuthorsByIdBook(Set<BooksEntity> authorBooksByIdAuthor) {
        this.authorBooksByIdAuthor = authorBooksByIdAuthor;
    }
}
