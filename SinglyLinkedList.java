import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap(){
        if (size <= 1){
            return; 
        }

        List<Node<E>> order = new ArrayList<>(size);
        Node<E> current = head;
        while (current != null){
            order.add(current);
            current = current.getNext();
        }

        int n = order.size();

        Integer[] indicesByValue = new Integer[n];
        for (int i = 0; i < n; i++){
            indicesByValue[i] = i;
        }
        Arrays.sort(indicesByValue,
                (a, b) -> order.get(a).getElement().compareTo(order.get(b).getElement()));

        
        int lo = 0, hi = n - 1;
        while (lo < hi){
            int posOfSmall = indicesByValue[lo];
            int posOfBig   = indicesByValue[hi];

            Node<E> temp = order.get(posOfSmall);
            order.set(posOfSmall, order.get(posOfBig));
            order.set(posOfBig, temp);

            lo++;
            hi--;
        }

        for (int i = 0; i < n - 1; i++){
            order.get(i).setNext(order.get(i + 1));
        }
        order.get(n - 1).setNext(null);

        head = order.get(0);
        tail = order.get(n - 1);

    }
   
}

