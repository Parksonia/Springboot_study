package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  //controller임을 알려 주는 어노테이션 꼭 입력
public class HelloController {



    @GetMapping("hello") //url주소 mapping
    public String hello(Model model) { //MVC (Model-View-Controller)

            model.addAttribute("data","hello!"); //모델에 담아줌

            return "hello"; // templates/hello.html view를 보여줌,렌더링
     }

     /*MVC 실습*/
     @GetMapping("hello-mvc") /*localhost:8081/hello-mvc?name=--- =>get방식으로 파라미터를 받음*/
    public String helloMvc(@RequestParam("name")String name, Model model) { //파라미터를 받아 모델에 담기
                            /*(@RequestParam(value="name",required=false) required 옵션을 false로 하면 값을 안넘겨도 되게됨 */
        model.addAttribute("name",name); //key-value

        return "hello-template";
    }

    /*API 실습1*/
    @GetMapping("hello-string")
    @ResponseBody  /*!!중요!!*/
    public String helloString(@RequestParam("name")String name) {
        return "hello " + name;  //요청한 클라이언트에게 그대로 들어감 ex) name을 spring 이라고 하면 => "hello spring" 문자가 그대로 들어감 view가 없음
    }
    /*API 실습2*/
    @GetMapping("hello-api")
    @ResponseBody
    public Hello hellloApi(@RequestParam("name")String name) {

        Hello hello = new Hello();
        hello.setName(name);  //파라미터로 받은 name을 저장

        return hello;  //json방식으로 리턴
    }

    static class Hello { //Static 이라서 class HelloController 에서도 사용 가능
     private String name;

        /*getter setter 만들기*/
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
