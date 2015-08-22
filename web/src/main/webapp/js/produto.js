/**
 * Created by rafa on 27/07/2015.
 */
$(document).ready(pageProdutosReady);


var divNovoProduto = '.divNovoProduto';
var btnVerCamposNovoProduto = '#btnVerCamposNovoProduto';
var btnRegistoNovoProduto = "#btnRegistoNovoProduto";


///// FIELDS NOVO PRODUTO
var ddlCatInProd = '.ddlCatInProd';
//var ddlCategoriasInProdutos = ".dropdown-menu";
var ddlCategoriasInProdutos = ".dropdownAllCat";
var txtNomeProduto = 'txtNomeProduto';
var txtPrecoProduto = 'txtPrecoProduto';

var tableListAllProdutos = '.tableListAllProdutos';

var idcategoriaProdut = '0';

var messageSuccessInsertProduto = '.messageSuccessInsertProduto';
var messageErrorInsertProduto = '.messageErrorInsertProduto';
var btnConfirmDeleteProduto = '#btnConfirmDeleteProduto';

var modalConfirmDeleteProduto = ".modalConfirmDeleteProduto";
var labelDeleteProdutoInfo = '#labelDeleteProdutoInfo';

var idProdutoToChangeInDB = 0;


var comboboxListCategoriasInProdutos = '.comboboxListCategoriasInProdutos';


var tableListProdFinal;

////////////////////////////////////////////////////////
//			COMBOBOX SELECT VALUE


$(btnVerCamposNovoProduto).click(function () {
    $(divNovoProduto).show();
    getAllCategoriasInProdutos();


    $(ddlCategoriasInProdutos + ' li a').click(function () {
        var selText = $(this).text();
        $(this).parents('.btn-group').find('.dropdown-toggle').html(selText + ' <span class="caret"></span>');
        idcategoriaProdut = $(this).data("id");
        alert("ID : " + idcategoriaProdut)
    });


});


function pageProdutosReady() {

    $(comboboxListCategoriasInProdutos).combobox()

    getAllProdutos();

    $(tableListAllProdutos).DataTable();


    //$(".dropdownAllCat li a").click(function () {


    $(btnRegistoNovoProduto).click(function () {
        alert(" CLICK CLICK ");
        inserirProduto();


    });

    $(btnConfirmDeleteProduto).click(deleteProduto);


}


function getAllProdutos() {

    var soapMsg = '';
    soapMsg += '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://services.mcm.ipg.pt/">';
    soapMsg += '<soapenv:Header/>';
    soapMsg += '<soapenv:Body>';
    soapMsg += '<ser:getProdutosDeSync>';
    soapMsg += '<versao>0</versao>';
    soapMsg += '</ser:getProdutosDeSync>';
    soapMsg += '</soapenv:Body>';
    soapMsg += '</soapenv:Envelope>';

    var soapGetAllProd = soapMsg;//g_soapBuilder.getSimpleEnvelope(soapMsg);

    wsProdutos(soapGetAllProd, successGetAllProdutos, tweak.errorCallBack);


    $('.tableListAllProdutos td.edit a').click(function () {
        idProdutoToChangeInDB = $(this).data('id');

        showEditElementProduto($(this).data('nome'), $(this).data('precounit'), $(this).data('categoriaid'));

    });


    $('.tableListAllProdutos td.delete a').click(function () {
        idProdutoToChangeInDB = $(this).data('id');

        $(labelDeleteProdutoInfo).text('Produto : ' + $(this).data('nome'));
        $(modalConfirmDeleteProduto).modal('show');

        //showEditElementProduto($(this).data('nome'), $(this).data('precounit'), $(this).data('categoriaid'));
        //alert('DELETE :  ' + idProdutoToChangeInDB);


    });

}


