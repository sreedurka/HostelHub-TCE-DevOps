package com.hostelhub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity @Table(name="feedback")
public class Feedback {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @NotBlank private String studentName;
    @NotBlank private String registerNumber;
    @Min(1) @Max(5) private int rating;
    @NotBlank private String category;
    @NotBlank @Column(length=1200) private String message;
    private LocalDateTime createdAt;
    public Feedback(){}
    public Feedback(String n,String r,int rating,String c,String m){studentName=n;registerNumber=r;this.rating=rating;category=c;message=m;}
    @PrePersist void init(){if(createdAt==null) createdAt=LocalDateTime.now();}
    public Long getId(){return id;} public String getStudentName(){return studentName;} public String getRegisterNumber(){return registerNumber;}
    public int getRating(){return rating;} public String getCategory(){return category;} public String getMessage(){return message;} public LocalDateTime getCreatedAt(){return createdAt;}
}
