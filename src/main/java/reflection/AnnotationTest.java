package reflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String key();
    String value();
}


//@MyAnnotation(key = "class", value = "AnnotationTest")
public class AnnotationTest {

    @MyAnnotation(key = "method", value = "annotationTestMethod")
    public void annotationTestMethod() {

    }

    public void simpleMethod(){

    }
}
