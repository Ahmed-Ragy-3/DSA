package Heaps;
import java.util.Arrays;

class Node<T> {
    T value;
    int key;
    public Node(T value, int key) {
        this.value = value;
        this.key = key;
    }
}

public class MaxHeap<T> {
    private Node<T>[] array;
    int pntr = 1;

    @SuppressWarnings("unchecked")
    public MaxHeap() {
        array = new Node[16];
    }
    @SuppressWarnings("unchecked")
    public MaxHeap(int size) {
        array = new Node[size];
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void insert(T value, int key) {
        if(pntr >= array.length)
        {
            rebuild();
        }
        array[pntr] = new Node(value, key);
        int tempPntr = pntr;
        while(hasParent(tempPntr))
        {
            if(array[tempPntr].key > array[tempPntr / 2].key)
            {
                swapWithParent(tempPntr);
                tempPntr = tempPntr / 2;
            }
            else
            {
                break;
            }
        }
        pntr++;
    }

    public int capacity() {
        return array.length-1;
    }
    public int size() {
        return pntr-1;
    }
    public T deleatMax() {
        T out = array[1].value;
        swap(1, pntr-1);
        pntr--;
        int tempPntr = 1;
        while(tempPntr < pntr) 
        {
            if((hasLeftChild(tempPntr) && array[tempPntr * 2].key > array[tempPntr].key) && (hasRightChild(tempPntr) && array[tempPntr * 2 + 1].key > array[tempPntr].key))
            {
                if(array[tempPntr * 2].key > array[tempPntr * 2 + 1].key)
                {
                    swap(tempPntr, tempPntr*2);
                    tempPntr = tempPntr * 2;
                }
                else{
                    swap(tempPntr, tempPntr * 2 + 1);
                    tempPntr = tempPntr * 2 + 1;
                }
            }
            else if(hasLeftChild(tempPntr) && array[tempPntr * 2].key > array[tempPntr].key)
            {
                swap(tempPntr, tempPntr * 2);
                tempPntr = tempPntr * 2;
            }
            else if(hasRightChild(tempPntr) && array[tempPntr * 2 + 1].key > array[tempPntr].key)
            {
                swap(tempPntr, tempPntr * 2 + 1);
                tempPntr = tempPntr * 2 + 1;
            }
            else{
                break;
            }
        }
        return out;
    }

    static int[] heapSort(int[] array)
    {
        MaxHeap<Integer> heap = new MaxHeap<Integer>(array.length);
        for(int i = 0; i < array.length; i++)
        {
            heap.insert(array[i], array[i]);
        }
        for(int i = 0; i < array.length; i++)
        {
            array[array.length - i-1] = heap.deleatMax();
        }
        return array;
    }

    private boolean hasLeftChild(int index)
    {
        if(2 * index <= pntr - 1)
        {
            return true;
        }
        return false;
    }

    private boolean hasRightChild(int index)
    {
        if(2 * index  + 1 <= pntr -1)
        {
            return true;
        }
        return false;
    }
    private boolean hasParent(int index)
    {
        if(index / 2 >= 1 && index / 2 <= array.length)
        {
            return true;
        }
        return false;
    }

    private void swapWithParent(int index) {
        if(!hasParent(index))
        {
            System.out.println("No parent Error!");
            System.exit(0);
        }
        Node<T> temp = array[index];
        array[index] = array[index / 2];
        array[index / 2] = temp;
    }

    private void swap(int index1, int index2)
    {
        Node<T> temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public void print() {
        System.out.print("Key: ");
        for(int i = 1; i < pntr; i++)
        {
            System.out.print(array[i].key + " ");
        }
        System.out.println();
        System.out.print("Val: ");
        for(int i = 1; i < pntr; i++)
        {

            System.out.print(array[i].value + " ");

        }
        System.out.println();
    }

    void rebuild()
    {
        MaxHeap<T> temp = new MaxHeap<T>(2 * this.capacity());
        for(int i = 1; i < pntr; i++)
        {
            temp.insert(array[i].value, array[i].key);
        }
        this.array = temp.array;
        this.pntr = temp.pntr;
        temp = null;
        System.gc();
    }
    public static void main(String[] args) {
        MaxHeap<Integer> heap = new MaxHeap<Integer>(16);
        heap.insert(1, 1);
        heap.insert(4, 4);
        heap.insert(5, 5);
        heap.insert(2, 2);
        heap.insert(3, 3);
        heap.insert(6, 6);
        heap.insert(7, 7);
        heap.insert(8, 8);
        heap.insert(9, 9);
        heap.insert(11, 11);
        heap.insert(11, 11);
        heap.insert(11, 11);
        heap.insert(13, 13);
        heap.insert(14, 14);
        heap.insert(15, 15);
        heap.insert(16, 16);
        heap.insert(17, 17);
        heap.insert(18, 18);

        //         15
        //     10       14
        //   7    9    11   13
        //  1 3  2 8  4 6  5 12
 
        heap.print();

        while(heap.size() > 0)
        {
            System.out.print(heap.deleatMax() + " ");
        }
        System.out.println();
        System.out.println(heap.capacity() );
        System.out.println(Arrays.toString(MaxHeap.heapSort(new int[]{3,6,1,7,5,1,8,10,9,2,0,1})));

        
        //     8
        //   7    6
        // 3  2  4  5
        //1
    }
}
