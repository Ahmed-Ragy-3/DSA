package Labs.Lab4.Part2;
import Labs.Lab4.Part1.*;
public class PolynomialSolver{
    
    static SingleLinkedList[] array = new SingleLinkedList[3];

    
    public static void setPolynomial(char poly, int[] terms) {
        SingleLinkedList list = array[(int)(poly - 'A')];
        for (int i = 0; i < terms.length; i++) {
            list.add(terms[i]);
        }
    }

    
    public static String print(char poly) {
        SingleLinkedList list = array[(int)(poly - 'A')];
        throw new UnsupportedOperationException("Unimplemented method 'print'");
    }

    
    public static void clearPolynomial(char poly) {
        SingleLinkedList list = array[(int)(poly - 'A')];
        list.clear();
    }

    
    public static float evaluatePolynomial(char poly, float value) {
        SingleLinkedList list = array[(int)(poly - 'A')];
        SingleNode temp = list.head;
        float result = 0.0f;

        for(int i = 0; i < list.size(); i++) {
            result += (Float)temp.getValue() * Math.pow(value, i);
            temp = temp.getNext();
        }
        return result;
    }

    
    public static int[] add(char poly1, char poly2) {
        
        SingleLinkedList list1 = array[(int)(poly1 - 'A')];
        SingleLinkedList list2 = array[(int)(poly2 - 'A')]; 
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    
    public static int[] subtract(char poly1, char poly2) {
        SingleLinkedList list1 = array[(int)(poly1 - 'A')];
        SingleLinkedList list2 = array[(int)(poly2 - 'A')]; 
        throw new UnsupportedOperationException("Unimplemented method 'subtract'");
    }

    
    public static int[] multiply(char poly1, char poly2) {
        SingleLinkedList list1 = array[(int)(poly1 - 'A')];
        SingleLinkedList list2 = array[(int)(poly2 - 'A')]; 
        throw new UnsupportedOperationException("Unimplemented method 'multiply'");
    }
    
    public static void main(String[] args) {
        
    }
}
