package a.b.c.presupuesto.modelo.entidad;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Stream;

import a.b.c.presupuesto.modelo.dto.PresupuestoDTO;

public final class Presupuestos {
    private static final boolean ES_FIJO = false;
    private static final boolean ES_PORCENTUAL = true;
    private static final BigDecimal CIEN_PORCIENTO = BigDecimal.valueOf(100);

    private List<Presupuesto> todos;
    private TotalPresupuestado totalPresupuestado;

    private Presupuestos(List<PresupuestoDTO> presupuestosDto) {
        this.todos = presupuestosDto.stream().map(this::reconstruirPresupuesto).toList();
        this.totalPresupuestado = TotalPresupuestado.calcular(this.todos);
        actualizarTotalPresupuestadoMes();
    }

    private Presupuesto reconstruirPresupuesto(PresupuestoDTO dto) {
        if (dto.isPorcentual()) {
            return Presupuesto.reconstruir(dto.getId(), dto.getNombre(),
                    dto.getDescripcion(), ES_PORCENTUAL, dto.getValorPorcentual());
        }
        return Presupuesto.reconstruir(dto.getId(), dto.getNombre(),
                dto.getDescripcion(), ES_FIJO, dto.getValorFijo());
    }

    public static Presupuestos reconstruir(List<PresupuestoDTO> presupuestosDto) {
        return new Presupuestos(presupuestosDto);
    }

    private void actualizarTotalPresupuestadoMes() {
        BigDecimal totalFijoMes = calcularTotalFijo();

        for (Presupuesto presupuesto : todos) {
            presupuesto.actualizarTotalPresupuestadoMes(totalFijoMes);
        }
    }

    private BigDecimal calcularTotalFijo() {
        BigDecimal porcentajeRelativoParaTotalFijo = CIEN_PORCIENTO
                .subtract(totalPresupuestado.getTotalPorcentual()).divide(CIEN_PORCIENTO);
        return totalPresupuestado.getTotalFijo().divide(porcentajeRelativoParaTotalFijo, 2, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(1.00));
    }

    public int size() {
        return todos.size();
    }

    public Presupuesto get(int indice) {
        return todos.get(indice);
    }

    public Stream<Presupuesto> stream() {
        return todos.stream();
    }

    public BigDecimal getTotalPresupuestado() {
        return calcularTotalFijo();
    }

    public BigDecimal getTotalGastado() {
        return TotalizadorGastadoPorPresupuesto.crear().reducir(todos, BigDecimal.ZERO);
    }

}
