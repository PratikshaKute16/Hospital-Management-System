package com.example.HospitalManagementSystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "UserDetail")  // <-- Change table name to "UserDetail"
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullName;
    private String email;
    private String password;
    private String gender;
    private String role;

    @OneToOne(mappedBy = "detail" ,cascade = CascadeType.ALL)
    private Appointment appointment;
}
