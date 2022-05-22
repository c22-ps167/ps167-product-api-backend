# BACKEND RESTFUL API

## API DOCUMENTATION

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
        "createdAt": "2022-05-22T21:53:14.112+00:00",
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
        "createdAt": "2022-05-22T21:53:14.112+00:00",
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
        "name": "Product C",
        "createdAt": "2022-05-22T21:49:37.675+00:00",
        "updatedAt": "2022-05-22T21:50:25.086+00:00",
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