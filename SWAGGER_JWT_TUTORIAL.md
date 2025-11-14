# Como Usar o Swagger com Autenticação JWT

Este guia mostra como testar a API Ronalds Car usando o Swagger UI com autenticação JWT.

## 1. Iniciar a Aplicação

```bash
./mvnw.cmd spring-boot:run
```

Aguarde até ver a mensagem: `Started RonaldsWsApplication in X seconds`

## 2. Acessar o Swagger UI

Abra o navegador e acesse:

```
http://localhost:8080/swagger-ui.html
```

Você verá a documentação completa da API com todos os endpoints organizados por categorias.

## 3. Realizar Login (Obter o Token JWT)

### Passo 1: Localizar o Endpoint de Login

- Procure pela seção **"Autenticação"**
- Clique no endpoint `POST /api/usuario/login`
- Clique no botão **"Try it out"**

### Passo 2: Preencher os Dados de Login

No campo **Request body**, insira suas credenciais:

```json
{
  "email": "seu-email@exemplo.com",
  "senha": "sua-senha"
}
```

### Passo 3: Executar a Requisição

- Clique no botão azul **"Execute"**
- Aguarde a resposta

### Passo 4: Copiar o Token

Na resposta (Response body), você verá algo assim:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c3VhcmlvQGV4ZW1wbG8uY29tIiwidWlkIjoxLCJyb2xlIjoiQURNSU4iLCJpYXQiOjE3MzE1ODQyNjIsImV4cCI6MTczMTYyMDI2Mn0.abc123xyz...",
  "usuario": {
    "id": 1,
    "nome": "Nome do Usuário",
    "email": "usuario@exemplo.com",
    ...
  }
}
```

**COPIE O VALOR DO CAMPO `token`** (todo o texto longo depois de "token":)

## 4. Autenticar no Swagger (Botão Authorize)

### Passo 1: Localizar o Botão Authorize

- No topo da página do Swagger, você verá um botão verde com um **cadeado** escrito **"Authorize"**
- Clique neste botão

### Passo 2: Inserir o Token

Uma janela popup será aberta com o campo **"Value"**:

```
Available authorizations
bearerAuth (http, bearer)

Value: [Cole o token aqui]
```

**IMPORTANTE**: Cole apenas o token, **SEM** a palavra "Bearer". O Swagger adiciona automaticamente.

❌ **ERRADO**: `Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...`
✅ **CORRETO**: `eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...`

### Passo 3: Confirmar

- Clique no botão **"Authorize"**
- Clique no botão **"Close"** para fechar a janela

### Passo 4: Verificar Autenticação

Após autenticar, o cadeado ficará **fechado/preenchido**, indicando que você está autenticado.

## 5. Testar Endpoints Protegidos

Agora você pode testar qualquer endpoint da API:

### Exemplo: Listar Clientes

1. Vá até a seção **"Clientes"**
2. Clique em `GET /api/cadastros/clientes`
3. Clique em **"Try it out"**
4. Ajuste os parâmetros se necessário (page, size, filtros)
5. Clique em **"Execute"**

O Swagger enviará automaticamente o token JWT no header `Authorization: Bearer {seu-token}` em todas as requisições!

## 6. Tempo de Expiração do Token

- **Validade do Token**: 10 horas
- **Senha**: Nunca expira

Quando o token expirar, você verá erros **401 Unauthorized**. Basta fazer login novamente (repetir passos 3 e 4).

## 7. Logout / Remover Autenticação

Para remover o token do Swagger:

1. Clique no botão **"Authorize"** (cadeado)
2. Clique no botão **"Logout"**
3. O cadeado ficará **aberto/vazio**

## 8. Endpoints que NÃO Requerem Autenticação

Apenas estes endpoints são públicos:

- `POST /api/usuario/login` - Fazer login
- `POST /api/usuario/forgot-password` - Recuperar senha

Todos os outros endpoints exigem autenticação JWT.

## Troubleshooting (Resolução de Problemas)

### Erro 401 Unauthorized

**Causa**: Token inválido, expirado ou não fornecido.

**Solução**:
1. Verifique se você clicou em "Authorize" e colou o token
2. Verifique se o token não expirou (10 horas)
3. Faça login novamente e obtenha um novo token

### Erro 403 Forbidden

**Causa**: Você está autenticado, mas não tem permissão para acessar o recurso.

**Solução**: Verifique seu perfil de usuário (ADMIN, USER, etc.)

### Token não funciona

**Verificações**:
1. Você copiou o token completo? (é um texto bem longo)
2. Você colocou apenas o token, SEM a palavra "Bearer"?
3. Não há espaços extras no início ou fim do token?
4. O token ainda está válido? (não expirou)

### Swagger não carrega

**Soluções**:
1. Verifique se a aplicação está rodando
2. Tente acessar: `http://localhost:8080/v3/api-docs`
3. Limpe o cache do navegador
4. Tente em modo anônimo/privado

## Dicas Úteis

### 1. Formato de Datas
As datas devem seguir o formato ISO 8601:
```
yyyy-MM-dd'T'HH:mm:ss
Exemplo: 2024-11-14T10:30:00
```

### 2. Paginação
Muitos endpoints suportam paginação:
- `page`: Número da página (começa em 0)
- `size`: Quantidade de itens por página
- `sort`: Campo para ordenação

### 3. Copiar cURL
Você pode copiar comandos cURL para usar no terminal:
- Após executar uma requisição no Swagger
- Procure por "Curl" na seção de resposta
- Copie o comando completo

### 4. Testar com Postman/Insomnia
Use o arquivo OpenAPI para importar no Postman:
```
http://localhost:8080/v3/api-docs
```

## Segurança

⚠️ **ATENÇÃO EM PRODUÇÃO**:

1. **Não compartilhe seu token JWT** - É como uma senha temporária
2. **Use HTTPS** em produção para criptografar a comunicação
3. **Configure CORS** adequadamente para permitir apenas origens confiáveis
4. **Altere o secret JWT** no arquivo `application.yaml` para um valor forte e único

---

## Links Úteis

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`
- OpenAPI YAML: `http://localhost:8080/v3/api-docs.yaml`

---

**Pronto!** Agora você pode testar toda a API de forma interativa e segura usando o Swagger UI com autenticação JWT! 🚀
