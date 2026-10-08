package com.hostelhub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity @Table(name="payments")
public class Payment {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank private String studentName; @NotBlank private String registerNumber;
 @Positive private double amount; @NotBlank private String feeType;
 private String status="PAID"; private LocalDateTime paidAt; private String receiptNumber;
 public Payment(){}
 public Payment(String n,String r,double a,String t){studentName=n;registerNumber=r;amount=a;feeType=t;}
 @PrePersist void init(){if(paidAt==null)paidAt=LocalDateTime.now();if(status==null)status="PAID";if(receiptNumber==null)receiptNumber="HH-"+System.currentTimeMillis();}
 public Long getId(){return id;} public String getStudentName(){return studentName;} public String getRegisterNumber(){return registerNumber;} public double getAmount(){return amount;} public String getFeeType(){return feeType;} public String getStatus(){return status;} public LocalDateTime getPaidAt(){return paidAt;} public String getReceiptNumber(){return receiptNumber;}
}
