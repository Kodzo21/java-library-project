package Entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "book_copies", schema = "labhibernate", catalog = "")
public class BookCopiesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idCopy")
    private int idCopy;
    @Basic
    @Column(name = "book_id",insertable = false,updatable = false)
    private int bookId;
    @Basic
    @Column(name = "publisher_id",insertable = false,updatable = false)
    private int publisherId;
    @OneToMany(mappedBy = "bookCopiesByBookCopyId")
    private Collection<BookBorrowsEntity> bookBorrowsByIdCopy;
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "idBook", nullable = false)
    private BooksEntity booksByBookId;
    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "idPublisher", nullable = false)
    private PublishersEntity publishersByPublisherId;

    public int getIdCopy() {
        return idCopy;
    }

    public void setIdCopy(int idCopy) {
        this.idCopy = idCopy;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookCopiesEntity that = (BookCopiesEntity) o;

        if (idCopy != that.idCopy) return false;
        if (bookId != that.bookId) return false;
        if (publisherId != that.publisherId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCopy;
        result = 31 * result + bookId;
        result = 31 * result + publisherId;
        return result;
    }

    public Collection<BookBorrowsEntity> getBookBorrowsByIdCopy() {
        return bookBorrowsByIdCopy;
    }

    public void setBookBorrowsByIdCopy(Collection<BookBorrowsEntity> bookBorrowsByIdCopy) {
        this.bookBorrowsByIdCopy = bookBorrowsByIdCopy;
    }

    public BooksEntity getBooksByBookId() {
        return booksByBookId;
    }

    public void setBooksByBookId(BooksEntity booksByBookId) {
        this.booksByBookId = booksByBookId;
    }

    public PublishersEntity getPublishersByPublisherId() {
        return publishersByPublisherId;
    }

    public void setPublishersByPublisherId(PublishersEntity publishersByPublisherId) {
        this.publishersByPublisherId = publishersByPublisherId;
    }
}
