package org.skillsmart.lesson9;

import java.lang.reflect.Array;
import java.util.Objects;

class BitDictionary<T>
{
    /**
     * Алгоритм
     * берем ключ, берем первые 4 бита - массив из 15 баскетов
     * следующие 4 бита - адресация внутри баскета
     * следующие 4 бита - адресация внутри баскета...
     */
    private static final int STORAGE_CAPACITY = 15;
    public int size;
    public int keySize;
    public Object [] baskets;


    public BitDictionary(int keySz, Class clazz)
    {
        keySize =keySz;
        baskets = prepareBaskets(keySz, clazz);
    }

    private Object[] prepareBaskets(int keySz, Class clazz) {
        if (keySz < 5) {
            return (T[]) Array.newInstance(clazz, STORAGE_CAPACITY);
        }
        //рекурсивно
        Object[] basket = (Object[]) Array.newInstance(Object.class, STORAGE_CAPACITY);
        for (int i = 0; i < STORAGE_CAPACITY; i++) {
            basket[i] = prepareBaskets(keySz - 4, clazz);
        }
        return basket;
    }

    public void put(String key, T value)
    {
        if (isKeySizeNotValid(key)) {
            throw new IllegalArgumentException("Key size should be " + keySize);
        }
        int iKey = Integer.parseInt(key, 2);
        T[] basket = getBasket(iKey);
        basket[(iKey >> (keySize - 4)) & STORAGE_CAPACITY] = value;
    }

    public T get(String key)
    {
        if (isKeySizeNotValid(key)) {
            return null;
        }
        int iKey = Integer.parseInt(key, 2);
        T[] basket = getBasket(iKey);
        return basket[(iKey >> (keySize - 4)) & STORAGE_CAPACITY];
    }

    private T[] getBasket(int iKey) {
        int mask = STORAGE_CAPACITY;
        Object[] basket = baskets;
        for (; iKey > mask; iKey = iKey>>4) {
            basket = (Object[]) basket[iKey & mask];
        }
        return (T[]) basket;
    }

    private boolean isKeySizeNotValid(String key) {
        return key.length() != keySize;
    }

    public void delete(String key) {
        put(key, null);
    }
}



