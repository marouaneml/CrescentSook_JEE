package session;

import entities.Article;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MAROUANE
 */
@Stateless
@LocalBean
public class GestionnaireArticle {

    @PersistenceContext
    private EntityManager em;

    public void creerArticle(Article a) {
        if (a != null) {
            em.persist(a);
            System.out.println("Article céée avec succès");
            return;
        }
        System.out.println("Articlé n'est pas céée!");
    }

    public int getQteRest(Long id) {
        Article a = findArticle(id);
        if (a != null) {
            return a.getQteStock();
        }
        return -1;
    }

    public int increment(Long id, int qt) {
        Article a = findArticle(id);
        if (a != null) {
            a.setQteStock(a.getQteStock() + qt);
            System.out.println("la qte de l'article : " + a.getDesignation() + " est incrementé");
            return a.getQteStock();
        }
        System.out.println("Erreur: incrementation echouée!");
        return -1;
    }

    public int decrement(Long id, int qt) {
        Article a = findArticle(id);
        if (a != null) {
            a.setQteStock(a.getQteStock() - qt);
            System.out.println("la qte de l'article : " + a.getDesignation() + " est décrementé");
            return a.getQteStock();
        }
        System.out.println("Erreur: décrementation echouée!");
        return -1;
    }

    public void supprimerArticle(Article a) {
        em.remove(em.merge(a));
        System.out.println("Article supprimé avec succès");
    }

    public Article findArticle(Long id) {
        return em.find(Article.class, id);
    }

    public List<Article> getAll() {
        return em.createNamedQuery("Article.findAll").getResultList();
    }

