package pl.edu.pw.elka.prm2t.cw3;

/**
 * Implementacja nielimitowanego stosu
 * @author Wojciech Ryba, Kajetan Rosik
 */

class Node {
    Node next = null;
    int data;
    Node(int newData){
        this.data = newData;
        this.next = null;
    }
    Node(int newData, Node nextElement){
        this.data = newData;
        this.next = nextElement;
    }

    public String toString(){
        String nextStr;
        if(this.next == null){
            nextStr = "null";
        } else {
            nextStr = "Node";
        }
        return "Node {data=" + this.data + ", next=" + nextStr + "}";
    }

}

public class UnlimitedStack {
    Node topElement;

    public void push(int newData){
        Node newElement = new Node(newData, topElement);
        topElement = newElement;
    }
    public void pop(){
        if(topElement == null){
            return;
        }
        Node temp = topElement;
        topElement = temp.next;
        temp = null;
    }
    public int peek(){
        if(topElement == null)
            return 0;
        return topElement.data;
    }
    public String display(){
        Node nextElement = topElement;;
        if(nextElement == null) {
            return "ni mo";
        }
        String res = "";
        do{
             res += nextElement.toString() + "\n";
             Node temp = nextElement;
             nextElement = temp.next;
             if(nextElement == null){
                 break;
             }
             temp = null;
        } while(true);

        return res;
    }

    UnlimitedStack(){
        this.topElement = null;
    }

    public static void main(String[] args){
        UnlimitedStack stack = new UnlimitedStack();
        stack.push(2137);
        System.out.println(stack.display());
        stack.push(6969);
        System.out.println(stack.display());
        System.out.println(stack.peek());
        stack.pop();
        System.out.println(stack.display());
        stack.pop();
        stack.pop();
        System.out.println(stack.display());
        System.out.println(stack.peek());
        System.out.println(stack.display());
    }
}
