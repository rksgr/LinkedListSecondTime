import org.junit.Assert;
import org.junit.Test;


public class MyNodeTest {

    // UC1: create a linked list containing 56, 30 and 70 as elements
    @Test
    public void given3NumbersWhenLinkedShouldPassLinkedListTest(){
        // Creation of three nodes
        MyNode<Integer> myNodeOne = new MyNode<>(56);
        MyNode<Integer> myNodeTwo = new MyNode<>(30);
        MyNode<Integer> myNodeThree = new MyNode<>(70);

        // Above nodes not linked together, we link them now
        myNodeOne.setNext(myNodeTwo);
        myNodeTwo.setNext(myNodeThree);

        boolean result = myNodeOne.getNext().equals(myNodeTwo) &&
                myNodeTwo.getNext().equals(myNodeThree);
        Assert.assertTrue(result);
    }
}
