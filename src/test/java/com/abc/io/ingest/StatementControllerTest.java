package com.abc.io.ingest;

import com.abc.io.config.WebTestConfig;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(StatementController.class)
@ContextConfiguration(classes = WebTestConfig.class)
public class StatementControllerTest {

    @MockBean
    private StatementService statementService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mvc;

    private static MockMultipartFile mFile;

    @BeforeClass
    public static void setUp(){
        String csv = "Reference,Account Number,Description,Start Balance,Mutation,End Balance\n" +
                "1,NL93ABNA0585619023,Test Description,10,10,10";
        mFile = new MockMultipartFile("text.csv","test.csv","text/csv",csv.getBytes());
    }

    @Test
    public void whenUploadCsvThenSuccess() throws Exception {
        given(statementService.process(mFile)).willReturn("File processing completed, 1 statements processed, 0 statements failed, check them out on data store");

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
                .file("file",mFile.getBytes()))
                .andExpect(jsonPath("$.message").value("Success"))
                .andReturn().getResponse();
    }
}