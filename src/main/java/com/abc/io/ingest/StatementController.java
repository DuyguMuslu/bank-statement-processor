package com.abc.io.ingest;

import com.abc.io.domain.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * StatementController is a controller for the Rest Service requests
 *
 * @author Duygu Muslu
 * @since  2020-05-06
 * @version 1.0
 */


@Controller
@RequestMapping("/upload")
@Slf4j
public class StatementController {

    final StatementService statementService;

    @Autowired
    public StatementController(StatementService statementService) {
        this.statementService = statementService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto<?>> upload(@RequestParam("file") MultipartFile file) throws Exception{
        ResponseDto<?> response = ResponseDto.builder()
                .status(HttpStatus.OK.toString())
                .body(statementService.process(file)).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
