package a.b.c.dominio.listas;

import java.util.List;

public interface Reductor<T, U> {

    /**
     * <p>
     * Esta es la funcion principal a la que se debe llamar. Permite dada una lista
     * de objetos, convertirlos por medio de la funcion
     * {@link #acumular(U, T)}.
     * </p>
     * 
     * @param reducible
     * @param valorInicial
     * @return
     */
    default U reducir(List<T> reducible, U valorInicial) {
        return reducible.stream().reduce(valorInicial, this::acumular, this::combinar);
    }

    /**
     * <p>
     * Esta es una funcion acumuladora de los valores del arreglo pasado a la
     * funcion
     * {@link #reducir(List)}.
     * </p>
     * 
     * @param acumuladoParcial objeto con valor parcial acumulado
     * @param actual           objeto de la lista de la iteracion en curso
     * @return
     */
    U acumular(U acumuladoParcial, T actual);

    /**
     * <p>
     * Esta es una funcion combinadora de acumulados parciales, si se quiere
     * paralelizar la funcion de reduccion
     * {@link #reducir(List)}.
     * </p>
     * <p>
     * Los parametros son objetos con valores parcial acumulado de alguna de las
     * ejecuciones en paralelo.
     * </p>
     * 
     * @param acumuladoParcial1
     * @param acumuladoParcial2
     * @return
     */
    U combinar(U acumuladoParcial1, U acumuladoParcial2);
}
