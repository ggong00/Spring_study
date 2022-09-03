package hello.login.web.sesstion;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class SessionManager {

    public static final String MY_SESSION_ID = "mySessionId";
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    /**
     * 세션생성
     * * sessionId 생성 (임으의 추청 불가능한 값 생성)
     * * 세션 저장소에 sessionID와 보관할 값 저장
     * * sessionId로 응답 쿠키를 생성하여 클라이언트에 전달
     */
    public void createSession(Object value, HttpServletResponse response) {

        //세션아이디를 생성하고 세션에 저장
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        //쿠키 생성
        Cookie cookie = new Cookie(MY_SESSION_ID, sessionId);
        response.addCookie(cookie);
    }

    /**
     * 세션조회
     */
    public Object getSession(HttpServletRequest request) {
        Cookie foundCookie = getCookie(request);

        if (foundCookie == null) {
            return null;
        }

        return sessionStore.get(foundCookie.getValue());
    }

    /**
     * 세션만료
     */
    public void expire(HttpServletRequest request) {
        Cookie foundCookie = getCookie(request);

        if (foundCookie != null) {
            sessionStore.remove(foundCookie.getValue());
        }
    }

    private Cookie getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie foundCookie = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(MY_SESSION_ID))
                .findFirst()
                .orElse(null);
        return foundCookie;
    }
}
