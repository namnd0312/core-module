package com.namnd.service;


import com.namnd.config.RequestHandler;
import com.namnd.config.RequestType;
import com.namnd.dto.RequestBodyType;
import org.springframework.stereotype.Service;

@Service
public class TestService implements RequestHandler {
    @Override
    public String processRequest(RequestBodyType request) {
        return "sadqdwqd";
    }

    @RequestType(value = "get-element-by-id")
    public String getElementById(RequestBodyType request) {
       return new String("get-by-id");
    }

    @RequestType(value = "get-all")
    public String getAll(RequestBodyType request) {
        return new String("get-all");
    }
}
