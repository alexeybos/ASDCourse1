package org.skillsmart.lesson11;

import java.util.ArrayList;
import java.util.List;

public class Lesson11utils {

    /**
     * Алгоритм подбора возможных значений, загруженных в фильтр Блюма. Допустим мы знаем m и n, длину строк
     * По словарю можно попробовать подбирать строки (используя isValue), пока не будет тождественно заполнен временный фильтр Блюма
     * Реализован именно этот, первый вариант - подбора по строкам.
     * Можно, подобрав строку на включенные биты фильтра, выключать полученные биты и далее подбирать для оставшихся включенных.
     * Но тут мне кажется вероятность получить хоть что-то похожее на входные данные еще меньше
     */
    public static void main(String[] args) {
        List<Character> chars = new ArrayList<>();
        BloomFilter filter = new BloomFilter(32);
        for (int i = 48; i <= 57; i++) chars.add((char)i);
        //for (int i = 65; i <= 90; i++) chars.add((char)i);
        //for (int i = 97; i <= 122; i++) chars.add((char)i);
        filter.add("0123456789");
        filter.add("1234567890");
        filter.add("2345678901");
        filter.add("3456789012");
        filter.add("4567890123");
        filter.add("5678901234");
        filter.add("6789012345");
        filter.add("7890123456");
        filter.add("8901234567");
        filter.add("9012345678");
        String[] same = new String[10];
        int sameCount = 0;
        int bruteForceFilterBloom = 0;
        boolean equalFilters = false;
        for (int i = 0; i < 10000 || !equalFilters; i++) {
            for (int ii = 0; ii < 100000 && sameCount < 10; ii++) {
                char[] randomChars = new char[10];
                for (int j = 0; j < 10; j++) {
                    randomChars[j] = chars.get((int) (Math.random() * chars.size()));
                }
                String forCheck = new String(randomChars);
                if (filter.isValue(forCheck)) {
                    same[sameCount] = forCheck;
                    sameCount += 1;
                }
            }
            BloomFilter bruteForceFilter = new BloomFilter(32);
            for (String s : same) {
                bruteForceFilter.add(s);
            }
            bruteForceFilterBloom = bruteForceFilter.bloom;
            equalFilters = filter.bloom == bruteForceFilter.bloom;
        }
        System.out.println("sameCount = " + sameCount);
        System.out.println("filter.bloom = " + filter.bloom);
        System.out.println("bruteForceFilter.bloom = " + bruteForceFilterBloom);
        for (String s : same) {
            System.out.println(s);
        }
    }
}

//Итоговый результат в принципе предсказуем - ни одного совпадения...
//filter.bloom = 671096864
//bruteForceFilter.bloom = 671096864
//7pchxJZ5p2
//Q0oORhwwPv
//70KazhqLLO
//yfP9enkAVN
//xlxjPW2Cnu
//Goz8WUVkZn
//v9j3lygUEk
//lGHkJmSLhI
//mucu2jc6wW
//dH5C9QE4Mq
//Даже если ограничить словарь одними цифрами:
//filter.bloom = 671096864
//bruteForceFilter.bloom = 671096864
//4059963124
//2652905888
//3782860263
//7499482370
//5043826483
//4761796892
//5824627333
//1901974068
//7258320819
//7382140903