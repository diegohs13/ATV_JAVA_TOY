# Easy Tech
Nossa plataforma será uma ferramenta multifuncional, abordando não apenas a identificação de pontos de poluição, mas também incluindo recursos de conscientização pública e denúncia de incidentes. Através de gráficos e mapas interativos, os usuários poderão visualizar claramente as áreas mais afetadas pela poluição, incentivando ações proativas para limpeza e prevenção.
## Integrantes

- MATHEUS MATOS - RM:99792
- KAREN VITORIA JESUS DA SILVA - RM:99468
- JULIANNY ARAUJO PEREIRA - RM:99554
- DIEGO HENRIQUE SANTOS DE OLIVEIRA - RM:550269
- JULIA DE FATIMA QUEIROZ - RM:551130

## Instruções para uso

### Configuração do Spring
- site: https://start.spring.io/
  ![Configuração Spring](https://github.com/diegohs13/GS_JAVA/blob/main/configSpringGs.png)
### Execução
![Execução](https://github.com/diegohs13/GS_JAVA/blob/main/runGs.png)
![Execução completa](https://github.com/diegohs13/GS_JAVA/blob/main/runGs2.png)

### Utilização
O usuario pode cadastrar os seguintes dados no sistema utilizando o metodo POST:
- Usuario
- Classificacao
- Cliente
- Marca
- Pedido
- Tipo

O usuario pode deletar os seguintes dados no sistema utilizando o metodo DELETE:
- Um brinquedo pelo id
- Um classificacao pelo id
- Uma cliente pelo id
- Uma marca pelo id
- Uma pedido pelo id
- Uma tipo pelo n_protocolo

O usuario pode visualizar os seguintes dados no sistema utilizando o metodo GET:
- Lista de todos os brinquedo e de um usuario específico
- Lista de todos os classificacao e de um relatorio específico
- Lista de todas as cliente e de uma localidade específica
- Lista de todas as marca e de uma intervencao específica
- Lista de todas as pedido e de uma denuncia específica
- Lista de todos os tipo e de um conscientizacao específico


O usuario pode atualizar os seguintes dados no sistema utilizando o metodo PUT:
- Preco do brinquedo
- Nome da classificacao
- Telefone da cliente
- Nome da marca
- Status da pedido
- Nome da tipo


## Endpoints
BRINQUEDO
- GET `/brinquedo` - Retorna a lista de brinquedo cadastrados no sistema.
- POST `/brinquedo` - Cadastra um novo brinquedo no sistema.
- GET `/brinquedo/{id}` - Retorna os dados de um brinquedo específico.
- PUT `/brinquedo/{id}/atualizarPreco` - Atualiza o preco de um brinquedo específico.
- DELETE `/brinquedo/{id}` - Deleta um brinquedo específico.

RELATORIOS
- GET `/classificacao` - Retorna a lista de classificacao no sistema.
- GET `/classificacao/{id}` - Retorna os dados de um classificacao específico.
- PUT `/classificacao/{id}/atualizarNome` - Atualiza nome de uma classificacao específico.
- DELETE `/classificacao/{id}` - Deleta um classificacao específico.
- POST `/classificacao` - Cadastra uma nova classificacao no sistema.

LOCALIDADES
- GET `/cliente` - Retorna a lista de cliente cadastradas no sistema.
- GET `/cliente/{id}` - Retorna os dados de um cliente específica.
- PUT `/cliente/{id}/atualizarTelefone` - Atualiza o telefone de um cliente específica.
- DELETE `/cliente/{id}` - Deleta um cliente específica.
- POST `/cliente` - Cadastra uma nov cliente no sistema.

INTERVENCOES
- GET `/marca` - Retorna a lista de marca cadastradas no sistema.
- GET `/marca/{id}` - Retorna os dados de uma marca específica.
- PUT `/marca/{id}/atualizarNome` - Atualiza o nome de uma marca específica.
- DELETE `/marca/{id}` - Deleta uma marca específica.
- POST `/marca` - Cadastra uma nova marca no sistema.


DENUNCIAS
- GET `/pedido` - Retorna a lista de pedido cadastradas no sistema.
- GET `/pedido/{id}` - Retorna os dados de uma pedido específica.
- PUT `/pedido/{id}/atualizarStatus` - Atualiza status de um pedido específica.
- DELETE `/pedido/{id}` - Deleta uma pedido específica.
- POST `/pedido` - Cadastra uma nova pedido no sistema.


CONSCIENTIZACOES
- GET `/tipo` - Retorna a lista de tipo cadastrados no sistema.
- GET `/tipo/{id}` - Retorna os dados de um tipo específico.
- PUT `/tipo/{id}/atualizarNome` - Atualiza o nome de um tipo específico.
- DELETE `/tipo/{id}` - Deleta um tipo específico.
- POST `/tipo` - Cadastra um novo tipo no sistema.


## Imagens
![Diagrama de relacionamentos](https://github.com/diegohs13/GS_JAVA/blob/main/bdRelation.png)
- _**Entidades e Atributos**_<br>
  <br>

- **Brinquedo**
- id_brinquedo (chave primária)
- id_classificacao (chave estrangeira)
- id_marca (chave estrangeira)
- id_tipo (chave estrangeira)
- nome_brinquedo
- preco<br>
  <br>

- **Classificacao**
- id_classificacao (chave primária)
- nome_classificacao (chave estrangeira)<br>
  <br>

- **Marca**
- id_marca (chave primária)
- nome_marca<br>
  <br>

- **Pedido**
- id_pedido (chave primária)
- id_brinquedo (chave estrangeira)
- id_cliente (chave estrangeira)<br>
  <br>

- **Cliente**
- id_cliente (chave primária)
- nome_cliente
- telefone
- cpf <br>
  <br>

- **Tipo**
- id_tipo (chave primária)
- nome_tipo <br>
  <br>
  <br>