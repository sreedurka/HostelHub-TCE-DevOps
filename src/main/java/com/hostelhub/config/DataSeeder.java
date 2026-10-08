package com.hostelhub.config;

import com.hostelhub.model.*;
import com.hostelhub.repository.*;
import com.hostelhub.controller.AuthController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataSeeder {
 @Bean CommandLineRunner seed(RoomRepository rooms, StudentRepository students, ComplaintRepository complaints,
   NoticeRepository notices, VisitorRepository visitors, LeaveRequestRepository leaves, PaymentRepository payments,
   MessMenuRepository menu, HostelUserRepository users, MovementRepository movements, FeedbackRepository feedback){
  return a->{
   // Demo-ready room inventory. The checks make this seeder safe to run on an existing database.
   List<Room> demoRooms = List.of(
    new Room("A-101",4,"A Block","1","4 Sharing"), new Room("A-102",4,"A Block","1","4 Sharing"),
    new Room("A-103",4,"A Block","1","4 Sharing"), new Room("A-104",4,"A Block","1","4 Sharing"),
    new Room("B-201",3,"B Block","2","3 Sharing"), new Room("B-202",2,"B Block","2","2 Sharing"),
    new Room("B-203",3,"B Block","2","3 Sharing"), new Room("C-301",4,"C Block","3","4 Sharing")
   );
   for(Room demo: demoRooms) if(rooms.findByRoomNumber(demo.getRoomNumber()).isEmpty()) rooms.save(demo);

   List<Student> demoStudents = List.of(
    new Student("Ananya Sharma","CSE001","ananya@college.edu","CSE","IV"),
    new Student("Kavin Raj","CSE002","kavin@college.edu","CSE","IV"),
    new Student("Meera Nair","ECE003","meera@college.edu","ECE","III"),
    new Student("Arun Kumar","IT004","arun@college.edu","IT","II"),
    new Student("Nivetha S","CSE005","nivetha@college.edu","CSE","IV"),
    new Student("Harini Priya","ECE006","harini@college.edu","ECE","III"),
    new Student("Dhivya Lakshmi","CSE007","dhivya@college.edu","CSE","III"),
    new Student("Keerthana R","IT008","keerthana@college.edu","IT","IV"),
    new Student("Sanjana M","CSE009","sanjana@college.edu","CSE","II"),
    new Student("Pavithra S","EEE010","pavithra@college.edu","EEE","III"),
    new Student("Janani V","CSE011","janani@college.edu","CSE","IV"),
    new Student("Aishwarya K","ECE012","aishwarya@college.edu","ECE","II")
   );
   for(Student demo: demoStudents){
    if(students.findByRegisterNumber(demo.getRegisterNumber()).isEmpty()) students.save(demo);
   }

   // Give demo residents realistic room assignments without overwriting manual allocations.
   for(Student s: students.findAll()){
    if(s.getRoomId()==null){
     Room available = rooms.findAll().stream().filter(r->r.getOccupied()<r.getCapacity()).findFirst().orElse(null);
     if(available!=null){
      s.setRoomId(available.getId()); s.setRoomNumber(available.getRoomNumber());
      s.setFeeStatus(s.getRegisterNumber().endsWith("1")||s.getRegisterNumber().endsWith("3")||s.getRegisterNumber().endsWith("5")?"PAID":"PENDING");
      students.save(s); available.setOccupied(available.getOccupied()+1); available.setStatus(available.getOccupied()>=available.getCapacity()?"FULL":"AVAILABLE"); rooms.save(available);
     }
    }
   }

   // Ensure login accounts exist for every seeded resident.
   if(users.findByUsername("admin").isEmpty()) users.save(new HostelUser("admin",AuthController.hash("admin123"),"ADMIN","Hostel Warden",null));
   for(Student s: students.findAll()){
    if(users.findByRegisterNumber(s.getRegisterNumber()).isEmpty()) users.save(new HostelUser(s.getRegisterNumber(),AuthController.hash("student123"),"STUDENT",s.getName(),s.getRegisterNumber()));
   }

   if(complaints.count()==0){
    complaints.save(new Complaint("Ananya Sharma","Maintenance","HIGH","Bathroom tap needs repair"));
    complaints.save(new Complaint("Kavin Raj","Internet","MEDIUM","Wi-Fi signal is weak in A-102"));
    complaints.save(new Complaint("Meera Nair","Mess","LOW","Need more vegetarian options for dinner"));
    complaints.save(new Complaint("Nivetha S","Electrical","HIGH","Study room tube light is not working"));
   }
   if(notices.count()==0){
    notices.save(new Notice("Hostel Inspection","Monthly room inspection will be conducted this Friday.","All Students"));
    notices.save(new Notice("Fee Reminder","Please clear pending hostel fees before the due date.","Students"));
    notices.save(new Notice("Weekend Gate Timings","Weekend outing requests must be approved before 6:00 PM.","Students"));
   }
   if(feedback.count()==0){
    feedback.save(new Feedback("Ananya Sharma","CSE001",5,"Mess","The weekly menu is much better this month."));
    feedback.save(new Feedback("Meera Nair","ECE003",4,"Hostel","Common areas are clean and well maintained."));
   }
   if(visitors.count()==0) visitors.save(new Visitor("Ananya Sharma","Priya Sharma","Mother","9876500000",LocalDate.now()));
   if(leaves.count()==0) leaves.save(new LeaveRequest("Kavin Raj","CSE002",LocalDate.now().plusDays(2),LocalDate.now().plusDays(4),"Family function"));
   if(menu.count()==0){
    menu.save(new MessMenu("Monday","Idli + Sambar","Rice + Dal + Veg","Tea + Biscuit","Chapati + Paneer"));
    menu.save(new MessMenu("Tuesday","Dosa + Chutney","Rice + Sambar + Poriyal","Fruit","Rice + Egg Curry"));
    menu.save(new MessMenu("Wednesday","Pongal + Vada","Rice + Rasam + Veg","Tea + Snack","Chapati + Kurma"));
    menu.save(new MessMenu("Thursday","Poori + Masala","Rice + Dal + Veg","Juice","Dosa + Chutney"));
    menu.save(new MessMenu("Friday","Idli + Sambar","Biryani + Raita","Tea + Biscuit","Chapati + Dal"));
    menu.save(new MessMenu("Saturday","Puttu + Kadala","Rice + Sambar + Veg","Fruit","Noodles + Manchurian"));
    menu.save(new MessMenu("Sunday","Pongal + Vada","Special Meal","Juice + Snack","Parotta + Kurma"));
   }
  };
 }
}
