package com.abc.io.ingest;


import com.abc.io.domain.StatementDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * StatementController is a controller for directing some specific HTTP requests  to related screens
 *
 * @author Duygu Muslu
 * @since  2020-05-06
 * @version 1.0
 */

@Controller
@RequestMapping("/")
@Slf4j
public class ViewController {

    final StatementService statementService;

    public ViewController(StatementService statementService) {
        this.statementService = statementService;
    }

    @GetMapping("/")
    public String showStatements(Model model) {
        List<StatementDto> statements = statementService.retrieveAll();
        model.addAttribute("statements",statements);
        return "index";
    }

    @RequestMapping(path = {"/edit/{reference}"})
    public String editStatementByReference(Model model, @PathVariable("reference") Long reference){
        model.addAttribute("statement", statementService.findByReference(reference));
        return "edit-statement";
    }

    @PostMapping(path = "/editStatement")
    public String editStatement(StatementDto statementDto) {
        statementService.editStatement(statementDto);
        return "redirect:/";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
