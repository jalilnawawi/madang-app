# Madang-App
## Authentication
### `POST` Register User

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

* **✅ 200 OK**
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
* **❌ 400 Bad Request**
````json
{
  "data" : null,
  "message" : "Password not matched"
}
````
---
### `POST` Login User
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
* **✅ 200 OK**
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

* **❌ 400 Bad Request**
````json
{
  "data" : null,
  "message" : "email or password is invalid"
}
````
