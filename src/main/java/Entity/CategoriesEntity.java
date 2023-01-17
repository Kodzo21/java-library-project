package Entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "categories", schema = "labhibernate", catalog = "")
public class CategoriesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idCategory")
    private int idCategory;
    @Basic
    @Column(name = "categoryName")
    private String categoryName;
    @OneToMany(mappedBy = "categoriesByIdCategory")
    private Collection<BooksEntity> booksByIdCategory;

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoriesEntity that = (CategoriesEntity) o;

        if (idCategory != that.idCategory) return false;
        if (categoryName != null ? !categoryName.equals(that.categoryName) : that.categoryName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCategory;
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        return result;
    }

    public Collection<BooksEntity> getBooksByIdCategory() {
        return booksByIdCategory;
    }

    public void setBooksByIdCategory(Collection<BooksEntity> booksByIdCategory) {
        this.booksByIdCategory = booksByIdCategory;
    }

    @Override
    public String toString() {
        return categoryName;
    }
}
