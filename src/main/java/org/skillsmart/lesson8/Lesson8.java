package org.skillsmart.lesson8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Lesson8 {

    public static void main(String[] args) {

        List<Character> chars = new ArrayList<>();
        for (int i = 48; i <= 57; i++) chars.add((char)i);
        for (int i = 65; i <= 90; i++) chars.add((char)i);
        for (int i = 97; i <= 122; i++) chars.add((char)i);
        SaltHashTable table = new SaltHashTable(19, 3, false);
        SaltHashTable tableSalt = new SaltHashTable(19, 3, true);
        String[] same = new String[10];
        int sameCount = 0;
        for (int i = 0; i < 100000 && sameCount < 10; i++) {
            char[] randomChars = new char[4];
            for (int j = 0; j < 4; j++) {
                randomChars[j] = chars.get((int) (Math.random() * chars.size()));
            }
            String forCheck = new String(randomChars);
            if (table.hashFun(forCheck) == 12) {
                same[sameCount] = forCheck;
                sameCount += 1;
            }
        }
        for (String s : same) {
            table.put(s);
        }
        System.out.println("collisionCount no salt = " + table.collisionCount); //много коллизий

        for (String s : same) {
            tableSalt.put(s);
        }
        System.out.println("collisionCount salt = " + tableSalt.collisionCount); //мало или нет коллизий
    }
}
