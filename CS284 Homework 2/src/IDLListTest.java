import static org.junit.Assert.*;
import org.junit.Test;

public class IDLListTest <E>{
    @Test
    public void test() {
        IDLList<Integer> l = new IDLList<Integer>();

        //adding 5 to front
        assertEquals(l.add(5), true);
        assertEquals(l.toString(), "null<-5->null");
        //adding 7 to front
        assertEquals(l.add(7), true);
        assertEquals(l.toString(), "null<-7-><-5->null");

        //adding 9 at index 1
        assertEquals(l.add(1, 9), true);
        assertEquals(l.toString(), "null<-7-><-9-><-5->null");

        //appends 8
        assertEquals(l.append(8), true);
        assertEquals(l.toString(), "null<-7-><-9-><-5-><-8->null");

        //gets head and tests get method
        assertEquals(l.getHead(), l.get(0));

        //gets tail and tests get method
        assertEquals(l.getLast(), l.get(3));

        //check size of list
        assertEquals(l.size, 4);

        //removes element at front of list then checks size
        l.remove();
        assertEquals(l.toString(), "null<-9-><-5-><-8->null");

        //adds 7 back to list
        assertEquals(l.add(7), true);
        assertEquals(l.toString(), "null<-7-><-9-><-5-><-8->null");

        //removes the last element of the list
        l.removeLast();
        assertEquals(l.toString(), "null<-7-><-9-><-5->null");

        //appends 8 back to the list
        assertEquals(l.append(8), true);
        assertEquals(l.toString(), "null<-7-><-9-><-5-><-8->null");

        System.out.println(l);
        //removes index 2
        l.removeAt(2);
        assertEquals(l.toString(), "null<-7-><-9-><-8->null");

        //adds 5 back at index 2
        assertEquals(l.add(2, 5), true);
        assertEquals(l.toString(), "null<-7-><-9-><-5-><-8->null");

        //appends 6 to the end of the list
        assertEquals(l.append(6), true);
        assertEquals(l.toString(), "null<-7-><-9-><-5-><-8-><-6->null");

        //removes the first occurrence of 5 in the list
        assertEquals(l.remove(5), true);
        assertEquals(l.toString(), "null<-7-><-9-><-8-><-6->null");

        //removes the last element
        System.out.println("here");
        l.removeLast();
        assertEquals(l.toString(), "null<-7-><-9-><-8->null");

        //adds 5 back to the front of the list
        assertEquals(l.add(5), true);
        assertEquals(l.toString(), "null<-5-><-7-><-9-><-8->null");

        //tries to remove a 10 in the list
        assertEquals(l.remove(10), false);
        assertEquals(l.toString(), "null<-5-><-7-><-9-><-8->null");

        System.out.println("All tests passed");

    }
}