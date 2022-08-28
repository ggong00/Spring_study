package hello.itemservice.web.form;

import hello.itemservice.domain.item.DeliveryCode;
import hello.itemservice.domain.item.ItemType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Getter @Setter
public class InputCategory {

    private final Map<String, String> regions;
    private final Map<String, String> regions_en;
    private final List<DeliveryCode> deliveryCodes;
    private final List<DeliveryCode> deliveryCodes_en;
    private final ItemType[] itemTypes;

    public InputCategory() {
        regions = regions();
        itemTypes = itemTypes();
        deliveryCodes = deliveryCodes();
        regions_en = regions_en();
        deliveryCodes_en = deliveryCodes_en();
    }

    private Map<String, String> regions() {
        Map<String, String> regions = new LinkedHashMap<>();
        regions.put("SEOUL", "서울");
        regions.put("BUSAN", "부산");
        regions.put("JEJU", "제주");

        return regions;
    }

    private Map<String, String> regions_en() {
        Map<String, String> regions = new LinkedHashMap<>();
        regions.put("SEOUL", "seoul");
        regions.put("BUSAN", "busan");
        regions.put("JEJU", "jeju");

        return regions;
    }

    private List<DeliveryCode> deliveryCodes() {
        List<DeliveryCode> deliveryCodes = new ArrayList<>();
        deliveryCodes.add(new DeliveryCode("FAST", "빠른 배송"));
        deliveryCodes.add(new DeliveryCode("NORMAL", "일반 배송"));
        deliveryCodes.add(new DeliveryCode("SLOW", "느린 배송"));

        return deliveryCodes;
    }

    private List<DeliveryCode> deliveryCodes_en() {
        List<DeliveryCode> deliveryCodes = new ArrayList<>();
        deliveryCodes.add(new DeliveryCode("FAST", "fast delivery"));
        deliveryCodes.add(new DeliveryCode("NORMAL", "normal delivery"));
        deliveryCodes.add(new DeliveryCode("SLOW", "slow delivery"));

        return deliveryCodes;
    }

    private ItemType[] itemTypes() {
        return ItemType.values();
    }
}
