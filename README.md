# Madang-App
## 📌 Authentication Service
### 1. Register User Endpoint

| Metrics   | Value                 |
|-----------|-----------------------|
| Method    | `POST`                |
| URL       | `api/v1/auth/user/register` |
| Auth Required | No                    |
 

#### Description 
This endpoint is to register a new user to Madang-App

#### Request Body : 
````json
{
  "fullname" : "string",
  "gender" : "string",
  "username" : "string",
  "email" : "string",
  "password" : "string",
  "role" : [
    "string"
  ],
  "image" : "image_file"
}
````


**Response Body** :

**✅ 201 Created**
````json
{
  "data": {
    "fullname": "string",
    "gender": "string",
    "username": "string",
    "email": "string",
    "role": [
      {
        "id": "UUID",
        "name": "enum"
      }
    ],
    "imageId" : "UUID",
    "imageLink" : "string"
  },
  "message": "success"
}
````
**❌ 400 Bad Request**
````json
{
  "data" : null,
  "message" : "Password not matched"
}
````
### 2. Login User Endpoint

| Metrics   | Value                |
|-----------|----------------------|
| Method    | `POST`               |
| URL       | `api/v1/auth/user/login` |
| Auth Required | No                   |

#### Description
This endpoint is to existing user login to the Madang-App

**Request Body** :
````json
{
  "email" : "string",
  "password" : "string"
}
````

**Response Body** :

**✅ 201 Created**
````json
{
  "data" : {
    "accessToken" : "JWT Token",
    "refreshToken" : "JWT Token",
    "userId" : "UUID",
    "userStatus" : "ACTIVE"
  },
  "message" : "login success"
}
````

**❌ 400 Bad Request**
````json
{
  "data" : null,
  "message" : "email or password is invalid"
}
````
---
## 📌 Restaurant Service
### 1. Create Restaurant

| Metrics   | Value                      |
|-----------|----------------------------|
| Method    | `POST`                     |
| URL       | `api/v1/restaurant/create` |
| Auth Required | Yes                        |

* Description : Create a new Restaurant
* URL : api/v1/restaurant
* Auth Required : Yes

**Request Body** :

````json
{
  "name": "string",
  "description": "string",
  "address": "string",
  "category": "enum",
  "image" : "image_file"
}
````

**Response Body** :
* **✅ 200 OK**

````json
{
  "data": {
    "restaurantId": "UUID",
    "name": "string",
    "description": "string",
    "address": "string",
    "imageId": "UUID",
    "imageLink" : "string",
    "userId" : "UUID"
  },
  "message": "success"
}
````

* **❌ 404 Bad Request**
````json
{
  "data" : null,
  "message" : "image already used"
}
````

### `GET` All Restaurant

| Metrics   | Value                                  |
|-----------|----------------------------------------|
| Method    | `GET`                                  |
| URL       | `api/v1/restaurant/get-all-restaurant` |
| Auth Required | No                                     |

**Response Body : ✅ 200 OK**

````json
{
  "data": [
    {
      "restaurantId": "UUID",
      "name": "string",
      "description": "string",
      "address": "string",
      "category": "enum",
      "rating": "float",
      "imageId" : "UUID",
      "imageLink": "string",
      "userId": "UUID"
    },
    {
      "restaurantId": "UUID",
      "name": "string",
      "description": "string",
      "address": "string",
      "category": "enum",
      "rating": "float",
      "imageId" : "UUID",
      "imageLink": "string",
      "userId": "UUID"
    }
  ],
  "message": "success"
}
````
### `GET` Get Restaurant by Id

| Metrics   | Value                                         |
|-----------|-----------------------------------------------|
| Method    | `POST`                                        |
| URL       | `api/v1/restaurant/get-restaurant-by-id/{id}` |
| Auth Required | Yes                                           |

**Response Body** :
* **✅ 200 OK**

````json
{
  "data": {
    "restaurantId" : "UUID",
    "name" : "string",
    "description" : "string",
    "address" : "string",
    "category" : "enum",
    "restaurantRating": "float",
    "imageId" : "UUID",
    "imageLink": "string",
    "userId": "UUID"
  }
}
````
* **❌ 400 Bad Request**
````json
{
  "data" : null,
  "message" : "restaurantId not found"
}
````
### `GET` Get Restaurant by UserId

| Metrics   | Value                                                 |
|-----------|-------------------------------------------------------|
| Method    | `GET`                                                 |
| URL       | `api/v1/restaurant/get-restaurant-by-userId/{userId}` |
| Auth Required | Yes                                                   |
**Response Body** :
* **✅ 200 OK**

````json
{
  "data": [
    {
      "restaurantId" : "UUID",
      "name" : "string",
      "description" : "string",
      "address" : "string",
      "category" : "enum",
      "restaurantRating": "float",
      "imageId" : "UUID",
      "imageLink": "string",
      "userId": "UUID"
    },
    {
      "restaurantId" : "UUID",
      "name" : "string",
      "description" : "string",
      "address" : "string",
      "category" : "enum",
      "restaurantRating": "float",
      "imageId" : "UUID",
      "imageLink": "string",
      "userId": "UUID"
    }
  ]
}
````
* **❌ 400 Bad Request**
````json
{
  "data" : null,
  "message" : "userId not found"
}
````
### `GET` Get Restaurant by Search

