package com.example.HospitalManagementSystem.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
//import org.apache.tomcat.jni.Time;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


@Data
@Entity
@Table(name = "appointment")
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Appointment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String contactnumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @DateTimeFormat(pattern = "HH:mm")
    private Date time;
    private String appointmentType;
    private boolean deleted;
    private boolean prescribe;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL)
    private List<Prescription> prescriptions;

    @OneToOne
    private UserDetail detail;

}