function successGetAllProdutos(data, status, req) {
    var json = $.xml2json(data);
    var produtos = tweak.forceArray(json.Body.getProdutosDeSyncResponse.return.resGetProdutos);

    var htmlRow = '';

    produtos.forEach(function (element, index, array) {

        var idProd = element.id;
        var nomeProd = element.nome;
        var precoUnitProd = element.preco_unitario;
        var categoriaID = element.categoria;

        htmlRow += "<tr>";
        htmlRow += "<td title=\"" + precoUnitProd + "\" style=\"width: 200px;\">" + nomeProd + "</td>";
        htmlRow += '<td class="edit">      <a data-id="' + idProd + '" data-nome="' + nomeProd + '" data-precounit="' + precoUnitProd + '" data-categoriaid="' + categoriaID + '" class="btn btn-success btn-circle"><i class="fa fa-pencil"></i></a></td>';
        htmlRow += '<td class="delete">    <a data-id="' + idProd + '" data-nome="' + nomeProd + '" data-precounit="' + precoUnitProd + '" data-categoriaid="' + categoriaID + '" class="btn btn-danger btn-circle"><i class="fa fa-trash"></i></a></td>';
        htmlRow += "</tr>";

    });

    $(tableListAllProdutos).append(htmlRow);


}

function inserirProduto() {

    var camposValidos = validaCamposProduto(getVal("#" + txtNomeProduto), getVal("#" + txtPrecoProduto), idcategoriaProdut);


    if (camposValidos == true) {
        var soapMsg = '';

        if (idProdutoToChangeInDB != '0') {
            //     UPDATE
            soapMsg = '';
            soapMsg += '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://services.mcm.ipg.pt/">';
            soapMsg += '    <soapenv:Header/>';
            soapMsg += '<soapenv:Body>';
            soapMsg += '<ser:updateProduto>';
            soapMsg += '<req-update-produtp>';
            soapMsg += '<idProduto> ' + idProdutoToChangeInDB + ' </idProduto>';
            soapMsg += '<nome>' + getVal("#" + txtNomeProduto) + '</nome>';
            soapMsg += '<preco-unitario>' + getVal("#" + txtPrecoProduto) + '</preco-unitario>';
            soapMsg += '<categoria> ' + idcategoriaProdut + ' </categoria>';
            soapMsg += '</req-update-produtp>';
            soapMsg += '</ser:updateProduto>';
            soapMsg += '</soapenv:Body>';
            soapMsg += '</soapenv:Envelope>';


        } else {
            //    INSERT
            soapMsg = '';
            soapMsg += '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://services.mcm.ipg.pt/">';
            soapMsg += '<soapenv:Header/>';
            soapMsg += '<soapenv:Body>';
            soapMsg += '<ser:addProduto>';
            soapMsg += '<req-add-produto>';
            soapMsg += '<nome>' + getVal("#" + txtNomeProduto) + '</nome>';
            soapMsg += '<preco-unitario>' + getVal("#" + txtPrecoProduto) + '</preco-unitario>';
            soapMsg += '<categoria>' + idcategoriaProdut + '</categoria>';
            soapMsg += '</req-add-produto>';
            soapMsg += '</ser:addProduto>';
            soapMsg += '</soapenv:Body>';
            soapMsg += '</soapenv:Envelope>';
        }

        wsProdutos(soapMsg, successProduto, tweak.errorCallBack);

    }


}


function showEditElementProduto(nome, precoUnit, idCategoria) {

    $('#' + txtNomeProduto).val('');
    $('#' + txtPrecoProduto).val('');

    $('#' + txtNomeProduto).val(nome);
    $('#' + txtPrecoProduto).val(precoUnit);
    $(ddlCategoriasInProdutos).val(idCategoria);


    $(divNovoProduto).show();


}


function successProduto(data, status, req) {
    //LIMPAR && LOAD &&
    var json = $.xml2json(data);
    var retorno = json.Body.addProdutoResponse.return.retorno;

    var codeMessage = retorno.codigo;
    var message = retorno.mensagem;

    if (codeMessage == "1") {
        $(messageSuccessInsertProduto).text(message);
        $(messageSuccessInsertProduto).show()
        cleanProdutosFields()

    } else {
        $(messageErrorInsertProduto).text(message);
        $(messageErrorInsertProduto).show()


    }


}


