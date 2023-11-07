# [Projeto Tech Challenge](https://github.com/ribeirofeu/techchallenge-soat1-fase1)

![design da aplicação](docs/layers.png)

> Este serviço tem como objetivo gerenciar pedidos de uma lanchonete de forma organizada e com rastreabilidade durante todo o processo, desde o pedido a entrega

### Stack

O projeto ainda está em desenvolvimento e as próximas atualizações serão voltadas nas seguintes tarefas:

- Java 17
- Maven
- Docker

## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

- Ter o JAVA (Mínimo JDK17) instalado em seu PC.
- Ter algum contexto do Docker (DAEMON) rodando localmente.

## 🚀 Instalando o projeto

Se você está rodando o projeto pela primeira vez, execute:

```
make build
```

Caso o comando `make` não funcione, execute o comando

```
docker-compose up -d --build
```

## ☕ Rodando o projeto

Verifique se os seguintes containers estão rodando com o comando `make status`:

```
- app
    - Container: techchallenge-soat1-fase1-app-1
    - Porta: 8080:8080

- db
  - Container: techchallenge-soat1-fase1-db-1
  - Porta: 3306
```

### Agora você pode:

| Comando      | Descrição              |
| ------------ | ---------------------- |
| `make up `   | Levantar os containers |
| `make down ` | Derrubar os containers |

Caso o comando `make` não funcione, execute o seguinte comando para levantar os containers:

```
docker-compose up -d
```

E o seguinte comando para derrubar os containers:

```
docker-compose down
```

## 📫 Contribuindo para o projeto

Pull requests são super bem-vindas!

Antes de contribuir, pedimos, por gentileza, que verifique se sua PR está em conformidade com:

1. Código limpo, desacoplado e sem qualquer utensílio de uso para debugs;
1. Commits seguindo os padrões de [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/)
1. Fluxo 100% testado (ou bem próximo a isto), incluindo os cenários de erros;
1. Swagger bem descrito e com exemplos, quando for o caso;
1. Variáveis de ambiente criadas no arquivo `.env.example`, quando for o caso;
1. Adicionar novo serviço lista de Dependências Interna/Externa (no README.md), quando for o caso;
1. Nome de métodos, variáveis e objetos de transferências (DTO, types) devem seguir o padrão `camelCase`;
1. Nome de classes devem seguir o padrão `PascalCase`;
1. Adicionado etiquetas/labels na PR de acordo com as boas práticas.

## Links úteis

### Swagger

| Ambiente | Link                                  |
| -------- | ------------------------------------- |
| LOCAL    | http://localhost:8080/swagger-ui.html |
| QA       |                                       |

### Health

| Ambiente | Link                                  |
| -------- | ------------------------------------- |
| LOCAL    | http://localhost:8080/actuator/health |
| QA       |                                       |
| PROD     |                                       |

## Depêndencias Internas

1.

## Depêndencias Externas

1.

[⬆ Voltar ao topo](#projeto-tech-challenge)<br>
