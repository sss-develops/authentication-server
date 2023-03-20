package project.goorm.authentication.common.annotation.helper;

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@RestController
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PresentationLayer {
}
