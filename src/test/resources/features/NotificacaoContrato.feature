# language: pt

@regressivo
Funcionalidade: Validar o contrato ao realizar um cadastro bem-sucedido de notificacao
  Como usuário da API
  Quero cadastrar uma nova notificacao
  Para que eu consiga validar se o contrato esta conforme o esperado

  Cenario: Validar contrato do cadastro bem-sucedido de notificacao
    Dado que eu tenha os seguintes dados específicos da notificacao:
      | campo          | valor                                          |
      | moradorId      | 3                                              |
      | mensagem       | Boa tarde, prezado(a) morador(a). Bem-vindo(a).|
      | dataNotificacao| 2024-05-24T14:30:00                            |
      | lida           | true                                           |
    Quando eu enviar a requisição para o endpoint "/api/notificacao/cadastro" de cadastro de notificacao
    Então o status code da resposta vinda da api deve ser 201
    E que o arquivo de contrato esperado de notificacao é o "Cadastro bem-sucedido de notificacao"
    Então a resposta da requisição tem que estar em conformidade com o contrato selecionado