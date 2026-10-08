package com.hostelhub.controller;
import com.hostelhub.model.LeaveRequest; import com.hostelhub.repository.LeaveRequestRepository; import com.hostelhub.service.RealtimeEventService; import org.springframework.web.bind.annotation.*; import jakarta.validation.Valid; import java.util.*;
@RestController @RequestMapping("/api/leaves") public class LeaveRequestController {
 private final LeaveRequestRepository repo; private final RealtimeEventService events; public LeaveRequestController(LeaveRequestRepository r,RealtimeEventService e){repo=r;events=e;}
 @GetMapping public List<LeaveRequest> all(){return repo.findAll();}
 @PostMapping public LeaveRequest add(@Valid @RequestBody LeaveRequest l){LeaveRequest x=repo.save(l);events.publish("leave","New leave request from "+x.getStudentName());return x;}
 @PatchMapping("/{id}/{status}") public LeaveRequest status(@PathVariable Long id,@PathVariable String status){LeaveRequest l=repo.findById(id).orElseThrow();l.setStatus(status.toUpperCase());l=repo.save(l);events.publish("leave","Leave request "+l.getStatus().toLowerCase()+" for "+l.getStudentName());return l;}
}
