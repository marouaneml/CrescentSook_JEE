package beans;

import entities.Article;
import java.io.File;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import session.GestionnaireArticle;

/**
 *
 * @author MAROUANE
 */
@ManagedBean(name = "articleMB")
@SessionScoped
public class ArticleMB implements Serializable{

    @EJB
    GestionnaireArticle ga;

    private Article article = new Article();
    
    private String msg = "";

    public ArticleMB() {
        
    }


    public Article getArticle() {
        return article;
    }

    public void setArticle(Article user) {
        this.article = user;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String creerArticleTest() {
        ga.creerArticlesDeTest();
        return "/pages/all/listeArticles?faces-redirect=true";
    }

    public List<Article> getAll() {
        if (ga.getAll().size() > 0) {
            msg ="";
            return ga.getAll();
        }
        msg = "Aucun article trouvé!";
        return null;
    }

    public Article getById(long id) {
        Article a = ga.findArticle(id);
        if (a != null) {
            return ga.findArticle(id);
        }
        return null;
    }

    public List<Article> getLast(int n) {
        if (ga.getLast(n).size() > 0) {
            msg ="";
            return ga.getLast(n);
        } else {
            msg = "Pas d'articles!";
            return null;
        }
    }

    public String supprimer(Article c) {
        ga.supprimerArticle(c);
        return "/pages/all/listeArticles?faces-redirect=true";
    }

    public String save() {

        if (article != null) {
            if (article.getPrix() != 0
                    && article.getQteStock() != 0) {
                ga.creerArticle(article);
                msg = "Nouveau article ajouté";
                return "/pages/all/listeArticles?faces-redirect=true";
            }
        }
        msg = "Des champs sont vides ou mal saisis!";
        return null;
    }

    public String voirDetailArticle(long id) {
        if (getById(id) != null) {

            article = getById(id);
            return "/pages/all/detailsArticle?id=" + id;
        }
        msg = "Article introuvalbe!";
        return "/pages/all/listeArticles?faces-redirect=true";
    }


    public List<Article> getRelatedArticles(Article a) {
        if (ga.getRelatedArticles(a) != null) {
            return ga.getRelatedArticles(a);
        }

        msg = "Pas d'articles similaires";
        return null;
    }
    public void renamePhotos() {
        String absolutePath = "C:\\Users\\MAROUANE\\Documents\\NetBeansProjects\\Shooping_EJB_JSF_webapp\\Shooping_EJB_JSF_webapp-war\\web\\resources\\uploads\\articles";
        File dir = new File(absolutePath);
        File[] filesInDir = dir.listFiles();
        int i = 0;
        String[] names = {"PHOTO-A-032", "PHOTO-B-299", "PHOTO-D-005", "PHOTO-A-711", "PHOTO-B-593", "PHOTO-B-148", "PHOTO-A-108", "PHOTO-B-912", "PHOTO-F-113", "PHOTO-B-966", "PHOTO-B-474", "PHOTO-A-512", "PHOTO-A-892", "PHOTO-C-256", "PHOTO-E-817", "PHOTO-B-990", "PHOTO-D-957", "PHOTO-B-980", "PHOTO-C-742", "PHOTO-F-950", "PHOTO-B-972", "PHOTO-D-208", "PHOTO-A-547", "PHOTO-D-432", "PHOTO-D-817", "PHOTO-E-235", "PHOTO-E-816", "PHOTO-C-450", "PHOTO-A-158", "PHOTO-E-693", "PHOTO-B-459", "PHOTO-F-265", "PHOTO-A-253", "PHOTO-D-309", "PHOTO-F-580", "PHOTO-A-674", "PHOTO-D-919", "PHOTO-E-293", "PHOTO-D-959", "PHOTO-C-194", "PHOTO-A-991", "PHOTO-A-512", "PHOTO-B-027", "PHOTO-F-141", "PHOTO-E-491", "PHOTO-C-038", "PHOTO-F-259", "PHOTO-B-296", "PHOTO-D-285", "PHOTO-F-895", "PHOTO-E-525", "PHOTO-E-545", "PHOTO-C-870", "PHOTO-E-588", "PHOTO-B-192", "PHOTO-A-705", "PHOTO-C-327", "PHOTO-B-463", "PHOTO-F-736", "PHOTO-B-911", "PHOTO-E-449", "PHOTO-A-047", "PHOTO-F-750", "PHOTO-C-801", "PHOTO-E-758", "PHOTO-E-011", "PHOTO-A-689", "PHOTO-D-365", "PHOTO-A-769", "PHOTO-D-660", "PHOTO-E-229", "PHOTO-A-634", "PHOTO-B-988", "PHOTO-D-127", "PHOTO-F-655", "PHOTO-A-141", "PHOTO-A-775", "PHOTO-B-081", "PHOTO-E-438", "PHOTO-A-464", "PHOTO-A-796", "PHOTO-A-380", "PHOTO-A-690", "PHOTO-E-823", "PHOTO-E-822", "PHOTO-A-962", "PHOTO-B-017", "PHOTO-B-084", "PHOTO-E-798", "PHOTO-D-588", "PHOTO-F-951", "PHOTO-A-461", "PHOTO-D-693", "PHOTO-C-990", "PHOTO-C-858", "PHOTO-C-497", "PHOTO-B-808", "PHOTO-C-817", "PHOTO-C-003", "PHOTO-E-737"};
        for (File file : filesInDir) {

            String name = file.getName();
            String newName = names[i] + ".jpg";
            String newPath = absolutePath + "\\" + newName;
            file.renameTo(new File(newPath));
            System.out.println(name + " changed to " + newName);
            i++;
        }
    }
}
