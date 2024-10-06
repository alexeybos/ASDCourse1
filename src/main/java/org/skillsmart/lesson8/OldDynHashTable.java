package org.skillsmart.lesson8;

public class OldDynHashTable extends HashTable
{
    private static final float FULLNESS_RATIO = 0.75f;

    public OldDynHashTable(int sz, int stp)
    {
        super(sz, stp);
    }

    public void makeArray(int _size) {
        String[] oldArray = slots;
        slots = new String[_size];
        size = _size;
        this.count = 0;
        for(int i=0; i<_size; i++) slots[i] = null;
        //пока придумалось только "в лоб"
        for (String s : oldArray) {
            if (s != null) {
                super.put(s);
            }
        }
    }

    @Override
    public int put(String value)
    {
        if ((float) count / size > FULLNESS_RATIO) {
            makeArray((size * 2) + 1);
        }
        return super.put(value);
    }

}



