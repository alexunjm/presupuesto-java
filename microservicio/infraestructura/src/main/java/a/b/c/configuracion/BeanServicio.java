package a.b.c.configuracion;

import a.b.c.gastos.puerto.dao.DaoGastos;
import a.b.c.gastos.puerto.repositorio.RepositorioGastos;
import a.b.c.gastos.servicio.ServicioDebitar;
import a.b.c.ingresos.puerto.dao.DaoIngresos;
import a.b.c.presupuesto.puerto.dao.DaoPresupuestos;
import a.b.c.presupuesto.servicio.ServicioObtenerPresupuestosParaDebitar;
import a.b.c.resumen.servicio.ServicioConsultarResumen;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioConsultarResumen servicioConsultarResumenAhorros() {
        return new ServicioConsultarResumen();
    }

    @Bean
    public ServicioDebitar servicioDebitar(RepositorioGastos repositorioGastos, DaoIngresos daoIngresos) {
        return new ServicioDebitar(daoIngresos, repositorioGastos);
    }

    @Bean
    public ServicioObtenerPresupuestosParaDebitar servicioObtenerPresupuestosParaDebitar(
            DaoPresupuestos daoPresupuestos, DaoGastos daoGastos) {
        return new ServicioObtenerPresupuestosParaDebitar(daoPresupuestos, daoGastos);
    }

}
