package impl;

import java.util.ArrayList;

public class ArrayListImpl<T> implements AbstractList<T>{

    ArrayList<T> arrayList;

    public ArrayListImpl() {
        arrayList = new ArrayList<>();
        System.out.println("ArrayList 로 구현합니다.");
    }

    @Override
    public void addElement(T obj) {
        arrayList.add(obj);
    }

    @Override
    public T deleteElement(int i) {
        return arrayList.remove(i);
    }

    @Override
    public int insertElement(T obj, int i) {
        arrayList.add(i, obj);
        return i;
    }

    @Override
    public T getElement(int i) {
        return arrayList.get(i);
    }

    @Override
    public int getElementSize() {
        return arrayList.size();
    }

    public String toString() {
        return arrayList.toString();
    }
}
