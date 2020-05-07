package com.abc.io.parse;


import com.abc.io.domain.StatementDto;

import java.io.File;
import java.util.List;

/**
 * StatementMapper is an interface for the mappers
 *
 * @author Duygu Muslu
 * @since  2020-05-05
 * @version 1.0
 */

public interface StatementMapper{

    List<StatementDto> map(File file) throws Exception;
}
