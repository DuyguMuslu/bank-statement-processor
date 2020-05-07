package com.abc.io.ingest;

import com.abc.io.Utility;
import com.abc.io.domain.*;
import com.abc.io.parse.StatementMapper;
import com.abc.io.parse.StatementMapperFactory;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * StatementService is a business service facade for Statement-related services
 *
 * @author Duygu Muslu
 * @since  2020-05-06
 * @version 1.0
 */


@Service
@Slf4j
public class StatementService {

    StatementMapperFactory factory;
    StatementRepository repository;
    StatementErrorRepository errorRepository;
    ModelMapper modelMapper;

    @Autowired
    public StatementService(StatementMapperFactory factory,
                            StatementRepository repository,
                            StatementErrorRepository errorRepository,
                            ModelMapper modelMapper) {
        this.factory = factory;
        this.repository = repository;
        this.errorRepository = errorRepository;
        this.modelMapper = modelMapper;
    }

    public String process(MultipartFile mFile) throws Exception{
        StatementMapper mapper = factory.getInstance(mFile.getContentType());
        List<StatementDto> statements = mapper.map(Utility.convert(mFile));
        List<Statement> entities = statements.stream().map(this::convertToEntity).collect(Collectors.toList());
        List<StatementError> invalidStatements = new ArrayList<>();
        entities.forEach(s -> {
            try{
                repository.save(s);
            }catch (Exception e){
                invalidStatements.add(new StatementError(s,e.getCause().getCause().getMessage()));
                log.error(e.getCause().getCause().getMessage());
            }
        });
        invalidStatements.forEach(e -> errorRepository.save(e));
        return "File processing completed, " + entities.size() + " statements processed, " + invalidStatements.size() + " statements failed, check them out on data store";
    }

    public StatementDto findByReference(Long reference){
        return convertToDto(repository.findByReference(reference));
    }

    public void editStatement(StatementDto statementDto) {
        repository.save(convertToEntity(statementDto));
    }

    public List<StatementDto> retrieveAll(){
        return repository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private Statement convertToEntity(StatementDto dto) {
        return modelMapper.map(dto, Statement.class);
    }

    private StatementDto convertToDto(Statement entity) {
        return modelMapper.map(entity, StatementDto.class);
    }

}
