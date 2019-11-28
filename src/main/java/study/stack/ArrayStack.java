package study.stack;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 基于数组实现的栈
 *
 * @author zyf
 */
public class ArrayStack<T> implements Iterable<T> {
    private Object[] items;
    private int count;
    private int index;

    public ArrayStack(int size) {
        this.items = new Object[size];
        this.count = 0;
        this.index = 0;
    }

    private synchronized void addElement(T element) {
        ++this.count;
        this.items[index++] = element;
    }

    public T push(T item) {
        this.addElement(item);
        return item;
    }

    public synchronized T pop() {
        if (this.count <= 0) {
            return null;
        }
        this.index--;
        this.count--;
        return (T) items[this.index];
    }

    public synchronized T peek() {
        if (this.count <= 0) {
            return null;
        }
        int curIndex = this.index - 1;
        return (T) items[curIndex];
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        if (this.count > 0) {
            for (int i = 0; i < this.count; i++) {
                action.accept((T) this.items[i]);
            }
        }
    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

    public static void main(String[] args) {
        ArrayStack<String> arrayStack = new ArrayStack<String>(10);
        arrayStack.forEach(System.out::println);
        System.out.println("=============");
        arrayStack.push("a");
        arrayStack.push("b");
        arrayStack.push("c");
        arrayStack.push("d");
        arrayStack.forEach(System.out::println);
        System.out.println("=============");
        arrayStack.pop();
        arrayStack.pop();
        arrayStack.pop();
        arrayStack.forEach(System.out::println);
        System.out.println("=============");
        arrayStack.peek();
        arrayStack.peek();
        arrayStack.push("b");
        arrayStack.forEach(System.out::println);
        System.out.println("=============");
    }
}
