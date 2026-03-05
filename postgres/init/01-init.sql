-- DB do seu sistema
CREATE USER pagadoria WITH PASSWORD 'pagadoria';
CREATE DATABASE pagadoria OWNER pagadoria;
GRANT ALL PRIVILEGES ON DATABASE pagadoria TO pagadoria;

-- DB do Keycloak
CREATE USER keycloak WITH PASSWORD 'keycloak';
CREATE DATABASE keycloak OWNER keycloak;
GRANT ALL PRIVILEGES ON DATABASE keycloak TO keycloak;

