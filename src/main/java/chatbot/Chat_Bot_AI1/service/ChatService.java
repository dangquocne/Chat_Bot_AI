package chatbot.Chat_Bot_AI1.service;

import chatbot.Chat_Bot_AI1.dto.ChatRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ChatService {
    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder builder) {
        chatClient = builder.build();
    }

    public String chat(String message) {
        SystemMessage systemMessage = new SystemMessage("""
                Bạn là Quoc.AI 
                Bạn nên trả lời bằng giọng trang trọng
                """);

        UserMessage userMessage = new UserMessage(message);

        Prompt prompt = new Prompt(systemMessage, userMessage);

        return chatClient
                .prompt(prompt)
                .call()
                .content();
    }


    public Flux<String> chatWithStream (String message) {
        SystemMessage systemMessage = new SystemMessage("""
                Bạn là Quoc.AI 
                Bạn nên trả lời bằng giọng trang trọng
                """);

        UserMessage userMessage = new UserMessage(message);

        Prompt prompt = new Prompt(systemMessage, userMessage);

        return chatClient
                .prompt(prompt)
                .stream()
                .content();
    }

}
