package com.hostelhub.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity @Table(name="students")
public class Student {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @NotBlank private String name;
    @NotBlank @Column(unique=true) private String registerNumber;
    @Email @NotBlank private String email;
    @NotBlank private String department;
    @NotBlank @Column(name="study_year") private String year;
    private Long roomId;
    private String roomNumber;
    private String feeStatus = "PENDING";
    public Student() {}
    public Student(String n,String r,String e,String d,String y){name=n;registerNumber=r;email=e;department=d;year=y;}
    public Long getId(){return id;} public String getName(){return name;} public String getRegisterNumber(){return registerNumber;}
    public String getEmail(){return email;} public String getDepartment(){return department;} public String getYear(){return year;}
    public Long getRoomId(){return roomId;} public String getRoomNumber(){return roomNumber;} public String getFeeStatus(){return feeStatus;}
    public void setName(String v){name=v;} public void setRegisterNumber(String v){registerNumber=v;} public void setEmail(String v){email=v;}
    public void setDepartment(String v){department=v;} public void setYear(String v){year=v;} public void setRoomId(Long v){roomId=v;}
    public void setRoomNumber(String v){roomNumber=v;} public void setFeeStatus(String v){feeStatus=v;}
}
