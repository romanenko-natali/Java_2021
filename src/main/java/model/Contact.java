package model;


import lombok.*;

@RequiredArgsConstructor
@NoArgsConstructor
@ToString(includeFieldNames = true, exclude = {"id", "email", "age"})
public class Contact {

    private @Getter
    @Setter
    Long id;

    private @Getter
    @Setter
    @NonNull String firstName;

    private @Getter
    @Setter
    @NonNull String lastName;

    private @Getter
    @Setter
    String email;

    private @Getter
    @Setter
    int age;

    public static void main(String[] args) {
        //Contact contact = new Contact("First", "Second");
        //Contact contact2 = new Contact();
        //System.out.println(contact);
    }
}