package ua.pidopryhora;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/file-manager")
public class GetEndpoint {

    @GetMapping("/file")
    public ResponseEntity<Object> getFile(){
        return ResponseEntity.ok(Map.of("message","Success"));
    }

}
