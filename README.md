# Testes Automatizados - SAFE COLETA

Este projeto contém uma suíte de testes automatizados desenvolvida para validar os endpoints da aplicação **SAFE COLETA**. A aplicação SAFE COLETA, cujo repositório está disponível [aqui](https://github.com/viniciuslopes98/SAFE-COLETA), é uma API RESTful implantada na nuvem Azure que oferece funcionalidades de agendamento e gerenciamento de coleta de resíduos.

## Estrutura do Projeto

O projeto está estruturado com **Cucumber** e **Gherkin**, utilizando a linguagem **Java** para a criação dos testes em BDD (Behavior Driven Development) e validação de contrato dos endpoints. Os testes garantem que as respostas da API estejam em conformidade com os contratos definidos, utilizando schemas JSON.

### Principais Diretórios e Arquivos

- `src/test/java`: Contém as classes de testes e serviços.
  - **Steps**: Classes que implementam os passos definidos nos arquivos Gherkin.
  - **Services**: Classes de serviços que encapsulam a lógica de comunicação com a API.
- `src/test/resources/features`: Diretório com os arquivos `.feature` escritos em Gherkin, descrevendo os cenários de teste.
- `src/test/resources/schemas`: Schemas JSON utilizados para validação de contrato da API.
- `pom.xml`: Arquivo de configuração do Maven, definindo as dependências do projeto.

## Tecnologias e Ferramentas Utilizadas

- **Java 17**: Linguagem utilizada para desenvolver os testes.
- **Cucumber**: Framework BDD utilizado para escrita dos cenários de testes em Gherkin.
- **REST Assured**: Biblioteca para facilitar a criação de requisições HTTP nos testes de APIs REST.
- **Json Schema Validator**: Utilizado para validar os contratos JSON das respostas da API.
- **Maven**: Gerenciador de dependências e executor de testes.

## Pré-requisitos

- **Java 17** ou superior
- **Maven**: Para gerenciar dependências e facilitar a execução dos testes.
- A aplicação **SAFE COLETA** precisa estar ativa e acessível para que os testes possam interagir com seus endpoints. Verifique o link para a aplicação no repositório da API ou execute localmente, se necessário.

## Configuração e Execução

1. **Clone o Repositório**:

   ```bash
   git clone https://github.com/viniciuslopes98/testes-automatizados.git
   cd testes-automatizados
   ```

2. **Configuração do Ambiente**:
  Verifique se o baseUrl nos arquivos de serviço está correto e apontando para a URL da aplicação SAFE COLETA.
    - Abra a classe CadastroAgendamentoColetaService.java e ajuste o valor de baseUrl se necessário:

      ```bash
         private String baseUrl = "https://url-da-aplicacao-safe-coleta";
      ```
3. **Execução dos Testes**:
   - Execute os testes utilizando o Maven:
     
      ``` bash
      mvn clean test
      ```

4. **Relatórios de Teste**:

     Após a execução dos testes, o Cucumber gera relatórios detalhados dos cenários de testes. Por padrão, esses relatórios ficam localizados no diretório ```target/cucumber-reports```
  

## Estrutura de um Cenário de Teste
    
  Os testes utilizam a sintaxe Gherkin para descrever cenários de teste. Exemplo de um cenário de validação de contrato para o cadastro de um agendamento de coleta:
  ``` bash
  Funcionalidade: Validar o contrato ao realizar um cadastro bem-sucedido de agendamento coleta
  Como usuário da API
  Quero cadastrar um novo agendamento de coleta
  Para que eu consiga validar se o contrato esta conforme o esperado

  Cenário: Validar contrato do cadastro bem-sucedido de agendamento de coleta
    Dado que eu tenha os seguintes dados específicos do agendamento de coleta:
      | campo          | valor                              |
      | caminhaoId     | 15                                 |
      | tipoResiduos   | Comum                              |
      | dataAgendamento| 2024-06-22                         |
      | horario        | 14:30                              |
      | endereco       | Av. Costa Eduardo, 267 SAO PAULO-SP|
      | confirmado     | true                               |
    Quando eu enviar a requisição para o endpoint "/api/agendamento/cadastro" de cadastro de agendamento de coleta
    Então o status code da resposta deve ser 201
    E que o arquivo de contrato esperado é o "Cadastro bem-sucedido de agendamento de coleta"
    Então a resposta da requisição deve estar em conformidade com o contrato selecionado
   ```
