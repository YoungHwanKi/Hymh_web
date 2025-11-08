package home.parns.Hymh_web.login.web;


import home.parns.Hymh_web.login.vo.HymhUserVo;
import home.parns.Hymh_web.login.service.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final LoginService loginService;

    @GetMapping({"/", "/login"})
    public String login() {
        return "login/login";   // templates/login.html
    }

    @PostMapping("/login")
    public String loginProcess(@RequestParam String userid,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {


        HymhUserVo user = loginService.login(userid, password);
        if (user == null) {
            model.addAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");
            return "login/login";
        }else {
            session.setAttribute("user", user);

            model.addAttribute("name", user.getYname());

            model.addAttribute("tpl", "main/main");   // 템플릿 경로만
            model.addAttribute("frag", "content");    // 프래그먼트 이름만

            return "main/home";
        }
    }

    @GetMapping("/home")
    public String home() {
        return "home";   // 로그인 성공 시 이동할 화면
    }

    @GetMapping("/register")
    public String registerPage() {
        return "login/register";
    }

    @PostMapping("/register")
    public String registerProcess(@RequestParam String yid,
                                  @RequestParam String password,
                                  @RequestParam String yname,
                                  @RequestParam(required = false) String ytel,
                                  Model model) {
        int regIdCnt = loginService.register(yid, password, yname, ytel);

        if(regIdCnt == 1){
            model.addAttribute("regprocok", "가입이 완료되었습니다.");
        }else{
            model.addAttribute("regprocfail", "가입이 실패 하였습니다.");
        }

        return "login/login";
    }

    @GetMapping("/main")
    public String home(Model model) {
        model.addAttribute("tpl", "main/main");   // 템플릿 경로만
        model.addAttribute("frag", "content");    // 프래그먼트 이름만

        return "main/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
