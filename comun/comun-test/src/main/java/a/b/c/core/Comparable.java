package a.b.c.core;

public class Comparable {

    private int resultadoComparacion;

    public Comparable(int resultadoComparacion) {
        this.resultadoComparacion = resultadoComparacion;
    }

    public static Comparable nuevo(int resultadoComparacion) {
        return new Comparable(resultadoComparacion);
    }

    public boolean sonIguales() {
        return resultadoComparacion == 0;
    }

}
