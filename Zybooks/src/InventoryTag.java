import java.util.Scanner;
// ===== Code from file InventoryTag.java =====
public class InventoryTag {
    private int quantityRemaining;

    public InventoryTag() {
        quantityRemaining = 0;
    }

    public int getQuantityRemaining() {
        return quantityRemaining;
    }

    public void addInventory(int numItems) {
        if (numItems > 10) {
            quantityRemaining = quantityRemaining + numItems;
        }
    }
}
// ===== end =====

// ===== Code from file CallInventoryTag.java =====

// ===== end =====