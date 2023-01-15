package Entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "book_borrows", schema = "labhibernate", catalog = "")
public class BookBorrowsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idBorrow")
    private int idBorrow;
    @Basic
    @Column(name = "start_time")
    private Timestamp startTime;
    @Basic
    @Column(name = "end_time")
    private Timestamp endTime;
    @Basic
    @Column(name = "book_copy_id",insertable = false,updatable = false)
    private int bookCopyId;
    @Basic
    @Column(name = "user_id",insertable = false,updatable = false)
    private int userId;
    @Basic
    @Column(name = "is_returned")
    private byte isReturned;
    @Basic
    @Column(name = "return_date")
    private Timestamp returnDate;
    @ManyToOne
    @JoinColumn(name = "book_copy_id", referencedColumnName = "idCopy", nullable = false)
    private BookCopiesEntity bookCopiesByBookCopyId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "idUser", nullable = false)
    private UsersEntity usersByUserId;

    public int getIdBorrow() {
        return idBorrow;
    }

    public void setIdBorrow(int idBorrow) {
        this.idBorrow = idBorrow;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public int getBookCopyId() {
        return bookCopyId;
    }

    public void setBookCopyId(int bookCopyId) {
        this.bookCopyId = bookCopyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public byte getIsReturned() {
        return isReturned;
    }

    public void setIsReturned(byte isReturned) {
        this.isReturned = isReturned;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookBorrowsEntity that = (BookBorrowsEntity) o;

        if (idBorrow != that.idBorrow) return false;
        if (bookCopyId != that.bookCopyId) return false;
        if (userId != that.userId) return false;
        if (isReturned != that.isReturned) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (returnDate != null ? !returnDate.equals(that.returnDate) : that.returnDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idBorrow;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + bookCopyId;
        result = 31 * result + userId;
        result = 31 * result + (int) isReturned;
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        return result;
    }

    public BookCopiesEntity getBookCopiesByBookCopyId() {
        return bookCopiesByBookCopyId;
    }

    public void setBookCopiesByBookCopyId(BookCopiesEntity bookCopiesByBookCopyId) {
        this.bookCopiesByBookCopyId = bookCopiesByBookCopyId;
    }

    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }
}
