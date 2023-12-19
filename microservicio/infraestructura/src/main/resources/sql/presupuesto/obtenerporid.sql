select
    id,
    nombre,
    descripcion,
    esPorcentual,
    valor
from presupuesto
where id = :id