function cargaModalidad() {
    // Objeto de curso con modalidad
    var lista = {
      Primer: ["B\u00E1sica"],
      Segundo: ["B\u00E1sica"],
      Tercer: ["Ciencias Naturales", "Ciencias Sociales y Humanidades"],
      Cuarto: ["Ciencias Naturales", "Ciencias Sociales y Humanidades"],
      Quinto: ["Ciencias Naturales", "Ciencias Sociales y Humanidades"]
    }
    
    var curso = document.getElementById('cur')
    var modalidades = document.getElementById('mod')
    var cursoSeleccionado = curso.value
    
    // Se limpian las modalidades
    modalidades.innerHTML = '<option selected disabled value="">Seleccione modalidad...</option>'
    
    if(cursoSeleccionado !== ''){
      // Se seleccionan las modalidades y se ordenan
      cursoSeleccionado = lista[cursoSeleccionado]
      cursoSeleccionado.sort()
    
      // Insertamos las modalidades
      cursoSeleccionado.forEach(function(mod){
        let opcion = document.createElement('option')
        opcion.value = mod
        opcion.text = mod
        modalidades.add(opcion)
      });
    }
    
  }