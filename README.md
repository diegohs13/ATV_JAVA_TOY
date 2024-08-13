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
- application.properties
  ![Configuração application.properties](https://github.com/diegohs13/GS_JAVA/blob/main/appProperties.png)

### Import insominia
- insomnia v4
- Importar arquivo
  ![Importar arquivo](https://github.com/diegohs13/GS_JAVA/blob/main/importInsomnia.png)
  ![Import completo](https://github.com/diegohs13/GS_JAVA/blob/main/importCompleto.png)
- Importe o arquivo InsomniaGS1.json para o Insomnia

### Execução
Após a importação do arquivo InsomniaGS1.json e a configuração do application.properties, o usuario pode rodar o GlobalSolutionJavaApplication dentro do projeto java.
![Execução](https://github.com/diegohs13/GS_JAVA/blob/main/runGs.png)
![Execução completa](https://github.com/diegohs13/GS_JAVA/blob/main/runGs2.png)

### Utilização
O usuario pode cadastrar os seguintes dados no sistema utilizando o metodo POST:
- Usuario
- Relarorio
- Localidade
- Intervencoes
- Denuncia
- Conscientizacao

O usuario pode deletar os seguintes dados no sistema utilizando o metodo DELETE:
- Um usuario pelo id
- Um relatorio pelo id
- Uma localidade pelo id
- Uma intervencao pelo id
- Uma denuncia pelo id
- Uma conscientizacao pelo n_protocolo

O usuario pode visualizar os seguintes dados no sistema utilizando o metodo GET:
- Lista de todos os usuarios e de um usuario específico
- Lista de todos os relatorios e de um relatorio específico
- Lista de todas as localidades e de uma localidade específica
- Lista de todas as intervencoes e de uma intervencao específica
- Lista de todas as denuncias e de uma denuncia específica
- Lista de todos os conscientizacoes e de um conscientizacao específico


O usuario pode atualizar os seguintes dados no sistema utilizando o metodo PUT:
- Senha do usuario
- Descrição da relatorio
- Nome da localidade
- Quantidade de residuos da intervenção
- Decrição da denuncia
- Descrição da conscientizacao

O usuario tambem pode realizar o login no sistema com os seguintes dados utilizando o metodo POST:
- id
- senha

## Endpoints
USUARIOS
- GET `/usuarios` - Retorna a lista de usuarios cadastrados no sistema.
- POST `/usuarios` - Cadastra um novo usuario no sistema.
- GET `/usuarios/{id}` - Retorna os dados de um usuario específico.
- PUT `/usuarios/{id}/atualizarSenha` - Atualiza a senha de um usuario específico.
- DELETE `/usuarios/{id}` - Deleta um usuario específico.
- POST `/usuarios/{id}/login` - Realiza o login de um usuario no sistema.

RELATORIOS
- GET `/relatorios` - Retorna a lista de exames relatorios no sistema.
- GET `/relatorios/{id}` - Retorna os dados de um relatorio específico.
- PUT `/relatorios/{id}/atualizarDescricao` - Atualiza a descrição de um relatorio específico.
- DELETE `/relatorios/{id}` - Deleta um relatorio específico.
- POST `/relatorios` - Cadastra um novo relatorio no sistema.

LOCALIDADES
- GET `/Localidades` - Retorna a lista de Localidades cadastradas no sistema.
- GET `/Localidades/{id}` - Retorna os dados de uma Localidade específica.
- PUT `/Localidades/{id}/atualizarNome` - Atualiza o nome de uma Localidade específica.
- DELETE `/Localidades/{id}` - Deleta uma Localidade específica.
- POST `/Localidades` - Cadastra uma nova Localidade no sistema.

INTERVENCOES
- GET `/intervencoes` - Retorna a lista de intervencoes cadastradas no sistema.
- GET `/intervencoes/{id}` - Retorna os dados de uma intervencao específica.
- PUT `/intervencoes/{id}/atualizarResiduos` - Atualiza a quantidade de residuos de uma intervencao específica.
- DELETE `/intervencoes/{id}` - Deleta uma intervencao específica.
- POST `/intervencoes` - Cadastra uma nova intervencao no sistema.


DENUNCIAS
- GET `/denuncias` - Retorna a lista de denuncias cadastradas no sistema.
- GET `/denuncias/{id}` - Retorna os dados de uma denuncia específica.
- PUT `/denuncias/{id}/atualizarDescricao` - Atualiza a descrição de uma denuncia específica.
- DELETE `/denuncias/{id}` - Deleta uma denuncia específica.
- POST `/denuncias` - Cadastra uma nova denuncia no sistema.


CONSCIENTIZACOES
- GET `/conscientizacoes` - Retorna a lista de conscientizacoes cadastrados no sistema.
- GET `/conscientizacoes/{id}` - Retorna os dados de uma conscientizacao específica.
- PUT `/conscientizacoes/{id}/atualizarDescricao` - Atualiza a descricao conscientizacao específica.
- DELETE `/conscientizacoes/{id}` - Deleta uma conscientizacao específica.
- POST `/conscientizacoes` - Cadastra uma nova conscientizacao no sistema.


## Imagens
![Diagrama de relacionamentos](https://github.com/diegohs13/GS_JAVA/blob/main/bdRelation.png)
- _**Entidades e Atributos**_<br>
  <br>

- **Usuario**
- id_usuario (chave primária)
- nome
- email
- senha
- tipo_usuario<br>
  <br>

- **Relatoeio**
- id_relatorio (chave primária)
- data_relatorio
- descricao
- quantidade_residuo
- Usuario_id_usuario (chave estrangeira)<br>
  <br>

- **Localidade**
- id_local (chave primária)
- nome_local
- latitude
- longitude
- Relatorio_id_relatorio (chave estrangeira)<br>
  <br>

- **Intervencoes**
- id_intervencao (chave primária)
- data_intervencao
- quantidade_residuos
- numero_pessoas
- Localidade_id_localidade (chave estrangeira)<br>
  <br>

- **Denuncia**
- id_denuncia (chave primária)
- data_denuncia
- descricao
- status
- Usuario_id_usuario (chave estrangeira)<br>
  <br>

- **Conscienizacao**
- id_campanha (chave primária)
- titulo_campanha
- descricao
- data_inicio
- data_fim<br>
  <br>
  <br>

## Proposta de projeto

**Enfrentando a Poluição nos Oceanos:** Uma Abordagem Inovadora
<br>
<br>
**O oceano carrega consigo um fardo alarmante:** cerca de 2.3 milhões de toneladas de plástico flutuam em suas águas, com o Brasil contribuindo com impressionantes 325 mil toneladas anualmente. Esta é uma carga que não só afeta a vida marinha, mas também causa impacto direto nas comunidades costeiras e no delicado equilíbrio dos ecossistemas marinhos.
Neste contexto, surge uma oportunidade crucial de aplicar tecnologia para monitorar e combater essa poluição, auxiliando organizações não governamentais (ONGs) e outras entidades dedicadas à proteção dos oceanos. Nosso projeto visa criar uma aplicação inovadora capaz de mapear os pontos críticos de poluição nas praias, simplificando assim o trabalho das ONGs no recolhimento desses materiais.
<br>
<br>
**Desafios e Oportunidades Atuais:** Ao observar fontes de avaliação como relatos em mídias sociais e plataformas de denúncia, fica evidente a extensão do problema da poluição nos oceanos. A dificuldade em localizar e remover os resíduos plásticos é um desafio significativo enfrentado pelas ONGs e voluntários, tornando o processo de limpeza lento e ineficiente.
<br>
<br>
**Proposta de Valor:** Como ponto de partida, nossa iniciativa se concentrará na localidade de Santos, um exemplo emblemático da urgência em lidar com a poluição nos oceanos. Propomos desenvolver uma plataforma abrangente que melhore significativamente a monitoração e a ação contra a poluição por plásticos. Ao receber relatórios de poluição de diversas fontes, como cidadãos preocupados ou patrulhas costeiras, nossa aplicação irá mapear e visualizar os locais com maior concentração de plásticos, facilitando assim a intervenção das equipes de limpeza. Além disso, nossa plataforma fornecerá dados sobre a quantidade de resíduos em cada localidade, permitindo uma alocação mais eficiente de recursos e facilitar na estimativa do número de pessoas necessárias para a limpeza.
<br>
<br>
**Recursos-Chave:**
- **Identificação de Pontos Críticos:** Utilizando tecnologias avançadas, nossa plataforma identificará e mapeará áreas com alta concentração de plásticos, incluindo aquelas próximas a indústrias e centros urbanos.
- **Conscientização Pública:** Além de fornecer dados sobre poluição, nossa plataforma educará o público sobre os impactos ambientais do plástico nos oceanos e incentivará práticas mais sustentáveis.
- **Disk Denúncia:** Implementaremos um sistema de denúncia integrado, permitindo que os usuários relatem incidentes de poluição de forma rápida e eficaz, contribuindo para uma resposta mais ágil das autoridades competentes.
- **Visualização de Dados:** Gráficos e mapas interativos fornecerão uma visão clara das áreas mais afetadas pela poluição, facilitando o planejamento e a execução de ações de limpeza.

  <br>
<br>**Benefícios:** Nossa plataforma oferece benefícios significativos para diversas partes interessadas. Para as comunidades costeiras, proporciona uma ferramenta eficaz para monitorar e combater a poluição, protegendo ecossistemas e fontes de subsistência. Para as autoridades e ONGs, simplifica e agiliza o processo de resposta a incidentes de poluição, permitindo uma gestão mais eficiente dos recursos.
Em suma, nossa plataforma representa um passo importante na luta contra a poluição nos oceanos, oferecendo uma abordagem abrangente e tecnologicamente avançada para proteger nossos preciosos recursos marinhos.
Benefícios para a Comunidade e o Meio Ambiente:Ao adotar essa abordagem inovadora, nossa plataforma oferece benefícios tangíveis para a comunidade e o meio ambiente. Para os cidadãos, a aplicação proporciona uma maneira fácil e eficaz de relatar e lidar com a poluição nas praias, promovendo um senso de responsabilidade ambiental e engajamento comunitário. Para as ONGs e voluntários, nossa plataforma simplifica e agiliza o processo de limpeza, permitindo uma resposta mais rápida e eficiente aos incidentes de poluição.
Crescimento e Impacto Sustentável:Empresas que adotam essa abordagem têm potencial para crescer rapidamente em um mercado que valoriza soluções inovadoras para problemas ambientais urgentes. Além disso, ao promover a conscientização e ações práticas para combater a poluição nos oceanos, nossa plataforma tem o potencial de gerar um impacto positivo duradouro no meio ambiente e na qualidade de vida das comunidades costeiras.
Em resumo, nossa aplicação oferece uma abordagem inovadora e eficaz para enfrentar o problema da poluição nos oceanos, fornecendo uma ferramenta poderosa para comunidades, ONGs e outras entidades comprometidas com a proteção dos nossos preciosos recursos marinhos.