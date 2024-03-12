# TP JAVA
## School Management

**Alumno: Alejandro Foresi - Legajo: 47790**

>Este trabajo práctico está enfocado en un sistema de gestión escolar, donde el objetivo principal del mismo es facilitar a los alumnos su historial académico y la inscripción a un curso durante cada año. El alumno podrá revisar las notas de materias, tanto las de cada cuatrimestre, como las notas finales. En este sistema los alumnos serán los protagonistas pero también serán participante un usuario administrador, el cual cumplirá como principal función gestionar las materias de cada curso, la gestión de profesores y su asignación a materias, y la inscripción de los alumnos ingresantes para que puedan entrar en este sistema.<br />

>Algunas reglas de negocio importantes a tener en cuenta son:<br />
>>* **RN1:** El registro de los alumnos en el sistema será responsabilidad del administrador, así como también su baja lógica o física.<br />
>>* **RN2:** El id del alumno, como también su legajo serán generados e informados automáticamente por el sistema luego de añadir a dicho alumno.<br />
>>* **RN3:** Un alumno podrá inscribirse a un ÚNICO curso por año, sí y solo sí, no haya desaprobado más de dos materias en el curso anterior.<br />
>>* **RN4:** Una inscripción de un alumno a un curso podrá eliminarse/editarse sólo habiendo pasado no más de dos meses de ser generada.<br />

![Screenshot_20240312-123026_Drive](https://github.com/chipcasla/tp-java-2022/assets/103225088/90db9b73-ecbc-49c2-9b8f-42f5918c22ed)


|Requerimiento|Cantidad|Detalle/Listado de casos incluidos|
|:-|-:|:-|
|ABMC simple|4|1. Alumno 2. Profesor 3. Modalidad 4. Materia|
|ABMC dependiente|2|1. Inscripción depende de alumno y curso 2. Asignatura depende del curso, modalidad y materia |
|CU resumen|1|1. Inscripción a curso+modalidad |
|CU usuario|6|1. Ver inscripciones 2. Ver boletin online 3. Asignar profesor a una materia  4. Modificar materias de un curso 5. Agregar modalidad a un curso 6. Inscribir a alumno ingresante(y dar de alta en sistema)|
|Nivel de acceso|2|1. Alumno 2. Administrador(Admin)|
|Listado complejo|4|1. Lista de notas por curso 2. Lista de modalidades por curso 3. Lista de materias de un curso y modalidad 4. Lista de alumnos y curso en el que se encuentran|
|Requerimiento extra |1|Envío de correo confirmando inscripción|


Cuentas:
Usuario: alef@g.com
Clave: asd

Administrador: admin@g.com
Clave: asd

https://78e0-181-117-217-96.ngrok-free.app/TP_School/
