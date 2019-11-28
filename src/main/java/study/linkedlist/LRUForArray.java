package study.linkedlist;

import java.util.ArrayList;

/**
 * 使用数组实现 LRU缓存淘汰算法
 */
public class LRUForArray<T> {
    private ArrayList<T> caches;
    private int capacity;
    public LRUForArray(int cacheSize) {
        this.capacity = cacheSize;
        this.caches = new ArrayList<>();
    }

    public T getFromCache(T data) {
        if (caches.contains(data)) {
            caches.remove(data);
            caches.add(0, data);
        } else {
            if (caches.size() >= capacity) {
                caches.remove(caches.size() - 1);
            }
            caches.add(0, data);
        }
        return data;
    }

    private void printCaches() {
        System.out.println(this.caches);
    }

    public static void main(String[] args) {
        LRUForArray<Integer> lruForArray = new LRUForArray<>(5);
        lruForArray.getFromCache(1);
        lruForArray.getFromCache(2);
        lruForArray.getFromCache(3);
        lruForArray.getFromCache(4);
        lruForArray.getFromCache(5);
        lruForArray.printCaches();
        lruForArray.getFromCache(2);
        lruForArray.printCaches();
        lruForArray.getFromCache(1);
        lruForArray.getFromCache(6);
        lruForArray.printCaches();
    }
}
