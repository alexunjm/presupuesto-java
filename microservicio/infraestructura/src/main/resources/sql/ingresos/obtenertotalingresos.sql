select
    SUM(valor) AS total
from ingreso
where id > 1
GROUP BY valor