    public void creerArticlesDeTest() {

        creerArticle(new Article("ipsum dolor sit", 90, "A-032", (float) 20.86, "cursus. Integer mollis. Integer tincidunt aliquam arcu. Aliquam ultrices iaculis odio. Nam interdum enim non nisi. Aenean eget", "PHOTO-A-032"));
        creerArticle(new Article("at, libero.", 56, "B-299", (float) 167.12, "venenatis vel, faucibus id, libero. Donec consectetuer mauris id sapien.", "PHOTO-B-299"));
        creerArticle(new Article("Aliquam nec enim. Nunc", 53, "D-005", (float) 519.86, "pharetra, felis eget varius ultrices, mauris ipsum porta elit, a feugiat tellus lorem eu metus. In", "PHOTO-D-005"));
        creerArticle(new Article("dapibus ligula. Aliquam", 77, "A-711", (float) 779.27, "amet ultricies sem magna nec quam. Curabitur vel lectus. Cum sociis natoque penatibus et magnis dis", "PHOTO-A-711"));
        creerArticle(new Article("molestie tortor nibh sit", 100, "B-593", (float) 648.16, "Donec luctus aliquet odio. Etiam ligula tortor, dictum eu, placerat eget, venenatis a, magna. Lorem", "PHOTO-B-593"));
        creerArticle(new Article("convallis erat, eget tincidunt", 54, "B-148", (float) 744.08, "massa. Suspendisse eleifend. Cras sed leo. Cras vehicula aliquet libero. Integer in magna. Phasellus dolor elit, pellentesque", "PHOTO-B-148"));
        creerArticle(new Article("ultricies ligula. Nullam", 39, "A-108", (float) 95.43, "a feugiat tellus lorem eu metus. In lorem. Donec elementum, lorem ut", "PHOTO-A-108"));
        creerArticle(new Article("tincidunt. Donec vitae erat", 79, "B-912", (float) 337.13, "Suspendisse sagittis. Nullam vitae diam. Proin dolor. Nulla semper tellus id nunc interdum feugiat.", "PHOTO-B-912"));
        creerArticle(new Article("varius orci, in consequat", 32, "F-113", (float) 59.68, "Integer aliquam adipiscing lacus. Ut nec urna et arcu imperdiet ullamcorper. Duis at lacus. Quisque purus", "PHOTO-F-113"));
        creerArticle(new Article("orci lobortis", 15, "B-966", (float) 87.86, "Curabitur vel lectus. Cum sociis natoque penatibus et magnis dis", "PHOTO-B-966"));
        creerArticle(new Article("Morbi metus. Vivamus", 67, "B-474", (float) 271.86, "dolor elit, pellentesque a, facilisis non, bibendum sed, est. Nunc laoreet", "PHOTO-B-474"));
        creerArticle(new Article("ut ipsum ac mi", 55, "A-512", (float) 322.05, "Proin sed turpis nec mauris blandit mattis. Cras eget nisi dictum augue malesuada malesuada. Integer id magna", "PHOTO-A-512"));
        creerArticle(new Article("rutrum eu, ultrices sit", 68, "A-892", (float) 364.15, "Fusce aliquam, enim nec tempus scelerisque, lorem ipsum sodales purus, in", "PHOTO-A-892"));
        creerArticle(new Article("ut dolor", 86, "C-256", (float) 289.89, "hendrerit consectetuer, cursus et, magna. Praesent interdum ligula eu enim. Etiam imperdiet dictum magna. Ut tincidunt", "PHOTO-C-256"));
        creerArticle(new Article("parturient montes,", 41, "E-817", (float) 250.69, "non, luctus sit amet, faucibus ut, nulla. Cras eu tellus eu augue porttitor interdum.", "PHOTO-E-817"));
        creerArticle(new Article("id risus quis", 61, "B-990", (float) 79.28, "erat nonummy ultricies ornare, elit elit fermentum risus, at fringilla purus mauris a nunc.", "PHOTO-B-990"));
        creerArticle(new Article("ipsum cursus vestibulum. Mauris", 12, "D-957", (float) 874.22, "aliquet diam. Sed diam lorem, auctor quis, tristique ac, eleifend vitae, erat. Vivamus nisi. Mauris nulla. Integer urna.", "PHOTO-D-957"));
        creerArticle(new Article("id magna", 11, "B-980", (float) 459.46, "hymenaeos. Mauris ut quam vel sapien imperdiet ornare. In faucibus. Morbi vehicula. Pellentesque tincidunt tempus risus. Donec egestas. Duis", "PHOTO-B-980"));
        creerArticle(new Article("rhoncus. Proin nisl", 57, "C-742", (float) 375.61, "nulla. Integer vulputate, risus a ultricies adipiscing, enim mi tempor lorem, eget mollis lectus pede et risus. Quisque libero lacus,", "PHOTO-C-742"));
        creerArticle(new Article("eu, euismod ac, fermentum", 68, "F-950", (float) 553.13, "eros. Nam consequat dolor vitae dolor. Donec fringilla. Donec feugiat metus sit amet ante. Vivamus", "PHOTO-F-950"));
        creerArticle(new Article("lectus pede, ultrices", 72, "B-972", (float) 305.98, "et, magna. Praesent interdum ligula eu enim. Etiam imperdiet dictum magna. Ut tincidunt orci quis lectus. Nullam suscipit,", "PHOTO-B-972"));
        creerArticle(new Article("rhoncus. Proin nisl", 28, "D-208", (float) 699.81, "dolor, tempus non, lacinia at, iaculis quis, pede. Praesent eu dui. Cum", "PHOTO-D-208"));
        creerArticle(new Article("aliquet libero.", 60, "A-547", (float) 581.28, "ipsum ac mi eleifend egestas. Sed pharetra, felis eget varius ultrices, mauris ipsum porta elit,", "PHOTO-A-547"));
        creerArticle(new Article("in, tempus eu, ligula.", 17, "D-432", (float) 521.83, "eu dolor egestas rhoncus. Proin nisl sem, consequat nec, mollis vitae, posuere at, velit. Cras lorem lorem, luctus ut, pellentesque", "PHOTO-D-432"));
        creerArticle(new Article("faucibus leo, in lobortis", 47, "D-817", (float) 347.49, "nisl arcu iaculis enim, sit amet ornare lectus justo eu arcu. Morbi sit amet massa. Quisque porttitor", "PHOTO-D-817"));
        creerArticle(new Article("Suspendisse commodo tincidunt nibh.", 76, "E-235", (float) 711.59, "Maecenas ornare egestas ligula. Nullam feugiat placerat velit. Quisque varius. Nam porttitor scelerisque neque. Nullam nisl.", "PHOTO-E-235"));
        creerArticle(new Article("Donec sollicitudin adipiscing", 99, "E-816", (float) 161.73, "faucibus orci luctus et ultrices posuere cubilia Curae; Donec tincidunt. Donec vitae erat vel pede blandit congue. In scelerisque", "PHOTO-E-816"));
        creerArticle(new Article("habitant morbi tristique", 81, "C-450", (float) 999.18, "Nullam feugiat placerat velit. Quisque varius. Nam porttitor scelerisque neque. Nullam nisl.", "PHOTO-C-450"));
        creerArticle(new Article("Donec felis", 14, "A-158", (float) 789.39, "in faucibus orci luctus et ultrices posuere cubilia Curae; Phasellus ornare. Fusce mollis.", "PHOTO-A-158"));
        creerArticle(new Article("molestie pharetra nibh.", 57, "E-693", (float) 120.43, "et netus et malesuada fames ac turpis egestas. Aliquam fringilla cursus purus. Nullam scelerisque neque sed sem egestas", "PHOTO-E-693"));
        creerArticle(new Article("eu tempor", 97, "B-459", (float) 89.97, "erat volutpat. Nulla dignissim. Maecenas ornare egestas ligula. Nullam feugiat placerat velit. Quisque varius. Nam porttitor scelerisque neque. Nullam nisl.", "PHOTO-B-459"));
        creerArticle(new Article("nulla. Integer urna.", 12, "F-265", (float) 958.81, "turpis egestas. Aliquam fringilla cursus purus. Nullam scelerisque neque sed sem", "PHOTO-F-265"));
        creerArticle(new Article("elit. Curabitur sed", 67, "A-253", (float) 407.01, "Nulla tincidunt, neque vitae semper egestas, urna justo faucibus lectus,", "PHOTO-A-253"));
        creerArticle(new Article("Nam nulla magna,", 98, "D-309", (float) 642.96, "lorem, luctus ut, pellentesque eget, dictum placerat, augue. Sed molestie. Sed id risus quis", "PHOTO-D-309"));
        creerArticle(new Article("neque venenatis lacus.", 46, "F-580", (float) 710.23, "rhoncus. Nullam velit dui, semper et, lacinia vitae, sodales at, velit. Pellentesque ultricies dignissim lacus. Aliquam rutrum lorem", "PHOTO-F-580"));
        creerArticle(new Article("ad litora torquent", 85, "A-674", (float) 396.85, "lacinia mattis. Integer eu lacus. Quisque imperdiet, erat nonummy ultricies ornare, elit elit", "PHOTO-A-674"));
        creerArticle(new Article("nascetur ridiculus mus.", 77, "D-919", (float) 872.71, "amet orci. Ut sagittis lobortis mauris. Suspendisse aliquet molestie tellus. Aenean", "PHOTO-D-919"));
        creerArticle(new Article("sociis natoque penatibus", 26, "E-293", (float) 390.95, "odio tristique pharetra. Quisque ac libero nec ligula consectetuer rhoncus. Nullam velit dui, semper et, lacinia vitae, sodales at, velit.", "PHOTO-E-293"));
        creerArticle(new Article("velit in aliquet lobortis,", 53, "D-959", (float) 417.13, "ac mattis velit justo nec ante. Maecenas mi felis, adipiscing fringilla, porttitor vulputate, posuere vulputate,", "PHOTO-D-959"));
        creerArticle(new Article("sociosqu ad litora", 14, "C-194", (float) 661.13, "In nec orci. Donec nibh. Quisque nonummy ipsum non arcu. Vivamus sit amet risus. Donec egestas. Aliquam", "PHOTO-C-194"));
        creerArticle(new Article("lacinia vitae, sodales at,", 85, "A-991", (float) 308.97, "erat volutpat. Nulla dignissim. Maecenas ornare egestas ligula. Nullam feugiat placerat velit. Quisque varius. Nam porttitor scelerisque neque. Nullam", "PHOTO-A-991"));
        creerArticle(new Article("egestas nunc sed libero.", 51, "A-512", (float) 646.63, "egestas ligula. Nullam feugiat placerat velit. Quisque varius. Nam porttitor scelerisque neque. Nullam nisl. Maecenas malesuada fringilla est. Mauris", "PHOTO-A-512"));
        creerArticle(new Article("quis turpis vitae purus", 25, "B-027", (float) 356.07, "dui, nec tempus mauris erat eget ipsum. Suspendisse sagittis. Nullam vitae diam.", "PHOTO-B-027"));
        creerArticle(new Article("dictum eleifend,", 77, "F-141", (float) 457.91, "blandit congue. In scelerisque scelerisque dui. Suspendisse ac metus vitae velit egestas lacinia. Sed congue, elit sed", "PHOTO-F-141"));
        creerArticle(new Article("Integer sem elit, pharetra", 22, "E-491", (float) 742.69, "sem mollis dui, in sodales elit erat vitae risus. Duis a mi fringilla mi lacinia", "PHOTO-E-491"));
        creerArticle(new Article("vel, faucibus id, libero.", 28, "C-038", (float) 610.02, "sem mollis dui, in sodales elit erat vitae risus. Duis a mi fringilla mi lacinia mattis. Integer", "PHOTO-C-038"));
        creerArticle(new Article("velit. Sed malesuada", 75, "F-259", (float) 321.35, "posuere at, velit. Cras lorem lorem, luctus ut, pellentesque eget, dictum placerat,", "PHOTO-F-259"));
        creerArticle(new Article("purus mauris", 20, "B-296", (float) 143.03, "sed dolor. Fusce mi lorem, vehicula et, rutrum eu, ultrices sit amet, risus. Donec nibh enim, gravida sit amet, dapibus", "PHOTO-B-296"));
        creerArticle(new Article("Quisque ornare tortor", 97, "D-285", (float) 514.72, "amet nulla. Donec non justo. Proin non massa non ante bibendum ullamcorper.", "PHOTO-D-285"));
        creerArticle(new Article("quam. Pellentesque habitant morbi", 87, "F-895", (float) 992.30, "ut eros non enim commodo hendrerit. Donec porttitor tellus non magna.", "PHOTO-F-895"));
        creerArticle(new Article("rutrum. Fusce dolor quam,", 11, "E-525", (float) 903.95, "est. Mauris eu turpis. Nulla aliquet. Proin velit. Sed malesuada augue ut lacus. Nulla tincidunt, neque", "PHOTO-E-525"));
        creerArticle(new Article("hendrerit neque. In", 29, "E-545", (float) 108.17, "scelerisque sed, sapien. Nunc pulvinar arcu et pede. Nunc sed orci lobortis augue scelerisque mollis.", "PHOTO-E-545"));
        creerArticle(new Article("et magnis", 94, "C-870", (float) 537.92, "hendrerit neque. In ornare sagittis felis. Donec tempor, est ac mattis semper, dui lectus rutrum urna, nec", "PHOTO-C-870"));
        creerArticle(new Article("fringilla purus mauris a", 73, "E-588", (float) 720.83, "turpis. In condimentum. Donec at arcu. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;", "PHOTO-E-588"));
        creerArticle(new Article("gravida non, sollicitudin a,", 36, "B-192", (float) 107.35, "cursus luctus, ipsum leo elementum sem, vitae aliquam eros turpis non enim. Mauris quis turpis vitae purus gravida sagittis. Duis", "PHOTO-B-192"));
        creerArticle(new Article("sagittis. Nullam vitae diam.", 13, "A-705", (float) 420.71, "nonummy. Fusce fermentum fermentum arcu. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia", "PHOTO-A-705"));
        creerArticle(new Article("nunc nulla vulputate", 98, "C-327", (float) 323.28, "lectus rutrum urna, nec luctus felis purus ac tellus. Suspendisse sed dolor. Fusce mi lorem, vehicula et, rutrum eu,", "PHOTO-C-327"));
        creerArticle(new Article("magna. Suspendisse tristique neque", 93, "B-463", (float) 189.64, "nulla at sem molestie sodales. Mauris blandit enim consequat purus. Maecenas libero", "PHOTO-B-463"));
        creerArticle(new Article("erat volutpat.", 85, "F-736", (float) 339.16, "nec luctus felis purus ac tellus. Suspendisse sed dolor. Fusce mi lorem, vehicula et, rutrum eu, ultrices", "PHOTO-F-736"));
        creerArticle(new Article("consequat enim", 68, "B-911", (float) 831.46, "cursus non, egestas a, dui. Cras pellentesque. Sed dictum. Proin eget", "PHOTO-B-911"));
        creerArticle(new Article("accumsan laoreet ipsum.", 86, "E-449", (float) 370.36, "dui quis accumsan convallis, ante lectus convallis est, vitae sodales nisi magna sed dui. Fusce aliquam,", "PHOTO-E-449"));
        creerArticle(new Article("Donec non justo.", 56, "A-047", (float) 6.07, "turpis. Aliquam adipiscing lobortis risus. In mi pede, nonummy ut, molestie in, tempus eu,", "PHOTO-A-047"));
        creerArticle(new Article("sem. Pellentesque ut ipsum", 72, "F-750", (float) 355.72, "mollis. Duis sit amet diam eu dolor egestas rhoncus. Proin", "PHOTO-F-750"));
        creerArticle(new Article("mi pede, nonummy", 96, "C-801", (float) 217.55, "id nunc interdum feugiat. Sed nec metus facilisis lorem tristique aliquet. Phasellus fermentum convallis ligula. Donec luctus aliquet odio. Etiam", "PHOTO-C-801"));
        creerArticle(new Article("velit. Quisque varius.", 29, "E-758", (float) 390.07, "ut odio vel est tempor bibendum. Donec felis orci, adipiscing non, luctus sit amet, faucibus ut, nulla.", "PHOTO-E-758"));
        creerArticle(new Article("montes, nascetur", 80, "E-011", (float) 518.48, "faucibus. Morbi vehicula. Pellentesque tincidunt tempus risus. Donec egestas. Duis ac arcu. Nunc mauris. Morbi non sapien molestie", "PHOTO-E-011"));
        creerArticle(new Article("sapien. Aenean massa.", 84, "A-689", (float) 306.17, "dictum magna. Ut tincidunt orci quis lectus. Nullam suscipit, est ac facilisis facilisis, magna tellus faucibus leo,", "PHOTO-A-689"));
        creerArticle(new Article("Donec felis orci, adipiscing", 43, "D-365", (float) 660.57, "metus. In nec orci. Donec nibh. Quisque nonummy ipsum non arcu. Vivamus sit amet risus. Donec egestas.", "PHOTO-D-365"));
        creerArticle(new Article("iaculis odio.", 87, "A-769", (float) 701.17, "elit, pharetra ut, pharetra sed, hendrerit a, arcu. Sed et libero. Proin mi.", "PHOTO-A-769"));
        creerArticle(new Article("pharetra ut, pharetra", 23, "D-660", (float) 3.36, "malesuada fames ac turpis egestas. Fusce aliquet magna a neque. Nullam ut", "PHOTO-D-660"));
        creerArticle(new Article("rhoncus. Nullam velit dui,", 55, "E-229", (float) 948.93, "parturient montes, nascetur ridiculus mus. Aenean eget magna. Suspendisse tristique neque venenatis lacus. Etiam bibendum fermentum metus. Aenean sed", "PHOTO-E-229"));
        creerArticle(new Article("Aenean egestas hendrerit", 81, "A-634", (float) 819.78, "enim. Sed nulla ante, iaculis nec, eleifend non, dapibus rutrum, justo. Praesent luctus. Curabitur egestas nunc sed", "PHOTO-A-634"));
        creerArticle(new Article("ipsum cursus", 83, "B-988", (float) 952.29, "scelerisque neque sed sem egestas blandit. Nam nulla magna, malesuada vel, convallis in, cursus et, eros. Proin", "PHOTO-B-988"));
        creerArticle(new Article("felis orci,", 11, "D-127", (float) 438.53, "in sodales elit erat vitae risus. Duis a mi fringilla mi lacinia mattis. Integer eu lacus. Quisque imperdiet, erat nonummy", "PHOTO-D-127"));
        creerArticle(new Article("dis parturient", 49, "F-655", (float) 530.70, "ultrices a, auctor non, feugiat nec, diam. Duis mi enim,", "PHOTO-F-655"));
        creerArticle(new Article("purus. Maecenas libero", 44, "A-141", (float) 538.57, "eget, ipsum. Donec sollicitudin adipiscing ligula. Aenean gravida nunc sed pede. Cum sociis natoque", "PHOTO-A-141"));
        creerArticle(new Article("eleifend vitae, erat.", 86, "A-775", (float) 167.50, "primis in faucibus orci luctus et ultrices posuere cubilia Curae; Phasellus ornare. Fusce mollis. Duis sit amet diam eu dolor", "PHOTO-A-775"));
        creerArticle(new Article("non sapien", 90, "B-081", (float) 659.28, "Suspendisse tristique neque venenatis lacus. Etiam bibendum fermentum metus. Aenean sed pede nec ante blandit viverra.", "PHOTO-B-081"));
        creerArticle(new Article("erat. Sed nunc", 66, "E-438", (float) 671.28, "libero. Morbi accumsan laoreet ipsum. Curabitur consequat, lectus sit amet luctus vulputate, nisi", "PHOTO-E-438"));
        creerArticle(new Article("nec, mollis", 50, "A-464", (float) 210.82, "sodales elit erat vitae risus. Duis a mi fringilla mi lacinia", "PHOTO-A-464"));
        creerArticle(new Article("facilisis, magna tellus faucibus", 52, "A-796", (float) 550.12, "quis turpis vitae purus gravida sagittis. Duis gravida. Praesent eu", "PHOTO-A-796"));
        creerArticle(new Article("fringilla euismod enim.", 67, "A-380", (float) 34.52, "velit in aliquet lobortis, nisi nibh lacinia orci, consectetuer euismod est arcu ac orci. Ut semper", "PHOTO-A-380"));
        creerArticle(new Article("natoque penatibus et magnis", 83, "A-690", (float) 169.96, "velit eu sem. Pellentesque ut ipsum ac mi eleifend egestas. Sed pharetra, felis eget varius ultrices, mauris ipsum", "PHOTO-A-690"));
        creerArticle(new Article("Vivamus non", 35, "E-823", (float) 486.00, "et, lacinia vitae, sodales at, velit. Pellentesque ultricies dignissim lacus. Aliquam rutrum lorem ac risus.", "PHOTO-E-823"));
        creerArticle(new Article("elit sed consequat", 61, "E-822", (float) 32.94, "Nunc pulvinar arcu et pede. Nunc sed orci lobortis augue scelerisque mollis.", "PHOTO-E-822"));
        creerArticle(new Article("quam quis diam. Pellentesque", 23, "A-962", (float) 360.54, "Donec sollicitudin adipiscing ligula. Aenean gravida nunc sed pede. Cum sociis natoque penatibus et magnis dis", "PHOTO-A-962"));
        creerArticle(new Article("et ultrices", 94, "B-017", (float) 916.24, "gravida molestie arcu. Sed eu nibh vulputate mauris sagittis placerat. Cras dictum ultricies ligula. Nullam enim.", "PHOTO-B-017"));
        creerArticle(new Article("adipiscing, enim mi", 35, "B-084", (float) 640.58, "diam at pretium aliquet, metus urna convallis erat, eget tincidunt dui augue eu", "PHOTO-B-084"));
        creerArticle(new Article("elementum sem, vitae aliquam", 10, "E-798", (float) 456.84, "sollicitudin commodo ipsum. Suspendisse non leo. Vivamus nibh dolor, nonummy ac,", "PHOTO-E-798"));
        creerArticle(new Article("suscipit, est", 90, "D-588", (float) 38.66, "ante. Vivamus non lorem vitae odio sagittis semper. Nam tempor diam dictum sapien. Aenean massa. Integer vitae nibh. Donec est", "PHOTO-D-588"));
        creerArticle(new Article("a mi fringilla mi", 99, "F-951", (float) 800.72, "velit eu sem. Pellentesque ut ipsum ac mi eleifend egestas. Sed pharetra, felis", "PHOTO-F-951"));
        creerArticle(new Article("amet metus. Aliquam", 10, "A-461", (float) 54.07, "est, mollis non, cursus non, egestas a, dui. Cras pellentesque. Sed dictum. Proin eget odio. Aliquam vulputate ullamcorper magna. Sed", "PHOTO-A-461"));
        creerArticle(new Article("convallis erat, eget", 13, "D-693", (float) 75.25, "sit amet, dapibus id, blandit at, nisi. Cum sociis natoque penatibus et magnis dis parturient montes,", "PHOTO-D-693"));
        creerArticle(new Article("amet, faucibus", 94, "C-990", (float) 478.93, "Nunc ac sem ut dolor dapibus gravida. Aliquam tincidunt, nunc ac mattis ornare,", "PHOTO-C-990"));
        creerArticle(new Article("metus. Aliquam", 57, "C-858", (float) 628.26, "enim nec tempus scelerisque, lorem ipsum sodales purus, in molestie tortor nibh sit amet orci. Ut sagittis", "PHOTO-C-858"));
        creerArticle(new Article("eu tellus eu", 53, "C-497", (float) 111.63, "penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aenean eget", "PHOTO-C-497"));
        creerArticle(new Article("ante lectus convallis", 51, "B-808", (float) 616.28, "lacus. Etiam bibendum fermentum metus. Aenean sed pede nec ante blandit viverra. Donec tempus,", "PHOTO-B-808"));
        creerArticle(new Article("metus. Aenean sed", 98, "C-817", (float) 62.88, "ac urna. Ut tincidunt vehicula risus. Nulla eget metus eu erat semper rutrum. Fusce dolor quam, elementum", "PHOTO-C-817"));
        creerArticle(new Article("lorem ac risus. Morbi", 73, "C-003", (float) 327.17, "nisi nibh lacinia orci, consectetuer euismod est arcu ac orci. Ut", "PHOTO-C-003"));
        creerArticle(new Article("tincidunt, nunc ac", 59, "E-737", (float) 791.13, "dui nec urna suscipit nonummy. Fusce fermentum fermentum arcu. Vestibulum ante ipsum primis in faucibus", "PHOTO-E-737"));
    }

    public List<Article> getLast(int n) {
        return em.createNamedQuery("Article.findAll").setMaxResults(n).getResultList();
    }

    public List<Article> getRelatedArticles(Article a) {
        if (!"".equals(a.getReference().trim())) {
            return em.createNamedQuery("Article.findRelated")
                    .setParameter("ref", a.getReference().substring(0, 2)).setMaxResults(10).getResultList();
        }
        return null;
    }

    public List<Article> chercherArticle(String s, String categ) {
        return em.createNamedQuery("Article.chercherArticle")
                .setParameter("str", s)
                .setParameter("categ", categ)
                .getResultList();
    }
}
