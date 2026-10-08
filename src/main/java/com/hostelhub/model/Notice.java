package com.hostelhub.model;
import jakarta.persistence.*; import jakarta.validation.constraints.*; import java.time.LocalDateTime;
@Entity @Table(name="notices")
public class Notice {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @NotBlank private String title; @NotBlank @Column(length=1500) private String message;
    private String audience; private LocalDateTime createdAt;
    public Notice(){}
    public Notice(String t,String m,String a){title=t;message=m;audience=a;}
    @PrePersist void init(){if(createdAt==null)createdAt=LocalDateTime.now();}
    public Long getId(){return id;} public String getTitle(){return title;} public String getMessage(){return message;}
    public String getAudience(){return audience;} public LocalDateTime getCreatedAt(){return createdAt;}
}
