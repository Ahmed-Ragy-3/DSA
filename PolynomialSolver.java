import java.util.Scanner;

public class PolynomialSolver{
    
    static SingleLinkedList[] array = new SingleLinkedList[4];
    
    
    public static void setPolynomial(char poly, int[] terms) {
        array[(int)(poly - 'A')] = new SingleLinkedList();
        SingleLinkedList list = array[(int)(poly - 'A')];
        for (int i = 0; i < terms.length; i++) {
            list.add(Integer.valueOf(terms[i]));
        }
    }
    
    public static String print_poly(char poly) {
        //display ----> x^3-9x^2+26x-24
        // input ----> -24 , 26 , -9 , 1
        // 0 , 1 , 0 , 2 , 10
        SingleLinkedList list = array[(int)(poly - 'A')] ;
        if(list.isEmpty()) { return "" ;}

        String str = new String() ;
        SingleNode temp = list.head ;

        for (int i = 0 ; i < list.size ; i++) {

            if((Integer)temp.getValue() == 0) { 
                temp = temp.next ;
                continue ;
            }

            if(i == 1) { // x^1

                if((Integer)temp.value < 0) {
                    str = ((temp.value) + "x") + str ;
                }else {
                    str = ("+" + (temp.value) + "x") + str ;
                }

            }else {

                if((Integer)temp.value < 0) {
                    str = ((temp.value) + "x^" + (i)) + str ;
                }else {
                    str = ("+" + (temp.value) + "x^" + (i)) + str ;
                }

            }
            temp = temp.next;
        }
        
        // handle first element
        if(str.charAt(0) == '+') {
            str = str.replaceFirst("\\+" , "") ;
        }

        str = str.replaceAll("x\\^0", "") ;
        str = str.replaceAll("1x", "x") ;
        
        return str ;
    }
    
    public static void clearPolynomial(char poly) {
        SingleLinkedList list = array[(int)(poly - 'A')];
        list.clear();
    }

    public static int evaluatePolynomial(char poly, float value) {
        SingleLinkedList list = array[(int)(poly - 'A')];
        SingleNode temp = list.head;
        int eval = 0;

        for(int i = 0; i < list.size(); i++) {
            eval += (Integer)temp.getValue() * Math.pow(value, i);
            temp = temp.getNext();
        }
        return eval;
    }

    public static int[] add(char poly1, char poly2) {
        
        SingleLinkedList list1 = array[(int)(poly1 - 'A')] ;
        SingleLinkedList list2 = array[(int)(poly2 - 'A')] ;
        array[3] = new SingleLinkedList();
        SingleLinkedList result = array[3];
        SingleNode temp1 = list1.head ;
        SingleNode temp2 = list2.head ;

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
        array[3] = new SingleLinkedList();
        SingleLinkedList result = array[3];
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
        return result.toArray() ;
    }

    public static int[] multiply(char poly1, char poly2) {

        int[] list1 = array[(int)(poly1 - 'A')].toArray() ;
        int[] list2 = array[(int)(poly2 - 'A')].toArray() ;
        int[] resultarray = new int[list1.length + list2.length - 1] ;

        for (int i = 0 ; i < list1.length ; i++) {
            for (int j = 0 ; j < list2.length ; j++) {
                resultarray[i + j] += list1[i] * list2[j] ;
            }
        }
        array[3] = new SingleLinkedList();
        SingleLinkedList result = array[3];
        for(int i = 0 ; i < resultarray.length ; i++) {
            result.addAtEnd(Integer.valueOf(resultarray[i]));
        }
        return resultarray;
    }

    public static int[] coeff_array(String str) { // convert input to terms[] array

        String[] str_array = str.split(",") ;
        int[] coeff = new int[str_array.length] ;
        //list.clear();
        for (int i = 0 ; i < coeff.length ; i++) {
            coeff[i] = Integer.parseInt(str_array[i]) ;
        }
        return coeff ;

    }
    
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in) ;
        while(true) {

            String operation = input.nextLine() ;

            if(operation.compareTo("set") == 0) {
                char c = input.nextLine().charAt(0) ;
                int[] terms = coeff_array(input.nextLine().replaceAll("\\[|\\]", "")) ;
                setPolynomial(c , terms) ;

            }else if(operation.compareTo("print") == 0) {
                System.out.println(print_poly(input.nextLine().charAt(0))) ;

            }else if(operation.compareTo("add") == 0) {
                char c1 = input.nextLine().charAt(0) ;
                char c2 = input.nextLine().charAt(0) ;
                add(c1,c2) ;
                System.out.println(print_poly('D'));
               

            }else if(operation.compareTo("sub") == 0) {
                char c1 = input.nextLine().charAt(0) ;
                char c2 = input.nextLine().charAt(0) ;
                subtract(c1, c2) ;
                System.out.println(print_poly('D'));

            }else if(operation.compareTo("mult") == 0) {
                char c1 = input.nextLine().charAt(0) ;
                char c2 = input.nextLine().charAt(0) ;
                multiply(c1,c2) ;
                System.out.println(print_poly('D'));

            }else if(operation.compareTo("clear") == 0) {
                clearPolynomial(input.nextLine().charAt(0)) ;
                System.out.println("[]") ;

            }else if(operation.compareTo("eval") == 0) {
                System.out.println(evaluatePolynomial(input.nextLine().charAt(0), input.nextInt())) ;
            }
        }
        //input.close() ;
    
    }
}
