package chatbot.Chat_Bot_AI1.controller;

import chatbot.Chat_Bot_AI1.dto.ChatRequest;
import chatbot.Chat_Bot_AI1.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }


    @PostMapping("/chat")
    public String chat(@RequestParam String message) {
        return chatService.chat(message);
    }

//    @PostMapping("/stream")
//    public String chatWithStream(@RequestBody ChatRequest request) {
//        return chatService.chat(request);
//    }


}
