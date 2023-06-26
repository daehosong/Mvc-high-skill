package hello.thymeleafbasic.basic;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("/text-basic")
    public String textBasic(Model model){
        model.addAttribute("data","Hello Spring!");
        return "basic/text-basic";
    }
    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model){
        model.addAttribute("data","Hello <b>Spring!</b>");
        return "basic/text-unescaped";
    }

    @GetMapping("/variable")
    public String variable(Model model){
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);
        Map<String,User> map = new HashMap<>();
        map.put("userA",userA);
        map.put("userB",userB);

        model.addAttribute("user",userA);
        model.addAttribute("users",list);
        model.addAttribute("userMap",map);

        return "basic/variable";
    }
    @Data
    static class User{
        private String username;
        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }
    @GetMapping("/basic-objects")
    public String basicObjects (Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        session.setAttribute("sessionData","Hello Session!");
        model.addAttribute("request",request);
        model.addAttribute("response",response);
        model.addAttribute("servletContext",request.getServletContext());
        return "basic/basic-objects";
    }
    //  nested static class의 경우 실제 클래스 이름이 '외부 클래스 이름 + static class 이름' 형태가 됩니다.
    // 즉, BasicController.HelloBean이 static class HelloBean의 전체 이름이 됩니다.
    // 만약 빈 이름을 별도로 지정하지 않는다면 스프링의 기본 값에 의해 basicController.HelloBean
    @Component("helloBean")
    static class HelloBean{
        public String hello(String data){
            return "Hello"+data;
        }
    }
    @GetMapping("/date")
    public String date(Model model){
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }
    @GetMapping("/link")
    public String link(Model model){
        model.addAttribute("param1","data1");
        model.addAttribute("param2","data2");
        return "basic/link";
    }
    @GetMapping("/literal")
    public String literal(Model model){
        model.addAttribute("data","spring");
        return "basic/literal";
    }
    @GetMapping("/operation")
    public String operation(Model model){
        model.addAttribute("nullData", null);
        model.addAttribute("data", "spring");
        return "basic/operation";
    }
    @GetMapping("/attribute")
    public String attribute(Model model){
    return "basic/attribute";
    }
    @GetMapping("/each")
    public String each(Model model){
        addUsers(model);
        return "basic/each";
    }
    private void addUsers(Model model){
        List<User> list = new ArrayList<>();
        list.add(new User("userA",10));
        list.add(new User("userB",20));
        list.add(new User("userC",30));
        model.addAttribute("users",list);
    }
    @GetMapping("/condition")
    private String condition(Model model){
        addUsers(model);
        return "basic/condition";
    }

    @GetMapping("/comments")
    public String comments(Model model){
        return "basic/comments";
    }

    @GetMapping("/block")
    public String block(Model model){
        addUsers(model);
        return "basic/block";
    }
    @GetMapping("/javascript")
    public String javascript(Model model){
        model.addAttribute("user",new User("userA",10));
        addUsers(model);
        return "basic/javascript";
    }
}
