-- DB do seu sistema
CREATE USER stella WITH PASSWORD 'stella';
CREATE DATABASE stella OWNER stella;
GRANT ALL PRIVILEGES ON DATABASE stella TO stella;

-- DB do Keycloak
CREATE USER keycloak WITH PASSWORD 'keycloak';
CREATE DATABASE keycloak OWNER keycloak;
GRANT ALL PRIVILEGES ON DATABASE keycloak TO keycloak;