| Metrics   | Value                      |
|-----------|----------------------------|
| Method    | `GET`                      |
| URL       | `api/v1/restaurant/search` |
| Auth Required | Yes                        |
**Response Body** :
* **✅ 200 OK**

````json
{
  "data": [
    {
      "restaurantId": "UUID",
      "name": "string",
      "description": "string",
      "address": "string",
      "category": "enum",
      "restaurantRating": "float",
      "imageId": "UUID",
      "imageLink": "string",
      "userId": "UUID"
    },
    {
      "restaurantId": "UUID",
      "name": "string",
      "description": "string",
      "address": "string",
      "category": "enum",
      "restaurantRating": "float",
      "imageId": "UUID",
      "imageLink": "string",
      "userId": "UUID"
    }
  ],
  "message": "success"
}
````
* **❌ 400 Bad Request**
````json
{
  "data" : null,
  "message" : "Restaurant not found"
}
````

### `PUT` Update Address Restaurant

| Metrics   | Value                              |
|-----------|------------------------------------|
| Method    | `PUT`                              |
| URL       | `api/v1/restaurant/update-address` |
| Auth Required | Yes                                |

**Request Body**
```json
{
  "address" : "string"
}
```

**Response Body** :
* **✅ 200 OK**

````json
{
  "data" : {
    "address" : "string"
  },
  "message" : "success"
}
````
* **❌ 400 Bad Request**
````json
{
  "data" : null,
  "message" : "userId not found"
}
````

---
## Product Service
### `POST` Create Product

* Description : Create a new product
* URL : api/v1/product
* Auth Required : Yes

**Request Body** :
````json
{
  "restaurantId" : "UUID",
  "productName" : "string",
  "price" : "double",
  "category" : "enum",
  "image" : "image_file"
}
````
**Response Body** :
* **✅ 200 OK**
````json
{
  "data" : {
    "productId" : "UUID",
    "productName" : "string",
    "price" : "double",
    "category" : "enum",
    "imageId" : "UUID",
    "imageLink" : "string",
    "restaurantName" : "string",
    "restaurantId" : "UUID",
    "userId" : "UUID"
  },
  "message" : "success"
}
````
* **❌ 400 Bad Request**
````json
{
  "data" : null,
  "message" : "restaurantId not found"
}
````

### `GET` Get All Product

| Metrics   | Value                            |
|-----------|----------------------------------|
| Method    | `POST`                           |
| URL       | `api/v1/product/get-all-product` |
| Auth Required | No                               |

**Response Body : ✅ 200 OK**
````json
{
  "data" : [
    {
      "productId" : "UUID",
      "productName" : "string",
      "price" : "double",
      "category" : "enum",
      "restaurantId" : "UUID",
      "imageId" : "UUID",
      "imageLink" : "string"
    },
    {
      "productId" : "UUID",
      "productName" : "string",
      "price" : "double",
      "category" : "enum",
      "productImageLink" : "string",
      "restaurantId" : "UUID"
    }
  ],
  "message" : "success"
}
````

### `GET` Get Product by Id
* Description : Get detail of product
* URL : api/v1/product/{productId}
* Auth Required : Yes

**Response Body** :
* **✅ 200 OK**
````json
{
  "data" : {
    "productId" : "UUID",
    "productName" : "string",
    "price" : "double",
    "category" : "enum",
    "productRating" : "float",
    "imageId" : "UUID",
    "imageLink" : "string",
    "restaurantId" : "UUID"
  },
  "message" : "success"
}
````
* **❌ 400 Bad Request**
````json
{
  "data" : null,
  "message" : "productId not found"
}
````

### `PUT` Update Product Price
* Description : Update product price
* URL : api/v1/product/{productId}
* Auth Required : Yes

**Request Body** :
````json
{
  "price" : "double"
}
````
**Response Body** :
* **✅ 200 OK**
````json
{
  "data" : {
    "productName" : "string",
    "price" : "double"
  },
  "message" : "success"
}
````
* **❌ 400 Bad Request**
````json
{
  "data" : null,
  "message" : "productId not found"
}
````
---
## Image Service
### `POST` Create Image
* Description : Insert image to database
* URL : api/v1/images
* Auth Required : Yes

**Request Body** : 
````json
{
  "imageSize" : "enum",
  "category" : "enum",
  "image" : "image_file"
}
````

**Response Body : ✅ 200 OK**
````json
{
  "data" : {
    "imageId" : "UUID",
    "imageLink" : "string",
    "imageSize" : "enum",
    "category" : "enum"
  },
  "message" : "success"
}
````

### `GET` Get Image by imageId
* Description : Get detail image
* URL : api/v1/restaurant/images/{imageId}
* Auth Required : Yes

**Response Body** :
* **✅ 200 OK**
````json
{
  "data" : [
    {
      "imageId" : "UUID",
      "imageLink" : "string",
      "size" : "small",
      "category" : "product_image"
    },
    {
      "imageId" : "UUID",
      "imageLink" : "string",
      "size" : "medium",
      "category" : "restaurant_image"
    },
    {
      "imageId" : "UUID",
      "imageLink" : "string",
      "size" : "large",
      "category" : "restaurant_image"
    }
  ],
  "message" : "success"
}
````

* **❌ 400 Bad Request**
````json
{
  "data" : null,
  "message" : "imageId not found"
}
````
---
//TODO CREATE ORDER DOCS
````
---
