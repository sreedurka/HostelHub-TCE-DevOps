package com.hostelhub.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service public class RealtimeEventService {
 private final List<SseEmitter> clients=new CopyOnWriteArrayList<>();
 public SseEmitter subscribe(){SseEmitter e=new SseEmitter(0L);clients.add(e);e.onCompletion(()->clients.remove(e));e.onTimeout(()->clients.remove(e));return e;}
 public void publish(String type,String message){for(SseEmitter e:clients){try{e.send(SseEmitter.event().name(type).data(message));}catch(IOException ex){clients.remove(e);}}}
}
