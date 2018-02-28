package beans;

import entities.Achat;
import entities.User;
import java.io.Serializable;
import java.security.Principal;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import session.GestionnaireConsommateur;
import session.GestionnairePanier;

/**
 *
 * @author MAROUANE
 */
@ManagedBean(name = "loginMB")
@SessionScoped
public class loginMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Logger log = Logger.getLogger(loginMB.class.getName());
    @EJB
    @Inject
    private GestionnaireConsommateur userEJB;
    
    @EJB
    private GestionnairePanier gp;
    private String email;
    private String password;
    private User user;

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(email, password);
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed!", null));
            return "/pages/all/seConnecter";
        }
        Principal principal = request.getUserPrincipal();
        this.user = userEJB.findUserById(principal.getName());
        log.log(Level.INFO, "Authentication done for user: {0}", principal.getName());
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("User", user);
        if (request.isUserInRole("users")) {
            return "/pages/all/index?faces-redirect=true";
        } else {
            return "/pages/all/inscription";
        }

    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            this.user = null;
            gp.setAchat(new Achat());
            request.logout();
            ((HttpSession) context.getExternalContext().getSession(false)).invalidate();
        } catch (ServletException e) {
            log.log(Level.SEVERE, "Failed to logout user!", e);
        }
        return "/pages/all/seConnecter?faces-redirect=true";
    }

    public User getAuthenticatedUser() {
        return user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
