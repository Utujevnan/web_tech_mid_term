package com.smartstudy.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "study_groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "max_members")
    private Integer maxMembers;

    @Column(name = "is_private")
    private Boolean isPrivate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "created_by_id", nullable = false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @OneToMany(mappedBy = "studyGroup", cascade = CascadeType.ALL)
    private List<StudySession> studySessions;

    @OneToMany(mappedBy = "studyGroup", cascade = CascadeType.ALL)
    private List<GroupMembership> groupMemberships;
}