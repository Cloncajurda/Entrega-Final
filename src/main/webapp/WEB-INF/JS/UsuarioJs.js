function Resumir()
{
var seleccionNombre=document.getElementById("validationNombre").value;
var seleccionApellido=document.getElementById("validationApellido").value;
var seleccionDni=document.getElementById("validationDni").value;
var seleccionCorreo=document.getElementById("validationCorreo").value;

var totalField = document.getElementById("totalField");
totalField.textContent = seleccionNombre +" "+ seleccionApellido +" "+ seleccionDni +" "+ seleccionCorreo;


}