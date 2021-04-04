INSERT INTO `cozinha` (`id`, `nome`) VALUES (NULL, 'ITALIANA');
INSERT INTO `cozinha` (`id`, `nome`) VALUES (NULL, 'MEXICANA');
INSERT INTO `cozinha` (`id`, `nome`) VALUES (NULL, 'FRANCESA');
INSERT INTO `cozinha` (`id`, `nome`) VALUES (NULL, 'BRASILEIRA');
INSERT INTO `cozinha` (`id`, `nome`) VALUES (NULL, 'BAIANA');
INSERT INTO `cozinha` (`id`, `nome`) VALUES (NULL, 'JAPONESA');

INSERT INTO `estado` (`id`, `nome`) VALUES (NULL, 'BAHIA');
INSERT INTO `estado` (`id`, `nome`) VALUES (NULL, 'SERGIPE');
INSERT INTO `estado` (`id`, `nome`) VALUES (NULL, 'SAO PAULO');
INSERT INTO `estado` (`id`, `nome`) VALUES (NULL, 'RIO DE JANEIRO');
INSERT INTO `estado` (`id`, `nome`) VALUES (NULL, 'RIO GRANDE DO SUL')

INSERT INTO `cidade` (`id`, `nome`, `estado_id`) VALUES (NULL, 'FEIRA DE SANTANA', '1');
INSERT INTO `cidade` (`id`, `nome`, `estado_id`) VALUES (NULL, 'ENTRE RIOS', '1');
INSERT INTO `cidade` (`id`, `nome`, `estado_id`) VALUES (NULL, 'ALAGOINHAS', '1');
INSERT INTO `cidade` (`id`, `nome`, `estado_id`) VALUES (NULL, 'GUANAMBI', '1');
INSERT INTO `cidade` (`id`, `nome`, `estado_id`) VALUES (NULL, 'BARREIRAS', '1');

INSERT INTO `permissao` (`id`, `descricao`, `nome`) VALUES (NULL, 'PERMITE USUARIO  A LEITURA', 'LEITURA');
INSERT INTO `permissao` (`id`, `descricao`, `nome`) VALUES (NULL, 'PERMITE USUARIO A DELETAR INFORMACOES', 'DELETAR DADOS');
INSERT INTO `permissao` (`id`, `descricao`, `nome`) VALUES (NULL, 'PERMITE USUARIO A INSERIR  DADOS', 'CRIAR DADOS');

INSERT INTO `grupo` (`id`, `nome`) VALUES (NULL, 'INCLUSAO');
INSERT INTO `grupo` (`id`, `nome`) VALUES (NULL, 'DELECAO');
INSERT INTO `grupo` (`id`, `nome`) VALUES (NULL, 'CONFIGURACAO');




INSERT INTO `usuario` (`id`, `data_cadastro`, `email`, `nome`, `senha`) VALUES (NULL, '2021-03-11 20:59:50.000000', 'RICARDO@cadf.com.br\r\n', 'RICARDO SOARES', NULL);
INSERT INTO `usuario` (`id`, `data_cadastro`, `email`, `nome`, `senha`) VALUES (NULL, '2021-04-10 20:59:50.000000', 'hrlima@cadf.com.br\r\n', 'SANDRA Roma', NULL);
INSERT INTO `usuario` (`id`, `data_cadastro`, `email`, `nome`, `senha`) VALUES (NULL, '2021-04-05 20:59:50.000000', 'GAEL@cadf.com.br\r\n', 'GAEL FILHO', NULL);


INSERT INTO `restaurante` (`id`, `aberto`, `ativo`, `data_atualizacao`, `data_cadastro`, `endereco_logradouro`, `endereco_bairro`, `endereco_cep`, `endereco_complemento`, `endereco_numero`, `nome`, `taxa_frete`, `cozinha_id`, `endereco_cidade`) VALUES (NULL, b'1', b'1', '2021-03-10 21:05:02', '2021-03-16 21:05:02', 'AVENIDA PARALELA , N 234', 'CENTRO', '41213-122', NULL, '654', 'RESTAURANTE ACARAJE DE DINHA ', '15.00', '6', '1');

INSERT INTO `restaurante` (`id`, `aberto`, `ativo`, `data_atualizacao`, `data_cadastro`, `endereco_logradouro`, `endereco_bairro`, `endereco_cep`, `endereco_complemento`, `endereco_numero`, `nome`, `taxa_frete`, `cozinha_id`, `endereco_cidade`) VALUES (NULL, b'1', b'1', '2020-03-10 21:05:02', '2021-03-16 21:05:02', 'RUA CHACARA , N 234', 'CENTRO', '41213-122', NULL, '654', 'PIZZA HUT ', '19.00', '6', '1');


INSERT INTO `produto` (`id`, `ativo`, `descricao`, `nome`, `preco`, `restaurante_id`) VALUES ('1', b'1', 'LAZANHA', 'LAZANHA DE CAMARAO', '82.00', '3');
INSERT INTO `produto` (`id`, `ativo`, `descricao`, `nome`, `preco`, `restaurante_id`) VALUES ('2', b'1', 'PEIXE GRELHADO', 'SARAPATEU A MOLHO PARDO', '42.00', '1');
INSERT INTO `produto` (`id`, `ativo`, `descricao`, `nome`, `preco`, `restaurante_id`) VALUES ('3', b'1', 'PIZZA GRANDE PARA A FAMILIA', 'PIZZA GOURME', '52.00', '1');
INSERT INTO `produto` (`id`, `ativo`, `descricao`, `nome`, `preco`, `restaurante_id`) VALUES ('4', b'1', 'SORVETE', 'SORVETE DA RIBEIRA ', '12.50', '4');
INSERT INTO `produto` (`id`, `ativo`, `descricao`, `nome`, `preco`, `restaurante_id`) VALUES ('5', b'1', 'SALADA', 'SALADA CEASAR', '13.00', '1');
INSERT INTO `produto` (`id`, `ativo`, `descricao`, `nome`, `preco`, `restaurante_id`) VALUES ('6', b'1', 'PIZZA GRANDE PARA A FAMILIA', 'FILE PARMEGIANA', '52.00', '1');


INSERT INTO `forma_pagamento` (`id`, `descricao`) VALUES (NULL, 'CARTAO DE CREDITO');
INSERT INTO `forma_pagamento` (`id`, `descricao`) VALUES (NULL, 'CARTAO DE DEBITO');
INSERT INTO `forma_pagamento` (`id`, `descricao`) VALUES (NULL, 'BITCOIN');
