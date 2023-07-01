package com.namnd.controller;


import com.namnd.config.RequestProcessor;
import com.namnd.dto.RequestBodyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessController {

    @Autowired
    private RequestProcessor requestProcessor;

    @PostMapping("/endpoint")
    public ResponseEntity<Object> handleRequest(@RequestBody RequestBodyType request) {
        Object result = requestProcessor.processRequest(request);
        return ResponseEntity.ok(result);
    }
}
