package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author MAROUANE
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Achat.findAll",
            query = "SELECT a FROM Achat a")
})
public class Achat implements Serializable {

    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private float montantTot;
    private String numCB;

    @OneToMany
    private List<Article> articles = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateExpirCarte;

    @ManyToOne
    private User consommateur;

    public Achat() {
        this.montantTot = 0;
        this.numCB = "";
        this.dateExpirCarte = null;
    }

    public Achat(float montantTot, String numCB, Date dateExpirCarte) {
        this.montantTot = montantTot;
        this.numCB = numCB;
        this.dateExpirCarte = dateExpirCarte;
    }

    public float getMontantTot() {
        return montantTot;
    }

    public void setMontantTot(float montantTot) {
        this.montantTot = montantTot;
    }

    public Date getDateExpirCarte() {
        return dateExpirCarte;
    }

    public void setDateExpirCarte(Date dateExpirCarte) {
        this.dateExpirCarte = dateExpirCarte;
    }

    public User getConsommateur() {
        return consommateur;
    }

    //
    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public void setConsommateur(User consommateur) {
        this.consommateur = consommateur;
    }

    public void setNumCB(String numCB) {
        this.numCB = numCB;
    }

    public List<Article> getArticles() {
        return articles;
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
        if (!(object instanceof Achat)) {
            return false;
        }
        Achat other = (Achat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Achat[ id=" + id + " ]";
    }

}
