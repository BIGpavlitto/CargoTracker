package com.example.cargologger.models.users;

import com.example.cargologger.models.Task;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@DiscriminatorValue("Driver")
public class Driver extends User {
    //private List<Trailer> trailer;
    //private List<Truck> trucks;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employer_id")
    @ToString.Exclude
    private Employer employer;
    //private List<Route> route;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Task> tasks = new ArrayList<>();
}
