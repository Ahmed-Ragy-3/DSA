package Labs.Lab4.Part2;
import Labs.Lab4.Part1.*;
public class PolynomialSolver{
    
    static SingleLinkedList[] array = new SingleLinkedList[3];
    
    
    public static void setPolynomial(char poly, int[] terms) {
        array[(int)(poly - 'A')] = new SingleLinkedList();
        SingleLinkedList list = array[(int)(poly - 'A')];
        for (int i = 0; i < terms.length; i++) {
            list.add(Integer.valueOf(terms[i]));
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

        return str ;
    }

    
    public static void clearPolynomial(char poly) {
        SingleLinkedList list = array[(int)(poly - 'A')];
        list.clear();
    }

    
    public static int evaluatePolynomial(char poly, float value) {
        SingleLinkedList list = array[(int)(poly - 'A')];
        SingleNode temp = list.head;
        int result = 0;

        for(int i = 0; i < list.size(); i++) {
            result += (Integer)temp.getValue() * Math.pow(value, i);
            temp = temp.getNext();
        }
        return result;
    }

    
    public static int[] add(char poly1, char poly2) {
        
        SingleLinkedList list1 = array[(int)(poly1 - 'A')];
        SingleLinkedList list2 = array[(int)(poly2 - 'A')]; 
        SingleLinkedList result = new SingleLinkedList();
        SingleNode temp1 = list1.head;
        SingleNode temp2 = list2.head;

        while(temp1 != null && temp2 != null)
        {
            result.addAtEnd((Integer)temp1.getValue() + (Integer)temp2.getValue());
            temp1 = temp1.getNext();
            temp2 = temp2.getNext();
        }
        while(temp2 != null)
        {
            result.addAtEnd((Integer)temp2.getValue());
            temp2 = temp2.getNext();
        }
        while(temp1 != null)
        {
            result.addAtEnd((Integer)temp1.getValue());
            temp1 = temp1.getNext();
        }
        return result.toArray();

    }

    
    public static int[] subtract(char poly1, char poly2) {
                
        SingleLinkedList list1 = array[(int)(poly1 - 'A')];
        SingleLinkedList list2 = array[(int)(poly2 - 'A')]; 
        SingleLinkedList result = new SingleLinkedList();
        SingleNode temp1 = list1.head;
        SingleNode temp2 = list2.head;

        while(temp1 != null && temp2 != null)
        {
            result.addAtEnd((Integer)temp1.getValue() - (Integer)temp2.getValue());
            temp1 = temp1.getNext();
            temp2 = temp2.getNext();
        }
        while(temp2 != null)
        {
            result.addAtEnd(-(Integer)temp2.getValue());
            temp2 = temp2.getNext();
        }
        while(temp1 != null)
        {
            result.addAtEnd((Integer)temp1.getValue());
            temp1 = temp1.getNext();
        }
        return result.toArray();
    }

    
    public static int[] multiply(char poly1, char poly2) {
        SingleLinkedList list1 = array[(int)(poly1 - 'A')];
        SingleLinkedList list2 = array[(int)(poly2 - 'A')]; 
        throw new UnsupportedOperationException("Unimplemented method 'multiply'");
    }
    
    public static void main(String[] args) {
        int[] array1 = {1, 1, 1};
        int[] array2 = {0, 1, 0, 2, 10};  
        int[] array3 = {4, 12, 3, 1};
        PolynomialSolver.setPolynomial('A', array1);
        PolynomialSolver.setPolynomial('B', array2);
        PolynomialSolver.setPolynomial('C', array3);
        array[0].print();
        array[1].print();
        array[2].print();
        int [] result;
        result = PolynomialSolver.add('A', 'B');
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
        result = PolynomialSolver.add('C', 'B');
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
        result = PolynomialSolver.subtract('C', 'B');
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
        result = PolynomialSolver.subtract('C', 'A');
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
        System.out.println(PolynomialSolver.evaluatePolynomial('A', 2));
    }
}
