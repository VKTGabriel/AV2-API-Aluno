
# Prova AV2

Uma pequena api para demonstrar a interação Muitos-Muitos em uma API web-service.

## Documentação

[Swagger Doc](http://localhost:8080/swagger-ui.html)


## ScreenShot

![App Screenshot](https://app-restaurante-bucket.s3.sa-east-1.amazonaws.com/swagger.png)
## Obter Token JWT

#### Retorna token

```http
  GET http://localhost:8081/realms/AV2/protocol/openid-connect/token
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `client_id` | `string` | **Obrigatório**. av2 |
| `username` | `string` | **Obrigatório**. your username |
| `password` | `string` | **Obrigatório**. your password  |
| `grant_type` | `string` | **Obrigatório**. your username |



## Rodando localmente

Clone o projeto

```bash
  git clone https://github.com/VKTGabriel/AV2-API-Aluno
```

Execute o docker compose

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

