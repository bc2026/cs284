import java.util.Scanner;

public class CallInventoryTag {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        InventoryTag redSweater = new InventoryTag();
        int sweaterShipment;
        int sweaterInventoryBefore;

        sweaterInventoryBefore = redSweater.getQuantityRemaining();
        sweaterShipment = scnr.nextInt();

        System.out.println("Beginning tests.");

        // FIXME add unit test for addInventory


        //Write a unit test for addInventory(), which has an error. Call redSweater.addInventory() with argument sweaterShipment.
        //Print the shown error if the subsequent quantity is incorrect. Sample output for failed unit test given initial
        //quantity is 10 and sweaterShipment is 50:

        int initValue = redSweater.getQuantityRemaining();
        redSweater.addInventory(sweaterShipment);

        if (redSweater.getQuantityRemaining() != initValue + sweaterShipment) {
            System.out.println("    UNIT TEST FAILED: addInventory()");
        }
        /* Your solution goes here  */
        System.out.println("Tests complete.");
    }
}
