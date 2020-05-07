package com.abc.io.parse;

import com.abc.io.domain.StatementDto;
import org.dom4j.DocumentException;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlStatementMapperTest {

    private static File file;

    private static XmlStatementMapper mapper;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @BeforeClass
    public static void setUp() throws IOException {
        String xml = "<records>\n" +
                "  <record reference=\"1\">\n" +
                "    <accountNumber>NL91RABO0315273637</accountNumber>\n" +
                "    <description>Test</description>\n" +
                "    <startBalance>10</startBalance>\n" +
                "    <mutation>10</mutation>\n" +
                "    <endBalance>10</endBalance>\n" +
                "  </record></records>";
        file = File.createTempFile("test",".xml");
        FileWriter writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
        mapper = new XmlStatementMapper();
    }

    @Test
    public void whenXMLMappedThenStatementDtoListReturned() throws Exception{
        List<StatementDto> statements  = mapper.map(file);

        assertThat(statements).hasSize(1);
        assertThat(statements.get(0).getAccountNumber()).isEqualTo("NL91RABO0315273637");
    }


    @Test
    public void whenInvalidXmlDocumentExceptionThrown() throws Exception {
        String csv = "<records>\n" +
                "  <record reference=\"1\">\n" +
                "    <accountNumber>NL91RABO0315273637</accountNumber>\n" +
                "    <description>Test</description>\n" +
                "    <startBalance>10</startBalance>\n" +
                "    <mutation>10</mutation>\n" +
                "    <endBalance>10</endBalance>\n" +
                "  </record>";
        file = File.createTempFile("test",".xml");
        FileWriter writer = new FileWriter(file);
        writer.write(csv);
        writer.close();

        expectedException.expect(DocumentException.class);
        List<StatementDto> statements  = mapper.map(file);
    }
}
