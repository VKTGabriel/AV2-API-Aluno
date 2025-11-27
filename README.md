
# Prova AV2

Uma pequena api para demonstrar a interação Muitos-Muitos em uma API web-service, assim como a segurança de aplicações com Oauth2 e Monitoramento.

## Documentação

[Swagger Doc](http://localhost:9000/swagger-ui/index.html)


## ScreenShot

![App Screenshot](https://av2-images.s3.us-east-1.amazonaws.com/Captura+de+tela+2025-11-26+223402.png)
## Obter Token JWT

#### Retorna token

Acesse a rota [Security/Token] na documentação Swagger e solicite um token de acesso admin.

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `username` | `string` | admin |
| `password` | `string` | 123  |

Insira o token obtido na aba de "Autorize" (Botão verde acima de todos os endpoints) da documentalção. Dessa forma, estará livre para utilizar todos os endpoints da API enquanto o token se mantes válido.
## Rodando localmente

Clone o projeto do GitHub.

```bash
  git clone https://github.com/VKTGabriel/AV2-API-Aluno
```

Execute o comando para iniciar o Build da aplicação e execução do container Docker utilizando o docker compose.

```bash
  docker-compose up

```


## Monitoramento

Acesse o Grafana

```bash
  http://localhost:3000
```

* Acesse Dashbord: JVM (Micrometer)
* Acesse Dashbord: Spring Boot Statistics
    
## Screenshots

![App Screenshot](https://app-restaurante-bucket.s3.sa-east-1.amazonaws.com/as.png)

