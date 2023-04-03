# Projeto da disciplina de Desenvolvimento Desktop - 2023/1

## Exercício da Telefonia
_Uma empresa de telefonia precisa manter os dados de seus clientes de forma organizada e fácil de ser encontrada._

_Cada cliente possui nome, CPF, quantos números de telefone ele solicitar e um endereço._ 

_O endereço por sua vez é composto pelo nome da rua, CEP, estado e cidade._

### Camada Model
###### Modelagem E-R

1. Tabela ENDERECO: ID(PK), RUA, NUMERO, CEP, CIDADE, ESTADO
2. Tabela CLIENTE: ID(PK), NOME, CPF, ID_ENDERECO(FK)
3. Tabela TELEFONE: ID(PK), DDD, NUMERO, ATIVO, MOVEL, ID_CLIENTE (NÃO É FK, POIS PODE SER NULO)

###### Entidades

1. Endereco: id(Integer), rua(String), numero(String), cep(String), cidade(String), estado(String)
2. Cliente:  id(Integer), nome(String), cpf(String), endereco(Endereco)
3. Telefone: id(Integer), ddd(String), numero(String), ativo(boolean), movel(boolean), dono(Cliente - pode ser null)

###### Data Access Objects (DAOs)
* Todo DAO deve conter os seguintes métodos: inserir(Entidade novoObjeto), atualizar(Entidade objetoParaAtualizar), excluir(int id), consultarPorId(int id) e consultarTodos()
1. EnderecoDAO
2. ClienteDAO
3. TelefoneDAO

###### Business Objects (BOs)
* Classes que encapsulam as **regras de negócio** do sistema

1. EnderecoBO: (i) não deixar excluir endereço que possua cliente associado, (ii) consultar CEP (TODO chamar ViaCep)

2. ClienteBO:  (i) não deixar excluir cliente que possua telefone associado, (ii) não deixar cadastrar cliente com CPF já usado (ok), (iii) não deixar cadastrar cliente sem endereço válido (ok)

3. TelefoneBO: (i) manter a consistência entre "ativo" e o telefone possuir ou não um cliente associado 

### Camada Controller
#####Classes responsáveis por: 

* Receber dados ou objetos da camada de view

* Realizar validações

* Controlar o fluxo de telas

* Chamar a camada de model para persistências ou consultas de dados

* Classes Controller, Service ou Servlet (varia conforme a arquitetura)


1. EnderecoBO: validar campos obrigatórios antes de inserir/atualizar

2. ClienteBO: validar campos obrigatórios antes de inserir/atualizar

3. TelefoneBO: validar campos obrigatórios antes de inserir/atualizar

### Camada View
* Camada com as classes/componentes responsáveis pela apresentação dos dados para o usuário


###### Tipos de telas mais comuns:
1. Tela de cadastro/atualização: campos (geralmente) organizados como um formulário

2. Tela de listagem: (geralmente) uma tabela ou cards mostrando todos os itens buscados. Dispõe de opções para editar ou excluir um item selecionado

3. Tela principal: pode apresentar uma tela de login, menus de navegação, orientações gerais