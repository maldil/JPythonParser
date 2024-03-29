package org.jpp.exposers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates a given class should be made visible to Python code as a builtin type.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ExposedType {

    /**
     * @return the name to expose this item as. Defaults to the actual name of the class.
     */
    String name() default "";

    /**
     * @return the base type of this type. Must be another class anotated with ExposedType. If
     *         unspecified, the base is set to object, or PyObject.class.
     */
    Class base() default Object.class;

    /**
     * @return Whether this type allows subclassing.
     */
    boolean isBaseType() default true;

    /**
     * Returns the __doc__ String for this type.
     */
    String doc() default "";
}
