package com.smartstudy.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "group_memberships")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupMembership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_role", nullable = false)
    private String groupRole;

    @Column(name = "joined_at")
    private LocalDateTime joinedAt;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private StudyGroup studyGroup;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private StudySession studySession;
}