# BACKEND RESTFUL API

Language: ```Kotlin```

Framework: ```Spring```

Dependencies:

- ```Spring data JPA```
- ```Spring validation```
- ```Spring Web```

## API DOCUMENTATION

### AUTHORIZATION

Endpoint: ```/*```

@header: ```X-API-KEY```

### CREATE PRODUCT

Endpoint: ```/api/p```

url: ```http://localhost:8080/api/p```

@body request:

```json
{
  "id": "idA123",
  "name": "Product A",
  "calories": 0,
  "totalFat": 0,
  "saturatedFat": 0,
  "protein": 0,
  "totalCarbohydrate": 0,
  "sugar": 0,
  "sodium": 0
}
```

@body response:

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "id": "idA123",
    "name": "Product A",
    "createdAt": 1653312282485,
    "updatedAt": null,
    "nutritionFact": {
      "calories": 0,
      "totalFat": 0,
      "saturatedFat": 0,
      "protein": 0,
      "totalCarbohydrate": 0,
      "sugar": 0,
      "sodium": 0
    }
  }
}
```

### GET PRODUCT

Endpoint: ```/api/p/{id}```

url: ```http://localhost:8080/api/p/idA123```

@body response:

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "id": "idA123",
    "name": "Product A",
    "createdAt": 1653312282485,
    "updatedAt": null,
    "nutritionFact": {
      "calories": 0,
      "totalFat": 0,
      "saturatedFat": 0,
      "protein": 0,
      "totalCarbohydrate": 0,
      "sugar": 0,
      "sodium": 0
    }
  }
}
```

### UPDATE PRODUCT

Endpoint: ```/api/p/{id}```

url: ```http://localhost:8080/api/p/idA123```

@body request:

```json
{
  "name": "Product A",
  "calories": 1,
  "totalFat": 1,
  "saturatedFat": 1,
  "protein": 1,
  "totalCarbohydrate": 1,
  "sugar": 1,
  "sodium": 1
}
```

@body response:

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "id": "idA123",
    "name": "Product A",
    "createdAt": 1653312282485,
    "updatedAt": 1653312377849,
    "nutritionFact": {
      "calories": 1,
      "totalFat": 1,
      "saturatedFat": 1,
      "protein": 1,
      "totalCarbohydrate": 1,
      "sugar": 1,
      "sodium": 1
    }
  }
}
```

### DELETE PRODUCT

Endpoint: ```/api/p/{id}```

url: ```http://localhost:8080/api/p/idA123```

@body response:

```json
{
  "code": 200,
  "status": "OK",
  "data": "idA123 deleted"
}
```

### CREATE PRODUCTS

Endpoint: ```/api/p/```

url: ```http://localhost:8080/api/p/```

@body request:

```json
[
  {
    "id": "idB123",
    "name": "Product B",
    "calories": 150,
    "totalFat": 4,
    "saturatedFat": 4,
    "protein": 7,
    "totalCarbohydrate": 20,
    "sugar": 18,
    "sodium": 60
  },
  {
    "id": "idC123",
    "name": "Product C",
    "calories": 140,
    "totalFat": 3.5,
    "saturatedFat": 3.5,
    "protein": 1,
    "totalCarbohydrate": 25,
    "sugar": 14,
    "sodium": 150
  }
]
```

@body response:

```json
{
  "code": 200,
  "status": "OK",
  "data": "2 products added"
}
```

### DELETE PRODUCTS

Endpoint: ```/api/p/```

url: ```http://localhost:8080/api/p/```

@Body response:

```json
{
  "code": 200,
  "status": "OK",
  "data": "2 products deleted"
}
```

### LIST PRODUCT

Endpoint: ```/api/p```

@Param: ```name``` ```page``` ```size```

url ```http://localhost:8080/api/p?name=Product&page=2&size=3```

@Body response:

```json
{
  "code": 200,
  "status": "OK",
  "data": [
    {
      "id": "idG123",
      "name": "Product G",
      "createdAt": 1653304234181,
      "updatedAt": null,
      "nutritionFact": {
        "calories": 140,
        "totalFat": 3,
        "saturatedFat": 3,
        "protein": 1,
        "totalCarbohydrate": 25,
        "sugar": 14,
        "sodium": 150
      }
    },
    {
      "id": "idH123",
      "name": "Product H",
      "createdAt": 1653304234181,
      "updatedAt": null,
      "nutritionFact": {
        "calories": 150,
        "totalFat": 4,
        "saturatedFat": 4,
        "protein": 7,
        "totalCarbohydrate": 20,
        "sugar": 18,
        "sodium": 60
      }
    },
    {
      "id": "idI123",
      "name": "Product I",
      "createdAt": 1653304234182,
      "updatedAt": null,
      "nutritionFact": {
        "calories": 140,
        "totalFat": 3,
        "saturatedFat": 3,
        "protein": 1,
        "totalCarbohydrate": 25,
        "sugar": 14,
        "sodium": 150
      }
    }
  ]
}
```