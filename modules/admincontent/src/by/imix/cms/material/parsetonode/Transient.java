package by.imix.cms.material.parsetonode;

import java.lang.annotation.*;

/**
 * Created by miha on 07.10.2014.
 */

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(value= RetentionPolicy.RUNTIME)
@Inherited
public @interface Transient {
}
