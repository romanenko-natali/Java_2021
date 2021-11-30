package reflection;

import java.lang.Class;
import java.lang.reflect.*;

class Dog {

    public String type;
    private String color;

    public Dog() {
    }

    private Dog(int age) {
    }

    public void display() {
        System.out.println("I am a dog.");
    }

    private void makeSound() {
        System.out.println("Bark Bark");
    }

    public static int returnFive(){
        return 5;
    }
    public int MethodWithDoubleStringParameters(double d, String s){
        return (int)d + s.length();
    }
}


