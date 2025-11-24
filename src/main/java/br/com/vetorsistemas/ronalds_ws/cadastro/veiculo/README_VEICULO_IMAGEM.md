# Módulo de Imagens de Veículos

## Descrição
Este módulo gerencia o upload, listagem, visualização e exclusão de imagens associadas a veículos no sistema.

## Estrutura do Módulo

```
cadastro/veiculo/
├── VeiculoImagem.java              # Entidade JPA
├── VeiculoImagemController.java    # Controller REST
├── VeiculoImagemService.java       # Lógica de negócio
├── VeiculoImagemRepository.java    # Repository JPA
├── dto/
│   └── VeiculoImagemDto.java      # DTO de resposta
└── mapper/
    └── VeiculoImagemMapper.java   # Mapper Entity <-> DTO
```

## Funcionalidades

### 1. Upload de Imagens
**Endpoint:** `POST /api/veiculos/{codVei}/imagens`

Faz upload de uma ou mais imagens para um veículo específico.

**Restrições:**
- Tamanho máximo por arquivo: 5MB
- Tipos permitidos: JPEG, PNG, GIF, WEBP, BMP
- Máximo de 10 imagens por requisição

**Exemplo de requisição:**
```bash
curl -X POST http://localhost:8080/api/veiculos/1/imagens \
  -H "Authorization: Bearer {token}" \
  -F "imagens=@foto1.jpg" \
  -F "imagens=@foto2.png"
```

**Resposta (201 Created):**
```json
[
  {
    "id": 1,
    "codVei": 1,
    "nomeArq": "foto1.jpg",
    "contentType": "image/jpeg",
    "tamanho": 524288,
    "urlPreview": "/api/veiculos/imagens/1/preview",
    "urlDownload": "/api/veiculos/imagens/1"
  }
]
```

### 2. Listar Imagens do Veículo
**Endpoint:** `GET /api/veiculos/{codVei}/imagens`

Retorna todas as imagens de um veículo específico.

**Exemplo de requisição:**
```bash
curl -X GET http://localhost:8080/api/veiculos/1/imagens \
  -H "Authorization: Bearer {token}"
```

**Resposta (200 OK):**
```json
[
  {
    "id": 1,
    "codVei": 1,
    "nomeArq": "foto1.jpg",
    "contentType": "image/jpeg",
    "tamanho": 524288,
    "urlPreview": "/api/veiculos/imagens/1/preview",
    "urlDownload": "/api/veiculos/imagens/1"
  },
  {
    "id": 2,
    "codVei": 1,
    "nomeArq": "foto2.png",
    "contentType": "image/png",
    "tamanho": 312456,
    "urlPreview": "/api/veiculos/imagens/2/preview",
    "urlDownload": "/api/veiculos/imagens/2"
  }
]
```

### 3. Visualizar Imagem (Preview)
**Endpoint:** `GET /api/veiculos/imagens/{id}/preview`

Retorna a imagem para visualização inline no navegador.

**Exemplo:**
```html
<img src="http://localhost:8080/api/veiculos/imagens/1/preview" alt="Foto do veículo" />
```

### 4. Download de Imagem
**Endpoint:** `GET /api/veiculos/imagens/{id}`

Faz download da imagem com o nome original do arquivo.

**Exemplo de requisição:**
```bash
curl -X GET http://localhost:8080/api/veiculos/imagens/1 \
  -H "Authorization: Bearer {token}" \
  -o foto-veiculo.jpg
```

### 5. Excluir Imagem
**Endpoint:** `DELETE /api/veiculos/imagens/{id}`

Exclui uma imagem específica.

**Exemplo de requisição:**
```bash
curl -X DELETE http://localhost:8080/api/veiculos/imagens/1 \
  -H "Authorization: Bearer {token}"
```

**Resposta:** 204 No Content

## Modelo de Dados

### Tabela: VEICULO_IMAGEM

| Campo        | Tipo         | Descrição                              | Obrigatório |
|--------------|--------------|----------------------------------------|-------------|
| ID           | INTEGER      | Identificador único                    | Sim         |
| CODVEI       | INTEGER      | Código do veículo (FK)                 | Sim         |
| DOCUMENTO    | BLOB         | Conteúdo binário da imagem             | Sim         |
| NOMEARQ      | VARCHAR(255) | Nome original do arquivo               | Sim         |
| CONTENT_TYPE | VARCHAR(100) | Tipo MIME (image/jpeg, etc)            | Não         |
| TAMANHO      | BIGINT       | Tamanho do arquivo em bytes            | Não         |

**Relacionamentos:**
- FK para CADVEI (ON DELETE CASCADE)
- Índice em CODVEI para melhor performance

