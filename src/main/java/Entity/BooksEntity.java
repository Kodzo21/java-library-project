package Entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@NamedQuery(name =  "BooksEntity.ByTitle",
        query = "select u from BooksEntity u where u.title like :name")
@Entity
@Table(name = "books", schema = "labhibernate", catalog = "")
public class BooksEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idBook")
    private int idBook;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "idCategory",insertable = false,updatable = false)
    private int idCategory;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name="book_author",joinColumns = @JoinColumn(name="book_id"),inverseJoinColumns = @JoinColumn(name="author_id"))
    private Set<AuthorsEntity> bookAuthorsByIdBook ;
    @OneToMany(mappedBy = "booksByBookId")
    private List<BookCopiesEntity> bookCopiesByIdBook;
    @ManyToOne
    @JoinColumn(name = "idCategory", referencedColumnName = "idCategory", nullable = false)
    private CategoriesEntity categoriesByIdCategory;

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BooksEntity that = (BooksEntity) o;

        if (idBook != that.idBook) return false;
        if (idCategory != that.idCategory) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idBook;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + idCategory;
        return result;
    }

    public Set<AuthorsEntity> getBookAuthorsByIdBook() {
        return bookAuthorsByIdBook;
    }

    public void setBookAuthorsByIdBook(Set<AuthorsEntity> bookAuthorsByIdBook) {
        this.bookAuthorsByIdBook = bookAuthorsByIdBook;
    }

    public List<BookCopiesEntity> getBookCopiesByIdBook() {
        return bookCopiesByIdBook;
    }

    public void setBookCopiesByIdBook(List<BookCopiesEntity> bookCopiesByIdBook) {
        this.bookCopiesByIdBook = bookCopiesByIdBook;
    }

    public CategoriesEntity getCategoriesByIdCategory() {
        return categoriesByIdCategory;
    }

    public void setCategoriesByIdCategory(CategoriesEntity categoriesByIdCategory) {
        this.categoriesByIdCategory = categoriesByIdCategory;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ksiazka: ").append("id:").append(idBook)
                .append(", tytul:'").append(title).append('\'').append(", autorzy:");
        bookAuthorsByIdBook.forEach(a->sb.append(a.getName()).append("   "));
        sb.append(", kategoria: ").append(categoriesByIdCategory);
        return sb.toString();
    }
}
