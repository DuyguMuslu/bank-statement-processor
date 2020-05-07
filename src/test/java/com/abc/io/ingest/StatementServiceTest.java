package com.abc.io.ingest;

import com.abc.io.domain.Statement;
import com.abc.io.domain.StatementDto;
import com.abc.io.domain.StatementErrorRepository;
import com.abc.io.domain.StatementRepository;
import com.abc.io.parse.StatementMapperFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class StatementServiceTest {

    @Mock
    private StatementRepository statementRepository;
    @Mock
    StatementErrorRepository errorRepository;

    private StatementService service;

    private static MultipartFile mFile;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        ModelMapper modelMapper = new ModelMapper();
        StatementMapperFactory factory = new StatementMapperFactory();

        service = new StatementService(factory,statementRepository,errorRepository,modelMapper);
    }

    @BeforeClass
    public static void setUp(){
        String csv = "Reference,Account Number,Description,Start Balance,Mutation,End Balance\n" +
                "1,NL93ABNA0585619023,Test Description,10,10,10";
        mFile = new MockMultipartFile("text.csv","test.csv","text/csv",csv.getBytes());
    }

    @Test
    public void whenFileProcessThenTrue() throws Exception {
        String result = service.process(mFile);
        assertThat(result).isEqualTo("File processing completed, 1 statements processed, 0 statements failed, check them out on data store");
    }

    @Test
    public void whenFindByReferenceThenStatementReturned(){
        Statement statement = new Statement();
        statement.setId(1L);
        statement.setAccountNumber("121313123123");
        statement.setEndBalance(new BigDecimal(1));
        statement.setStartBalance(new BigDecimal(1));
        statement.setMutation(new BigDecimal(1));

        given(statementRepository.findByReference(1L)).willReturn(statement);

        StatementDto dto = service.findByReference(1L);


        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getAccountNumber()).isEqualTo("121313123123");
    }
}
