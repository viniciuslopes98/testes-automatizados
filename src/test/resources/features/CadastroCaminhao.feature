# language: pt

@regressivo
Funcionalidade: Cadastro de um novo caminhao
  Como usuário da API
  Quero cadastrar um novo caminhao
  Para que o registro seja salvo corretamente no sistema

  Cenário: Cadastro bem-sucedido de caminhao
    Dado que eu tenha os seguintes dados do caminhao:
      | campo             | valor               |
      | placa             | DUR4535             |
      | motorista         | Joao da silva       |
      | status            | ativo               |
      | ultimaAtualizacao | 2024-08-22T12:32:00 |
    Quando eu enviar a requisição para o endpoint "/api/caminhao/cadastro" de cadastro de caminhao
    Então o status code da resposta deve ser 201

  Cenário: Cadastro de caminhao sem sucesso ao passar o campo placa inválido
    Dado que eu tenha os seguintes dados do caminhao:
      | campo             | valor               |
      | placa             | DUR45               |
      | motorista         | Joao da silva       |
      | status            | ativo               |
      | ultimaAtualizacao | 2024-08-22T12:32:00 |
    Quando eu enviar a requisição para o endpoint "/api/caminhao/cadastro" de cadastro de caminhao
    Então o status code da resposta deve ser 400
    E o corpo de resposta de erro da api deve retornar a mensagem "O tamanho da Placa é inválido! Deve conter todos o 7 caracteres. "