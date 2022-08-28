package hello.itemservice.domain.item;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter @ToString
public class Item {

//    @NotNull(groups = EditCheck.class)
    private Long id;

//    @NotBlank(groups = {EditCheck.class, SaveCheck.class})
    private String itemName;

//    @NotNull(groups = {EditCheck.class, SaveCheck.class})
//    @Range(min = 1000, max = 1000000 , groups = {EditCheck.class, SaveCheck.class})
    private Integer price;

//    @NotNull(groups = {EditCheck.class, SaveCheck.class})
//    @Max(value = 9999, groups = SaveCheck.class )
    private Integer quantity;

    private Boolean open;   //판매여부
    private List<String> regions;   //등록지역
    private ItemType itemType;  //상품종류
    private String deliveryCode;    //배송 방식
    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

}
