
# Pagadoria – Infraestrutura Local

Ambiente de desenvolvimento para o backend **Pagadoria**, composto por:

- PostgreSQL
- Keycloak
- Spring Boot API

Arquitetura:

Cliente → Keycloak (9080) → JWT → API Spring Boot (8080) → PostgreSQL (5432)

---

# 1. Subir o ambiente

Na raiz do projeto:

docker compose up -d

Verificar containers:

docker compose ps

---

# 2. Endereços dos serviços

Serviço | URL
------ | ------
API | http://127.0.0.1:8080
Keycloak | http://127.0.0.1:9080
PostgreSQL | 127.0.0.1:5432

---

# 3. Credenciais

## Keycloak (admin)

Painel:
http://127.0.0.1:9080

Login:

user: admin  
password: admin

---

## PostgreSQL

host: 127.0.0.1  
port: 5432  
database: pagadoria  
user: pagadoria  
password: pagadoria  

Banco usado pelo Keycloak:

database: keycloak  
user: keycloak  
password: keycloak  

---

# 4. Realm configurado

Realm:

pagadoria

Roles criadas:

ADMIN_PAGADORIA  
CONTRATANTE  
PAGADOR  
FAVORECIDO

Usuários de teste:

| usuário | senha | role |
|------|------|------|
pagadoria_admin | admin123 | ADMIN_PAGADORIA
contratante1 | demo123 | CONTRATANTE
pagador1 | demo123 | PAGADOR
favorecido1 | demo123 | FAVORECIDO

---

# 5. Client criado

Client para a API:

clientId: pagadoria-api  
type: bearer-only

Client para testes:

clientId: pagadoria-cli  
type: public  
direct access grants: enabled

---

# 6. Gerar token (teste rápido)

curl -X POST http://127.0.0.1:9080/realms/pagadoria/protocol/openid-connect/token -d "client_id=pagadoria-cli" -d "username=pagadoria_admin" -d "password=admin123" -d "grant_type=password"

Resposta contém:

access_token

---

# 7. Usar token na API

curl http://127.0.0.1:8080/api/test \
-H "Authorization: Bearer SEU_TOKEN"

---

# 8. Configuração no Spring Boot

application.yml

server:
  port: 8080

spring:
  datasource:
    url: jdbc:postgresql://127.0.0.1:5432/pagadoria
    username: pagadoria
    password: pagadoria

  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: http://127.0.0.1:9080/realms/pagadoria

---

# 9. Subir apenas a infraestrutura

docker compose up postgres keycloak -d

Depois rodar a API pelo IntelliJ.

---

# 10. Estrutura sugerida do projeto

src/main/java/...

domain  
repository  
service  
controller  
dto  
security  
config  

Entidades principais:

Pessoa  
Transacao  
ParticipacaoTransacao  
Parcela  
SolicitacaoAntecipacao
