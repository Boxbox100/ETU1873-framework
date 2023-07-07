package ETU1873.framework.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

import static ETU1873.framework.servlet.Mapping.*;


public class FrontServlet extends HttpServlet {

    HashMap<String,Mapping> MappingUrls;
    HashMap<String,Object>map_singleton;

    public HashMap<String, Object> getMap_singleton() {
        return map_singleton;
    }

    public void setMap_singleton(HashMap<String, Object> map_singleton) {
        this.map_singleton = map_singleton;
    }

    public HashMap<String, Mapping> getMappingUrls() {
        return MappingUrls;
    }

    public void setMappingUrls(HashMap<String, Mapping> mappingUrls) {
        MappingUrls = mappingUrls;
    }

    public void init() throws ServletException {
        this.MappingUrls=new HashMap<>();
        this.map_singleton = new HashMap<>();
        File fichier= new File("/home/pri/IdeaProjects/ETU1873-framework/testFramework/src/main/java/andrana/");
        File[] files= fichier.listFiles();
        for(File file:files)
        {
            Class<?> classtemp=null;
            String fileName=file.getName().split(".java")[0];

            try {
                classtemp=Class.forName("andrana."+fileName);
                this.checkSingleton(classtemp);

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            Object obj=null;
            try {
                obj=classtemp.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            Method[] list_method=obj.getClass().getDeclaredMethods();
            for(Method method:list_method)
            {
                if (method.isAnnotationPresent(annota1.class))
                {
                    String classname=obj.getClass().getSimpleName();
                    String url = method.getAnnotation(annota1.class).url();
                    String methodname=method.getName();
                    MappingUrls.put(url,new Mapping(classname,methodname));


                }

            }




        }



    }
    public void reset(Object objet) throws IllegalAccessException, InvocationTargetException{
        Field[] lfield = objet.getClass().getDeclaredFields();
        for (Field field : lfield) {
            String fieldName = upperFirst(field.getName());
            Method methodSet = null;
            try {
                methodSet = objet.getClass().getMethod("set"+fieldName, field.getType());
            } catch (Exception e) {
                continue;
            }
            if(field.getType().equals(int.class)){
                methodSet.invoke(objet, 0);
            }
            if(field.getType().equals(double.class)){
                methodSet.invoke(objet, 0);
            }
            if(field.getType().equals(float.class)){
                methodSet.invoke(objet, 0);
            }
            if(field.getType().equals(Object.class)){
                methodSet.invoke(objet, (Object) null);
            }
        }
    }
    public Object getInClassInstance(String className,Class<?> tempclass) throws IllegalAccessException, InvocationTargetException, InstantiationException{
        Object objet = null;
        if(this.getMap_singleton().containsKey(className)){
            Object objet2 = this.getMap_singleton().get(className);
            if(objet2 == null){
                objet2 = tempclass.newInstance();
                objet = objet2;
                this.getMap_singleton().put(className, objet);
            }else{
                reset(objet2);
                objet = objet2;
            }
        }
        else{
            objet = tempclass.newInstance();
        }
        return objet;
    }



    public void checkSingleton(Class check){
        if(check.isAnnotationPresent(Singleton.class)){

            String className = check.getName();
            this.getMap_singleton().put(className, null);
        }
    }

//////////////////////////
public Method getMethod(String method, Object obj) {
    Method[] listeMethod = obj.getClass().getDeclaredMethods();
    Method valiny = null;
    for (Method method1 : listeMethod) {
        if (method1.getName().equals(method)) {
            valiny = method1;
        }
    }
    return valiny;
}
    public String upperFirst(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }



public ModelView getModeleView(String url, String[] params, String[] values,PrintWriter out) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
    ModelView valiny = null;
    if (getMappingUrls().get(url) instanceof Mapping) {
        Mapping util = getMappingUrls().get(url);
        Class<?> classname= Class.forName("andrana."+util.getClassName());
        Object test = this.getInClassInstance(classname.getName(), classname);
        Method m = this.getMethod(util.getMethods(), test);
        Field[] fields = classname.getDeclaredFields();
        Parameter[] parametres = m.getParameters();
        Object[] tableau = new Object[parametres.length];
        String[] allparm = new String[0];
        for (Field field : fields) {
            for (int i = 0; i < params.length; i++) {
                if (params[i].equals(field.getName())) {
                    Method setobject = classname.getMethod("set" + this.upperFirst(params[i]), field.getType());
                    Object type = null;
                    if (field.getType() == String.class) {
                        type = values[i];

                    } else if (field.getType() == int.class || field.getType() == Integer.class) {
                        type = Integer.valueOf(values[i]);
                    } else if (field.getType() == double.class || field.getType() == Double.class) {
                        type = Double.valueOf(values[i]);
                    } else if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                        type = Boolean.valueOf(values[i]);
                    }
                    allparm = Arrays.copyOf(allparm, allparm.length + 1);
                    ///////on met le nom de la classe de l attribut dans le tableau
                    allparm[allparm.length - 1] = type.getClass().getName();
                    //miantso setter
                    setobject.invoke(test, type);
                }
            }
        }
        int x = 0;
        for (int j = 0; j < parametres.length; j++) {
            for (int i = 0; i < params.length; i++) {
                if (params[i].equals(parametres[j].getName())) {
                    Object type = null;
                    if (parametres[j].getType() == String.class) {
                        type = values[i];
                    } else if (parametres[j].getType() == int.class || parametres[j].getType() == Integer.class) {
                        type = Integer.valueOf(values[i]);
                    } else if (parametres[j].getType() == double.class || parametres[j].getType() == Double.class) {
                        type = Double.valueOf(values[i]);
                    } else if (parametres[j].getType() == boolean.class || parametres[j].getType() == Boolean.class) {
                        type = Boolean.valueOf(values[i]);
                    }
                    tableau[j] = type;
                    int f = 0;
                }
            }
        }
        valiny = (ModelView) m.invoke(test, tableau);
    }
    return valiny;
}
    public String[] getParams(HttpServletRequest request)
    {
        Enumeration<String> parameters = request.getParameterNames();
        String[] attributs = new String[0];
        String[] values = new String[0];
        while (parameters.hasMoreElements()) {
            String parametre = parameters.nextElement();
            String value = request.getParameter(parametre);
            attributs = Arrays.copyOf(attributs, attributs.length + 1);
            values = Arrays.copyOf(values, values.length + 1);
            attributs[attributs.length - 1] = parametre;
            values[values.length - 1] = value;
        }
        return attributs;
    }
    public String[] getValue(HttpServletRequest request)
    {
        Enumeration<String> parameters = request.getParameterNames();
        String[] attributs = new String[0];
        String[] values = new String[0];
        while (parameters.hasMoreElements()) {
            String parametre = parameters.nextElement();
            String value = request.getParameter(parametre);
            attributs = Arrays.copyOf(attributs, attributs.length + 1);
            values = Arrays.copyOf(values, values.length + 1);
            attributs[attributs.length - 1] = parametre;
            values[values.length - 1] = value;
        }
        return values;
    }




////////////////////////////////







