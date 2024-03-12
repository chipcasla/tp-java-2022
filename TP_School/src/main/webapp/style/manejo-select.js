function cargaModalidad(miLista) {
    // Objeto de curso con modalidad
    /*var lista = {
      Primer: ["A","B","C"],
      Segundo: ["A","B","C"],
      Tercer: ["Ciencias Naturales", "Ciencias Sociales y Humanidades"],
      Cuarto: ["Ciencias Naturales", "Ciencias Sociales y Humanidades"],
      Quinto: ["Ciencias Naturales", "Ciencias Sociales y Humanidades"]
    }
    */
    
    var lista = {
      Primer: miLista[1],
      Segundo: miLista[2],
      Tercer: miLista[3],
      Cuarto: miLista[4],
      Quinto: miLista[5],
      Sexto: miLista[6],
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