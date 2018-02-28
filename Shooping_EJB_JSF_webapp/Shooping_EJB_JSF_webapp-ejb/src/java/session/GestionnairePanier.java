package session;

import entities.Achat;
import entities.Article;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MAROUANE
 */
@Stateful
public class GestionnairePanier {

    @PersistenceContext
    private EntityManager em;

    private Achat achat = new Achat();

    public boolean AjouterArticle(Article a) {
        
        return this.achat.getArticles().add(a);
    }

    public boolean SupprimerArticle(Article a) {
        return this.achat.getArticles().remove(a);
    }

    public boolean checkout(Achat a) {
        if (a.getArticles().size() > 0) {
            em.persist(a);
            System.out.println("Achat effectué avec succès.");
            achat.getArticles().clear();
            return true;
        }
        
        System.out.println("Achat echoué!");
        return false;
    }
    public List<Achat> getAllAchats(){
        return em.createNamedQuery("Achat.findAll").getResultList();
    }

    public Achat getAchat() {
        return achat;
    }

    public void setAchat(Achat achat) {
        this.achat = achat;
    }

}
