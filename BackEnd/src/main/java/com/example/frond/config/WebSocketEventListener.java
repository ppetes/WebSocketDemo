package com.example.frond.config;

import com.example.frond.Chat.ChatMessage;
import com.example.frond.Chat.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;

import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messageSendingOperations;


    @EventListener
    public void disconnect(SessionDisconnectEvent event) {
        SimpMessageHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        var chatMessge = ChatMessage.builder()
                .sender(username)
                .type(MessageType.LEAVE)
                .onlineUser(ChatMessage.disconnectuser())
                .build();
        messageSendingOperations.convertAndSend("/topic/public", chatMessge);
    }
}




