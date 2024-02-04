package com.example.HospitalManagementSystem.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
@ToString
@Table(schema = "prescription")
public class Prescription
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    private String patientName;

    private int patientAge;
    private String diagnosis;
    private String medicationName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date prescriptionDate;

    private String dosage;
    private String instructions;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date followUpDate;

    private String paymentStatus;

    @ManyToOne
    private Appointment appointment;

}