## Validações

### Arquivo
- ✅ Não pode ser vazio
- ✅ Tamanho máximo: 5MB
- ✅ Tipos permitidos: image/jpeg, image/jpg, image/png, image/gif, image/webp, image/bmp
- ✅ Nome do arquivo deve ser válido

### Veículo
- ✅ Deve existir no banco de dados

### Quantidade
- ✅ Máximo de 10 imagens por requisição

## Tratamento de Erros

| Código | Descrição                                | Quando ocorre                          |
|--------|------------------------------------------|----------------------------------------|
| 400    | Bad Request                              | Arquivo inválido, vazio ou muito grande|
| 404    | Not Found                                | Veículo ou imagem não encontrado       |
| 500    | Internal Server Error                    | Erro ao processar o arquivo            |

**Exemplo de resposta de erro:**
```json
{
  "timestamp": "2025-11-19T14:30:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Arquivo muito grande (foto.jpg). Tamanho maximo: 5MB",
  "path": "/api/veiculos/1/imagens"
}
```

## Logs

O módulo utiliza SLF4J para logging com os seguintes níveis:

- **INFO**: Upload, exclusão e operações bem-sucedidas
- **DEBUG**: Validações e consultas
- **WARN**: Tentativas de acesso a recursos inexistentes
- **ERROR**: Erros ao processar arquivos

**Exemplos de logs:**
```
INFO  - Iniciando upload de 2 imagens para o veículo 1
INFO  - Imagem salva com sucesso: ID=1, Nome=foto1.jpg, Tamanho=524288 bytes
INFO  - Upload concluído: 2 imagens salvas para o veículo 1
WARN  - Tentativa de upload de imagens para veículo inexistente: 999
ERROR - Erro ao processar arquivo: foto.jpg
```

## Segurança

- ✅ Requer autenticação via JWT (bearer token)
- ✅ Validação de tipos de arquivo
- ✅ Limite de tamanho de arquivo
- ✅ Proteção contra SQL Injection via JPA
- ✅ CORS configurado globalmente

## Performance

- Índice em CODVEI para consultas rápidas
- Lazy loading do relacionamento com Veiculo
- Transações otimizadas
- Queries preparadas via JPA

## Migração do Banco de Dados

Execute o script SQL localizado em:
```
src/main/resources/db/veiculo_imagem_migration.sql
```

## Swagger/OpenAPI

Acesse a documentação interativa em:
```
http://localhost:8080/swagger-ui.html
```

Procure pela tag "Imagens de Veículos" para testar os endpoints.

## Melhorias Implementadas

### Versão Atual (v2.0)

1. **Entidade:**
   - ✅ Relacionamento @ManyToOne com Veiculo
   - ✅ Campo contentType para armazenar tipo MIME
   - ✅ Campo tamanho para armazenar tamanho em bytes
   - ✅ Índice para melhor performance
   - ✅ Uso de @Getter/@Setter ao invés de @Data

2. **Controller:**
   - ✅ Remoção de @CrossOrigin (configurado globalmente)
   - ✅ Endpoint de preview adicionado
   - ✅ Melhor documentação Swagger
   - ✅ Uso de @RequiredArgsConstructor
   - ✅ Adição de @SecurityRequirement

3. **Service:**
   - ✅ Logging detalhado
   - ✅ Validações aprimoradas
   - ✅ Limite de imagens por requisição
   - ✅ Mensagens de erro mais descritivas
   - ✅ Uso de @Transactional(readOnly = true)

4. **DTO:**
   - ✅ URLs de preview e download
   - ✅ Documentação Swagger em cada campo
   - ✅ @JsonInclude para omitir nulls

5. **Repository:**
   - ✅ Método countByCodVei
   - ✅ Documentação JavaDoc
   - ✅ Query customizada com @Modifying

6. **Mapper:**
   - ✅ Geração automática de URLs
   - ✅ Fallback para cálculo de tamanho
   - ✅ Documentação JavaDoc

## Próximas Melhorias Sugeridas

- [ ] Compressão de imagens automática
- [ ] Geração de thumbnails
- [ ] Armazenamento em sistema de arquivos ou cloud (S3, Azure Blob)
- [ ] Metadata EXIF das imagens
- [ ] Watermark automático
- [ ] Redimensionamento automático
- [ ] Cache de imagens frequentemente acessadas
- [ ] Versionamento de imagens
- [ ] Ordem de exibição customizável
- [ ] Tags ou categorias para imagens

## Suporte

Para dúvidas ou problemas, consulte a documentação do projeto ou entre em contato com a equipe de desenvolvimento.
