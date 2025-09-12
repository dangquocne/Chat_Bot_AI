package chatbot.Chat_Bot_AI1.controller;

import chatbot.Chat_Bot_AI1.dto.ChatRequest;
import chatbot.Chat_Bot_AI1.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

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

    @GetMapping("/stream")
    public Flux<String> chatWithStream(@RequestParam String message) {

        return  chatService.chatWithStream(message);
    }

    


}
