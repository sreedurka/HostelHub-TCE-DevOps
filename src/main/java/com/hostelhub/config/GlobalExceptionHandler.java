package com.hostelhub.config;
import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.time.Instant; import java.util.*;
@RestControllerAdvice public class GlobalExceptionHandler{
 @ExceptionHandler(Exception.class) @ResponseStatus(HttpStatus.BAD_REQUEST)
 public Map<String,Object> handle(Exception e){return Map.of("timestamp",Instant.now().toString(),"error","Request failed","message",e.getMessage()==null?"Unexpected error":e.getMessage());}
}
