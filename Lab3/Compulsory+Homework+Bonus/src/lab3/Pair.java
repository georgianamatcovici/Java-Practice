package lab3;

public class Pair <T, U> {
    private T firstElement;
    private U secondElement;

    public Pair(T firstElement, U secondElement) {
        this.firstElement = firstElement;
        this.secondElement = secondElement;
    }

    public T getFirstElement() {
        return firstElement;
    }
    public U getSecondElement() {
        return secondElement;
    }
}
