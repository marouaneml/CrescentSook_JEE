package beans;

import entities.Article;
import entities.User;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.smartcardio.CardException;
import nfc.MyNFC;
import session.GestionnaireConsommateur;

/**
 *
 * @author MAROUANE
 */
@ManagedBean(name = "NFCMB")
@SessionScoped
public class NFCMB implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private GestionnaireConsommateur gc;

    private String readNfc;

    public String readCard() {
        MyNFC nfc = new MyNFC();
        String uid = "";
        System.out.println("readNfc : " + readNfc);
        if (readNfc != null) {
            try {
                System.out.println("Attente de lecteur NFC:");
                uid = nfc.getCardUID(30000);
                System.out.println("UID : " + uid);
            } catch (CardException ex) {
                Logger.getLogger(MyNFC.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (uid != null && !uid.isEmpty()) {
                readNfc = null;
                return uid;
            }
        }
        return null;
    }

    public String cumulerPoints(Article a) {
        MyNFC nfc = new MyNFC();
        String uid = "";
        System.out.println("readNfc : " + readNfc);
        if (readNfc != null) {
            readNfc = null;
            try {
                System.out.println("points à gagner: " + a.getPointsFidelite());
                System.out.println("Attente de lecteur NFC:");
                uid = nfc.getCardUID(30000);
                System.out.println("UID : " + uid);
                User u = gc.findUserByUID(uid);
                if( u != null){
                    u.setPoints(u.getPoints() + a.getPointsFidelite());
                    gc.majCompte(u.getEmail(), u);
                    System.out.println("Bravoo! vous aves cumuler " + a.getPointsFidelite());
                }
                
            } catch (CardException ex) {
                Logger.getLogger(MyNFC.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (uid != null && !uid.isEmpty()) {
                
                return uid;
            }
            
        }
        return null;
    }
    public String getReadNfc() {
        return readNfc;
    }

    public void setReadNfc(String readNfc) {
        this.readNfc = readNfc;
    }

}
