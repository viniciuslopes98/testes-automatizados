# language: pt

@regressivo
Funcionalidade: Cadastro de um novo agendamento de coleta
  Como usuário da API
  Quero cadastrar um agendamento de coleta
  Para que o registro seja salvo corretamente no sistema

  Contexto: Cadastro bem-sucedido de caminhao
    Dado que eu tenha os seguintes dados do caminhao:
      | campo             | valor               |
      | placa             | DUR4535             |
      | motorista         | Joao da silva       |
      | status            | ativo               |
      | ultimaAtualizacao | 2024-08-22T12:32:00 |
    Quando eu enviar a requisição para o endpoint "/api/caminhao/cadastro" de cadastro de caminhao
    Então o status code da resposta deve ser 201

  Cenário: Cadastro bem-sucedido de agendamento de coleta
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

  Cenário: Cadastro de agendamento de coleta sem sucesso ao passar o campo dataAgendamento vazio
    Dado que eu tenha os seguintes dados específicos do agendamento de coleta:
      | campo          | valor                              |
      | caminhaoId     | 1                                 |
      | tipoResiduos   | Comum                              |
      | dataAgendamento|                                    |
      | horario        | 14:30                              |
      | endereco       | Av. Costa Eduardo, 267 SAO PAULO-SP|
      | confirmado     | true                               |
    Quando eu enviar a requisição para o endpoint "/api/agendamento/cadastro" de cadastro de agendamento de coleta
    Então o status code da resposta da requisição deve ser 400
    E o corpo de resposta de erro deve retornar a mensagem "A data não pode ser nula!"


