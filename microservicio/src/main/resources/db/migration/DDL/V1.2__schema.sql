create table gasto (
 id int(11) not null auto_increment,
 idPresupuesto int(11) not null,
 descripcion varchar(200) not null,
 valor DECIMAL(10,2) not null,
 primary key (id)
);

ALTER TABLE gasto
ADD CONSTRAINT presupuesto_fk
  FOREIGN KEY (idPresupuesto)
  REFERENCES presupuesto (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;