package com.hostelhub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Table(name="visitors")
public class Visitor {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank private String studentName; @NotBlank private String visitorName;
 private String relation; @NotBlank private String phone;
 private LocalDate visitDate; private LocalDateTime checkIn; private LocalDateTime checkOut;
 private String status="EXPECTED";
 public Visitor(){}
 public Visitor(String s,String v,String r,String p,LocalDate d){studentName=s;visitorName=v;relation=r;phone=p;visitDate=d;}
 @PrePersist void init(){if(visitDate==null)visitDate=LocalDate.now();if(status==null)status="EXPECTED";}
 public Long getId(){return id;} public String getStudentName(){return studentName;} public String getVisitorName(){return visitorName;} public String getRelation(){return relation;} public String getPhone(){return phone;} public LocalDate getVisitDate(){return visitDate;} public LocalDateTime getCheckIn(){return checkIn;} public LocalDateTime getCheckOut(){return checkOut;} public String getStatus(){return status;}
 public void setStatus(String v){status=v;} public void setCheckIn(LocalDateTime v){checkIn=v;} public void setCheckOut(LocalDateTime v){checkOut=v;} public void setVisitDate(LocalDate v){visitDate=v;}
}
