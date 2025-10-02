package items;

public class ItemStack {
    public final Item item;
    public final double quantity;

    public ItemStack(Item item, double quantity) {
        this.item = item;
        this.quantity = quantity;
    }
    public ItemStack(Item item) {
        this.item = item;
        this.quantity = 1;
    }
}
