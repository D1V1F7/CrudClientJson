# CrudClientJson
Java Spring Boot

EndPoint del Servicio

GET:
    http://localhost:8080/crud

POST:
    http://localhost:8080/crud

    Request:
    {
        "name": "David Vazquez Flores",
        "email": "da-150@hotmail.com"
    }

PUT:
    http://localhost:8080/crud

    Request:
    {
        "id": 17,
        "name": "David Vazquez Flores",
        "email": "da-150@hotmail.com"
    }

DELETE:
    http://localhost:8080/crud/{id}

*NOTA*
Los datos se almacenan en un archivo json en la raiz del proyecto con el nombre "jsonData.json"