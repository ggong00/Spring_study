package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

class ItemRepositoryTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    ItemRepository repository = ac.getBean("itemRepository", ItemRepository.class);

    @AfterEach
    void afterEach () {
        repository.clearStore();
    }

    @DisplayName("상품 저장")
    @Test
    void save () {
        //given
        Item itemA = new Item("itemA", 10000, 10);

        //when
        Item savedItem = repository.save(itemA);

        //then
        Assertions.assertThat(savedItem).isEqualTo(itemA);
    }

    @DisplayName("전체상품 조회")
    @Test
    void findAll () {
        //given
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 5);
        Item save = repository.save(item1);
        Item save1 = repository.save(item2);

        //when
        List<Item> findItems = repository.findAll();

        //then
        Assertions.assertThat(findItems.size()).isEqualTo(2);
        Assertions.assertThat(findItems).contains(item1, item2);
    }

    @DisplayName("상품정보 수정")
    @Test
    void update () {
        //given
        Item item1 = new Item("item1", 10000, 10);
        repository.save(item1);
        Item updateParam = new Item("item2", 20000, 5);

        //when
        repository.update(item1.getId(),updateParam);
        Item updatedItem = repository.findById(item1.getId());

        //then
        Assertions.assertThat(updatedItem.getItemName()).isEqualTo(updateParam.getItemName());
        Assertions.assertThat(updatedItem.getPrice()).isEqualTo(updateParam.getPrice());
        Assertions.assertThat(updatedItem.getQuantity()).isEqualTo(updateParam.getQuantity());

    }

}