# API de Gerenciamento de Cursos

API para gestão de cursos de programação desenvolvida como desafio prático do curso da Rocketseat.

## 📌 Funcionalidades Atuais

### CRUD de Cursos
- **Criação** de cursos com nome e categoria
- **Listagem** de todos os cursos com filtros
- **Atualização** parcial (nome e/ou categoria)
- **Exclusão** de cursos
- **Ativação/Desativação** de cursos
- 

### Endpoints
| Método | Rota                 | Descrição                              |
|--------|----------------------|----------------------------------------|
| POST   | /courses/            | Cria novo curso                        |
| GET    | /courses/            | Lista todos os cursos (com filtros)    |
| PUT    | /courses/{id}        | Atualiza dados do curso                |
| DELETE | /courses/{id}        | Remove curso                           |
| PATCH  | /courses/{id}/active | Alterna status ativo/inativo do curso  |

## 🚀 Tecnologias Utilizadas

### Backend
- **Java**
- **Spring Boot**
- **Spring Data JPA**
- **Banco de Dados PostgreSQL**
- **Maven**
- **Docker**

### Futuras Tecnologias (a implementar)
- **Thymeleaf** (para integração frontend)
- **Spring Security**

### Ferramentas Utilizadas
- **Postman**
- **Docker Desktop**
- **pgAdmin4**


## 📄 Documentação da API

### Exemplo de Request (POST)
```json
{
  "name": "Java Fundamentals",
  "category": "Backend"
}
```

### Response (201 Created)
```json
{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "name": "Java + Spring",
  "category": "Backend",
  "active": "ACTIVE",
  "createdAt": "2025-03-22T12:00:00",
  "updatedAt": "2025-03-22T12:00:00"
}
```

## ✅ Validações
- Campos obrigatórios nas rotas POST/PUT
- Tratamento de exceções customizadas
- Atualização automática de datas (@CreationTimestamp e @UpdateTimestamp)


---

Desenvolvido por Jhonatan Sousa

