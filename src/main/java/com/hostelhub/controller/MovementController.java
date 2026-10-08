package com.hostelhub.controller;

import com.hostelhub.model.Movement;
import com.hostelhub.repository.MovementRepository;
import com.hostelhub.service.RealtimeEventService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;

@RestController @RequestMapping("/api/movements")
public class MovementController {
    private final MovementRepository repo; private final RealtimeEventService events;
    public MovementController(MovementRepository r,RealtimeEventService e){repo=r;events=e;}
    @GetMapping public List<Movement> all(){return repo.findAllByOrderByRequestedAtDesc();}
    @GetMapping("/student/{register}") public List<Movement> student(@PathVariable String register){return repo.findByRegisterNumberOrderByRequestedAtDesc(register);}
    @PostMapping public Movement request(@Valid @RequestBody Movement m){Movement x=repo.save(m);events.publish("gate","Exit request from "+x.getStudentName());return x;}
    @PatchMapping("/{id}/approve") public Movement approve(@PathVariable Long id,@RequestParam(defaultValue="Warden") String by){Movement m=repo.findById(id).orElseThrow();m.setStatus("OUT");m.setOutTime(LocalDateTime.now());m.setApprovedBy(by);m=repo.save(m);events.publish("gate",m.getStudentName()+" checked OUT");return m;}
    @PatchMapping("/{id}/reject") public Movement reject(@PathVariable Long id,@RequestParam(defaultValue="Warden") String by){Movement m=repo.findById(id).orElseThrow();m.setStatus("REJECTED");m.setApprovedBy(by);m=repo.save(m);events.publish("gate",m.getStudentName()+" exit request rejected");return m;}
    @PatchMapping("/{id}/check-in") public Movement checkIn(@PathVariable Long id){Movement m=repo.findById(id).orElseThrow();m.setStatus("IN");m.setInTime(LocalDateTime.now());m=repo.save(m);events.publish("gate",m.getStudentName()+" checked IN");return m;}
}
