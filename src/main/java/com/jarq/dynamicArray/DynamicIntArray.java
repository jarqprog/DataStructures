package com.jarq.dynamicArray;

import java.util.Arrays;

public class DynamicIntArray implements IDynamicIntArray {

    private int[] storage;

    DynamicIntArray(int numberOfElements) {
        this.storage = new int[numberOfElements+1];
    }

    DynamicIntArray() {
        this.storage = new int[0];
    }

    @Override
    public void add(int num) {
        extendStorageSizeByOne();
        int lastPlace = storage.length-1;
        storage[lastPlace] = num;
    }

    @Override
    public void remove(int index) {
        if(index >= storage.length || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            storage = concatIntArrays(
                    Arrays.copyOfRange(storage, 0, index),
                    Arrays.copyOfRange(storage, index+1, storage.length));
        }
    }

    @Override
    public void insert(int index, int num) {
        if (index >= storage.length) {
            add(num);
        } else {
            int tmp = storage[index];
            storage[index] = num;
            storage = concatIntArrays(
                    Arrays.copyOfRange(storage, 0, index+1),
                    new int[]{tmp},
                    Arrays.copyOfRange(storage, index+1, storage.length));
        }
    }

    public String toString() {
        return " " + Arrays.toString(storage)
                .replaceAll("\\[", "")
                .replaceAll("]","")
                .replaceAll(",", "");
    }

    private void extendStorageSizeByOne() {
        storage = Arrays.copyOf(storage, storage.length +1);
    }

    private int[] concatIntArrays(int[]... arrays) {
        int outputSize = 0;
        for(int[] array : arrays) {
            outputSize += array.length;
        }

        int[] first = arrays[0];
        int[] output = Arrays.copyOf(arrays[0], outputSize);
        int currentSize = first.length;

        for (int i=1; i<arrays.length; i++) {
            System.arraycopy(arrays[i], 0, output, currentSize, arrays[i].length);
            currentSize += arrays[i].length;
        }
        return output;
    }
}
