# Madang-App
## Authentication
### `POST` Register

* Description : Register to Application
* URL : api/v1/auth/user/register
* Auth Required : No

**Request Body** : 
````json
{
  "name" : "string",
  "email" : "string",
  "password" : "string",
  "confirmPassword" : "string"
}
````

**Response Body** :

* **<span style="color: green;">200 OK</span>**
````json
{
  "data" : {
    "name" : "string",
    "email" : "string",
    "userStatus" : "enum"
  },
  "message" : "Register success"
}
````
* **<span style="color: red;">400 Bad Request</span>**
````json
{
  "data" : null,
  "message" : "Password not matched"
}
````
---
### `POST` Login
* Description : Register to Application
* URL : api/v1/auth/user/register
* Auth Required : No

**Request Body** :
````json
{
  "email" : "string",
  "password" : "string"
}
````

**Response Body** :
* **<span style="color: green;">200 OK</span>**
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

* **<span style="color: red;">400 Bad Request</span>**
````json
{
  "data" : null,
  "message" : "email or password is invalid"
}
````
