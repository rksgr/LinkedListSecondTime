

public class MyNode <K> {

    private K key;
    private MyNode next;

    public MyNode(K key){
        this.key = null;
        this.next = null;
    }

    public K getKey(){
        return key;
    }
    public void setKey(K key){
        this.key = key;
    }

    public MyNode getNext(){
        return next;
    }
    public void setNext(MyNode next){
        this.next = next;
    }

    public void addElement(K key){

    }
}
