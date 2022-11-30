package caps.testing.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/api/home")
    public String getHome(@RequestParam(value = "123")int id, @RequestParam(value = "pwd")String pwd, @RequestParam(value = "alexko")String email) {
        return "/api/home";
    }

    @GetMapping("/api/test")
    public String getTest() {
        return "안녕하세요";
    }
}
