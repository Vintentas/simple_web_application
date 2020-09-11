package org.example.springtest.controllers;

import lombok.RequiredArgsConstructor;
import org.example.springtest.entities.User;
import org.example.springtest.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static org.example.springtest.common.Paths.LOGS;

@Controller
@RequiredArgsConstructor
public class LoggerController {
    final private LoggerService loggerService;


    @GetMapping(value = "logger")
    public String loggerShowAll(Model model) {
        model.addAttribute(LOGS, loggerService.findAllLogs());
        return "logger";
    }

}
