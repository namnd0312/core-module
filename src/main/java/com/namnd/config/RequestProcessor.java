package com.namnd.config;


import com.namnd.dto.RequestBodyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RequestProcessor {
    private final Map<String, RequestHandler> requestHandlerMap;

    @Autowired
    public RequestProcessor(List<RequestHandler> requestHandlers) {
        requestHandlerMap = new HashMap<>();
        for (RequestHandler handler : requestHandlers) {
            Class<? extends RequestHandler> handlerClass = handler.getClass();
            for (Method method : handlerClass.getDeclaredMethods()) {
                RequestType methodAnnotation = method.getAnnotation(RequestType.class);
                if (methodAnnotation != null) {
                    String requestType = methodAnnotation.value();
                    requestHandlerMap.put(requestType, handler);
                }
            }
        }
    }

    public Object processRequest(RequestBodyType request) {
        String requestType = request.getType();
        RequestHandler requestHandler = requestHandlerMap.get(requestType);
        if (requestHandler != null) {
            Method[] methods = requestHandler.getClass().getDeclaredMethods();
            for (Method method : methods) {
                RequestType methodAnnotation = method.getAnnotation(RequestType.class);
                if (methodAnnotation != null && methodAnnotation.value().equals(requestType)) {
                    try {
                        return method.invoke(requestHandler, request);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        // Handle any exceptions that may occur during method invocation
                        e.printStackTrace();
                        // Return an appropriate response or throw an exception
                    }
                }
            }
        }

        return null;
    }
}
