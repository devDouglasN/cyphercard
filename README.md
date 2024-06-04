
# 💳 CypherCard  
### Projeto composto por cinco módulos unificados, simula a avaliação de crédito e emissão de cartões com base na renda do cliente. Utiliza Docker para execução e RabbitMQ para comunicação assíncrona entre as aplicações. 

<br>

## 🎥  Assista ao vídeo do projeto  
### Vídeo detalhado do projeto onde cada recurso é explicado e demonstrado em ação! Veja abaixo:

https://github.com/devDouglasN/cyphercard/assets/122110326/14d67315-26bc-4774-a51a-64d61dc9040b



<br>

## ⚡ Execução do projeto   
Pré-requisitos:

✔️ Java 17   
✔️ Docker    
✔️ Postman     
✔️ H2   
✔️ Intellij (ou outra de sua preferência)  

<br>

## ⏳ Getting Started
### Você pode:
+ [Baixar o ZIP do projeto](https://github.com/devDouglasN/cyphercard/archive/refs/heads/main.zip) e abri-lo em uma IDE de sua preferência.

### OU 
- Clonar o repositório <span style="color: grey;">https://github.com/devDouglasN/cyphercard</span>
- Rodar <span style="color: grey;">mvn clean install</span> para instalar as dependências do projeto.

<br>

## ⚙️ Funcionalidades
+ 🧑‍🤝‍🧑 CRUD de Clientes  
+ 💳 CRUD de Cartões  
+ 🏦 Avaliação de Crédito  
+ 📬 Emissão de Cartão 
+ 📜 Documentação Swagger  

<br>

## 🛠️ Tecnologias
| Tecnologia            | Versão  |  
|-----------------------|---------|  
| Java                  | 17      |
| Spring Boot           | 3.2.3   |  
| Spring Cloud          | 2023.0.0|
| Springdoc OpenAPI     | 2.5.0   |
| Docker                | 24.0.7  |
| RabbitMQ              | 3.13.1  |
| Keycloak              | 24.0.2  |
| H2 Database           | 2.2.224 |

<br>


## 🐳 Docker
### Executando o projeto em contêineres

✅Criar a Network Docker  
+ Crie a rede Docker chamada creditappraiser-network para facilitar a comunicação entre os contêineres.
```
docker network create <nome_da_network>
````

<br>

✅Construir a imagem de cada microserviço conectada á network:  
+ Navegue até cada pasta de microserviço, construa as imagens Docker correspondentes usando o Dockerfile de cada um e substitua NOME_DA_IMAGEM pelo nome do microserviço. 
  + eurekaserver   
  + mscards   
  + msclients   
  + mscreditevaluator   
  + mscloudgateway   

  Comando para construir a imagem do microserviço eureka:
  ```
  docker build --tag cyphercard-eureka .
  ````
  Comando para construir a imagem do microserviço de cartão:  
   ```
  docker build --tag cyphercard-mscards .
  ```` 
  Comando para construir a imagem do microserviço de cliente:  
   ```
  docker build --tag cyphercard-msclients .
  ````
  Comando para construir a imagem do microserviço de avaliação de crédito:  
   ```
  docker build --tag cyphercard-mscreditevaluator .
  ````
  Comando para construir a imagem do RabbitMQ pelo powershell:  
   ```
  docker pull rabbitmq:3.13-management
  ````
  Comando para construir a imagem do Gateway:  
   ```
  docker build --tag cyphercard-gateway .
  ````
  - Comando para construir a imagem do Keycloak junto ao container pelo PowerShell:
  + OBS: Necessário configurar a Front-URL do Keycloak
Atualize a front-url do Keycloak para apontar para o endereço do contêiner, garantindo a correta integração de autenticação:
Basta acessar o dashboard do keycloak e alterar para: http://keycloak:8080. Após o comando, um usuário com as seguintes credenciais será criado:
👤 Login: admin
🔑 Senha: admin

 ```
docker run --name cyphercard-keycloak -p 8081:8080 -e 
KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORK=admin quay.io/keycloak/keycloak:24.0.2 start.dev
````

✅ Criando o Realm 
Baixar o realm : Acessar os links úteis e baixar o arquivo json contendo o realm.

- Acessar o dashboard do keycloak utilizando o login e senha.
- Após entrar no dash board, clicar na aba de realms onde estará selecionado o realm "master" [master] 🔻
- Importar o arquivo Realm.json que foi baixado anteriormente  e clicar em [CREATE]

<br>

✔️Siga os comandos abaixo para criar um contêiner individual utilizando variáveis de ambiente para cada microserviço, utilizando a imagem que foi construída.
- Comando para criar um contêiner para o microserviço Eureka a partir da imagem:
```
docker run --name cyphercard-eureka -p 8761:8761 --network cyphercard-network -d cyphercard-eureka
````
- Comando para criar um contêiner para o microserviço de Cartão a partir da imagem:
```
docker run --name cyphercard-mscards --network cyphercard-network -d cyphercard-mscards
````
- Comando para criar um contêiner para o microserviço de Cliente a partir da imagem:
```
docker run --name cyphercard-msclients --network cyphercard-network -d cyphercard-mscards
````
- Comando para criar um contêiner para o microserviço de Avaliação de Crédito a partir da imagem:
```
docker run --name cyphercard-mscreditevaluator --network cyphercard-network -d cyphercard-mscreditevaluator
````
- Comando para criar um contêiner para o microserviço de Avaliação de Crédito a partir da imagem:
  - O usuário e as senhas padrão do RabbitMQ já estão pré-configurados no projeto.
  - O serviço responsável por enviar a mensagem já está ajustado para criar a fila.
```
docker run --name cyphercard-rabbitmq --network cyphercard-network rabbitmq:3.13-management
````
- Comando para criar um contêiner para o Gateway a partir da imagem:
```
docker run --name cyphercard-gateway -p 8080:8080 -e EUREKA_SERVER=cyphercard-eureka -e KEYCLOAK_SERVER=cyphercard-keycloak -e KEYCLOAK_PORT=8081 --network cyphercard-network -d cyphercard-gateway 
````

<br>

## 🔗 Endpoints

Você pode utilizar o Insomnia, Postman ou qualquer outra ferramenta de sua preferência para realizar as requisições.

### Clientes
POST ( Cadastrar cliente )   
+ http://localhost:8080/customers
```
 {
      "name": "Digite o nome do cliente",
	  "cpf": "Digite o CPF do cliente",
	  "age": "Digite a idade do cliente"
}
````

GET ( Retornando os dados cliente )
+ http://localhost:8080/customers  
QUERY PARAMETERS:   
Inclua a palavra ‘cpf’ no parâmetro, seguida pelo CPF do cliente cujos dados você deseja receber.

<br>

### Cartões
POST ( Registrar cartão )  
http://localhost:8080/cards   
```
 {
    "name": Digite o nome do cartão. Ex: "bradesco visa",  
    "flagCard": Digite a bandeira do cartão. Ex: "VISA",  
  	"income": Digite a renda do cliente. Ex: 2700,  
	"limitBasic": Digite o limite a ser disponibilizado. Ex: 250  
}
````
GET ( Retornará os cartões disponíveis para uma renda até o valor que você inserir )  
http://localhost:8080/cards
QUERY PARAMETERS:  
Inclua a palavra ‘income’ no parâmetro, seguida pelo renda cujos cartões disponíveis você deseja receber. 

GET ( Os cartões disponíveis para o cliente especificado serão retornados após você inserir o CPF dele )  
http://localhost:8080/cards
QUERY PARAMETERS:  
Inclua a palavra ‘income’ no parâmetro, seguida pelo renda cujos cartões disponíveis você deseja receber. 

<br>

### Avaliador de crédito
POST ( Avaliação do pedido do cartão realizada com base na renda do cliente. )  
http://localhost:8080/assessments-credit
```
 {
      "cpf": "Insira o CPF do cliente cuja renda será avaliada",  
	  "income": "Insira a renda do cliente para determinar o limite"     
}
````

POST ( Com a renda já informada e o limite estabelecido, solicita o envio do cartão e um protocolo será retornado para acompanhamento. )  
http://localhost:8080/assessments-credit
```
 {
    "protocol": Ex: "39fef195-4396-4906-bc7c-a15c3f7431ce"
}
````

GET ( Retorna as informações do cliente e os cartões disponíveis, com base no CPF informado. )  
http://localhost:8080/assessments-credit/situation-customer
QUERY PARAMETERS:  
Inclua a palavra cpf no parâmetro, seguida pelos números do cpf para obter informações do cliente e os cartões disponíveis. 

<br>



## Ilustração gráfica da arquitetura do projeto em execução em containers Docker:
![cyphercard](https://github.com/devDouglasN/cyphercard/assets/122110326/7c991429-babd-4c40-83e6-4a9fc8b1696a)

## Construção das imagens dos microserviços:
![imagens dos projetos](https://github.com/devDouglasN/cyphercard/assets/122110326/99573c4e-b498-4f21-af26-108c297cbd65)

## Arquitetura do projeto em plena operação nos containers Docker:
![docker containers](https://github.com/devDouglasN/cyphercard/assets/122110326/15691953-5c40-4c3e-9374-fabec7a34c42)

## Servidor Eureka (Discovery Server)
![eureka](https://github.com/devDouglasN/cyphercard/assets/122110326/e1ec9f98-81ad-43ab-842b-4f5c385ab873)

<br>

## 🏁 Disparo de requisições 
+ Obter o "token" gerado pelo keycloak
  + 1. Acesse o endpoint de Login e forneça o objeto contendo as credenciais pré-cadastradas no Realm. 👉 {"username": "admin", "password": "admin"}. (O usuário ‘admin’ possui acesso irrestrito, enquanto o usuário ‘comum’ possui restrições a determinados endpoints.)
  + 2. Depois de enviar a solicitação, receberemos um Access Token em resposta.
  + 3. Copie o token e adicione-o na aba ‘Authorization’ do Postman/Insomnia como um cabeçalho do tipo ‘Bearer Token’ para todas as requisições feitas no Postman.
+ Agora você pode fazer o download da documentação disponibilizada no link mencionado. Uma vez baixada, é possível importá-la para o Postman. Em seguida, gere um novo token e utilize-o nas suas requisições.
