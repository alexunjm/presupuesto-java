package a.b.c.gastos.excepcion;

import java.math.BigDecimal;

import a.b.c.gastos.modelo.dto.NuevoGasto;

public class ExcepcionGastoNoPermitido extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionGastoNoPermitido(String mensaje, BigDecimal disponible, NuevoGasto gasto) {
        super(mensaje + "\n Gasto:\t\t" + gasto.getValor() + "\n Disponible:\t" + disponible + "\n");
    }
}
