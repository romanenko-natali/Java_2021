package main;

import annotations.ObjectToJsonConverter;
import annotations.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SuppressWarnings(value={"deprecation", "unchecked"})
public class NewMain {

    List versions = new ArrayList();
    public static void main(String[] args) {

        Date date = new Date(2009, 9, 30);

        System.out.println("date = " + date);

        Person person = new Person("ivan", "sydorets", "34uuuuu", "Address");

        ObjectToJsonConverter serializer = new ObjectToJsonConverter();
        String jsonString = serializer.convertToJson(person);
        System.out.println(jsonString);

    }

/*    @SuppressWarnings("unchecked")
    // or
    @SuppressWarnings({"unchecked"})*/
    public void addVersion(String version) {
        versions.add(version);
    }



    }
