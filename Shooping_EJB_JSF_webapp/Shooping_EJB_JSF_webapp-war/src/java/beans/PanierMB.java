package beans;

import entities.Achat;
import entities.Article;
import entities.User;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import session.GestionnaireConsommateur;
import session.GestionnairePanier;

/**
 *
 * @author MAROUANE
 */
@ManagedBean(name = "panierMB")
@SessionScoped
public class PanierMB {

    private Date dateExp;
    @EJB
    private GestionnaireConsommateur gc;
    @EJB
    GestionnairePanier gp;
    private String msg = "";
    private String cb = "";

    public String AjouterArticle(Article a) {
        if (gp.AjouterArticle(a)) {
            msg = "l'article " + a.getDesignation() + " est bien ajouté dans votre panier";
            return "";
        }
        return "";
    }
    

    public String supprimer(Article a) {
        if (gp.SupprimerArticle(a)) {
            msg = "l'article " + a.getDesignation() + " est bien supprimé de votre panier";
            return "/pages/user/panier?faces-redirect=true";
        }
        return "/pages/user/panier";
    }

    public String validerAchat() {
        if (cb.length() == 16 && isNumeric(cb)) {
            float tot = 0;
            Achat a = new Achat(tot, cb, dateExp);
            for (Article ach : gp.getAchat().getArticles()) {
                tot += ach.getPrix();
                a.getArticles().add(ach);
            }

            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            Principal principal = request.getUserPrincipal();
            User u = gc.findUserById(principal.getName());
            a.setConsommateur(u);
            a.setMontantTot(tot);
            if (gp.checkout(a)) {
                msg = "";
                return "/pages/user/achatValide?faces-redirect=true";
            }

        }
        return "/pages/user/panier";
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    private boolean carteValide(String numCarte) {
        String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|"
                + "(?<mastercard>5[1-5][0-9]{14})|"
                + "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|"
                + "(?<amex>3[47][0-9]{13})|"
                + "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|"
                + "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(numCarte);
        if (matcher.matches()) {
            System.out.println("Carte bancaire valide");
            return true;
        }
        System.out.println("Carte bancaire n'est pas valide!");
        return false;
    }

    public List<Achat> getAllachats() {
        return gp.getAllAchats();
    }

    public Achat getArticles() {
        return gp.getAchat();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCb() {
        return cb;
    }

    public void setCb(String cb) {
        this.cb = cb;
    }

    public Date getDateExp() {
        return dateExp;
    }

    public void setDateExp(Date dateExp) {
        this.dateExp = dateExp;
    }

}
