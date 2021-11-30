package model;

import model.enums.Genre;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.*;
import java.time.Year;
import java.util.Set;

public class BookNew {
    @Pattern(regexp = "\\d-\\d{2}-\\d{6}-\\d", message = "The format for ISBN should be like '1-23-234567-2'")
    private String isbn;

    @Size(min = 2, max = 100, message
            = "Title must be between 2 and 100 characters")
    private String title;

    private Genre genre;

    @NotBlank
    private String author;

    @PastOrPresent
    private Year published;
    private String description;

    private BookNew(){}

    private BookNew(Builder builder) {
        this.isbn = builder.isbn;
        this.title = builder.title;
        this.genre = builder.genre;
        this.author = builder.author;
        this.published = builder.published;
        this.description = builder.description;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public Year getPublished() {
        return published;
    }

    public String getDescription() {
        return description;
    }

    public static class Builder {
        private final String isbn;
        private final String title;
        private Genre genre;
        private String author;
        private Year published;
        private String description;

        public Builder(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }

        public Builder genre(Genre genre) {
            this.genre = genre;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder published(Year published) {
            this.published = published;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public BookNew build() throws IllegalArgumentException {
            validate();
            return new BookNew(this);
        }

        private void validate() throws IllegalArgumentException {

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            BookNew book = new BookNew(this);

            Set<ConstraintViolation<BookNew>> violations = validator.validate(book);

            StringBuilder mb = new StringBuilder();

             for (ConstraintViolation<BookNew> violation : violations) {
                 mb.append("Error for field " + violation.getPropertyPath() + ": '"+ violation.getInvalidValue() + " " + violation.getMessage()).append("\n");
            }

            if (mb.length() > 0) {
                throw new IllegalArgumentException(mb.toString());
            }
        }
    }
}

