# language: pt

@regressivo
Funcionalidade: Cadastro de uma notificacao
  Como usuário da API
  Quero cadastrar uma notificacao
  Para que o registro seja salvo corretamente no sistema

  Contexto: Cadastro bem-sucedido de morador
    Dado que eu tenha os seguintes dados do morador:
      | campo | valor                |
      | nome  | Ana Maria            |
      | email | ana.maria@ggmail.com |
    Quando eu enviar a requisição para o endpoint "/api/morador/cadastro" de cadastro de morador
    Então o status code da resposta vinda deve ser 201

  Cenário: Cadastro bem-sucedido de notificacao
    Dado que eu tenha os seguintes dados específicos da notificacao:
      | campo          | valor                                          |
      | moradorId      | 3                                             |
      | mensagem       | Boa tarde, prezado(a) morador(a). Bem-vindo(a).|
      | dataNotificacao| 2024-05-24T14:30:00                            |
      | lida           | true                                           |
    Quando eu enviar a requisição para o endpoint "/api/notificacao/cadastro" de cadastro de notificacao
    Então o status code da resposta vinda da api deve ser 201

  Cenário: Cadastro de notificacao sem sucesso ao passar o campo mensagem vazio
    Dado que eu tenha os seguintes dados específicos da notificacao:
      | campo          | valor                                          |
      | moradorId      | 15                                             |
      | mensagem       |                                                |
      | dataNotificacao| 2024-05-24T14:30:00                            |
      | lida           | true                                           |
    Quando eu enviar a requisição para o endpoint "/api/notificacao/cadastro" de cadastro de notificacao
    Então o status code da resposta vinda da api deve ser 400
    E o corpo de resposta de erro vinda da api de notificao deve retornar a mensagem "Mensagem é obrigatória!"


