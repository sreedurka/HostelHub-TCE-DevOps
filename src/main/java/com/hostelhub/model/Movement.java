package com.hostelhub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity @Table(name="student_movements")
public class Movement {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @NotBlank private String studentName;
    @NotBlank private String registerNumber;
    private String roomNumber;
    @NotBlank private String reason;
    private LocalDateTime requestedAt;
    private LocalDateTime outTime;
    private LocalDateTime inTime;
    private String status="REQUESTED";
    private String approvedBy;
    public Movement(){}
    public Movement(String n,String r,String room,String reason){studentName=n;registerNumber=r;roomNumber=room;this.reason=reason;}
    @PrePersist void init(){if(requestedAt==null) requestedAt=LocalDateTime.now(); if(status==null) status="REQUESTED";}
    public Long getId(){return id;} public String getStudentName(){return studentName;} public String getRegisterNumber(){return registerNumber;} public String getRoomNumber(){return roomNumber;}
    public String getReason(){return reason;} public LocalDateTime getRequestedAt(){return requestedAt;} public LocalDateTime getOutTime(){return outTime;} public LocalDateTime getInTime(){return inTime;} public String getStatus(){return status;} public String getApprovedBy(){return approvedBy;}
    public void setStatus(String v){status=v;} public void setOutTime(LocalDateTime v){outTime=v;} public void setInTime(LocalDateTime v){inTime=v;} public void setApprovedBy(String v){approvedBy=v;}
}
