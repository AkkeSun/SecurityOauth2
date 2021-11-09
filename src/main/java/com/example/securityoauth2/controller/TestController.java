package com.example.securityoauth2.controller;

import com.example.securityoauth2.owner.Owner;
import com.example.securityoauth2.owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TestController {

    @Autowired
    private OwnerService ownerService;

    // 현재 로그인한 Owner 정보 가져오기
    public Owner LoginObject() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();
        return ownerService.findByUsername(username).get();
    }


    @GetMapping("/test/test1")
    public String test1(Model model){

        if(LoginObject() == null){
            System.out.println("그대로 리턴");
        }
        else{
            System.out.println(LoginObject().getUsername());
            System.out.println("Create : Writer 데이터 넣기");
            System.out.println("링크 추가하기 -> UPDATE, CREATE");
        }
        return "test1";
    }

    @GetMapping("/test/test2")
    public String test2(){
        return "test2";
    }

    @GetMapping("/test/test3")
    public String test3(){
        return "test2";
    }
}
