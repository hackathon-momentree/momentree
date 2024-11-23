package com.dteam.momentree.api.test;

import com.dteam.momentree.application.test.TestService;
import com.dteam.momentree.application.config.auth.Auth;
import com.dteam.momentree.application.config.auth.LoginUser;
import com.dteam.momentree.domain.test.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {

    private final TestService testService;
    private final LocalDateTime version = LocalDateTime.now();

    @GetMapping("/{string}")
    public Long test(@PathVariable String string){
        return testService.save(string);
    }

    @GetMapping("/find")
    public List<Test> findList(){
        return testService.findList();
    }

    @GetMapping("/whoami")
    public Long findList(@Auth LoginUser user){
        return user.getUserId();
    }

    @GetMapping("/version")
    public String version(){
        return version.toString();
    }

}
