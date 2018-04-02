package session;

import entities.User;
import entities.Group;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import utilities.AuthenticationUtils;

/**
 *
 * @author MAROUANE
 */
@Stateless
public class GestionnaireConsommateur {

    @PersistenceContext(name = "Shooping_EJB_JSF_webapp-ejbPU")
    private EntityManager em;

    public User creerConsommateur(User c) {
        try {
            c.setPassword(AuthenticationUtils.encodeSHA256(c.getPassword()));
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        Group group = new Group();
        group.setEmail(c.getEmail());
        group.setGroupname(Group.USERS_GROUP);
        em.persist(c);
        em.persist(group);
        return c;
    }

    public void supprimerConsommateur(User c) {
        Group g = (Group) em.createNamedQuery("Group.findByEmail").setParameter("email", c.getEmail()).getSingleResult();
        em.remove(em.merge(g));
        em.remove(em.merge(c));
    }

    public List<User> getAll() {
        return em.createNamedQuery("Consommateur.findAll").getResultList();
    }

    public User findUserById(String id) {
        TypedQuery<User> query = em.createNamedQuery("Consommateur.findUserById", User.class);
        query.setParameter("email", id);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public User findUserByUID(String uid) {
        TypedQuery<User> query = em.createNamedQuery("Consommateur.findUserByUID", User.class);
        query.setParameter("codeEtu", uid);
        User user = null;
        try {
            user = query.getSingleResult();
            System.out.println("User: " + user.getNom() + "\n" + 
                    "Code: " + user.getCodeEtu());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean majCompte(String email, User compte) {

        User u = (User) em.find(User.class, email);
        if (u != null) {
            u.setNom(compte.getNom());
            u.setCodeEtu(compte.getCodeEtu());
            u.setPoints(compte.getPoints());
            u.setPrenom(compte.getPrenom());
            u.setPhotoProfil(compte.getPhotoProfil());
            return true;
        }
        return false;
    }

    public void ceerConsommateursDeTest() {
        creerConsommateur(new User("Harrington", "Cassandra", 41, "Maecenas@In.edu", "1611011192999", null));
        creerConsommateur(new User("Morse", "Lara", 64, "mauris@Vivamus.com", "1636011337399", null));
        creerConsommateur(new User("Washington", "Hall", 20, "est.ac.mattis@Nulla.co.uk", "1641062394499", null));
        creerConsommateur(new User("Kramer", "Brynne", 61, "ac.arcu@velit.edu", "1698040415899", null));
        creerConsommateur(new User("Moon", "Kameko", 26, "et.libero@nondui.ca", "1662011418099", null));
        creerConsommateur(new User("Blackwell", "Nerea", 46, "varius.orci@lectuspede.org", "1643010737699", null));
        creerConsommateur(new User("Richards", "Rana", 20, "accumsan@incursus.co.uk", "1688012222699", null));
        creerConsommateur(new User("Fowler", "Melyssa", 53, "consectetuer.adipiscing@hendreritconsectetuer.com", "1623062697499", null));
        creerConsommateur(new User("Slater", "Amena", 45, "ligula.Donec.luctus@augue.co.uk", "1688011164299", null));
        creerConsommateur(new User("Horne", "Keaton", 73, "lorem.semper.auctor@semNullainterdum.edu", "1628080257299", null));
        creerConsommateur(new User("Burnett", "Akeem", 19, "lobortis@liberoMorbiaccumsan.edu", "1666073077399", null));
        creerConsommateur(new User("Estrada", "Kevyn", 47, "elit.Curabitur.sed@vellectus.edu", "1620072784299", null));
        creerConsommateur(new User("Head", "Nomlanga", 43, "nec@feugiat.co.uk", "1635121227399", null));
        creerConsommateur(new User("Vega", "Cheyenne", 44, "conubia.nostra@dis.edu", "1672061063599", null));
    }

}
