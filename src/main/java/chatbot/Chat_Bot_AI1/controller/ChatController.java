package chatbot.Chat_Bot_AI1.controller;

import chatbot.Chat_Bot_AI1.dto.ChatRequest;
import chatbot.Chat_Bot_AI1.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@CrossOrigin
public class ChatController {

    private final ChatService chatService;
    private final ChatMemory chatMemory;

    public ChatController(ChatService chatService,ChatMemory chatMemory) {
        this.chatService = chatService;
        this.chatMemory = chatMemory;

    }


    @PostMapping("/chat")
    public String chat(@RequestParam String message) {
        return chatService.chat(message);
    }

    @GetMapping("/stream")
    public Flux<String> chatWithStream(@RequestParam String message) {

        return  chatService.chatWithStream(message);
    }

//    @PostMapping("/chat-with-image")
//    Flux<String> chatWithImage(@RequestParam("file") MultipartFile file,
//                         @RequestParam("message") String message) {
//        return chatService.chatWithImage(file, message);
//    }

    @PostMapping("/chat-with-image")
    public Flux<String> chatWithImage(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "message", required = false) String message) {

        if (file != null && !file.isEmpty()) {
            // xử lý trường hợp có ảnh (có thể kèm message hoặc không)
            return chatService.chatWithImage(file, message);
        }

        if (message != null && !message.isBlank()) {
            // xử lý text
            return chatService.chatWithStream(message);
        }


        return Flux.just(" Bạn chưa nhập tin nhắn hoặc chọn ảnh.");
    }


    @GetMapping("/history/{id}")
    public List<Message> history(@PathVariable String id ){
        return chatMemory.get(id);
    }


}
