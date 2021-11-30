package reflection;

import java.lang.reflect.Method;

public class AnnotationHandling {

    public static void handleMethods(Class<?> clazz){
        try {
            //Class<?> cls = clazz;
            //Method method = cls.getMethod("annotationTestMethod");
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method: methods) {
                if (method.isAnnotationPresent(MyAnnotation.class)) {
                    MyAnnotation myAnno = method.getAnnotation(MyAnnotation.class);
                    System.out.println("key: " + myAnno.key());
                    System.out.println("value: " + myAnno.value());
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public static void main(String ... args){

        AnnotationHandling.handleMethods(AnnotationTest.class);
    }
}
