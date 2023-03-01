package assignments;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class testBinaryNumber {
    @Test
    public void firstTest()
    {
        Assert.assertTrue(true);
    }

    @Test
    public void secondTest()
    {
        fail("Not yet implemented");
    }

    @Test
    public void toDecimalTest()
    {
        int [] decimals = {0, 1, 11, 1, 26,63};
        String[] testStrings = {"0", "1", "1011", "0001", "11010", "111111"};

        for (int i = 0; i < testStrings.length; i++) {
            BinaryNumber bn = new BinaryNumber(testStrings[i]);
            assertEquals(decimals[i], bn.toDecimal());
        }

    }}
