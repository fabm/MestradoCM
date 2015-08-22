/**
 * Created by rafa on 28/06/2015.
 */
$(document).ready(pageReady);

var mensagemGuardaNovaCategoria = 'Guardar Nova Categoria';
var mensagemAtualizarCategoria = 'Atualizar Categoria';

var divNovaCategoria = ".divNovaCategoria";
var divSuccessMessageInsert = ".messageSuccessInsertCategorie";
var divErrorMessageInsert = ".messageErrorInsertCategorie";

var btnVerCamposNovaCategoria = "#btnVerCamposNovaCategoria";
var btnSaveCategoria = 'btnRegistoNovaCategoria';
var txtNomeCategoria = 'txtNomeCategoria';

var txtDescricaoCategoria = 'txtDescricaoCategoria';

var tableListAllCategories = '.tableListAllCategories';

var idCategoriaToChangeInDB = '';

var divModalConfirmDeleteCategorie = '.modalConfirmDeleteCategorie';

var labelDeleteCategoriaInfo = "#labelDeleteCategoriaInfo";


$(btnVerCamposNovaCategoria).click(function () {
    $(divNovaCategoria).show();
    $('#' + txtNomeCategoria).val('');
    $('#' + txtDescricaoCategoria).val('');
    $('#' + btnSaveCategoria).text(mensagemGuardaNovaCategoria);

});

$('#' + btnSaveCategoria).click(function () {
    alert('BOTAO CATEGORIA CARREGADO');
    var text = $(this).text();

    if (text == mensagemAtualizarCategoria) {
        updateCategoria();
    } else {
        inserirCategoria();
    }


});


function pageReady() {
    getAllCategories();
    $(tableListAllCategories).DataTable();

}

////////////////////////////////////////////
//      INSERIR CATEGORIA
function inserirCategoria() {
    var camposvalidos = validaCamposCategoria(getVal('#' + txtNomeCategoria), getVal('#' + txtDescricaoCategoria));

    if (camposvalidos === true) {
        $(classFTS(txtNomeCategoria)).removeClass('has-error');
        $('.txtDescricaoCategoria').removeClass('has-error');
        $(tableListAllCategories + ' tbody').empty();


        var nameCateg = $('#' + txtNomeCategoria).val();


        var soapMsg = '';
        soapMsg += '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://services.mcm.ipg.pt/">';
        soapMsg += '<soapenv:Header/>';
        soapMsg += '<soapenv:Body>';
        soapMsg += '<ser:add-categoria>';
        soapMsg += '<request>';
        soapMsg += '<nome>' + nameCateg + '</nome>';
        if ($('#' + txtDescricaoCategoria).val())
            soapMsg += '<descricao>' + $('#' + txtDescricaoCategoria).val() + '</descricao>';

        soapMsg += '</request>';
        soapMsg += '</ser:add-categoria>';
        soapMsg += '</soapenv:Body>';
        soapMsg += '</soapenv:Envelope>';

        wsCategorias(soapMsg, successInserirCategoria, tweak.errorCallBack);

        alert('CONSUMIR WEBSERVICE DAS CATEGORIAS !!!');


    }

}

function successInserirCategoria(data, status, req) {
    var json = $.xml2json(data);
    var retorno = json.Body.add_categoriaResponse.response.retorno;

    var codeMessage = retorno.code;
    var message = retorno.mensagem;

    if (codeMessage != "1") {
        $(divSuccessMessageInsert).text(message);
        $(divSuccessMessageInsert).show()
        cleanCategoriesFields();
        getAllCategories();
    } else {
        $(divErrorMessageInsert).text(message);
        $(divErrorMessageInsert).show();
    }

}


////////////////////////////////////////////
//      UPDATE CATEGORIA
function updateCategoria() {
    var camposvalidos = validaCamposCategoria(getVal('#' + txtNomeCategoria), getVal('#' + txtDescricaoCategoria));

    if (camposvalidos === true) {

        $(classFTS(txtNomeCategoria)).removeClass('has-error');
        $('.txtDescricaoCategoria').removeClass('has-error');
        $(tableListAllCategories + ' tbody').empty();


        var soapMsg = '';
        soapMsg += '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://services.mcm.ipg.pt/">';
        soapMsg += '<soapenv:Header/>';
        soapMsg += '<soapenv:Body>';
        soapMsg += '<ser:updateCategoria>';
        soapMsg += '<req-update-categoria>';
        soapMsg += '<idCategoria>'+ idCategoriaToChangeInDB +'</idCategoria>';
        soapMsg += '<nome>'+ getVal('#'+txtNomeCategoria) +'</nome>';
        soapMsg += '<descricao>'+ getVal('#' + txtDescricaoCategoria) +'</descricao>';
        soapMsg += '</req-update-categoria>';
        soapMsg += '</ser:updateCategoria>';
        soapMsg += '</soapenv:Body>';
        soapMsg += '</soapenv:Envelope>';

        wsCategorias(soapMsg, successUpdatecategoria, tweak.errorCallBack);

    }
}

