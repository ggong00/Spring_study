package hello.login.web.login;

import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @GetMapping("login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "login/loginForm";
    }

    @PostMapping("login")
    public String login(@Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        log.info("loginForm {}", loginForm);

        Member member = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        if (member == null) {
            bindingResult.reject("loginFail", "아이디또는 비밀번호가 올바르지 않습니다,");
            return "login/loginForm";
        }

        Cookie cookie = new Cookie("memberId", String.valueOf(member.getId()));
        response.addCookie(cookie);

        return "redirect:/";
    }

    @PostMapping("logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("memberId",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "home";
    }
}
