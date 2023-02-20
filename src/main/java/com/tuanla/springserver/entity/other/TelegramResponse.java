package com.tuanla.springserver.entity.other;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TelegramResponse {
    private String ok;
    private List<Result> resultArr;

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Result {
        private String updateId;
        private List<String> message;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Message {
        private String messageId;
        private From from;
        private Chat chat;
        private String date;
        private LeftChatParticipant leftChatParticipant;
        private LeftChatMember leftChatMember;
        private NewChatParticipant newChatParticipant;
        private NewChatMember newChatMember;
        private ArrayList<NewChatMember> newChatMembers;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class From {
        private String id;
        private String isBot;
        private String firstName;
        private String lastName;
        private String username;
        private String languageCode;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Chat {
        private String id;
        private String title;
        private String type;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LeftChatParticipant {
        private String id;
        private String idBot;
        private String firstName;
        private String username;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LeftChatMember {
        private String id;
        private String idBot;
        private String firstName;
        private String username;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NewChatParticipant {
        private String id;
        private String idBot;
        private String firstName;
        private String username;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NewChatMember {
        private String id;
        private String idBot;
        private String firstName;
        private String username;
    }
}
