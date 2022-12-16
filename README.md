# TP JAVA
## School Management

**Alumno: Alejandro Foresi - Legajo: 47790**

>Este trabajo práctico está enfocado en un sistema de gestión escolar, donde el objetivo principal del mismo es facilitar a los alumnos su historial académico y la inscripción a un curso durante cada año. El alumno podrá revisar las notas subidas por los profesores, tanto las de cada cuatrimestre, como las notas finales. En este sistema los alumnos serán los protagonistas pero también serán participantes los profesores (encargados de subir las notas) y un usuario administrador, el cual cumplirá como principal función gestionar las materias de cada curso, y la inscripción de los alumnos ingresantes para que puedan entrar en este sistema.
>Algunas reglas de negocio importantes a tener en cuenta son:<br />
**RN1:** El registro de los alumnos en el sistema será responsabilidad del administrador, así como también su baja lógica o física.<br />
**RN2:** El id del alumno, como también su legajo serán generados e informados automáticamente por el sistema luego de añadir a dicho alumno.<br />
**RN3:** Un alumno podrá inscribirse a un ÚNICO curso por año, sí y solo sí, no haya desaprobado más de dos materias en el curso anterior.<br />
**RN4:** Una inscripción de un alumno a un curso podrá eliminarse/editarse sólo habiendo pasado no más de dos meses de ser generada.<br />

![WhatsApp Image 2022-12-16 at 08 38 47](https://user-images.githubusercontent.com/103225088/208100861-cfe03e32-f259-4061-bd30-1c96b09a3103.jpeg)


**REQUERIMIENTOS REGULARIDAD**

>ABMC simple: Inscribir alumno, Dar de baja alumno, Editar datos alumno<br />

>ABMC dependiente: Inscripcion a nuevo curso, Dar de baja inscripción a curso, Cambiar modalidad de curso<br />

>CU NO-ABMC: Inscripción a nuevo curso (RN3,RN4)

>Listado simple: 
  - El alumno quiere los cursos a los que se ha inscripto, sistema muestra curso, modalidad, fecha de inscripcion, y (si le es posible por RN4) las opciones para editar     y/o eliminar.<br />
  - El administrador puede ver los alumnos en la escuela y en qué curso se encuentran<br />

>Listado complejo: 
  - El alumno desea ver su historial de calificaciones en cada año en los cursos a los que se ha inscripto, o ver si se cargaron las notas de algún cuatrimestre.             Selecciona un curso y el sistema muestra todas las materias de ese curso con sus respectivas notas.<br />
