package com.example.cargologger.models.users;

import com.example.cargologger.models.Company;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@DiscriminatorValue("Employer")
public class Employer extends User {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Driver> drivers = new ArrayList<>();

}
