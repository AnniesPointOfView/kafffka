package com.example.producer.controller;

import com.example.producer.model.Message;
import com.example.producer.service.MessageSender;
import lombok.*;
import lombok.experimental.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/message")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageController {

    MessageSender messageSender;

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestParam(value = "part", required = false) Integer partition,
                                       @RequestParam(value = "topic") String topicName,
                                       @RequestBody Message message) {
        if (messageSender.send(message.getMessageText(), topicName, partition)) {
            return ResponseEntity.ok("ok");
        }
        return status(INTERNAL_SERVER_ERROR)
                .body("kafka isn't available");
    }

}
