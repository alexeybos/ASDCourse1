package org.skillsmart.lesson1;

public class Lesson1Task8 {

    public static void main(String[] args) {
        Lesson1Utils utils = new Lesson1Utils();
        LinkedList small = new LinkedList();
        small.addInTail(new Node(1));

        LinkedList list1000000 = new LinkedList();
        for (int i = 0; i < 1000000; i++) {
            list1000000.addInTail(new Node(i));
        }
        LinkedList list1000000rew = new LinkedList();
        for (int i = 1000000; i > 0; i--) {
            list1000000rew.addInTail(new Node(i));
        }

        LinkedList list10000000 = new LinkedList();
        for (int i = 0; i < 10000000; i++) {
            list10000000.addInTail(new Node(i));
        }
        LinkedList list10000000rew = new LinkedList();
        for (int i = 10000000; i > 0; i--) {
            list10000000rew.addInTail(new Node(i));
        }

        /*long firstStartTime = System.currentTimeMillis();
        LinkedList result1 = utils.sumEqualLengthLists1(list1000000, list1000000rew);
        long firstCost =System.currentTimeMillis()-firstStartTime;
        System.out.println("for 1 method cost for 1000000= "+ firstCost);*/
        //16 ms

        /*long firstStartTimeBig = System.currentTimeMillis();
        LinkedList result1big = utils.sumEqualLengthLists1(list10000000, list10000000rew);
        long firstCostBig =System.currentTimeMillis()-firstStartTimeBig;
        System.out.println("for 1 method cost for 10000000= "+ firstCostBig);*/
        //4492ms

        /*long secondStartTime = System.currentTimeMillis();
        LinkedList result2 = utils.sumEqualLengthLists2(list1000000, list1000000rew);
        long secondCost =System.currentTimeMillis()-secondStartTime;
        System.out.println("for 2 method cost for 1000000= "+ secondCost);*/
        //12

        /*long secondStartTimeBig = System.currentTimeMillis();
        LinkedList result2big = utils.sumEqualLengthLists2(list10000000, list10000000rew);
        long secondCostBig =System.currentTimeMillis()-secondStartTimeBig;
        System.out.println("for 2 method cost for 10000000= "+ secondCostBig);
        System.out.println("result2big.count() = "+ result2big.count());*/
        //4266

        /*long thirdStartTime = System.currentTimeMillis();
        LinkedList result3 = utils.sumEqualLengthLists3(list1000000, list1000000rew);
        long thirdCost =System.currentTimeMillis()-thirdStartTime;
        System.out.println("for 3 method cost for 1000000= "+ thirdCost);*/
        //9

        /*long thirdStartTimeBig = System.currentTimeMillis();
        LinkedList result3big = utils.sumEqualLengthLists3(list10000000, list10000000rew);
        long thirdCostBig =System.currentTimeMillis()-thirdStartTimeBig;
        System.out.println("for 3 method cost for 10000000= "+ thirdCostBig);*/
        //4223

        long firstStartTimeBigNoEqual = System.currentTimeMillis();
        LinkedList result1noEqual = utils.sumEqualLengthLists1(list10000000, small);
        long firstTimeSmall =System.currentTimeMillis()-firstStartTimeBigNoEqual;
        System.out.println("firstTimeSmall = "+ firstTimeSmall);
        //24

        //у sumEqualLengthLists2 = 0
        //у sumEqualLengthLists3 = 0
    }

}
