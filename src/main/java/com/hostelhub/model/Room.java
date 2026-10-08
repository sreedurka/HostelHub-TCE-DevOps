package com.hostelhub.model;
import jakarta.persistence.*; import jakarta.validation.constraints.*;
@Entity @Table(name="rooms")
public class Room {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @NotBlank @Column(unique=true) private String roomNumber;
    @Min(1) private int capacity;
    private int occupied;
    @NotBlank private String blockName;
    private String floor;
    private String type;
    private String status;
    public Room(){}
    public Room(String n,int c,String b,String f,String t){roomNumber=n;capacity=c;blockName=b;floor=f;type=t;occupied=0;status="AVAILABLE";}
    public Long getId(){return id;} public String getRoomNumber(){return roomNumber;} public int getCapacity(){return capacity;}
    public int getOccupied(){return occupied;} public String getBlockName(){return blockName;} public String getFloor(){return floor;}
    public String getType(){return type;} public String getStatus(){return status;}
    public void setRoomNumber(String v){roomNumber=v;} public void setCapacity(int v){capacity=v;} public void setOccupied(int v){occupied=v;}
    public void setBlockName(String v){blockName=v;} public void setFloor(String v){floor=v;} public void setType(String v){type=v;} public void setStatus(String v){status=v;}
}
