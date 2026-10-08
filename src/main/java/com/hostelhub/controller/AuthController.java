package com.hostelhub.controller;

import com.hostelhub.model.HostelUser;
import com.hostelhub.repository.HostelUserRepository;
import org.springframework.web.bind.annotation.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

@RestController @RequestMapping("/api/auth")
public class AuthController {
    private final HostelUserRepository users;
    public AuthController(HostelUserRepository users){this.users=users;}

    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody Map<String,String> body){
        String role=Optional.ofNullable(body.get("role")).orElse("STUDENT").toUpperCase();
        String identity=Optional.ofNullable(body.get("identity")).orElse("").trim();
        String password=Optional.ofNullable(body.get("password")).orElse("");
        Optional<HostelUser> found = role.equals("ADMIN") ? users.findByUsername(identity) : users.findByRegisterNumber(identity);
        HostelUser u=found.filter(x->x.isActive() && x.getRole().equals(role) && x.getPasswordHash().equals(hash(password)))
                .orElseThrow(()->new IllegalArgumentException("Invalid credentials"));
        return Map.of("authenticated",true,"role",u.getRole(),"username",u.getUsername(),"displayName",u.getDisplayName(),"registerNumber",u.getRegisterNumber()==null?"":u.getRegisterNumber());
    }
    public static String hash(String value){try{MessageDigest md=MessageDigest.getInstance("SHA-256");byte[] b=md.digest(value.getBytes(StandardCharsets.UTF_8));StringBuilder s=new StringBuilder();for(byte x:b)s.append(String.format("%02x",x));return s.toString();}catch(Exception e){throw new IllegalStateException(e);}}
}
