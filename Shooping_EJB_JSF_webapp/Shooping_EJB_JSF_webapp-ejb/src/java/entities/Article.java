package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author MAROUANE
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Article.findAll",
            query = "SELECT a FROM Article a WHERE a.qteStock > 0 ORDER BY a DESC"),
    @NamedQuery(name = "Article.findRelated",
            query = "SELECT a FROM Article a WHERE (a.qteStock > 0 AND SUBSTRING(a.reference, 1 ,2) = :ref) ORDER BY a DESC"),
    @NamedQuery(name = "Article.chercherArticle",
            query = "SELECT a FROM Article a WHERE a.designation LIKE :str AND SUBSTRING(a.reference, 1 ,1) = :categ")

})
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String designation;

    private int qteStock;

    private String reference;

    private float prix;

    private String caracteristiques;

    private String photo;
    
    private int pointsFidelite;

    public Article() {
        this.designation = "";
        this.qteStock = 0;
        this.reference = "";
        this.prix = 0;
        this.caracteristiques = "";
        this.pointsFidelite = 0;
    }

    public Article(String designation, int qteStock, String reference, float prix, String caracteristiques, String photo) {
        this.designation = designation;
        this.qteStock = qteStock;
        this.reference = reference;
        this.prix = prix;
        this.caracteristiques = caracteristiques;
        this.photo = photo;
        this.pointsFidelite = (int) (Math.random() * 20);
    }

    public int getPointsFidelite() {
        return pointsFidelite;
    }

    public void setPointsFidelite(int pointsFidelite) {
        this.pointsFidelite = pointsFidelite;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public String getReference() {
        return reference;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getCaracteristiques() {
        return caracteristiques;
    }

    //
    public void setCaracteristiques(String caracteristiques) {
        this.caracteristiques = caracteristiques;
    }

    public String getDesignation() {
        return designation;
    }

    public void setQteStock(int qteStock) {
        this.qteStock = qteStock;
    }

    public int getQteStock() {
        return qteStock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Article[ id=" + id + " ]";
    }

}
