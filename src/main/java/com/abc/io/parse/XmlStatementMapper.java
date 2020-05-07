package com.abc.io.parse;

import com.abc.io.domain.StatementDto;
import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.http.MediaType;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * XmlStatementMapper is used for mapping XML files to list of StatementDto
 *
 * @author Duygu Muslu
 * @since  2020-05-06
 * @version 1.0
 */
@MediaTypeMap(mediaType = MediaType.APPLICATION_XML_VALUE)
public class XmlStatementMapper implements StatementMapper{
    @Override
    public List<StatementDto> map(File file) throws Exception{
        List<StatementDto> statements = new ArrayList<>();
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        for (Iterator<Element> i = root.elementIterator(); i.hasNext(); ) {
            Element e = i.next();
            StatementDto dto = new StatementDto();
            dto.setReference(Long.parseLong(e.attributeValue("reference")));
            for(Element child : e.elements()){
                BeanUtils.setProperty(dto, child.getName(),child.getText());
            }
            statements.add(dto);
        }
        return statements;
    }
}