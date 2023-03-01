public class Main {
    public static void main(String[] args) {

        IDLList<String> L = new IDLList();
        L.add(0, "A");
        L.add(1, "B");
        L.add("C");
        L.add("D");

        IDLList<Integer> L2 = new IDLList();
        L2.add(0, 33);
        L2.add(1, 1);
        L2.add(50);
        L2.append(33);

        System.out.println(L2);
        System.out.println();

        System.out.println("after removal: ");


        L2.remove(1);



        System.out.println(L2);







    }
}