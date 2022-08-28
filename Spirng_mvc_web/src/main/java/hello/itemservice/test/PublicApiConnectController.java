package hello.itemservice.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PublicApiConnectController {

    private final PublicApiConnectService api;

    @GetMapping("/search")
    public PublicData searchFacility(@ModelAttribute Facility facility) {

        PublicData publicData;
        try {
            publicData = api.connectApi(facility);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return publicData;
    }
}
