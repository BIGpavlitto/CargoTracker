package com.example.cargologger.models;

import com.example.cargologger.models.users.Driver;
import com.example.cargologger.models.users.Employer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private String registrationNum;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<VisitPoint> visitPoints;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id")
    @ToString.Exclude
    private Driver driver;
}
