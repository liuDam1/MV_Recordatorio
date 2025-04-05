# MV_Recordatorio
# Índice
+ [Contecto de la aplicación](#contecto-de-la-aplicación)
  + [Problema del mundo real](#problema-del-mundo-real)
  + [Aplicaciones existentes](#aplicaciones-existentes)
  + [Mi aplicación](#mi-aplicación)
    + [Caso de uso](#caso-de-uso)
    + [Requisito Funcionales y No Funcionales](#requisito-funcionales-y-no-funcionales)
+ [Diseño](#diseño)
    + [GUI](#gui)
---
## Contecto de la aplicación
### Problema del mundo real
Los aplicaciones de hoy en dia por lo visto solo es anotar texto y marcarlo en el calendario como aviso. Pero no puede avisarte como un alrma o una notificación. 

Además los datos no comparten la misma en diferentes dispositivos.

### Aplicaciones existentes
+ Evernote: Complejo, con funciones innecesarias para usuarios básicos.
+ Google Keep: Sencillo pero con organización limitada.
+ Notion: Potente pero con curva de aprendizaje alta.
+ Apple Notes: Solo para ecosistema Apple.

### Mi aplicación
+ **Simple y fácil de usar:** Interfaz simple y intuitiva para que los usuarios puedan crear y gestionar recordatorios de manera rápida y sencilla.
+ **Multiplataforma:** Android, iOS, Web.
+ **Notificaciones:** Avisos de recordatorio por notificación y alarmas dependiendo del tiempo, dia, mes y año. A cerca de la fecha establecida los 3 dias anteriores se le avisara hasta que se cumpla la fecha. Puede ser periodicos o una vez.
+ **Usuario:** Deberá registrarse para poder usar la aplicación. Así se podrá guardar los recordatorios y acceder a ellos desde cualquier dispositivo de la misma cuenta.

#### Caso de uso
+ **Registro de usuario:** El usuario se registra con email y contraseña para crear una cuenta.
+ **Inicio de sesión:** El usuario inicia sesión en la aplicación con sus credenciales.
+ **Crear recordatorio:** El usuario puede crear un nuevo recordatorio indicando título, descripción, fecha y hora. Puede ser periódico o único.
+ **Editar recordatorio:** El usuario puede modificar la información de un recordatorio existente.
+ **Eliminar recordatorio:** El usuario puede borrar un recordatorio que ya no necesita.
+ **Recibir notificaciones:** El sistema envía notificaciones y/o alarmas configuradas (ej: 3 días antes, 1 hora antes).
+ **Acceso a base de datos:** Los datos del usuario se guardan en una base de datos, permitiendo su acceso desde distintos dispositivos.
+ **Cerrar sesión:** El usuario puede cerrar su sesión en la aplicación.

#### Requisito Funcionales y No Funcionales
+ Funcionales:
    + Registro e inicio de sesión de usuario.
    + Crear, editar y eliminar recordatorios.
    + Recibir notificaciones y alarmas según la fecha y configuración del recordatorio.
    + Base de datos para almacenar los recordatorios y datos del usuario.

+ No funcionales:
    + Interfaz amigable y accesible.
    + Seguridad en el manejo de datos del usuario.
    + Disponibilidad multiplataforma.

## Diseño
### GUI
<img src="/Multimedia/InterfazUsuario.jpg">

