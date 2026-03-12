package com.smartstudy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ELocationType type;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Location parent;

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private List<Location> children;

    @OneToMany(mappedBy = "village")
    @JsonIgnore
    private List<User> users;
}