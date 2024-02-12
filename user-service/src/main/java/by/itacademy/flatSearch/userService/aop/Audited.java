package by.itacademy.flatSearch.userService.aop;

import by.itacademy.exceptions.enums.action.Actions;
import by.itacademy.exceptions.enums.action.EssenceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Audited {
    Actions action();
    EssenceType essenceType();
}
