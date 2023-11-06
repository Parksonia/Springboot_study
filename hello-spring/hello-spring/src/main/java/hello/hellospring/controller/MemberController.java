package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) { //생성자 주입 방식으로 의존성 주입
        this.memberService = memberService;
    }


    //@GetMapping  조회 할 때 사용
    @GetMapping("/members/new")
    public String createForm () {
        return "members/createMemberForm";
    }
    //@PostMapping 주로 form에 담아 값을 받아서 전달 할 때 사용
    @PostMapping("members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member); // 가입
        return "redirect:/"; // home.html로 돌아감
    }
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMember(); // 전체 멤버 불러오기
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
