# language: pt

@regressivo
Funcionalidade: Cadastro de um novo morador
  Como usuário da API
  Quero cadastrar um novo morador
  Para que o registro seja salvo corretamente no sistema

  Cenário: Cadastro bem-sucedido de morador
    Dado que eu tenha os seguintes dados do morador:
      | campo | valor                |
      | nome  | Ana Maria            |
      | email | ana.maria@ggmail.com |
    Quando eu enviar a requisição para o endpoint "/api/morador/cadastro" de cadastro de morador
    Então o status code da resposta vinda deve ser 201

  Cenário: Cadastro de morador sem sucesso ao passar o campo email inválido
    Dado que eu tenha os seguintes dados do morador:
      | campo | valor              |
      | nome  | Ana Maria          |
      | email | ana.mariagmail.com |
    Quando eu enviar a requisição para o endpoint "/api/morador/cadastro" de cadastro de morador
    Então o status code da resposta vinda deve ser 400
    E o corpo de resposta de erro vinda da api deve retornar a mensagem "O e-mail está com formato inválido!"