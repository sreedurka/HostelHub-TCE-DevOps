package com.hostelhub.model;

import jakarta.persistence.*;

@Entity @Table(name="hostel_users")
public class HostelUser {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(unique=true, nullable=false) private String username;
    @Column(nullable=false) private String passwordHash;
    @Column(nullable=false) private String role;
    private String displayName;
    private String registerNumber;
    private boolean active=true;
    public HostelUser(){}
    public HostelUser(String u,String p,String r,String n,String reg){username=u;passwordHash=p;role=r;displayName=n;registerNumber=reg;}
    public Long getId(){return id;} public String getUsername(){return username;} public String getPasswordHash(){return passwordHash;}
    public String getRole(){return role;} public String getDisplayName(){return displayName;} public String getRegisterNumber(){return registerNumber;} public boolean isActive(){return active;}
    public void setPasswordHash(String v){passwordHash=v;} public void setActive(boolean v){active=v;}
}
