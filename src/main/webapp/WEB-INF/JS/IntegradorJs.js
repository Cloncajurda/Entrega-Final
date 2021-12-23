function Calcular()
{
var seleccion=document.getElementById("Categoria");
var selectionValue=seleccion.selectedIndex;
var cantidad=document.getElementById("Cantidad").value;
var total=0;
var precio=200;
var DescEst= 0.2;
var DescTrai= 0.5;
var DescJun= 0.85;
var totalField = document.getElementById("totalField");

switch(selectionValue)
{
    case 0:
        total=precio*cantidad*DescEst;
        break;
    case 1:
        total=precio*cantidad*DescTrai;
        break;
    case 2:
        total=precio*cantidad*DescJun;
        break;
}

totalField.value = total;


}