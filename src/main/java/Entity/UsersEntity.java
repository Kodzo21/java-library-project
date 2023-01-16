package Entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@NamedQuery(name =  "UsersEntity.ByLogin",
        query = "select u from UsersEntity u where u.login =?1 ")
@Entity
@Table(name = "users", schema = "labhibernate", catalog = "")
public class UsersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUser")
    private int idUser;
    @Basic
    @Column(name = "login")
    private String login;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "firstName")
    private String firstName;
    @Basic
    @Column(name = "lastName")
    private String lastName;
    @Basic
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "usersByUserId")
    private List<BookBorrowsEntity> bookBorrowsByIdUser;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name="book_borrows",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="book_copy_id"))
    private Set<BookCopiesEntity> bookCopiesByUser;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity users = (UsersEntity) o;

        if (idUser != users.idUser) return false;
        if (login != null ? !login.equals(users.login) : users.login != null) return false;
        if (password != null ? !password.equals(users.password) : users.password != null) return false;
        if (firstName != null ? !firstName.equals(users.firstName) : users.firstName != null) return false;
        if (lastName != null ? !lastName.equals(users.lastName) : users.lastName != null) return false;
        if (email != null ? !email.equals(users.email) : users.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public List<BookBorrowsEntity> getBookBorrowsByIdUser() {
        return bookBorrowsByIdUser;
    }

    public void setBookBorrowsByIdUser(List<BookBorrowsEntity> bookBorrowsByIdUser) {
        this.bookBorrowsByIdUser = bookBorrowsByIdUser;
    }

    public Set<BookCopiesEntity> getBookCopiesByUser() {
        return bookCopiesByUser;
    }

    public void setBookCopiesByUser(Set<BookCopiesEntity> bookCopiesByUser) {
        this.bookCopiesByUser = bookCopiesByUser;
    }
}
