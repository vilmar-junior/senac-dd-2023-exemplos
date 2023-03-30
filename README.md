
# Projeto da disciplina de Desenvolvimento Desktop - 2023/1

## Exercício da Telefonia

### Camada Model
* **Modelagem E-R**

1. Tabela ENDERECO: ID(PK), RUA, NUMERO, CEP, CIDADE, ESTADO
2. Tabela CLIENTE: ID(PK), NOME, CPF, ID_ENDERECO(FK)
3. Tabela TELEFONE: ID(PK), DDD, NUMERO, ATIVO, MOVEL, ID_CLIENTE (NÃO É FK, POIS PODE SER NULO)

* **Entidades**

1. Endereco: id(Integer), rua(String), numero(String), cep(String), cidade(String), estado(String)
2. Cliente:  id(Integer), nome(String), cpf(String), endereco(Endereco)
3. Telefone: id(Integer), ddd(String), numero(String), ativo(boolean), movel(boolean), dono(Cliente - pode ser null)

* **Data Access Objects (DAOs)**
* Todo DAO deve conter os seguintes métodos: inserir(Entidade novoObjeto), atualizar(Entidade objetoParaAtualizar), excluir(int id), consultarPorId(int id) e consultarTodos()
1. EnderecoDAO
2. ClienteDAO
3. TelefoneDAO

* **Business Objects (BOs)**
* Classes que encapsulam as **regras de negócio** do sistema

1. EnderecoBO: (i) não deixar excluir endereço que possua cliente associado, (ii) consultar CEP (TODO chamar ViaCep)

3. ClienteBO:  (i) não deixar excluir cliente que possua telefone associado, (ii) não deixar cadastrar cliente com CPF já usado (ok), (iii) não deixar cadastrar cliente sem endereço válido (ok)

4. TelefoneBO: (i) manter a consistência entre "ativo" e o telefone possuir ou não um cliente associado 

### Camada Controller

### Camada View
