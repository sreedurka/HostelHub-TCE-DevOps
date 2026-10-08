package com.hostelhub.model;
import jakarta.persistence.*; import jakarta.validation.constraints.*; import java.time.LocalDateTime;
@Entity @Table(name="complaints")
public class Complaint {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @NotBlank private String studentName; @NotBlank private String category;
    @NotBlank private String priority; @NotBlank @Column(length=1000) private String description;
    private String status="OPEN"; private LocalDateTime createdAt;
    public Complaint(){}
    public Complaint(String s,String c,String p,String d){studentName=s;category=c;priority=p;description=d;}
    @PrePersist void init(){if(createdAt==null)createdAt=LocalDateTime.now();if(status==null)status="OPEN";}
    public Long getId(){return id;} public String getStudentName(){return studentName;} public String getCategory(){return category;}
    public String getPriority(){return priority;} public String getDescription(){return description;} public String getStatus(){return status;} public LocalDateTime getCreatedAt(){return createdAt;}
    public void setStatus(String v){status=v;}
}