function successUpdatecategoria(data, status, req){
    var json = $.xml2json(data);
    var retorno = json.Body.updateCategoriaResponse.return.retorno;

    var codeMessage = retorno.code;
    var message = retorno.mensagem;

    if (codeMessage != "1") {
        $(divSuccessMessageInsert).text(message);
        $(divSuccessMessageInsert).show()
        cleanCategoriesFields();
        getAllCategories();
    } else {
        $(divErrorMessageInsert).text(message);
        $(divErrorMessageInsert).show()
    }
}


/////////////////////////////////////////////
//      ~VALIDA CAMPOS (se estao preenchidos)
function validaCamposCategoria(nome, description) {
    var isValid = true;

    if (nome == "" || nome == null || nome == undefined) {
        $(classFTS(txtNomeCategoria)).addClass('has-error');
        isValid = false
    } else {
        $(classFTS(txtNomeCategoria)).removeClass('has-error');

    }

    if (description == "") {
        $('.txtDescricaoCategoria').addClass('has-error');
        isValid = false
    } else {
        $('.txtDescricaoCategoria').removeClass('has-error');

    }

    return isValid;
}


////////////////////////////////////////////////////////////////
//      TODAS AS CATEGORIAS
function getAllCategories() {


    var soapMsg = '';
    soapMsg += '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://services.mcm.ipg.pt/"> <soapenv:Header/>';
    soapMsg += '<soapenv:Body>';
    soapMsg += '<ser:get-all-categorias/>';
    soapMsg += '</soapenv:Body>';
    soapMsg += '</soapenv:Envelope>';


    wsCategorias(soapMsg, successGetAllCategorias, tweak.errorCallBack)


    $('.tableListAllCategories td.edit a').click(function () {
        //alert('EDIT\n' + $(this).data('id') + '\n' + $(this).data('nome') + '\n' + $(this).data('descricao'));
        idCategoriaToChangeInDB = $(this).data('id');

        showEditElements($(this).data('nome'), $(this).data('descricao'));

    });

    $('.tableListAllCategories td.delete a').click(function () {
        //alert('DELETE\n' + $(this).data('id') + '\n' + $(this).data('nome') + '\n' + $(this).data('descricao'));

        idCategoriaToChangeInDB = $(this).data('id');
        $(labelDeleteCategoriaInfo).text('Nome : ' + $(this).data('nome')  );

        $(divModalConfirmDeleteCategorie).modal('show');

    });


    //************ CRIAR AS FUNCÇÕES PARA PREENCHER E APAGAR OS DADOS DA TABELA.
}

function successGetAllCategorias(data, status, req) {
    var json = $.xml2json(data);
    var categorias = tweak.forceArray(json.Body.get_all_categorias_response.response.categorias.categoria);

    var htmlRown = '';

    categorias.forEach(function (element, index, array) {
        var id = element.id;
        var nome = element.nome;
        var descript = element.descricao;

        htmlRown += "<tr>";
        htmlRown += "<td title=\"" + descript + "\" style=\"width: 200px;\">" + nome + "</td>";
        htmlRown += '<td class="edit"> <a data-id="' + id + '" data-nome="' + nome + '" data-descricao="' + descript + '" class="btn btn-success  btn-circle"><i class="fa fa-pencil"></i></a></td>';
        htmlRown += '<td class="delete"> <a data-id="' + id + '" data-nome="' + nome + '" data-descricao="' + descript + '" class="btn btn-danger btn-circle"><i class="fa fa-trash"></i></a></td>';
        htmlRown += "</tr>";


        console.log(element.nome);


    });

    $(tableListAllCategories).append(htmlRown);
}


function showEditElements(name, description) {
    $("#" + txtNomeCategoria).val();
    $("#" + txtDescricaoCategoria).val();

    $("#" + txtNomeCategoria).val(name);
    $("#" + txtDescricaoCategoria).val(description);

    $('#' + btnSaveCategoria).text(mensagemAtualizarCategoria);
    $(divNovaCategoria).show();
}

function cleanCategoriesFields() {
    $("#" + txtNomeCategoria).val('');
    $("#" + txtDescricaoCategoria).val('');
    $(classFTS(txtNomeCategoria)).removeClass('has-error');
    $('.txtDescricaoCategoria').removeClass('has-error');

    idCategoriaToChangeInDB = '';

    $('#btnRegistoNovaCategoria').text('Guardar Nova Categoria');

}