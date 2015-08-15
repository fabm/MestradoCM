/**
 * Created by rafa on 27/07/2015.
 */
$(document).ready(pageProdutosReady);


var ftsTxtProdutoNome = '';
var ftsTxtProdutoPrecoUnitario = '';
var ftsDLLCategoriaInProduto = '';

var divNovoProduto = '.divNovoProduto';
var btnVerCamposNovoProduto = '#btnVerCamposNovoProduto';




////////////////////////////////////////////////////////
//			COMBOBOX SELECT VALUE
$(".dropdown-menu li a").click(function(){
    var selText = $(this).text();
    $(this).parents('.btn-group').find('.dropdown-toggle').html(selText+' <span class="caret"></span>');
});



$(btnVerCamposNovoProduto).click(function(){
    $(divNovoProduto).show();

});




function pageProdutosReady(){
    getAllCategoriasInProdutos();
}


function validaCamposProduto(nome, precoUnitario, categoria) {
    var isValid = true;

    if (nome == '' || nome.equals('') || name == null)
        isValid = false;

    if (precoUnitario == '' || precoUnitario == null || precoUnitario.equals(''))
        isValid = false;

    if (categoria == '' || categoria == null || categoria.equals(''))
        isValid = false;

    return isValid;
}



function getAllCategoriasInProdutos(){

    var soapMsg = '';
    soapMsg += '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://services.mcm.ipg.pt/"> <soapenv:Header/>';
    soapMsg += '<soapenv:Body>';
    soapMsg += '<ser:get-all-categorias/>';
    soapMsg += '</soapenv:Body>';
    soapMsg += '</soapenv:Envelope>';

    wsCategorias(soapMsg, successGetAllCategoriasInProdutos, tweak.errorCallBack)

}

function successGetAllCategoriasInProdutos(data, status, req){
    var json = $.xml2json(data);
    var categorias = tweak.forceArray(json.Body.get_all_categorias_response.response.categorias.categoria);

    var htmlDLLCat = '';

    categorias.forEach(function (element, index, array) {
        var id = element.id;
        var nome = element.nome;
        var descript = element.descricao;

        htmlDLLCat = '<li><a data-id="'+id+'" href="#" title='+descript+'>' + nome +'</a></li>';
        $(".dropdown-menu").append(htmlDLLCat);
    });

    var htmlAddNewCategoria = '';
    htmlAddNewCategoria += '<li class="divider"></li>';
    htmlAddNewCategoria += '<li><a href="../pages/categoria.html"><span class="glyphicon glyphicon-star"></span> Nova</a></li>';


}
