package reflection;

import org.apache.commons.lang3.StringUtils;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {

    public static final int REPEAT = 40;

    public static void main(String[] args) {

        //FIELDS
        System.out.println(StringUtils.repeat("-", REPEAT));
        try {

            Dog d1 = new Dog();
            Class<? extends Dog> obj = d1.getClass();

            Field field1 = obj.getField("type");
            field1.set(d1, "labrador");
            String typeValue = (String) field1.get(d1);
            System.out.println("Value: " + typeValue);


            int mod = field1.getModifiers();

            String modifier1 = Modifier.toString(mod);
            System.out.println("Modifier: " + modifier1);
            System.out.println(" ");

            Field field2 = obj.getDeclaredField("color"); // private field
            field2.setAccessible(true);
            field2.set(d1, "brown");
            String colorValue = (String) field2.get(d1);
            System.out.println("Value: " + colorValue);

        } catch (NoSuchFieldException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
        System.out.println(StringUtils.repeat("-", REPEAT));


        //MODIFIERS
        System.out.println(StringUtils.repeat("-",REPEAT));
        Class<Dog> obj = Dog.class;

        Method[] methods = obj.getDeclaredMethods();

        for (Method m : methods) {

            System.out.println("Method Name: " + m.getName());
            int modifier = m.getModifiers();
            System.out.println("Modifier: " + Modifier.toString(modifier));
            System.out.println("Return Types: " + m.getReturnType());
            System.out.println(" ");
        }
        System.out.println(StringUtils.repeat("-",REPEAT));

        //CONSTRUCTORS
        System.out.println(StringUtils.repeat("-",REPEAT));
        try {
            // create an object of Dog
            Dog d1 = new Dog();
            Class obj1 = d1.getClass();

            Constructor[] constructors = obj.getDeclaredConstructors();

            for (Constructor c : constructors) {

                // get the name of constructors
                System.out.println("Constructor Name: " + c.getName());

                // get the access modifier of constructors
                // convert it into string form
                int modifier = c.getModifiers();
                String mod = Modifier.toString(modifier);
                System.out.println("Modifier: " + mod);

                // get the number of parameters in constructors
                System.out.println("Parameters: " + c.getParameterCount());
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(StringUtils.repeat("-",REPEAT));

        //METHODS
        System.out.println(StringUtils.repeat("-",REPEAT));
        try {


            Method returnFive = Dog.class.getDeclaredMethod("returnFive");

            int result
                    = (Integer) returnFive.invoke(null);
            System.out.println("Return 5 ? " + result);


            Method withTwoParams
                    = Dog.class.getMethod("MethodWithDoubleStringParameters", double.class, String.class);

            Dog dog = new Dog();
            result
                    = (Integer) withTwoParams.invoke(dog, 1, "3");
            System.out.printf("Invoke %s with params %d, %s: %s %n" , "MethodWithDoubleStringParameters", 1, "3", result);

        final MethodHandles.Lookup lookup = MethodHandles.lookup();
        final MethodType methodType = MethodType.methodType( int.class );
        final MethodHandle methodHandle;

            methodHandle = lookup.findVirtual( String.class, "length", methodType );

        final int length = ( int )methodHandle.invokeExact( "sample string" );
            System.out.printf("Length of word '%s' is %d %n", "sample string", length);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println(StringUtils.repeat("-",REPEAT));

    }
}