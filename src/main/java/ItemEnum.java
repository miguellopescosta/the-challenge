

enum ItemEnum {

    SOUP(0.65f,"Soup", "2 soup + 1 bread 1/2 price:"),
    BREAD(0.80f,"Bread", "2 soup + 1 bread 1/2 price:"),
    MILK(1.30f,"Milk", null),
    APPLES(1.00f,"Apples", "Apples 10%% off:");

    public final float value;
    public final String description;
    public final String discount;

    ItemEnum(float value, String description, String discount) {
        this.value = value;
        this.description = description;
        this.discount = discount;
    }

}
