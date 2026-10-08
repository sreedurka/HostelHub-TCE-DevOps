package com.hostelhub.controller;
import com.hostelhub.model.Payment; import com.hostelhub.model.Student; import com.hostelhub.repository.PaymentRepository; import com.hostelhub.repository.StudentRepository; import com.hostelhub.service.RealtimeEventService; import org.springframework.web.bind.annotation.*; import jakarta.validation.Valid; import java.util.*;
@RestController @RequestMapping("/api/payments") public class PaymentController {
 private final PaymentRepository repo; private final StudentRepository students; private final RealtimeEventService events; public PaymentController(PaymentRepository r,StudentRepository s,RealtimeEventService e){repo=r;students=s;events=e;}
 @GetMapping public List<Payment> all(){return repo.findAll();}
 @PostMapping public Payment add(@Valid @RequestBody Payment p){Payment x=repo.save(p);students.findByRegisterNumber(x.getRegisterNumber()).ifPresent(s->{s.setFeeStatus("PAID");students.save(s);});events.publish("payment","Payment received from "+x.getStudentName()+" (₹"+String.format("%.0f",x.getAmount())+")");return x;}
}
