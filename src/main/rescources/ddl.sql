create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255), nome varchar(255), preco varchar(255), restaurante_id bigint, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao dateTime, data_cadastro dateTime, endereco_logradouro varchar(255), endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_numero varchar(255), nome varchar(255), taxa_frete decimal(19,2), cozinha_id bigint, endereco_cidade bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento bigint not null) engine=InnoDB
create table tb_cozinha (id bigint not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime(6), email varchar(255), nome varchar(255), senha varchar(255), primary key (id)) engine=InnoDB
create table usuarios_gupos (id_usuario bigint not null, id_grupo bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FKgxht9od033141bf8nkxvac8vm foreign key (cozinha_id) references tb_cozinha (id)
alter table restaurante add constraint FKa3ii9yjt0c00jbq7pjxi59jfm foreign key (endereco_cidade) references cidade (id)
alter table restaurante_forma_pagamento add constraint FKf10p5g5n4pk0sfv2xjvnm90jx foreign key (forma_pagamento) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuarios_gupos add constraint FKfb4c6kl50j6nvt296bbnhwm07 foreign key (id_grupo) references grupo (id)
alter table usuarios_gupos add constraint FKgr6df97kht5nn1qwadqnkdww0 foreign key (id_usuario) references usuario (id)
