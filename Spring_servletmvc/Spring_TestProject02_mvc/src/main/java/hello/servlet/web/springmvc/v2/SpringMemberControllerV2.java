package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class SpringMemberControllerV2 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v2/members/new-form")
    public ModelAndView save() {
        return new ModelAndView("new-form");
    }

    @RequestMapping("/springmvc/v2/members/save")
    public ModelAndView result(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView modelAndView = new ModelAndView("save");
        modelAndView.addObject("member",member);

        return modelAndView;
    }

    @RequestMapping("/springmvc/v2/members")
    public ModelAndView list() throws ServletException, IOException {
        List<Member> memberList = memberRepository.findAll();

        ModelAndView modelView = new ModelAndView("members");
        modelView.addObject("members",memberList);

        return modelView;
    }
}
