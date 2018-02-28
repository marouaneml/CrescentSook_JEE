package beans;

import entities.User;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import session.GestionnaireConsommateur;

/**
 *
 * @author MAROUANE
 */
@ManagedBean(name = "consommateurMB")
@RequestScoped
public class ConsommateurMB {

    @EJB
    GestionnaireConsommateur gc;
    private User user = new User();
    private String msg = "";

    public ConsommateurMB() {
    }

    public String creerConsommateurTest() {
        gc.ceerConsommateursDeTest();
        return "/pages/admin/listeUtilisateurs?faces-redirect=true";
    }

    public List<User> getAll() {
        if (gc.getAll().size() > 0) {
            return gc.getAll();
        } else {
            msg = "Aucun utilisateur trouvé!";
            return null;
        }
    }

    public String supprimer(User c) {
        gc.supprimerConsommateur(c);
        return "/pages/admin/listeUtilisateurs?faces-redirect=true";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String save() {

        if (user != null) {
            if (user.getEmail() != ""
                    && user.getNom() != ""
                    && user.getPassword() != ""
                    && user.getAge() > 17
                    && user.getPrenom() != "") {
                
                gc.creerConsommateur(user);
                msg = "Nouveau consommateur ajouté";
                return "/pages/all/regisok?faces-redirect=true";
            }
        }
        msg = "Des champs sont vides ou mal saisis!";
        return null;
    }
}
