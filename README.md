# Mail API (Spring Boot)

API simples para envio de e-mails via SMTP.

## Requisitos
- Java 17+
- Maven 3.9+
- Credenciais SMTP (ex.: Gmail com App Password)

## Configuração
Defina as variáveis de ambiente ao executar (recomendado):
```bash
MAIL_USER=seu.email@gmail.com MAIL_PASS=app-password ./mvnw spring-boot:run
```
Ou edite `src/main/resources/application.yml`.

## Executar
```bash
./mvnw spring-boot:run
```

## Endpoint
`POST /api/email/send`

### Body (JSON)
```json
{
  "to": "destinatario@exemplo.com",
  "subject": "Assunto",
  "message": "<h1>Olá</h1>",
  "html": true
}
```

### Resposta
```json
{ "status": 200, "message": "E-mail enviado com sucesso" }
```

## Observações
- Para anexos/múltiplos destinatários, evolua a `EmailService` usando `MimeMessageHelper`.
- Para Gmail, ative 2FA e gere um App Password.
