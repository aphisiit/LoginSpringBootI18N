package com.guy.login.controller;

import com.guy.login.Service.TaskService;
import com.guy.login.domain.TaskUser;
import com.guy.login.domain.AppUser;
import flexjson.JSONSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/task")
public class TaskController {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TaskService taskService;

    @GetMapping("/createTask")
    public String creatTask(Model model){
        model.addAttribute("task", new TaskUser());
        return "task/createTask";
    }

    @PostMapping("/saveTask")
    public ResponseEntity<String> saveTask(@Valid TaskUser task, BindingResult bindingResult){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json; charset=utf-8");
        Map<String,String> mapResponse = new HashMap<>();
        try {
            taskService.saveTask(task);
            mapResponse.put("status","success");
            return new ResponseEntity<>(new JSONSerializer().serialize(mapResponse),headers, HttpStatus.OK);
        }catch (Exception e){
            LOGGER.error("{}",e.getMessage());
            mapResponse.put("status","error");
            return new ResponseEntity<>(new JSONSerializer().serialize(mapResponse),headers, HttpStatus.OK);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<String> findAll(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json; charset=utf-8");
        Map<String,String> mapResponse = new HashMap<>();
        try {
            return new ResponseEntity<>(new JSONSerializer().prettyPrint(true).exclude("*.class").serialize(taskService.findAll()),headers, HttpStatus.OK);
        }catch (Exception e){
            LOGGER.error("{}",e.getMessage());
            mapResponse.put("status","error");
            return new ResponseEntity<>(new JSONSerializer().serialize(mapResponse),headers, HttpStatus.OK);
        }
    }

    @GetMapping("/findByTitle")
    public ResponseEntity<String> findByTitle(@RequestParam("title") String title){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json; charset=UTF-8;");
        Map<String,String> mapResponse = new HashMap<>();
        try{
            return new ResponseEntity<>(new JSONSerializer().prettyPrint(true).exclude("*.class").serialize(taskService.findCustomByTitle(title)),headers, HttpStatus.OK);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            mapResponse.put("status","error");
            return new ResponseEntity<>(new JSONSerializer().serialize(mapResponse),headers, HttpStatus.OK);
        }
    }
}