function validaCamposProduto(nome, precoUnitario, categoria) {
    var isValid = true;
    if (categoria == 0 || categoria == '' || categoria == null || categoria == '') {
        $(ddlCatInProd).addClass('has-error');

        isValid = false;
    } else {
        $(ddlCatInProd).removeClass('has-error');
    }

    if (nome == '' || name == null) {
        $(classFTS(txtNomeProduto)).addClass('has-error');
        isValid = false;
    } else {
        $(classFTS(txtNomeProduto)).removeClass('has-error');
    }

    if (precoUnitario == '' || precoUnitario == null) {
        $(classFTS(txtPrecoProduto)).addClass('has-error');
        isValid = false;
    } else {
        $(classFTS(txtPrecoProduto)).removeClass('has-error');
    }


    return isValid;
}


function getAllCategoriasInProdutos() {

    // LIMPAR DDL
    $(ddlCategoriasInProdutos).empty();

    var soapMsg = '';
    soapMsg += '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://services.mcm.ipg.pt/"> <soapenv:Header/>';
    soapMsg += '<soapenv:Body>';
    soapMsg += '<ser:get-all-categorias/>';
    soapMsg += '</soapenv:Body>';
    soapMsg += '</soapenv:Envelope>';

    wsCategorias(soapMsg, successGetAllCategoriasInProdutos, tweak.errorCallBack)

}

function successGetAllCategoriasInProdutos(data, status, req) {
    var json = $.xml2json(data);
    var categorias = tweak.forceArray(json.Body.get_all_categorias_response.response.categorias.categoria);

    var htmlDLLCat = '';

    categorias.forEach(function (element, index, array) {
        var id = element.id;
        var nome = element.nome;
        var descript = element.descricao;

        //htmlDLLCat = "<li><a href=\"#\" >" + nome +"</a></li>";
        htmlDLLCat = '<li><a data-id="' + id + '" href="#" title=' + descript + '>' + nome + '</a></li>';
        $(ddlCategoriasInProdutos).append(htmlDLLCat);
    });

    var htmlAddNewCategoria = '';
    htmlAddNewCategoria += '<li class="divider"></li>';
    htmlAddNewCategoria += '<li><a href="../pages/categoria.html"><span class="glyphicon glyphicon-plus"></span> Nova </a></li>';

    $(ddlCategoriasInProdutos).append(htmlAddNewCategoria);
}


function deleteProduto() {
    var soapMsg = '';

    soapMsg += '<ser:deleteProduto>';
    soapMsg += '<id>' + idProdutoToChangeInDB + '</id>';
    soapMsg += '</ser:deleteProduto>';

    var soapDeleteProd = g_soapBuilder.getSimpleEnvelope(soapMsg);

    wsProdutos(soapDeleteProd, successDeleteProduto, tweak.errorCallBack);

}

function successDeleteProduto(data, status, req) {
    var json = $.xml2json(data);
    var retorno = json.Body.deleteProdutoResponse.return.retorno;

    var codeMessage = retorno.codigo;
    var message = retorno.mensagem;

    if (codeMessage == "1") {
        $(messageSuccessInsertProduto).text(message);
        $(messageSuccessInsertProduto).show()
        $(modalConfirmDeleteProduto).modal('hide');
        cleanProdutosFields()
    } else {
        $(messageErrorInsertProduto).text(message);
        $(messageErrorInsertProduto).show()
    }

}


function cleanProdutosFields() {
    getAllProdutos();
    idcategoriaProdut = 0;
    $("#" + txtNomeProduto).val('');
    $("#" + txtPrecoProduto).val('');

    $("#" + txtNomeProduto).removeClass('has-error');
    $("#" + txtPrecoProduto).removeClass('has-error');

    idProdutoToChangeInDB = 0;

    $(btnRegistoNovoProduto).text('Guardar Novo Produto');


}