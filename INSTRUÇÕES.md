# Fórum de Discussões - Instruções de Execução

## Pré-requisitos

- Java 21
- PostgreSQL 5.432 ou superior
- Node.js 20+
- npm

## Configuração do Banco de Dados

1. Certifique-se de que o PostgreSQL está rodando
2. Crie um banco de dados chamado `tpjdb`:
   ```sql
   CREATE DATABASE tpjdb;
   ```
3. Crie um usuário (se necessário):
   ```sql
   CREATE USER aluno WITH PASSWORD 'aluno';
   GRANT ALL PRIVILEGES ON DATABASE tpjdb TO aluno;
   ```

## Executando o Backend (Spring Boot)

1. Entre no diretório do backend:
   ```bash
   cd backend
   ```

2. Execute o projeto:
   ```bash
   ./gradlew bootRun
   ```

   No Windows use:
   ```bash
   gradlew.bat bootRun
   ```

3. O backend estará disponível em: `http://localhost:8080/api/v1`

## Executando o Frontend (Next.js)

1. Entre no diretório do frontend:
   ```bash
   cd frontend
   ```

2. Verifique se existe o arquivo `.env.local` com a URL do backend:
   ```
   NEXT_PUBLIC_API_URL=http://localhost:8080/api/v1
   ```
   (O arquivo já deve existir, mas se não existir, crie-o com esse conteúdo)

3. Instale as dependências:
   ```bash
   npm install
   ```
   **IMPORTANTE:** Se você já tinha executado `npm install` antes, execute novamente para instalar o axios.

4. Execute o projeto:
   ```bash
   npm run dev
   ```

5. Acesse: `http://localhost:3000`

**Nota:** Se você encontrar o erro "api.get is not a function", pare o servidor (Ctrl+C) e reinicie-o. Isso ocorre porque o Next.js precisa recarregar após a instalação do axios.

## Funcionalidades Implementadas

### 1. Criar Post
- Usuário pode criar posts com título, corpo e tags
- Tags são separadas por vírgula (ex: "Java, SpringBoot, Backend")
- Validação de campos obrigatórios
- Posts são vinculados ao autor (userId: 1 para testes)

### 2. Comentar em Post
- Usuário pode adicionar comentários aos posts
- Comentários ficam vinculados ao post original
- Suporte para formatação de texto (quebras de linha)

### 3. Editar Posts e Comentários
- Apenas o autor pode editar
- Edição permitida apenas nas primeiras 24 horas
- Validação no backend e frontend
- Feedback visual quando a edição não é permitida

## Endpoints da API

### Posts
- `GET /api/v1/posts` - Lista todos os posts
- `GET /api/v1/posts/{id}` - Busca post por ID
- `POST /api/v1/posts` - Cria novo post
- `PUT /api/v1/posts/{id}` - Atualiza post
- `DELETE /api/v1/posts/{id}` - Deleta post

### Comentários
- `GET /api/v1/posts/{postId}/comments` - Lista comentários de um post
- `POST /api/v1/posts/{postId}/comments` - Cria comentário
- `PUT /api/v1/posts/comments/{id}` - Atualiza comentário
- `DELETE /api/v1/posts/comments/{id}` - Deleta comentário

## Dados de Teste

O sistema vem com dados de exemplo:
- 3 usuários (Alice, Bob, Charlie)
- 3 posts sobre Spring Boot, Java e JPA
- 4 comentários distribuídos entre os posts

## Estrutura do Projeto

```
project/
├── backend/              # Spring Boot API
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── ifsc/edu/tpj/
│   │       │       ├── controller/
│   │       │       ├── service/
│   │       │       ├── repository/
│   │       │       ├── model/
│   │       │       └── dto/
│   │       └── resources/
│   │           ├── application.properties
│   │           └── schema.sql
│   └── build.gradle
│
└── frontend/            # Next.js UI
    ├── src/
    │   ├── app/
    │   ├── components/
    │   ├── services/
    │   └── types/
    └── package.json
```

## Solução de Problemas

### Backend não inicia
- Verifique se o PostgreSQL está rodando
- Verifique as credenciais em `backend/src/main/resources/application.properties`
- Certifique-se de que a porta 8080 está livre

### Frontend não conecta ao backend
- Verifique se o backend está rodando em `http://localhost:8080`
- Verifique o arquivo `.env` na raiz do projeto
- Veja o console do navegador para erros de CORS

### Erro "Post not found"
- O banco de dados pode não ter sido inicializado
- Reinicie o backend para executar o schema.sql novamente
