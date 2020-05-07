package com.abc.io.parse;

import com.abc.io.exception.UnsupportedMediaTypeException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class StatementMapperFactoryTest {

    StatementMapperFactory factory;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp(){
        factory = new StatementMapperFactory();
    }

    @Test
    public void whenCsvPassedMapperReturned() throws Exception {
        StatementMapper mapper = factory.getInstance("text/csv");

        assertThat(mapper).isInstanceOf(CsvStatementMapper.class);
    }

    @Test
    public void whenXmlPassedMapperReturned() throws Exception {
        StatementMapper mapper = factory.getInstance("application/xml");

        assertThat(mapper).isInstanceOf(XmlStatementMapper.class);
    }

    @Test
    public void whenUnsupportedMediaExceptionThrown() throws Exception {
        expectedException.expect(UnsupportedMediaTypeException.class);
        StatementMapper mapper = factory.getInstance("application/duygu");
    }
}
