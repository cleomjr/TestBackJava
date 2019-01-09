# TestBackJava README file

Esta aplicação fornece um controle de gastos básico, de acordo com os requisitos descritos no [REQUIREMENTS.md](REQUIREMENTS.md)

O banco de dados em uso é o **Redis**, configurado para ser acessado conforme definido em `application.properties`. A aplicação
requer que o servidor Redis esteja disponível para inicializar com sucesso. Os valores default apontam para `localhost:6379`.

## Executando a aplicação
Você pode executar a aplicação via Maven, com o comando:
```
$ mvn spring-boot:run
```  
Ou, via jar:

```
$ mvn package
$ java -jar target/testbackjava-0.0.1-SNAPSHOT.jar
```

Para avaliar os testes, prefira a opção via Maven.


## Casos de uso
A documentação da API pode ser vista em [http://localhost:8090/swagger-ui.html](http://localhost:8090/swagger-ui.html), assumindo que a aplicação esteja executando localmente na porta *8090*.

A seguir, estão mapeados os endpoints que devem satisfazer cada caso de uso listado nos requisitos.
 
### Integração de gastos por cartão
Utilize o POST request para criação do gasto

### Listagem de gastos
Utilize o GET de gastos com os parâmetros de data `fromDate` e `toDate` definidos para os últimos 5 segundos.

Exemplo:

`GET http://localhost:8090/api/expense?fromDate=2019-01-08T12%3A28%3A58.596Z&toDate=2019-01-08T12%3A32%3A58.596Z`

### Filtro de gastos
Utilize também o GET de gastos com os parâmetros de data `fromDate` e `toDate` definidos para as 24h do mesmo dia.

### Categorização de gastos
Utilize o PUT de gastos com o novo valor da categoria

### Sugestão de categoria
Utilize o GET de categorias com os caracteres digitados. Mínimo 3 caracteres para executar a busca.

### Categorização automatica de gasto
Antes de fazer o POST, utilize o GET de gastos com o parâmetro description definido, para checar a existência de um 
mesmo gasto com a categoria definida, para ser usada no POST.
 
O GET vai procurar somente valores *idênticos* ao parâmetro.


## Notas
- No arquivo `application.properties` é possível alterar algumas configurações da aplicação, como que porta ela deve ouvir.

- Os testes criados com base nos requisitos descritos no enunciado do problema, [REQUIREMENTS.md](REQUIREMENTS.md), dependem do
microserviço em execução. Portanto, inicie com `spring-boot:run` antes de executar os testes de API.
 
- Há um bug de cache do Redis, criado automaticamente pelo Spring Boot, que adiciona à chave do registro a ser mudado caracteres como 
`\xac\xed\x00\x05t\x00\t`, criando um novo registro. A criação do cache manager foi feita 'manualmente' mas só isso não foi suficiente...
 Como faltou tempo para investigar, esta questão ficou em aberto.
 
- O projeto do SoapUI pode ser importado de [REST-Project-1-soapui-project.xml](REST-Project-1-soapui-project.xml)