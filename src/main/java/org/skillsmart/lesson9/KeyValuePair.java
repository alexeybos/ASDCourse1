package org.skillsmart.lesson9;

public class KeyValuePair<T> {
    public String key;
    public T value;

    public KeyValuePair(String _key, T _value) {
        key = _key;
        value = _value;
    }
}
