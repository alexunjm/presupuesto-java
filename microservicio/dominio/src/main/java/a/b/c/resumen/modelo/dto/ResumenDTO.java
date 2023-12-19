package a.b.c.resumen.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

import a.b.c.presupuesto.modelo.dto.PresupuestoDTO;

@AllArgsConstructor
@Getter
public class ResumenDTO {
    private BigDecimal totalDisponible;
    private BigDecimal totalIngresosMesEnCurso;
    private BigDecimal totalGastadoMesEnCurso;
    private BigDecimal totalGastosPresupuestados;
    private List<PresupuestoDTO> presupuestos;
}
