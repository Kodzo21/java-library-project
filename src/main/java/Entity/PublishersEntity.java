package Entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "publishers", schema = "labhibernate", catalog = "")
public class PublishersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPublisher")
    private int idPublisher;
    @Basic
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "publishersByPublisherId")
    private Collection<BookCopiesEntity> bookCopiesByIdPublisher;

    public int getIdPublisher() {
        return idPublisher;
    }

    public void setIdPublisher(int idPublisher) {
        this.idPublisher = idPublisher;
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

        PublishersEntity that = (PublishersEntity) o;

        if (idPublisher != that.idPublisher) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPublisher;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public Collection<BookCopiesEntity> getBookCopiesByIdPublisher() {
        return bookCopiesByIdPublisher;
    }

    public void setBookCopiesByIdPublisher(Collection<BookCopiesEntity> bookCopiesByIdPublisher) {
        this.bookCopiesByIdPublisher = bookCopiesByIdPublisher;
    }
}
