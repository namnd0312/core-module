package com.namnd.config;

import com.namnd.dto.RequestBodyType;

public interface RequestHandler {

    String processRequest(RequestBodyType request);
}
