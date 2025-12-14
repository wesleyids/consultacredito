# Projeto Consulta Crédito


### Tecnologias utilizadas
- Java
- Angular
- Docker
- Kafka

### Executando o projeto

##### Executar kafka

- Inicializar zookeeper
```console
~/kafka/bin/zookeeper-server-start.sh config/zookeeper.properties
```

- Inicializar kafka server
```console
~/kafka/bin/kafka-server-start.sh config/server.properties
```

- Criar topic
```console
~/kafka/bin/kafka-topics.sh --create --topic topic_eicon_creditoconsulta_log --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```

- Inicializar consumer do kafka para ver registros de logs de consulta de crédito
```console
~/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic topic_eicon_creditoconsulta_log --from-beginning --max-messages 1000
```

##### Executar via docker

1. Backend
```console
cd consultacredito-backend
docker build -t eclipse-temurin:consultacredito-backend .
docker run -p "8080:8080" -d <image_id> --network host
```

2. Frontend
```console
cd consultacredito-frontend
docker build -t nginx:consultacredito-frontend .
docker run -p "4040:80" -d <image_id>
```

##### Executar via docker compose
```console
sudo docker-compose up --build
```

##### Acessar aplicação

```
http://localhost:4040
```

##### Executar localhost

1. Configurar variáveis de ambiente

```console
export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/db?useSSL=false&serverTimezone=UTC
export SPRING_DATASOURCE_USERNAME=root
export SPRING_DATASOURCE_PASSWORD=password
export SPRING_JPA_HIBERNATE_DDL_AUTO=auto
export SPRING_KAFKA_URL=localhost:9092
```

2. Build backend

```console
cd consultacredito-backend
mvn clean install -DskipTests
```

- (Opcional) Executar testes unitários e integração

```console
cd consultacredito-backend
mvn clean test -Dspring.profiles.active=testing
```

3. Executar o backend
```console
cd consultacredito-backend
java -jar target/consultacredito.jar 
```

4. Build frontend

```console
cd consultacredito-frontend

npm i

npm run build:prod
```

5. Executar frontend

```console
cd consultacredito-frontend

http-server --port 4040 dist/consultacredito/
```

### Endpoints

###### GET
    
- http://localhost:8080/api/creditos/{numeroNfse}

- http://localhost:8080/api/creditos/credito/{numeroCredito}
