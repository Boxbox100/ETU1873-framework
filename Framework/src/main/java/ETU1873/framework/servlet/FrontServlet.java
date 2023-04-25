package ETU1873.framework.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class FrontServlet extends HttpServlet {

    HashMap<String,Mapping> MappingUrls;


    public void init() throws ServletException {
        this.MappingUrls=new HashMap<>();
        File fichier= new File("/home/pri/IdeaProjects/ETU1873-framework/testFramework/src/main/java/andrana/");
        File[] files= fichier.listFiles();
        for(File file:files)
        {
            Class<?> classtemp=null;
            String fileName=file.getName().split(".java")[0];

            try {
                classtemp=Class.forName("andrana."+fileName);

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
                    String url=method.getAnnotation(annota1.class).url();
                    String methodname=method.getName();
                    MappingUrls.put(url,new Mapping(classname,methodname));


                }
            }




        }



    }






    protected void ProcessRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        response.setContentType("text/html");
        PrintWriter out= response.getWriter();
        String prefix="/";
        String bob =request.getRequestURL().toString().split(prefix)[request.getRequestURL().toString().split(prefix).length-1];

        if (MappingUrls.containsKey(bob))
        {
            String nom = (String) request.getAttribute("nom");
            String prenom=(String) request.getAttribute("prenom");


            Mapping map= MappingUrls.get(bob);
            Class<?> tempClass=Class.forName("andrana."+map.getClassName());
            Object objet=tempClass.newInstance();
            //objet.getClass().getMethod()
            ModelView model=(ModelView)objet.getClass().getMethod(map.getMethods()).invoke(objet);
            out.println("hello");


            Set setkey =model.getData().keySet();
            for(Map.Entry m: model.getData().entrySet())
            {
                request.setAttribute((String) m.getKey(),m.getValue());
                out.println(request.getAttribute("key"));
            }

            String obj= model.getView();
            RequestDispatcher dispatch=request.getRequestDispatcher(obj);
            dispatch.forward(request,response);


        }
        else
        {
            out.println("tsy nety");
        }
    }


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
