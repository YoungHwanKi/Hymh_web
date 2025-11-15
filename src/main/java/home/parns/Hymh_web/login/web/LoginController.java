package home.parns.Hymh_web.login.web;


import home.parns.Hymh_web.login.vo.HymhUserVo;
import home.parns.Hymh_web.login.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

//    @GetMapping({"/", "/login"})
//    public String login() {
//        return "login/login";   // templates/login.html
//    }

    @GetMapping({"/", "/login"})
    public String loginPage(HttpServletRequest request, Model model) {

        String savedId = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("userid")) {
                    savedId = c.getValue();
                }
            }
        }

        model.addAttribute("savedUserId", savedId);
        return "login/login";
    }

    @PostMapping("/login")
    public String loginProcess(@RequestParam String userid,
                               @RequestParam String password,
                               @RequestParam(required = false) String rememberId,
                               HttpSession session,
                               HttpServletResponse response,
                               Model model) {


        HymhUserVo user = loginService.login(userid, password);
        if (user == null) {
            model.addAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");
            return "login/login";
        }else {

            // 5회이상 패스워드 실패일경우
            if(user.getFailcnt().intValue() >= 5){

                model.addAttribute("error", "패스워드 5회 실패로 정지된 계정입니다.");
                return "login/login";

            }else{

                if(user.getStatus().equals("0")) {

                    // 쿠키 처리
                    if (rememberId != null) {  // 체크되어 있으면 쿠키 저장
                        Cookie cookie = new Cookie("userid", userid);
                        cookie.setPath("/");
                        cookie.setMaxAge(60 * 60 * 24 * 30);  // 30일
                        response.addCookie(cookie);
                    } else {  // 체크 안되어 있으면 쿠키 삭제
                        Cookie cookie = new Cookie("userid", null);
                        cookie.setPath("/");
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }

                    // 성공일경우
                    session.setAttribute("user", user);

                    model.addAttribute("name", user.getYname());

                    model.addAttribute("tpl", "main/main");   // 템플릿 경로만
                    model.addAttribute("frag", "content");    // 프래그먼트 이름만

                    return "main/home";

                }else{

                    model.addAttribute("error", "사용 가능한 상태가 아닙니다.");
                    return "login/login";

                }
            }
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
