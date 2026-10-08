package com.hostelhub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity @Table(name="mess_menu")
public class MessMenu {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank @Column(unique=true) private String dayOfWeek;
 private String breakfast; private String lunch; private String snacks; private String dinner;
 public MessMenu(){}
 public MessMenu(String d,String b,String l,String s,String di){dayOfWeek=d;breakfast=b;lunch=l;snacks=s;dinner=di;}
 public Long getId(){return id;} public String getDayOfWeek(){return dayOfWeek;} public String getBreakfast(){return breakfast;} public String getLunch(){return lunch;} public String getSnacks(){return snacks;} public String getDinner(){return dinner;}
 public void setBreakfast(String v){breakfast=v;} public void setLunch(String v){lunch=v;} public void setSnacks(String v){snacks=v;} public void setDinner(String v){dinner=v;}
}