    protected void ProcessRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        PrintWriter out= response.getWriter();
        try{
        response.setContentType("text/html");

        String prefix="/";
        String separateur="!";
        String key =request.getRequestURL().toString().split(prefix)[request.getRequestURL().toString().split(prefix).length-1];
        String[] lparametres =key.split(separateur);
        key=lparametres[0];


        if (MappingUrls.containsKey(key))
        {
            String nom = (String) request.getAttribute("nom");
            String prenom=(String) request.getAttribute("prenom");
            Field[] fields = request.getClass().getDeclaredFields();


            Mapping map=MappingUrls.get(key);
            Class<?> tempClass=Class.forName("andrana."+map.getClassName());
            Object objet=tempClass.newInstance();
            Field [] fields1=objet.getClass().getDeclaredFields();

            ///////sprint7 formulaire

            if (map.verifierField(fields1,request))
            {
                map.getForm(fields1,request,objet,out);
            }

            //////////sprint8
            String[] values=getValue(request);
            String[] attributs=getParams(request);

            ModelView model8=null;
            out.println(key);
            for (int i = 0; i < attributs.length ; i++) {
                out.println(attributs[i]);
            }
            for (int i = 0; i < values.length ; i++) {
                out.println(values[i]);
            }


            if (getModeleView(key, attributs, values,out) != null) {
                model8 = getModeleView(key, attributs, values,out);
                String page = model8.getView();
                for (Map.Entry m : model8.getData().entrySet()) {
                    request.setAttribute((String) m.getKey(), m.getValue());
                }

            }

            for(Map.Entry m: model8.getData().entrySet())
            {
                request.setAttribute((String) m.getKey(),m.getValue());
                //out.println(request.getAttribute("key"));
            }


            String obj= model8.getView();
            out.println(obj);
            RequestDispatcher dispatch=request.getRequestDispatcher(obj);
            dispatch.forward(request,response);


        }
        else
        {
            out.println("tsy nety");
        }
    }
    catch (Exception e) {
        e.printStackTrace(out);
    }}



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {


        try {
            ProcessRequest(request,response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            ProcessRequest(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
