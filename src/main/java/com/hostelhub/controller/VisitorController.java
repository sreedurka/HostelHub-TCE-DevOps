package com.hostelhub.controller;
import com.hostelhub.model.Visitor; import com.hostelhub.repository.VisitorRepository; import com.hostelhub.service.RealtimeEventService; import org.springframework.web.bind.annotation.*; import jakarta.validation.Valid; import java.time.LocalDateTime; import java.util.*;
@RestController @RequestMapping("/api/visitors") public class VisitorController {
 private final VisitorRepository repo; private final RealtimeEventService events; public VisitorController(VisitorRepository r,RealtimeEventService e){repo=r;events=e;}
 @GetMapping public List<Visitor> all(){return repo.findAll();}
 @PostMapping public Visitor add(@Valid @RequestBody Visitor v){Visitor x=repo.save(v);events.publish("visitor","New visitor pass for "+x.getVisitorName());return x;}
 @PatchMapping("/{id}/check-in") public Visitor checkIn(@PathVariable Long id){Visitor v=repo.findById(id).orElseThrow();v.setCheckIn(LocalDateTime.now());v.setStatus("CHECKED_IN");v=repo.save(v);events.publish("visitor","Visitor checked in: "+v.getVisitorName());return v;}
 @PatchMapping("/{id}/check-out") public Visitor checkOut(@PathVariable Long id){Visitor v=repo.findById(id).orElseThrow();v.setCheckOut(LocalDateTime.now());v.setStatus("CHECKED_OUT");v=repo.save(v);events.publish("visitor","Visitor checked out: "+v.getVisitorName());return v;}
}
