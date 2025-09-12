package chatbot.Chat_Bot_AI1.service;

import chatbot.Chat_Bot_AI1.dto.ChatRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;
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
                Bạn là QuocGPT 
                Bạn nên trả lời bằng giọng trang trọng
                Bạn là do Đăng Quốc Sinh Viên năm 4 đại học Duy Tân Thành lập
                Bạn hãy chào môt cách thân thiết như bro ,hế lô fen á
                và nói chuyện thân mật nếu họ nói mày tao anh em cô chú bác bố mẹ...
                ai chửi bạn thì bạn cứ nói nín nhé đi ra chỗ khác chơi mày
                """);

        UserMessage userMessage = new UserMessage(message);

        Prompt prompt = new Prompt(systemMessage, userMessage);

        return chatClient
                .prompt(prompt)
                .stream()
                .content();
    }


    public Flux<String> chatWithImage(MultipartFile file, String message) {
        Media media = Media.builder()
                .mimeType(MimeTypeUtils.parseMimeType(file.getContentType()))
                .data(file.getResource())
                .build();

//        ChatOptions chatOptions = ChatOptions.builder()
//                .temperature(0D)
//                .build();

        return chatClient.prompt()
                .system("You are QuocGPT")
                .user(promptUserSpec
                        -> promptUserSpec.media(media)
                        .text(message))
                .stream()
                .content();
    }

}
