package hello.itemservice.domain.item;

public enum ItemType {

    BOOK("책"), FOOD("음식"), ETC("기타"),
    BOOK_EN("book"), FOOD_EN("food"), ETC_EN("etc");

    private final String description;

    ItemType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}
