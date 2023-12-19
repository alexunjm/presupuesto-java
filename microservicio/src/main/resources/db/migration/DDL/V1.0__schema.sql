create table ingreso (
 id int(11) not null auto_increment,
 descripcion varchar(200) not null,
 valor DECIMAL(10,2) not null,
 primary key (id)
);

create table presupuesto (
 id int(11) not null auto_increment,
 nombre varchar(60) not null,
 descripcion varchar(200) default null,
 esPorcentual TINYINT not null,
 valor DECIMAL(10,2) not null,
 primary key (id)
);