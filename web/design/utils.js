// Perimite digitar apenas números.
function numericNoSignal($this, e) {
	var tecla;
	// Internet Explorer
	if (document.all)
		tecla = event.keyCode;
	// Outros Browsers
	else
		tecla = e.which;

	if ((tecla > 47 && tecla < 58) || tecla == 8 || tecla == 0) {
		return true;
	} else {
		return false;
	}
}

// Aplica somente a máscara, não valida se o valor informado é valido.
function formatDate(campo){
	if(campo.value.length == 2){
		campo.value += "/";
	}else if(campo.value.length == 5){
		campo.value += "/";
	}else if(campo.value.length > 10){
		return false;
	}
}

//Aplica somente a máscara, não valida se o valor informado é valido.
function verificaCampo(campo){
	if(campo.value.length != 10){
		campo.value = "";
	}
}

// Abrir nova pagina html
function openNewPage(form){
	form.target = "_blank";
}
