insert into presupuesto(id, nombre, descripcion, esPorcentual, valor)
values(1,'Gastos Fijos','Arriendo, Servicios Públicos, Claro, Funeraria, Vigilancia, Celular', 0, 900.00);
insert into presupuesto(id, nombre, esPorcentual, valor)
values(2,'Generosidad',1, 10.00);

UPDATE ingreso SET valor = '1000.00' WHERE (`id` = '1');
insert into ingreso(id, descripcion, valor) values(2,'Pago mes diciembre',500.00);

insert into gasto(idPresupuesto,descripcion,valor) values(1, 'Arriendo', 800.00);
insert into gasto(idPresupuesto,descripcion,valor) values(2, 'Ofrenda', 50.00);
