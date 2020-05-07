package com.abc.io.parse;

import com.abc.io.domain.StatementDto;
import com.opencsv.bean.BeanField;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.opencsv.exceptions.CsvBadConverterException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CsvStatementMapper is used for mapping CSV files to list of StatementDto
 *
 * @author Duygu Muslu
 * @since  2020-05-06
 * @version 1.0
 */

@MediaTypeMap(mediaType = "text/csv")
public class CsvStatementMapper implements StatementMapper {
    @Override
    public List<StatementDto> map(File file) throws Exception{
        return new CsvToBeanBuilder(new FileReader(file)).withType(StatementDto.class)
                .withMappingStrategy(new AnnotationStrategy(StatementDto.class))
                .withSeparator(',')
                .withIgnoreLeadingWhiteSpace(true)
                .build()
                .parse();
    }

    static class AnnotationStrategy<T> extends HeaderColumnNameTranslateMappingStrategy<T> {
        public AnnotationStrategy(Class<T> clazz){
            Map<String,String> map=new HashMap<>();
            for(Field field:clazz.getDeclaredFields()){
                CsvBindByName annotation = field.getAnnotation(CsvBindByName.class);
                if(annotation!=null){
                    map.put(annotation.column(),annotation.column());
                }
            }
            setType(clazz);
            setColumnMapping(map);
        }

        @Override
        public String[] generateHeader(T bean) throws CsvRequiredFieldEmptyException{
            String[] result=super.generateHeader(bean);
            for(int i=0;i<result.length;i++){
                result[i]=getColumnName(i);
            }
            return result;
        }

        /**
         * Overrided because input headers has spaces which causes opencsv to fail to detect fields
         * @param col
         * @return
         * @throws CsvBadConverterException
         */
        @Override
        protected BeanField<T, String> findField(int col) throws CsvBadConverterException {
            BeanField<T, String> beanField = null;
            String columnName = getColumnName(col);
            if (columnName == null) {
                return null;
            }
            columnName = columnName.trim().replaceAll("\\s","");
            if (!columnName.isEmpty()) {
                beanField = fieldMap.get(columnName.toUpperCase());
            }
            return beanField;
        }

    }
}
