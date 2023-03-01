package assignments;

public class BinaryNumber {

    private int data[];
    private int length;
    private String numberString;
    private int numberInt;
    private int[] numberArray;

    public BinaryNumber(int length) {
        String stringBinary = "";
        for (int i = 0; i < length; i++) {
            stringBinary += "0";
        }

        this.length = length;
        this.numberString = stringBinary;
        this.numberInt = Integer.parseInt(stringBinary);
        this.numberArray = getInnerArray();


    }

    public BinaryNumber(String str) {
        int intBinary = Integer.parseInt(str);

        this.numberString = str;
        this.numberInt = intBinary;
        this.length = str.length();
        this.numberArray = getInnerArray();
    }


    public String getNumber() {
        return this.numberString;
    }

    public int getLength() {
        return this.length;
    }

    public static boolean Bool(int intValue) {
        // The integer value
        // The expected boolean value
        boolean boolValue;

        // Check if it's greater than equal to 1
        if (intValue >= 1) {
            boolValue = true;
        } else {
            boolValue = false;
        }

        return boolValue;

    }
    public int[] getInnerArray() {
        int[] returnArray = new int[this.length];

        for (int i = 0; i < this.length; i++) {
            returnArray[i] = Integer.parseInt(numberString.substring(i, i + 1));
        }

        return returnArray;
    }

    public int getDigit(int index) throws Exception {

        if (index < 0) {
            throw new Exception("IndexOutOfBoundsException");
        } else {
            return Integer.parseInt(numberString.substring(index, index + 1));
        }
    }

    public int toDecimal() {
        int decimalNumber = 0;

        for (int i = 0; i < this.length; i++) {
            if (numberString.substring(i, i + 1) == "1") {
                decimalNumber += Math.pow(2, i);
            }
        }
        return decimalNumber;
    }

    public void bitShift(int dir, int amt) {

        int result = 0;

        if (dir > 0) {
            result = Integer.parseInt(numberString.substring(0, numberString.length() - amt));
        } else {
            String zeroes = "";
            for (int i = 0; i < amt; i++) {
                zeroes += "0";
            }
            result = Integer.parseInt(numberString + zeroes);
        }

        this.numberInt = result;
        this.numberString = String.valueOf(result);

    }
    public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2) {
        int[] bn1Array = bn1.getInnerArray();
        int[] bn2Array = bn2.getInnerArray();

        int[] returnValue = new int[bn1.getLength()];

        if (bn1Array.length == bn2Array.length) {
            for (int i = 0; i < bn1Array.length; i++) {
                int val0 = bn1Array[i];
                int val1 = bn2Array[i];

                if (Bool(val0) || Bool(val1)) {
                    returnValue[i] = 1;
                } else {
                    returnValue[i] = 0;

                }

                System.out.println(returnValue[i]);
            }

        } else {
            returnValue = new int[]{-1};
        }
        return returnValue;
    }

    public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2) {
        int[] bn1Array = bn1.getInnerArray();
        int[] bn2Array = bn2.getInnerArray();

        int[] returnValue = new int[bn1.getLength()];

        if (bn1Array.length == bn2Array.length) {
            for (int i = 0; i < bn1Array.length; i++) {
                int val0 = bn1Array[i];
                int val1 = bn2Array[i];

                if (Bool(val0) && Bool(val1)) {
                    returnValue[i] = 1;
                } else {
                    returnValue[i] = 0;

                }

                System.out.println(returnValue[i]);
            }

        } else {
            returnValue = new int[]{-1};
        }
        return returnValue;
    }

    public String toString() {
        return numberString;
    }

    public static String addZeroes(String number, int howMany)
    {
        String result = "";
        for (int i = 0; i < howMany; i++) {
            result += "0";
        }

        return (result + number);
    }
    public void add(BinaryNumber Number2)
    {
        // Number 1 is this instance
        // Number 2 is another instance

        String num1 = this.numberString;
        String num2 = Number2.getNumber();

        int l1 = num1.length();
        int l2 = num2.length();

        int diff = l1 - l2;

        String fixedNum1 = num1;
        String fixedNum2 = num2;

        if(diff > 0)
        {
            // No.1 is bigger so no.2 needs addZeroes
            fixedNum2 = addZeroes(fixedNum2, diff);
        }
        if (diff < 0)
        {
            fixedNum1 = addZeroes(fixedNum1, -1*diff);
        }

        int size = Math.max(l1,l2);

        String result = "0";
        int carry = 0;
        int [] sum = new int[size];

        int numInt1 = Integer.parseInt(fixedNum1);
        int numInt2 = Integer.parseInt(fixedNum2);

        int i = 0;
        while (numInt1 != 0 || numInt2 != 0)
        {
            sum[i++] = (int)((numInt1 % 10 + numInt2 % 10 + carry) % 2);
            carry = (int)((numInt1 % 10 + numInt2 % 10 + carry) / 2);
            numInt1 = numInt1 / 10;
            numInt2 = numInt2 / 10;
        }
        if (carry != 0) {
            sum[i++] = carry;
        }
        --i;


        while (i >= 0) {
            result += sum[i--];
        }

        this.numberString = result;

    }
}
