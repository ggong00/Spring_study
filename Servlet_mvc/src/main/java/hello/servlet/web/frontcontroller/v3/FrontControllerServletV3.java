package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMapV3 = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMapV3.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMapV3.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMapV3.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        ControllerV3 controllerV3 = controllerMapV3.get(requestURI);

        Map<String, String> requestMap = getMap(request);
        ModelView modelView = controllerV3.process(requestMap);
        MyView myView = getMyView(modelView);

        myView.render(modelView.getMap(),request,response);



    }

    private MyView getMyView(ModelView modelView) {
        return new MyView("/WEB-INF/views/" + modelView.getViewName() + ".jsp");
    }

    private Map<String, String> getMap(HttpServletRequest request) {
        Map<String,String> requestMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(getName -> requestMap.put(getName, request.getParameter(getName)));
        return requestMap;
    }
}
