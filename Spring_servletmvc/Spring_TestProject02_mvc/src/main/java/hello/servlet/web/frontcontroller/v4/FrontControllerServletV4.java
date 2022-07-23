package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMapV4 = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMapV4.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMapV4.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMapV4.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        ControllerV4 controllerV4 = controllerMapV4.get(requestURI);

        Map<String, String> requestMap = getMap(request);
        Map<String,Object> model = new HashMap<>();
        String viewName = controllerV4.process(requestMap, model);
        MyView myView = getMyView(viewName);

        myView.render(model,request,response);

    }

    private MyView getMyView(String modelView) {
        return new MyView("/WEB-INF/views/" + modelView + ".jsp");
    }

    private Map<String, String> getMap(HttpServletRequest request) {
        Map<String,String> requestMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(getName -> requestMap.put(getName, request.getParameter(getName)));
        return requestMap;
    }
}
