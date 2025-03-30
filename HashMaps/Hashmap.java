package HashMaps;

import java.util.ArrayList;
import java.util.LinkedList;

class Node<V, T> {
    int key;
    T value;

    Node(int key, T value) {
        this.key = key;
        this.value = value;
    }
}

public class Hashmap<V, T> { // key (Integer) -> Value (T) // hashmap by (((((seperate chaining)))))
    private LinkedList<Node<V, T>>[] array;
    private ArrayList<Integer> keySet;
    private int filledBuckets = 0;
    private int size = 11;
    private float threshold = 0.75f;

    @SuppressWarnings("unchecked")
    public Hashmap() {
        array = (LinkedList<Node<V, T>>[]) new LinkedList[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = new LinkedList<Node<V, T>>();
        }
        keySet = new ArrayList<Integer>();
    }

    @SuppressWarnings("unchecked")
    public Hashmap(int size) {
        this.size = size;
        array = (LinkedList<Node<V, T>>[]) new LinkedList[size];
        
        for (int i = 0; i < array.length; i++) {
            array[i] = new LinkedList<Node<V, T>>();
        }
        keySet = new ArrayList<Integer>();
    }

    @SuppressWarnings("unchecked")
    public Hashmap(int size, float threshold) {
        this.size = size;
        this.threshold = threshold;
        array = (LinkedList<Node<V, T>>[]) new LinkedList[size];
        
        for (int i = 0; i < array.length; i++) {
            array[i] = new LinkedList<Node<V, T>>();
        }
        keySet = new ArrayList<Integer>();
    }

    public void put(int key, T value) {
        keySet.add(key);
        int index = hashCode(key) % array.length;
        LinkedList<Node<V, T>> list = array[index];
        if (!hasKey(key)) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).key == key) {
                    list.get(i).value = value;
                    break;
                }
            }
        } else {
            if (array[index].isEmpty()) {
                filledBuckets++;
            }
            array[index].add(new Node<V, T>(key, value));
            if (loadFactor() > threshold) {
                this.rebuild();
            }
        }
    }

    public T get(int key) {
        if (!hasKey(key)) {
            throw new IllegalStateException("No such key: " + key);
        }
        int index = hashCode(key) % array.length;
        LinkedList<Node<V, T>> list = array[index];
        T value = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).key == key) {
                value = list.get(i).value;
                break;
            }
        }
        return value;
    }

    public void remove(int key) {
        if (!hasKey(key)) {
            throw new IllegalStateException("No such key: " + key);
        }
        keySet.remove(key);
        int index = hashCode(key) % size;

        LinkedList<Node<V, T>> list = array[index];
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).key == key) {
                list.remove(i);
                break;
            }
        }
        if (list.isEmpty()) {
            filledBuckets--;
        }
    }

    public ArrayList<Integer> keySet() {
        return keySet;
    }

    public boolean hasKey(int key) {
        if (!keySet.contains(key)) {
            return false;
        }
        return true;
    }

    public int hashCode(int key) {
        return Math.abs(key);
    }

    public float loadFactor() {
        return filledBuckets / (float) size;
    }

    private void rebuild() {
        Hashmap<V, T> temp = new Hashmap<V, T>(this.size * 2);
        for (int i = 0; i < this.keySet.size(); i++) {
            temp.put(this.keySet.get(i), this.get(this.keySet.get(i)));
        }
        this.array = temp.array;
        this.keySet = temp.keySet;
        this.size = temp.size;
        this.filledBuckets = temp.filledBuckets;
    }

    public static void main(String[] args) {
        Hashmap<Integer, String> map = new Hashmap<Integer, String>();
        map.put(0, "sa");
        map.put(1, "aa");
        map.put(2, "ca");
        map.put(3, "ba");
        map.put(4, "s");
        map.put(5, "a");
        map.put(6, "t");
        map.put(-7, "e");
        map.put(8, "g");
        System.out.println(map.get(0));
        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.get(3));
        System.out.println(map.get(4));
        System.out.println(map.get(5));
        System.out.println(map.get(6));
        System.out.println(map.get(-7));
        System.out.println(map.get(8));
        map.remove(0);
        System.out.println(map.keySet());
        System.out.println(map.loadFactor());
    }
}
