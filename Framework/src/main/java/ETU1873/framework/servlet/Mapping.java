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



        public static String findFonction(HttpServletRequest req, Class<?> tempClass,Object object,String key)
        {
            String methodname = null;
            Method[] methods=object.getClass().getDeclaredMethods();
            for (Method method:methods)
            {
                annota1 annotation=method.getAnnotation(annota1.class);
                if (annotation!=null && annotation.url().equals(key))
                {
                    methodname=method.getName();
                    return methodname;
                }
            }
            return methodname;

        }
        public static Object[] convertArguments(String[] argumentValues, String[] argumentTypes) throws ClassNotFoundException {
            if (argumentValues.length != argumentTypes.length) {
                throw new IllegalArgumentException("Le nombre d'arguments ne correspond pas.");
            }

            Object[] arguments = new Object[argumentValues.length];
            for (int i = 0; i < argumentValues.length; i++) {
                String argumentType = argumentTypes[i];
                String argumentValue = argumentValues[i];

                Class<?> type = Class.forName(argumentType);

                Object convertedValue = null;

                if (type == String.class) {
                    convertedValue = argumentValue;}
                if (type == int.class || type == Integer.class) {
                    convertedValue = Integer.parseInt(argumentValue);}
                if (type == double.class || type == Double.class) {
                    convertedValue = Double.parseDouble(argumentValue);
                }
                // Ajoutez des conditions pour d'autres types si nécessaire

                arguments[i] = convertedValue;
            }

            return arguments;
        }
        public static Method findMethod(Class<?> clazz, String methodName, String[] argumentTypes) {
            Method[] methods = clazz.getDeclaredMethods();

            // Parcourez toutes les méthodes
            for (Method method : methods) {
                // Vérifiez si le nom de la méthode correspond
                if (method.getName().equals(methodName)) {
                    Class<?>[] parameterTypes = method.getParameterTypes();

                    // Vérifiez si les types de paramètres correspondent
                    if (parameterTypes.length == argumentTypes.length) {
                        boolean match = true;
                        for (int i = 0; i < parameterTypes.length; i++) {
                            try {
                                Class<?> parameterType = Class.forName(argumentTypes[i]);
                                if (!parameterType.isAssignableFrom(parameterTypes[i])) {
                                    match = false;
                                    break;
                                }
                            } catch (ClassNotFoundException e) {
                                match = false;
                                break;
                            }
                        }

                        if (match) {
                            return method;
                        }
                    }
                }
            }

            return null; // Aucune méthode correspondante n'a été trouvée
        }





            ///




/*else if (type==LocalDate.class) {
                    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyy-MM-dd");
                    convertedValue=LocalDate.parse(argumentValue,formatter);
                }*/




    }
