Feature: Administrar posts de la aplicacion
  Como publicador
  Necesito administrar mis posts
  Para mantenerlos actualizados

  Scenario: Obtener post por id
    Given que "Juan" se conecta al api
    When el obtiene el post 1
    Then el deberia de ver los datos del post

  Scenario: Crear un post
    Given que "Juan" se conecta al api
    When el crea un post
      | title            | body                           | userId |
      | Capa Screen Rest | Este el body de la publicacion | 1      |
    Then el deberia de ver el post creado