
Feature: Creacion y consulta de ordenes en la API de petStore

  @CrearOrder
  Scenario Outline: Creacion de order
    Given siendo la URL "https://petstore.swagger.io/v2"
    When envio los datos id <id>, petId <petId>, quantity <quantity>, shipDate "<shipDate>", status "<status>", complete "<complete>"
    Then verifico que el codigo de respuesta sea <statusCode>
    And muestro los datos para verificarlos
    Examples:
      | id | petId | quantity | shipDate                     | status | complete | statusCode |
      | 10 | 10    | 10       | 2024-12-17T00:06:08.000+0000 | placed | true     | 200        |
      | 11 | 11    | 11       | 2024-12-17T00:06:08.000+0000 | placed | true     | 200        |
      | 12 | 12    | 12       | 2024-12-17T00:06:08.000+0000 | placed | true     | 200        |

  @ConsultarOrder
  Scenario Outline: Consulta de order
    Given siendo la URL "https://petstore.swagger.io/v2"
    When envio el dato orderId <id>
    Then verifico que el codigo de respuesta sea <statusCode>
    And verifico los siguientes datos petId <petId>, quantity <quantity>, shipDate "<shipDate>", status "<status>", complete "<complete>"
    Examples:
      | id | statusCode | petId | quantity | shipDate                     | status | complete |
      | 10 | 200        | 10    | 10       | 2024-12-17T00:06:08.000+0000 | placed | true     |
      | 11 | 200        | 11    | 11       | 2024-12-17T00:06:08.000+0000 | placed | true     |
      | 12 | 200        | 12    | 12       | 2024-12-17T00:06:08.000+0000 | placed | true     |