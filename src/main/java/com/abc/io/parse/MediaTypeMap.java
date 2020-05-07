package com.abc.io.parse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * MediaTypeMap is used for declaring the media types
 *
 * @author Duygu Muslu
 * @since  2020-05-05
 * @version 1.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MediaTypeMap {
    String mediaType();
}
