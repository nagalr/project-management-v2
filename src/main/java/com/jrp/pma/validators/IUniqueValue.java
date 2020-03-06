package com.jrp.pma.validators;

/*
 we build a custom Interface, since annotations built based on Interfaces
 we use the '@' before 'interface' so it will defined as annotation
 Another definition is the @Target annotation
 and we specify that this interface related to fields in the class
 not related to the all class
*/


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
public @interface IUniqueValue {

    // this message will be displayed when there is an error
    String message() default "Unique Constraint violated";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default{};


}
