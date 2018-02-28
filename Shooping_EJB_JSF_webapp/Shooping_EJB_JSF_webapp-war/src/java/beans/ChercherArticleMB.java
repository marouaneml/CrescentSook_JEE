package beans;

import entities.Article;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import session.GestionnaireArticle;

/**
 *
 * @author MAROUANE
 */
@ManagedBean(name = "chercherArticleMB")
@RequestScoped
public class ChercherArticleMB {

    @EJB
    GestionnaireArticle ga;
    private String category;
    private String msg = "";
    private List<Article> articles = new ArrayList<>();

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public String chercherArticle(String s, String categ) {
        if (ga.chercherArticle("%" + s + "%", categ).size() > 0) {
            articles = ga.chercherArticle("%" + s + "%", categ);

        } else {
            msg = "Article introuvable pour la recherche: " + s;
        }
        return "/pages/all/listeArticles";
    }
}
