package org.skillsmart.lesson11;

import org.skillsmart.lesson10.Bag;

public class EnhancedBloomFilter {

    public int filter_len;
    public int bloom;
    public Bag counter;


    public EnhancedBloomFilter(int f_len)
    {
        filter_len = f_len;
        bloom = 2 << (f_len - 1);
        counter = new Bag();
    }

    public int hash1(String str1)
    {
        int prevRes = 0;
        for(int i=0; i<str1.length(); i++)
        {
            int code = (int)str1.charAt(i);
            prevRes = (prevRes * 17 + code) % filter_len;
        }
        return prevRes;
    }

    public int hash2(String str1)
    {
        int prevRes = 0;
        for(int i=0; i<str1.length(); i++)
        {
            int code = (int)str1.charAt(i);
            prevRes = (prevRes * 223 + code) % filter_len;
        }
        return prevRes;
    }

    public void add(String str1)
    {
        int index1 = 1 << hash1(str1);
        int index2 = 1 << hash2(str1);
        bloom |= index1;
        bloom |= index2;
        counter.put(String.valueOf(index1));
        counter.put(String.valueOf(index2));
    }

    public void delete(String str1) {
        int index1 = 1 << hash1(str1);
        int index2 = 1 << hash2(str1);
        counter.delete(String.valueOf(index1));
        counter.delete(String.valueOf(index2));
        if (counter.elementFrequency(String.valueOf(index1)) == 0) bloom = bloom & ~index1;
        if (counter.elementFrequency(String.valueOf(index2)) == 0) bloom = bloom & ~index2;
    }

    public boolean isValue(String str1)
    {
        int index1 = 1 << hash1(str1);
        int index2 = 1 << hash2(str1);
        return ((bloom & index1) == index1) && ((bloom & index2) == index2);
    }

}
