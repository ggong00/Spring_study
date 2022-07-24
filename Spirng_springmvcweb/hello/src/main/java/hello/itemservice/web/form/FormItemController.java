package hello.itemservice.web.form;

import hello.itemservice.domain.item.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.plaf.synth.Region;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/form/items")
@RequiredArgsConstructor
public class FormItemController {

    private final ItemRepository itemRepository;
    private final InputCategory inputCategory;

    @ModelAttribute("regions")
    public Map<String, String> regions(Locale locale) {
        Map<String, String> regions = null;

        if (locale.equals(Locale.KOREAN)) {
            regions = inputCategory.getRegions();
        } else {
            regions = inputCategory.getRegions_en();
        }

        return regions;
    }

    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes(Locale locale) {
        ItemType[] temp = inputCategory.getItemTypes();
        ItemType[] itemTypes = new ItemType[temp.length/2];
        int i = 0;

        if (locale.equals(Locale.KOREAN)) {
            for (ItemType itemType : temp) {
                if (!itemType.name().contains("_EN")) {
                    itemTypes[i] = itemType;
                    i++;
                }
            }

        } else {
            for (ItemType itemType : temp) {
                if (itemType.name().contains("_EN")) {
                    itemTypes[i] = itemType;
                    i++;
                }
            }
        }
        log.debug("itemType값 {}" , itemTypes);
        return itemTypes;
    }

    @ModelAttribute("deliveryCodes")
    public List<DeliveryCode> deliveryCodes(Locale locale) {
        List<DeliveryCode> deliveryCodes = null;

        if (locale.equals(Locale.KOREAN)) {
            deliveryCodes= inputCategory.getDeliveryCodes();
        } else {
            deliveryCodes = inputCategory.getDeliveryCodes_en();
        }

        return deliveryCodes;
    }


    @GetMapping
    public String items(Model model) {
        List<Item> itemList = itemRepository.findAll();
        model.addAttribute(itemList);
        return "form/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model, Locale locale) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "form/item";
    }

    @GetMapping("/add")
    public String DirectAddForm(Model model,Locale locale) {
        Item item = new Item();
        log.debug("언어분류 {} " , locale);
        model.addAttribute("item", item);

        return "form/addForm";
    }

    @PostMapping("/add")
    public String AddItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        log.debug("open.value = {} ", item.getOpen());
        log.debug("item.regions = {} ", item.getRegions());
        log.debug("item.type = {} ", item.getItemType());

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/form/items/{itemId}";
    }


    @GetMapping("/{itemId}/edit")
    public String DirectEditForm(@PathVariable Long itemId, Model model, Locale locale) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "form/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String EditItem(@PathVariable Long itemId,
                           @ModelAttribute Item item) {

        itemRepository.update(itemId,item);

        return "redirect:/form/items/{itemId}";
    }

}
