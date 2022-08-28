package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class MyView {
    private String url;

    public MyView(String url) {
        this.url = url;
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(getUrl());
        requestDispatcher.forward(request,response);
    }

    public void render(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Set<String> stringSet = map.keySet();
        for (String s : stringSet) {
            request.setAttribute(s,map.get(s));
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(getUrl());
        requestDispatcher.forward(request,response);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
