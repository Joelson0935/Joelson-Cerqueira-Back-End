# Joelson-Cerqueira-Back-End
* Teste técnico para vaga de desenvolvedor backend para a empresa Attornatus Procuradoria Digital

## Pré Requisitos
* Java 17
* Spring Boot 3.1.4

## Link do Swagger
```
http://localhost:8080/swagger-ui/index.html

```
![swagger imagem](https://github.com/Joelson0935/Joelson-Cerqueira-Back-End/assets/56981455/2642446d-b1c4-40cc-9d44-f33bc0f2529e)

## Lista de endpoints e json de cada método
### Criar Pessoa
* endpoint
  ```
  http://localhost:8080/pessoa/guardar
  
  ```
* json
```
{
  "id": 0,
  "nome": "string",
  "dataNascimento": "25/09/2023"
}
```
### Editar Pessoa
* endpoint
  ```
  http://localhost:8080/pessoa/editar/{id}
  
  ```
* json
```
{
  "id": 0,
  "nome": "string",
  "dataNascimento": "25/09/203"
}
```

### Consultar Pessoa
* endpoint
  ```
  http://localhost:8080/pessoa/consultar/{id}
  
  ```
### Listar Pessoas
* endpoint
```
  http://localhost:8080/pessoa/listar
  
  ```
### Listar Enderecos da Pessoa
* endpoint
  ```
  http://localhost:8080/pessoa/listar-enderecos-pessoa
  
  ```
### Buscar Endereco Principa da Pessoa Por Id
* endpoint
  ```
  http://localhost:8080/pessoa/buscar-endereco-principal
  
  ```
### Criar Endereco
* endpoint
  ```
  http://localhost:8080/endereco/criar
  
  ```
* json
```
{
  "pessoa": {
    "id": 4
  },
  "principal": true,
  "logradouro": "rua lagoinhas",
  "cep": "28890561",
  "numero": "55",
  "cidade": "rio bonito"
}
```
 
