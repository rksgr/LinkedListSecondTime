import org.junit.Assert;
import org.junit.Test;


public class MyLinkedListTest {

    // UC2: Create a linked list by adding 56 and 30 to 70 (56->30->70)
    @Test
    public void given3NumbersWhenAddedToLinkedListShouldBeAddedToTop(){
        MyNode<Integer> myNodeOne = new MyNode<>(70);
        MyNode<Integer> myNodeTwo = new MyNode<>(30);
        MyNode<Integer> myNodeThree = new MyNode<>(56);

        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.add(myNodeOne);
        myLinkedList.add(myNodeTwo);
        myLinkedList.add(myNodeThree);

        myLinkedList.printMyNodes();
        boolean result = myLinkedList.head.equals(myNodeThree)  &&
                            myLinkedList.head.getNext().equals(myNodeTwo) &&
                            myLinkedList.tail.equals(myNodeOne);
        Assert.assertTrue(result);
    }

    // UC3: Create a linked list by appending 30 and 70 to 56 (56->30->70)
    @Test
    public void given3NumbersWhenAppendedToLinkedListShouldBeAppendedToEnd(){
        MyNode<Integer> myNodeOne = new MyNode<>(56);
        MyNode<Integer> myNodeTwo = new MyNode<>(30);
        MyNode<Integer> myNodeThree = new MyNode<>(70);
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.append(myNodeOne);
        myLinkedList.append(myNodeTwo);
        myLinkedList.append(myNodeThree);
        boolean result = myLinkedList.head.equals(myNodeOne) &&
                        myLinkedList.head.getNext().equals(myNodeTwo) &&
                        myLinkedList.tail.equals(myNodeThree);
        Assert.assertTrue(result);
    }

    // UC 4: Insert a node between 56 and 70, Append 70 to 56
    @Test
    public void givenANumberInsertedAfterANumbersShouldBeInsertedAfter(){
        MyNode<Integer> myNodeOne = new MyNode<>(56);
        MyNode<Integer> myNodeTwo = new MyNode<>(30);
        MyNode<Integer> myNodeThree = new MyNode<>(70);

        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.append(myNodeOne);
        myLinkedList.append(myNodeThree);
        myLinkedList.insert(myNodeOne, myNodeTwo);

        boolean result = myLinkedList.head.equals(myNodeOne) &&
                myLinkedList.head.getNext().equals(myNodeTwo) &&
                myLinkedList.tail.equals(myNodeThree);
        Assert.assertTrue(result);
    }
}
