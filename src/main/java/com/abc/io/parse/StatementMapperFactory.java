package com.abc.io.parse;

import com.abc.io.exception.UnsupportedMediaTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.StandardServletEnvironment;

/**
 * StatementMapperFactory is used for creating new instance with the suitable Statement mapper based on the uploaded file type
 *
 * @author Duygu Muslu
 * @since  2020-05-05
 * @version 1.0
 */

@Slf4j
@Component
public class StatementMapperFactory {

    private static final String packageName = "com.abc.io.parse";
    public StatementMapper getInstance(String mediaType) throws Exception{
        return findSuitableMapper(mediaType);
    }

    public StatementMapper findSuitableMapper(String mediaType) throws Exception{
        final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true, new StandardServletEnvironment());
        provider.addIncludeFilter(new AnnotationTypeFilter(MediaTypeMap.class));
        Class<?> suitableMapper = null;
        for (BeanDefinition beanDefinition : provider.findCandidateComponents(packageName)) {
            try {
                Class<?> mapperClass = Class.forName(beanDefinition.getBeanClassName());
                MediaTypeMap annotation = AnnotationUtils.getAnnotation(mapperClass ,MediaTypeMap.class);
                if(annotation != null && annotation.mediaType().equals(mediaType)){
                    suitableMapper = mapperClass;
                }
            } catch (ClassNotFoundException e) {
                log.warn("Could not resolve class object for bean definition", e);
            }
        }
        if(suitableMapper == null){
            throw new UnsupportedMediaTypeException("No suitable mapper found for this media type..");
        }
        return (StatementMapper)suitableMapper.getDeclaredConstructor().newInstance();
    }
}
