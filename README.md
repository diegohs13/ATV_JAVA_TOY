# Easy Tech
## Integrantes

- MATHEUS MATOS - RM:99792
- KAREN VITORIA JESUS DA SILVA - RM:99468
- JULIANNY ARAUJO PEREIRA - RM:99554
- DIEGO HENRIQUE SANTOS DE OLIVEIRA - RM:550269
- JULIA DE FATIMA QUEIROZ - RM:551130

## Instruções para uso

### Configuração do Spring
- site: https://start.spring.io/
  ![Configuração Spring](https://github.com/diegohs13/ATV_JAVA_TOY/blob/main/configSpring.png)
### Configuração do Spring
![application.properties](https://github.com/diegohs13/ATV_JAVA_TOY/blob/main/applicationProperties.png)
### Execução
![Execução](https://github.com/diegohs13/ATV_JAVA_TOY/blob/main/Application.png)

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


## Endpoint

https://atv-java-toy.onrender.com/brinquedo
<br>

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