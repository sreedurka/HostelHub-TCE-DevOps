package com.hostelhub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Table(name="leave_requests")
public class LeaveRequest {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank private String studentName; @NotBlank private String registerNumber;
 private LocalDate fromDate; private LocalDate toDate; @NotBlank @Column(length=1000) private String reason;
 private String status="PENDING"; private LocalDateTime appliedAt;
 public LeaveRequest(){}
 public LeaveRequest(String n,String r,LocalDate f,LocalDate t,String reason){studentName=n;registerNumber=r;fromDate=f;toDate=t;this.reason=reason;}
 @PrePersist void init(){if(appliedAt==null)appliedAt=LocalDateTime.now();if(status==null)status="PENDING";}
 public Long getId(){return id;} public String getStudentName(){return studentName;} public String getRegisterNumber(){return registerNumber;} public LocalDate getFromDate(){return fromDate;} public LocalDate getToDate(){return toDate;} public String getReason(){return reason;} public String getStatus(){return status;} public LocalDateTime getAppliedAt(){return appliedAt;}
 public void setStatus(String v){status=v;}
}
