    package andrana;
import ETU1873.framework.servlet.annota1;
import ETU1873.framework.servlet.ModelView;
public class hah {
    @annota1(url ="bob")
    public ModelView bob ()
    {
        ModelView m1=new ModelView("test.jsp");
       m1.addItem("key","coucouuuuuuu");
        return m1;
    }
    String nom;
    String prenom;

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

}
