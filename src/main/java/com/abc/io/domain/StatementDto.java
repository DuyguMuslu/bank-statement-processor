package com.abc.io.domain;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
 /**
 * StatementDto.java - a data transfer object for representing Statement class to use in application.
 * @see Statement
 *
 * @author Duygu Muslu
 * @since  2020-05-05
 * @version 1.0
 */

 @Getter
 @Setter
 public class StatementDto {

     Long id;

     @CsvBindByName(column = "Reference", required = true)
     Long reference;

     @CsvBindByName(column = "Account Number", required = true)
     String accountNumber;

     @CsvBindByName(column = "Start Balance", required = true)
     BigDecimal startBalance;

     @CsvBindByName(column = "Mutation", required = true)
     BigDecimal mutation;

     @CsvBindByName(column = "Description", required = true)
     String description;

     @CsvBindByName(column = "End Balance", required = true)
     BigDecimal endBalance;

 }


