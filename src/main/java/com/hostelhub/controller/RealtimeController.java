package com.hostelhub.controller;
import com.hostelhub.service.RealtimeEventService; import org.springframework.web.bind.annotation.*; import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
@RestController @RequestMapping("/api/events") public class RealtimeController { private final RealtimeEventService events; public RealtimeController(RealtimeEventService e){events=e;} @GetMapping("/stream") public SseEmitter stream(){return events.subscribe();} }
