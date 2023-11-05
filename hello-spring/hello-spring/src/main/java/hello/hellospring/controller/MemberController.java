package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) { //생성자 주입 방식으로 의존성 주입
        this.memberService = memberService;
    }
    @GetMapping("/members/new")
    public String createForm () {
        return "members/createMemberForm";
    }
}
