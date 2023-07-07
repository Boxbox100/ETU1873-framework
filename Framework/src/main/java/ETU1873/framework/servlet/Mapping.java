    package ETU1873.framework.servlet;

    import jakarta.servlet.http.HttpServletRequest;

    import java.io.PrintWriter;
    import java.lang.reflect.Field;
    import java.lang.reflect.InvocationTargetException;
    import java.lang.reflect.Method;
    import java.lang.reflect.Parameter;
    import java.time.LocalDate;
    import java.time.format.DateTimeFormatter;
    import java.util.Arrays;
    import java.util.Enumeration;
    import java.util.HashMap;
    import java.util.Locale;

    import static java.lang.System.out;

    public class Mapping {
        String className;
        String method;

        public Mapping(String className, String method) {
            this.className = className;
            this.method = method;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getMethods() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public static String Maj(String mot)
        {
            return mot.substring(0,1).toUpperCase()+ mot.substring(1);
        }
        public static boolean verifierField(Field[] fields, HttpServletRequest request)
        {
            for (int i = 0; i < fields.length ; i++) {
                if (request.getClass().getDeclaredFields()[i]!=null)
                {
                    return true;
                }


            }
            return false;
        }
        public static Object getForm(Field[] fields, HttpServletRequest request, Object object, PrintWriter out) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException
        {
            Enumeration<String>attributsNames = request.getParameterNames();
            /*while(attributsNames.hasMoreElements()){
                String attributename= attributsNames.nextElement();
                String attributevalue= (String)request.getParameter(attributename);
                Field field = object.getClass().getDeclaredField(attributename);
                out.println(field.getName());
                out.println(attributevalue);
            }*/

            for (Field fieldUnder:
                 fields) {
                //out.println(fieldUnder.getName());
            }

                    for (int i = 0; i < fields.length ; i++) {
                        String arg=request.getParameter(fields[i].getName());
                        //out.println(arg);
                        Method method1 = object.getClass().getMethod("set"+Maj(fields[i].getName()), String.class);
                       method1.invoke(object, arg);
                      //out.println(object.getClass().getMethod("set"+Maj(fields[i].getName())).invoke(object));
                    }


                return object;
            }












            ///




/*else if (type==LocalDate.class) {
                    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyy-MM-dd");
                    convertedValue=LocalDate.parse(argumentValue,formatter);
                }*/




    }
