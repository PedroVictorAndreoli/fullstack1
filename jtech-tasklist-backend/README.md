![Jtech Logo](http://www.jtech.com.br/wp-content/uploads/2015/06/logo.png)

# jtech-tasklist

## What is
`jtech-tasklist` é uma API RESTful desenvolvida para gerenciamento de tarefas (TODO List).  
Permite criar, listar, atualizar e deletar tarefas, aplicando boas práticas de desenvolvimento backend com Spring Boot, Spring Data JPA e validação de dados.

---

## Composite by
O projeto foi desenvolvido por profissionais da J-Tech e implementa os seguintes conceitos:  
- Arquitetura limpa (Clean Architecture)  
- Boas práticas de codificação (KISS, DRY)  
- Tratamento centralizado de erros  
- Validação robusta das entradas  

---

## Services
O projeto possui os seguintes serviços:  

- **Tasklist Service**: Lida com a lógica de negócios para criação, atualização, busca e exclusão de tarefas.  
- **Exception Handler**: Gerencia erros globalmente, retornando mensagens amigáveis em português e códigos HTTP adequados.  
- **Validation Service**: Valida entradas do usuário, garantindo que campos obrigatórios estejam preenchidos corretamente.  

---

## Helper
O projeto contém helpers para:  

- Conversão entre **domain objects** e **entities** do banco de dados.  
- Enumeração de status de tarefas (`PENDENTE`, `CONCLUIDA`).  
- Geração de respostas de erro consistentes usando `ApiError`, `ApiSubError` e `ApiValidationError`.  

---

## How to use
A API é consumida via endpoints REST:  

| Método | Endpoint         | Descrição                              |
|--------|-----------------|----------------------------------------|
| POST   | /tasks/           | Criar uma nova tarefa                   |
| GET    | /tasks/           | Listar todas as tarefas                 |
| GET    | /tasks/{id}      | Obter detalhes de uma tarefa específica|
| PUT    | /tasks/{id}      | Atualizar uma tarefa                    |
| DELETE | /tasks/{id}      | Deletar uma tarefa                      |

> Todos os dados devem ser enviados em JSON.  

## Sample

Exemplo de requisição usando `curl`:

**Criar tarefa:**

```bash
curl -X POST http://localhost:8080/tasks \
-H "Content-Type: application/json" \
-d '{"title": "Finalizar documentação", "description": "Concluir relatório", "status": "PENDENTE"}'
```

**Listar tarefas:**

```bash
curl -X GET http://localhost:8080/tasks
```

**Atualizar tarefa:**

```bash
curl -X PUT http://localhost:8080/tasks/{id} \
-H "Content-Type: application/json" \
-d '{"title": "Atualizar documentação", "status": "CONCLUIDA"}'
```

**Deletar tarefa:**

```bash
curl -X DELETE http://localhost:8080/tasks/{id}
```

---

## How to run

Para rodar o projeto localmente, siga os passos:

### Pré-requisitos

* Java 17 ou superior
* Maven ou Gradle
* Banco H2 (em memória) ou PostgreSQL configurado

### Rodando

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/jtech-tasklist.git
cd jtech-tasklist
```

2. Build do projeto:

```bash
mvn clean install
# ou
gradle build
```

3. Rodar a aplicação:

```bash
mvn spring-boot:run
# ou
gradle bootRun
```

4. Acesse a API em:

```
http://localhost:8080
```

### Testes

Para rodar os testes automatizados:

```bash
mvn test
# ou
gradle test
```

---

## Points to improve

Possíveis melhorias futuras:

* Implementar autenticação e autorização (JWT ou OAuth2).
* Adicionar paginação e filtros na listagem de tarefas.
* Criar front-end em React integrado à API.
* Adicionar logs estruturados e monitoramento (ex: Spring Boot Actuator).
* Persistência de arquivos ou attachments para tarefas.
* Internacionalização das mensagens de erro.

