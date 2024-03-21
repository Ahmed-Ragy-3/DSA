package Labs.Lab4.Part2;
import Labs.Lab4.Part1.*;
public class PolynomialSolver{
    
    static SingleLinkedList[] array = new SingleLinkedList[3];
    // polynomial exponents are in ascending order in the linkedlist (lowest power is first)
    // Ex. --->   5 + x + 8x^2 - 3x^3    (5 , 1 , 8 , -3)
    public static void setPolynomial(char poly, int[] terms) {
        SingleLinkedList list = array[(int)(poly - 'A')] ;
        for (int i = 0 ; i < terms.length ; i++) {
            list.add(terms[i]) ; // add first
        }
    }
    
    public static String print(char poly) {
        //display ----> x^3-9x^2+26x-24
        SingleLinkedList list = array[(int)(poly - 'A')] ;
        String str = new String() ;
        SingleNode temp = list.head ;
        str = (String)(temp.getValue()) ;

        for (int i = 1 ; i < list.size ; i++) {

            if((Integer)temp.getValue() == 0) {
                continue ;
            }else if((Integer)temp.getValue() < 0) {
                str = ((temp.getValue()) + "x^" + (list.size-i)) + str ;
            }else {
                str = ("+" + (temp.getValue()) + "x^" + (list.size-i)) + str ;
            }

        }
        //str.replace("^1", "") ;
        return str ;
    }

    
    public static void clearPolynomial(char poly) {
        SingleLinkedList list = array[(int)(poly - 'A')] ;
        list.clear() ;
    }

    
    public static float evaluatePolynomial(char poly, float value) {

        SingleLinkedList list = array[(int)(poly - 'A')] ;
        SingleNode temp = list.head ;
        float result = 0.0f ;

        for(int i = 0; i < list.size(); i++) {
            result += (Float)temp.getValue() * Math.pow(value, i) ;
            temp = temp.getNext() ;
        }
        return result ;
    }

    
    public static int[] add(char poly1, char poly2) {
        
        SingleLinkedList list1 = array[(int)(poly1 - 'A')] ;
        SingleLinkedList list2 = array[(int)(poly2 - 'A')] ; 
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
        throw new UnsupportedOperationException("Unimplemented method 'multiply'") ;
    }
    
    public static void main(String[] args) {
        setPolynomial('A', null) ;
    }
}
