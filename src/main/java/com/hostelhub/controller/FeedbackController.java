package com.hostelhub.controller;
import com.hostelhub.model.Feedback;
import com.hostelhub.repository.FeedbackRepository;
import com.hostelhub.service.RealtimeEventService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.*;
@RestController @RequestMapping("/api/feedback")
public class FeedbackController{
 private final FeedbackRepository repo; private final RealtimeEventService events;
 public FeedbackController(FeedbackRepository r,RealtimeEventService e){repo=r;events=e;}
 @GetMapping public List<Feedback> all(){return repo.findAll();}
 @GetMapping("/student/{register}") public List<Feedback> student(@PathVariable String register){return repo.findByRegisterNumberOrderByCreatedAtDesc(register);}
 @PostMapping public Feedback add(@Valid @RequestBody Feedback f){Feedback x=repo.save(f);events.publish("feedback","New feedback from "+x.getStudentName()+" · "+x.getRating()+"/5");return x;}
}
