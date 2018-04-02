package beans;

import entities.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.Principal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import session.GestionnaireConsommateur;

/**
 *
 * @author MAROUANE
 */
@ManagedBean(name = "compteMB")
@SessionScoped
public class CompteMB implements Serializable {

    private Part file;
    @EJB
    private GestionnaireConsommateur gc;
    private String msg;
    private User compte;

    public String afficherCompte() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Principal principal = request.getUserPrincipal();
        compte = gc.findUserById(principal.getName());
        return "/pages/user/compte?faces-redirect=true";
    }

    public String sauvegarder() throws FileNotFoundException, IOException {

        boolean fileSuccess = false;
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context
                .getExternalContext().getContext();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Principal principal = request.getUserPrincipal();
        String fileName = "";
        if (file != null) {
            InputStream inputStream = null;
            OutputStream outputStream = null;


            if (file.getSize() > 0) {
                fileName = getFileNameFromPart(file);
                File outputFile = new File("C:\\Users\\MAROUANE\\Documents\\NetBeansProjects\\Shooping_EJB_JSF_webapp\\Shooping_EJB_JSF_webapp-war\\web\\resources\\uploads\\profils\\" + fileName.substring(0, fileName.length()-3)+"jpg");
                
                inputStream = file.getInputStream();
                outputStream = new FileOutputStream(outputFile);
                byte[] buffer = new byte[1024];
                int bytesRead = 0;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                fileSuccess = true;
            }
        }
        if (fileSuccess) {
            compte.setPhotoProfil(fileName);
            if (gc.majCompte(principal.getName(), compte)) {
                msg = "Vous données ont été mise à jour avec succès";
                return "/pages/user/compte";
            }
        }
        msg = "Erreur lors de la mise à jour!";
        return "/pages/user/compte?faces-redirect=true";
    }

    public User getCompte() {
        return compte;
    }

    public void setCompte(User compte) {
        this.compte = compte;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getFileNameFromPart(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                String fileName = content.substring(content.indexOf('=') + 1)
                        .trim().replace("\"", "");
                return fileName;
            }
        }
        return null;
    }
}
