insert into tb_cozinha (id, nome) values (1, 'Tailandesa');
insert into tb_cozinha (id, nome) values (2, 'Indiana');
insert into tb_cozinha (id, nome) values (3, 'Italiana');
insert into tb_cozinha (id, nome) values (4, 'chinesa');




insert into restaurante (id, data_cadastro, data_atualizacao, nome, taxa_frete) values (1,utc_timestamp, utc_timestamp, 'Thai Gourmet', 10 );
insert into restaurante (id, data_cadastro, data_atualizacao, nome, taxa_frete ) values (2,utc_timestamp, utc_timestamp, 'Thai Delivery', 9.50 );
insert into restaurante (id, data_cadastro, data_atualizacao, nome, taxa_frete) values  (3,utc_timestamp, utc_timestamp, 'Tuk Tuk Comida ', 15);
insert into restaurante (id, nome , taxa_frete ) values (4, 'Casa Oriental', 22);
insert into restaurante (id, nome , taxa_frete) values (5, 'Yan Ping, chinesa', 30);
insert into restaurante (id, nome , taxa_frete) values (6, 'coza  nostra', 5);

insert into produto ( nome, descricao, preco, restaurante_id, ativo ) values ('HAMBURGER', 'COM SALADA', 30 ,1, 1 );
insert into produto ( nome, descricao, preco, restaurante_id, ativo ) values ('REFRIGERANTE', 'SEM GAS', 13.45 ,1, 1 );
insert into produto ( nome, descricao, preco, restaurante_id, ativo ) values ('CARNE DO SOL', 'COM FRITAS', 22.87 ,4, 1 );
insert into produto ( nome, descricao, preco, restaurante_id, ativo ) values ('FRIGIDEIRA', 'COM FRITAS', 2.87 ,2, 1 );
insert into produto ( nome, descricao, preco, restaurante_id, ativo ) values ('SORVETE', 'GELATO', 15.18 ,3, 0 );
 
 
 
insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');
Insert into estado (id, nome) values (4, 'Bahia');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);
insert into cidade (id, nome, estado_id) values (6, "Salvador",4);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento) values (1,2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento) values (1,2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento) values (1,3);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento) values (2,3);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento) values (5,1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento) values (5,2);


insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');