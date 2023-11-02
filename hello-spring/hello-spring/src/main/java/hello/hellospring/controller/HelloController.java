package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  //controller임을 알려 주는 어노테이션 꼭 입력
public class HelloController {

    @GetMapping("hello") //url주소 mapping
    public String hello(Model model) { //MVC (Model-View-Controller)

            model.addAttribute("data","hello!"); //모델에 담아줌

            return "hello"; // templates/hello.html view를 보여줌,렌더링
     }
}
