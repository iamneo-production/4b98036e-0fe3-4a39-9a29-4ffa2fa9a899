package com.virtusa.hackathon.agent.dto;

import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customerdto {

    private Long customerId;
    private String customerName;
    private String customerEmail;
    private String customerPassword;
    private String customerContactNumber;
    private String customerLocation;
    private String customerLevel;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @ElementCollection
    private List<Ticketdto> tickets;
}