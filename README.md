
# Prova AV2

O projet consiste em uma pequena api de sistema acadêmico criada para demonstrar a interação de entidades em uma API web-service, assim como a segurança de aplicações com Oauth2, monitoramento em tempo real e deploy na nuvem.

## Documentação
Utilizando o link abaixo, voce pode acessar a documentação interativa da API. Para utiliza-la, basta obter um token de autenticação seguindo os passos da próxima etapa e acessar cada um dos endpoints informando os dados por eles solicitados.

[Swagger Doc](http://18.230.22.90:9000/swagger-ui/index.html)


## ScreenShot

![App Screenshot](https://av2-images.s3.us-east-1.amazonaws.com/Captura+de+tela+2025-11-26+223402.png)
## Obter Token JWT

Acesse a rota [Security/Token] na documentação Swagger e solicite um token de acesso.

#### Acesso ADMIN

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `username` | `string` | admin |
| `password` | `string` | 123  |

#### Acesso USER [Autoridade limitada a listar os cursos]

| Parâmetro   | Tipo       | Descrição |
| :---------- | :--------- |:----------|
| `username` | `string` | user      |
| `password` | `string` | 123       |


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

OBS: Para que o monitoramento funcione corretamente no ambiente local, substitua o id "18.230.22.90" por "host.docker.internal" no arquivo prometheus.yml


## Monitoramento

Utilizando o link abaixo, voce pode acessar o minitoramento em Grafana da API.

[Grafana](http://18.230.22.90:3000/)

Informe as seguintes credenciais e, em seguida, acesse a aba de Dashboards para selecionar a opção desejada.

| Parâmetro  | Tipo       | Descrição |
|:-----------| :--------- |:----------|
| `login`    | `string` | admin     |
| `password` | `string` | admin     |


* Acesse Dashbord: JVM (Micrometer)
* Acesse Dashbord: Spring Boot Sistem Monitor
    
## Screenshots

![App Screenshot](https://app-restaurante-bucket.s3.sa-east-1.amazonaws.com/as.png)


## Tecnologias Empregadas
- Spring Web
- Spring Validation 
- Spring Securiry Oauth2 - Keycloak
- Spring JPA - H2 Database
- Spring DOC - Swagger
- Spring Actuator - Prommeteus
- Map Struct
- Jmeter
- Grafana - Dashboards
- Docker - Docker Compose
- Aws Services - EC2 Instances

