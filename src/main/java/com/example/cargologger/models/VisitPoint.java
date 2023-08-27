package com.example.cargologger.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class VisitPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    @ToString.Exclude
    private Task task;
    @Column(nullable = false)
    private String Name;
}
