package com.amanraj.distributed_promt2prod.account_service.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;

    @Column(unique = true)
    String stripePriceId;
    String maxProjects;
    String maxTokensPerDay;
    Integer maxPreviews; // maximum number of preview allowed per plan
    Boolean unlimitedAi;

    Boolean active;
}
