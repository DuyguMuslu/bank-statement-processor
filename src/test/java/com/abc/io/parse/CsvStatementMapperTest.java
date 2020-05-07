package com.abc.io.parse;

import com.abc.io.domain.StatementDto;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class CsvStatementMapperTest {

    private static File file;

    private static CsvStatementMapper mapper;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @BeforeClass
    public static void setUp() throws IOException {
        String csv = "Reference,Account Number,Description,Start Balance,Mutation,End Balance\n" +
                "1,NL93ABNA0585619023,Test Description,10,10,10";
        file = File.createTempFile("test",".csv");
        FileWriter writer = new FileWriter(file);
        writer.write(csv);
        writer.close();
        mapper = new CsvStatementMapper();
    }

    @Test
    public void whenCSVMappedThenStatementDtoListReturned() throws Exception{
        List<StatementDto> statements  = mapper.map(file);

        assertThat(statements).hasSize(1);
        assertThat(statements.get(0).getAccountNumber()).isEqualTo("NL93ABNA0585619023");
    }

    @Test
    public void whenHeaderMismatchExceptionThrown() throws Exception {
        String csv = "Account Number,Description,Start Balance,Mutation,End Balance\n" +
                "1,NL93ABNA0585619023,Test Description,10,10,10";
        file = File.createTempFile("test",".csv");
        FileWriter writer = new FileWriter(file);
        writer.write(csv);
        writer.close();

        expectedException.expect(RuntimeException.class);
        List<StatementDto> statements  = mapper.map(file);
    }
}
