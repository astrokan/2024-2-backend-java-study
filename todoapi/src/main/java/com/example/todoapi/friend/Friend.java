package com.example.todoapi.friend;


import com.example.todoapi.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private Member receiver;

    @Column(name = "status")
    private String status = "pending"; // 'pending' 'accepted'

    public Friend(Member sender, Member receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public void updateAccepted() {
        this.status = "accepted";
    }
}
