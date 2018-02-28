package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author MAROUANE
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Consommateur.findAll",
            query = "SELECT c FROM User c"),
    @NamedQuery(name = "Consommateur.findUserById",
            query = "SELECT u FROM User u WHERE u.email = :email")
})

@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "email", nullable = false, length = 255)
    private String email;

    private String nom;
    private String prenom;
    private int age;

    @Column(name = "password", nullable = false, length = 64)
    private String password;
    
    private String photoProfil;

    @OneToMany(mappedBy = "consommateur")
    private List<Achat> achats = new ArrayList<>();

    public User() {
        this.nom = "";
        this.prenom = "";
        this.age = 0;
        this.email = "";
        this.password = "";
        this.photoProfil = null;
    }

    public User(String nom, String prenom, int age, String email, String password, String photo) {

        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.email = email;
        this.password = password;
        this.photoProfil = photo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getPhotoProfil() {
        return photoProfil;
    }

    //
    public void setAchats(List<Achat> achats) {
        this.achats = achats;
    }

    public List<Achat> getAchats() {
        return achats;
    }

    public void setPhotoProfil(String photoProfil) {
        this.photoProfil = photoProfil;
    }

    public void setAchats(ArrayList<Achat> achats) {
        this.achats = achats;
    }

}
