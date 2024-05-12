package br.com.mentaltech.entrypoint;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/home")
@RestController
@Controller
public class HomeController {

    @GetMapping
    public ResponseEntity getAllMessagens() {
            return ResponseEntity.ok("");
    }
}
