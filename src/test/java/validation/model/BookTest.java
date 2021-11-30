package validation.model;

import model.enums.Genre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class BookTest {

    @Test
    public void validBook(){
        Book book = new Book.Builder("0-12-345698-9", "Moby-Dick")
                .genre(Genre.ADVENTURE_FICTION)
                .author("Herman Melville")
                .published(Year.of(2021))
                .description("description omitted for brevity").build();
    }

    @Test
    public void invalidIsbn(){
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book.Builder("0-12-34569Y8-9", "Moby-Dick")
                    .genre(Genre.ADVENTURE_FICTION)
                    .author("Herman Melville")
                    .published(Year.of(2021))
                    .description("description omitted for brevity").build();
        });

        assertThat(thrown.getMessage(), containsString("Error for field isbn"));
    }

    @Test
    public void invalidNullDescription(){
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book.Builder("0-12-345698-9", "Moby-Dick")
                    .genre(Genre.ADVENTURE_FICTION)
                    .author("Herman Melville")
                    .published(Year.of(2021))
                    .build();
        });

        assertThat(thrown.getMessage(), containsString("Error for field description"));
    }
}
