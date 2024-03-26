package org.example.process_payment.application.presenters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ReponseHandler {
    public static ResponseEntity<Object> generateResponse(HttpStatus status, String message, Object data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("statusCode", status);
        map.put("data", data);
        if(!message.isEmpty()) {
            map.put("message", message);
        }
        return new ResponseEntity<Object>(map, status);
    }
}
