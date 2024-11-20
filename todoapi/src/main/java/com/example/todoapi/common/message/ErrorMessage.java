package com.example.todoapi.common.message;

public class ErrorMessage {

    // member 에러
    public static final String MEMBER_NOT_EXISTS = "존재하지 않는 멤버입니다.";
    public static final String MEMBER_ID_NOT_NULL = "memberId는 반드시 포함되어야 합니다.";

    // todo 에러
    public static final String TODO_NOT_EXISTS = "Todo not exists";
    public static final String LENGTH_OF_CONTENT_SHOULD_NOT_EXCEED = "content 길이는 200자를 넘길 수 없습니다.";
    // todo 수정 및 삭제 시 다른 멤버 todo에 접근
    public static final String CANNOT_ACCESS_TODO_OF_ANOTHER_MEMBER = "다른 멤버의 todo는 접근할 수 없습니다.";

    // login
    public static final String LOGIN_ID_NOT_NULL = "로그인 시 아이디를 반드시 입력해야 합니다.";
    public static final String LOGIN_PASSWORD_NOT_NULL = "로그인 시 비밀번호를 반드시 입력해야 합니다.";;
    public static final String WRONG_PASSWORD = "비밀번호가 일치하지 않습니다.";

    // Friend 에러
    public static final String SENDER_ID_NOT_NULL = "senderId는 반드시 포함되어야 합니다.";
    public static final String RECEIVER_ID_NOT_NULL = "receiverId는 반드시 포함되어야 합니다.";
    public static final String FRIEND_ID_NOT_NULL = "friendId는 반드시 포함되어야 합니다.";
    public static final String RECEIVER_NOT_EXISTS = "친구는 존재하지 않는 멤버입니다.";
    public static final String FRIEND_RELATIONSHIP_NOT_EXISTS = "존재하지 않는 친구 관계입니다.";
    public static final String REQUEST_FOR_FRIEND_NOT_EXISTS = "친구 요청 기록이 없습니다.";
    public static final String ONLY_RECEIVER_CAN_ACCEPT_REQUEST_FOR_FRIEND = "친구 요청 수락은 요청 받은 유저만 가능합니다.";
    public static final String REQUEST_FOR_FRIEND_ALREADY_ACCEPTED = "이미 친구 요청을 수락하였습니다.";

}
