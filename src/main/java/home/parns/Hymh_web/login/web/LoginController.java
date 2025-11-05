package home.parns.Hymh_web.login.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";   // templates/login.html
    }

    @PostMapping("/login")
    public String loginPost(String username, String password) {

        // ✅ 임시 예시: admin / 1234 일 때 로그인 성공
        if ("admin".equals(username) && "1234".equals(password)) {
            return "redirect:/home";
        }

        // 실패 → 다시 로그인 화면 + error 표시
        return "redirect:/login?error";
    }

    @GetMapping("/home")
    public String home() {
        return "home";   // 로그인 성공 시 이동할 화면
    }
}
