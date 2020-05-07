package com.abc.io.parse;

import com.abc.io.exception.UnsupportedMediaTypeException;

/**
 * SupportedMediaType is used for defining and checking supported media types
 *
 * @author Duygu Muslu
 * @since  2020-05-05
 * @version 1.0
 */


public enum SupportedMediaType {
    XML("application/xml"),
    CSV("text/csv");

    String mediaType;

    SupportedMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public static SupportedMediaType fromValue(String value){
        for(SupportedMediaType s : SupportedMediaType.values()){
            if(value.equals(s.mediaType)){
                return s;
            }
        }
        throw new UnsupportedMediaTypeException("Unsupported MediaType... ");
    }
}
