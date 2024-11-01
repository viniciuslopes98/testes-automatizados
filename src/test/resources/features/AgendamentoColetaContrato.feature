# language: pt

@regressivo
Funcionalidade: Validar o contrato ao realizar um cadastro bem-sucedido de agendamento coleta
  Como usuário da API
  Quero cadastrar um novo agendamento de coleta
  Para que eu consiga validar se o contrato esta conforme o esperado

  Cenario: Validar contrato do cadastro bem-sucedido de agendamento de coleta
    Dado que eu tenha os seguintes dados específicos do agendamento de coleta:
      | campo          | valor                              |
      | caminhaoId     | 1                                 |
      | tipoResiduos   | Comum                              |
      | dataAgendamento| 2024-06-22                         |
      | horario        | 14:30                              |
      | endereco       | Av. Costa Eduardo, 267 SAO PAULO-SP|
      | confirmado     | true                               |
    Quando eu enviar a requisição para o endpoint "/api/agendamento/cadastro" de cadastro de agendamento de coleta
    Então o status code da resposta da requisição deve ser 201
    E que o arquivo de contrato esperado é o "Cadastro bem-sucedido de agendamento de coleta"
    Então a resposta da requisição deve estar em conformidade com o contrato selecionado