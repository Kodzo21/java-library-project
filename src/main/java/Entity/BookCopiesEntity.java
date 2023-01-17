package Entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
    @Basic
    @Column(name="isFree")
    private byte isFree;
    @OneToMany(mappedBy = "bookCopiesByBookCopyId")
    private List<BookBorrowsEntity> bookBorrowsByIdCopy;
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "idBook", nullable = false)
    private BooksEntity booksByBookId;
    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "idPublisher", nullable = false)
    private PublishersEntity publishersByPublisherId;

    @ManyToMany(mappedBy="bookCopiesByUser")
    private Set<UsersEntity> usersByBookCopy;

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

    public byte isFree() {
        return isFree;
    }

    public void setFree(byte free) {
        isFree = free;
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

    public List<BookBorrowsEntity> getBookBorrowsByIdCopy() {
        return bookBorrowsByIdCopy;
    }

    public void setBookBorrowsByIdCopy(List<BookBorrowsEntity> bookBorrowsByIdCopy) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(booksByBookId).append(", wydawnictwo: ").append(publishersByPublisherId).append(", numer kopii ksiazki:" ).append(idCopy);
        if (isFree()==0) {
            BookBorrowsEntity bookBorrows = bookBorrowsByIdCopy.get(bookBorrowsByIdCopy.size() - 1);
            Timestamp startTime = bookBorrows.getStartTime();
            LocalDateTime endTime = startTime.toLocalDateTime();
            endTime = endTime.plusWeeks(4);
            sb.append(", data wypozyczenia: ").append(startTime).append(", nalezy zwrocic do: ").append(endTime);
        }

        return sb.toString();
    }
}
