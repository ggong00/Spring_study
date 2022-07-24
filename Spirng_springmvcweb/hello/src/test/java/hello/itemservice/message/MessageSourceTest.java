package hello.itemservice.message;

import hello.itemservice.domain.item.ItemType;
import hello.itemservice.web.form.InputCategory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.util.Arrays;
import java.util.Locale;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    void helloMessage() {
        String result = ms.getMessage("hello", null, null);

        Assertions.assertThat(result).isEqualTo("안녕");
    }

    @Test
    void test() {
        InputCategory inputCategory = new InputCategory();
        ItemType[] temp = inputCategory.getItemTypes();
        ItemType[] itemTypes = new ItemType[temp.length/2];
        int i = 0;

        for (ItemType itemType : temp) {
            if (!itemType.name().contains("_EN")) {
                itemTypes[i] = itemType;
                i++;
            }
        }
        System.out.println("type = " + itemTypes[1]);
    }

}
