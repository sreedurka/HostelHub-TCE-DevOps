package com.hostelhub.controller;
import com.hostelhub.model.MessMenu; import com.hostelhub.repository.MessMenuRepository; import org.springframework.web.bind.annotation.*; import jakarta.validation.Valid; import java.util.*;
@RestController @RequestMapping("/api/mess") public class MessMenuController {
 private final MessMenuRepository repo; public MessMenuController(MessMenuRepository r){repo=r;}
 @GetMapping public List<MessMenu> all(){return repo.findAll();}
 @PostMapping public MessMenu add(@Valid @RequestBody MessMenu m){return repo.save(m);}
 @PutMapping("/{id}") public MessMenu update(@PathVariable Long id,@RequestBody MessMenu x){MessMenu m=repo.findById(id).orElseThrow();m.setBreakfast(x.getBreakfast());m.setLunch(x.getLunch());m.setSnacks(x.getSnacks());m.setDinner(x.getDinner());return repo.save(m);}
}
