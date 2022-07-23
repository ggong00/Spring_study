package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "spring";

        log.trace("log info = {} " , name);
        log.debug("log info = {} " , name);
        log.info("log info = {} " , name);
        log.warn("log info = {} " , name);
        log.error("log info = {} " , name);

        return "ok";
    }

}
