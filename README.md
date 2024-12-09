
## API Reference


## APIs Franchise

```http
  GET /api/v1/franchises
```

| Parameter | Return Type                  | Description                |
| :-------- |:-----------------------------| :------------------------- |
| `none` | `Page<FranchiseResponseDTO>` | Return all franchises. |


```http
  GET /api/v1/franchises/{id}
```

| Parameter | Return Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `FranchiseResponseDTO` | Return a franchise with the specific **id** 

```http
  POST /api/v1/franchises
```

| Parameter                          | Return Type          | Description                                          |
|:-----------------------------------|:---------------------|:-----------------------------------------------------|
| `@RequestBody FranchiseRequestDTO` | `HttpStatus.CREATED` | Save a new franchises and return an HttpStatus Code. |

```http
  PUT /api/v1/franchises/{id}
```

| Parameter        | Return Type | Description                                                    |
|:-----------------|:-----------|:---------------------------------------------------------------|
| `id && ?newName` | `HttpStatus.OK` | Find a Franchise with the specific **id** and update its name. |

```http
  DELETE /api/v1/franchises/{id}
```

| Parameter | Return Type | Description                                             |
|:----------|:-----------|:--------------------------------------------------------|
| `id`      | `HttpStatus.OK` | Find a Franchise with the specific **id** to delete it. |

## APIs Branches

```http
  GET /api/v1/branches
```

| Parameter | Return Type                | Description                |
| :-------- |:---------------------------| :------------------------- |
| `none` | `Page<BrancheResponseDTO>` | Return all branches. |


```http
  GET /api/v1/branches/{id}
```

| Parameter | Return Type          | Description                       |
| :-------- |:---------------------| :-------------------------------- |
| `id`      | `BrancheResponseDTO` | Return a branche with the specific **id** 

```http
  POST /api/v1/branches
```

| Parameter                        | Return Type          | Description                                         |
|:---------------------------------|:---------------------|:----------------------------------------------------|
| `@RequestBody BrancheRequestDTO` | `HttpStatus.CREATED` | Save a new branche and return an HttpStatus Code.   |

```http
  PUT /api/v1/branches/{id}
```

| Parameter        | Return Type | Description                                                  |
|:-----------------|:-----------|:-------------------------------------------------------------|
| `id && ?newName` | `HttpStatus.OK` | Find a branche with the specific **id** and update its name. |

```http
  DELETE /api/v1/branches/{id}
```

| Parameter | Return Type | Description                                            |
|:----------|:-----------|:-------------------------------------------------------|
| `id`      | `HttpStatus.OK` | Find a branche with the specific **id** to delete it.  |

## APIs Products

```http
  GET /api/v1/products
```

| Parameter | Return Type                | Description                |
| :-------- |:---------------------------| :------------------------- |
| `none` | `Page<ProductResponseDTO>` | Return all products. |


```http
  GET /api/v1/products/{id}
```

| Parameter | Return Type          | Description                       |
| :-------- |:---------------------| :-------------------------------- |
| `id`      | `ProductResponseDTO` | Return a product with the specific **id** 

```http
  POST /api/v1/products
```

| Parameter                        | Return Type          | Description                                          |
|:---------------------------------|:---------------------|:-----------------------------------------------------|
| `@RequestBody ProductRequestDTO` | `HttpStatus.CREATED` | Save a new product and return an HttpStatus Code. |

```http
  PUT /api/v1/franchises/{id}
```

| Parameter                 | Return Type | Description                                                   |
|:--------------------------|:-----------|:--------------------------------------------------------------|
| `id && ?newStockQuantity` | `HttpStatus.OK` | Find a product with the specific **id** and update its stock. |

```http
  DELETE /api/v1/products/{id}
```

| Parameter | Return Type | Description                                             |
|:----------|:-----------|:--------------------------------------------------------|
| `id`      | `HttpStatus.OK` | Find a product with the specific **id** to delete it. |



## How to run this container

Construye la imagen de Docker y pruébala


```bash
  docker build -t prueba-accenture .
```

```bash
  docker run -p 8089:8089 prueba-accenture
```
