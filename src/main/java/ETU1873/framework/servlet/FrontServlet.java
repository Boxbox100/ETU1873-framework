package ETU1873.framework.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;


public class FrontServlet extends HttpServlet {

    HashMap<String,Mapping> MappingUrls;


    public void init() throws ServletException {
        this.MappingUrls=new HashMap<>();
        File fichier= new File("/home/pri/IdeaProjects/ETU1873-framework/src/main/java/test/");
        File[] files= fichier.listFiles();
        for(File file:files)
        {
            Class classtemp=null;
            String fileName=file.getName().split(".java")[0];
            try {
                classtemp=Class.forName("test."+fileName);
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
        String prefix="http://localhost:8080/ETU1873_framework_war_exploded/";
        String bob =request.getRequestURL().toString().split(prefix)[1];

        if (MappingUrls.containsKey(bob))
        {
            Mapping map= MappingUrls.get(bob);

            out.println("nom de classe:" +map.getClassName());
            out.println("method"+ map.getMethod()) ;

        }
        else
        {
            out.println("tsy nety");
        }

        out.println(bob);
        out.println();

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
