    package andrana;
import ETU1873.framework.servlet.annota1;
import ETU1873.framework.servlet.ModelView;
import ETU1873.framework.servlet.Singleton;
@Singleton(isSingleton= true)
public class hah {
    @annota1(url ="bob")
    public ModelView bob ()
    {
        ModelView m1=new ModelView("test.jsp");
        this.setPrenom(this.getPrenom()+"1");
       m1.addItem("key",this);
        return m1;
    }
    @annota1(url="upload")
    public ModelView upload ()
    {
        ModelView m1=new ModelView("upload.jsp");
        m1.addItem("key",this);
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
    public void setNom() {
        this.nom="boboboboobbob";
    }
    public void setPrenom() {
        this.prenom="befrbvergre";
    }
    @annota1(url="parametre")
    public ModelView test(String nom,String prenom )

    {
        ModelView m1=new ModelView("test.jsp");
        this.setNom(nom);
        this.setPrenom(prenom);
        m1.addItem("key",this);
        return m1;
    }

    @annota1(url="nombre")
    public int getnombre(int a,int b)
    {
        int c=a+b;
        return c;
    }
}
