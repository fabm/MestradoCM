/**
 * Created by rafa on 01/09/2015.
 */
$(document).ready(encomendaReady);


var dropdownCategFiltroProdutos = '.dropdownCategFiltroProdutos';
var tableListProdutosAEncomendar = '.tableListProdutosAEncomendar';
var btnGuardarEncomenda = "#btnGuardarEncomenda";
var txtDtaEntregaEncomenda = '#txtDtaEntregaEncomenda';

var messageSuccessInsertEncomenda = '.messageSuccessInsertEncomenda';
var messageErrorInsertEncomenda  = '.messageErrorInsertEncomenda';

function encomendaReady() {
    //getCategInProdutos();

    getProdutosEncomenda();

    $(btnGuardarEncomenda).click(function () {
        // VER QUAIS AS CHECKBOX CHECK
        var chkboxSelected = $(tableListProdutosAEncomendar + ' tbody td.chkboxProdAEncomendar :checked');

        //SELECCIONADAS
        var sProdEnc = '';

        $.each(chkboxSelected, function (i, element) {
            var idProd = $(element).data('idprod');
            var idCat = $(element).data('idcateg');

            var quantidade = $('#txtquant_' + idProd).val();

            var msg = 'Prod : ' + idProd + ' |CAT: ' + idCat + ' |Quant: ' + quantidade;

            alert(msg);


            //    soap encomendas

            sProdEnc += '<produtoEncomendadoList>';
            sProdEnc += '<idProduto>'+idProd+'</idProduto>';
            sProdEnc += '<quantidade>'+quantidade+'</quantidade>';
            sProdEnc += '</produtoEncomendadoList>';


        });



        var idCliente = '1'
        var dtaEntrega = $(txtDtaEntregaEncomenda).val();
        novaEncomenda(idCliente, dtaEntrega, sProdEnc)


    });


}

////////////////////////////////////////////////
//  NOVA ENCOMENDA
////////////////////////////////////////////////
function novaEncomenda(idCliente, dtaEntrega, produtoEncomendadoList) {
    var soapMsg = '';
    //soapMsg += '<ser:addEncomendas>';
    //soapMsg += '<addEncomendas>';
    //soapMsg += '    <encomendas>';
    //soapMsg += '        <idCliente>' + idCliente + '</idCliente>';
    //soapMsg += '        <dataEntrega>' + dtaEntrega + '</dataEntrega>';
    //
    //soapMsg +=              produtoEncomendadoList
    //
    //soapMsg += '</encomendas>';
    //soapMsg += '</addEncomendas>';
    //soapMsg += '</ser:addEncomendas>';

    soapMsg += '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://services.mcm.ipg.pt/">';
    soapMsg += '<soapenv:Header/>';
    soapMsg += '<soapenv:Body>';
    soapMsg += '    <ser:addEncomendas>';
    soapMsg += '        <addEncomendas>';
    soapMsg += '            <encomendas>';
    soapMsg += '            <idCliente>'+idCliente+'</idCliente>';
    soapMsg += '            <dataEntrega>'+dtaEntrega+'</dataEntrega>'; //2015-09-11T12:08:56.235-07:00
    soapMsg +=              produtoEncomendadoList
    soapMsg += '            </encomendas>';
    soapMsg += '        </addEncomendas>';
    soapMsg += '    </ser:addEncomendas>';
    soapMsg += '</soapenv:Body>';
    soapMsg += '</soapenv:Envelope>';

    //var sNovaEncomenda =  g_soapBuilder.getSimpleEnvelope(soapMsg);
    //
    console.log(soapMsg);

    wsEncomendas(soapMsg, successNovaEncomenda, tweak.errorCallBack);


}

function successNovaEncomenda(data, status, req){
    var json = $.xml2json(data);
    var retorno = json.Body.updateCategoriaResponse.return.retorno;

    var codeMessage = retorno.code;
    var message = retorno.mensagem;

    if (codeMessage != "1") {
        $(messageSuccessInsertEncomenda).text(message);
        $(messageSuccessInsertEncomenda).show()
        cleanCategoriesFields();
        getAllCategories();
    } else {
        $(messageErrorInsertEncomenda).text(message);
        $(messageErrorInsertEncomenda).show()
    }

}

////////////////////////////////////////////////
//  PRODUTOS
////////////////////////////////////////////////
function getProdutosEncomenda() {
    var soapMsg = '';

    //soapMsg += '<ser:getProdutosCategorias>';
    //soapMsg += '<categoria>'+ idFilter +'</categoria>';
    //soapMsg += '</ser:getProdutosCategorias>';
    //soapMsg += '<ser:getProdutosCategorias>';
    //soapMsg += '<categoria>1</categoria>';
    //soapMsg += '</ser:getProdutosCategorias>';

    soapMsg += '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://services.mcm.ipg.pt/">';
    soapMsg += '<soapenv:Header/>';
    soapMsg += '<soapenv:Body>';
    soapMsg += '<ser:getProdutosDeSync>';
    soapMsg += '<versao>0</versao>';
    soapMsg += '</ser:getProdutosDeSync>';
    soapMsg += '</soapenv:Body>';
    soapMsg += '</soapenv:Envelope>';


    //var soapProdEnc =  g_soapBuilder.getSimpleEnvelope(soapMsg);

    wsProdutos(soapMsg, succ222, tweak.errorCallBack);


    //COLOCAR OS CLICK NA CHECKBOX

}

function succ222(data, status, req) {
    var json = $.xml2json(data);
    var filterProdutos = tweak.forceArray(json.Body.getProdutosDeSyncResponse.return.resGetProdutos);

    $(tableListProdutosAEncomendar + ' tbody').empty();

    var row = '';

    filterProdutos.forEach(function (element, index, array) {

        var idProd = element.id;
        var nomeProduto = element.nome;
        var descricaoProduto = element.descricao;
        var nomeCategoria = element.categoria;
        var idCategoria = element.categoria;
        var precoProd = element.preco_unitario;

        var chkboxID = 'chkbox_' + idProd;
        var txtQuantID = 'txtquant_' + idProd;
        var txtDtaEntregID = 'txtdtaentreg_' + idProd;

        var title = descricaoProduto + changeLineInTitle + precoProd;


        row += '<tr>';
        row += '    <td class="chkboxProdAEncomendar" > <input type="checkbox" id=' + chkboxID + ' data-idprod=' + idProd + ' data-idcateg=' + idCategoria + '> </td>';
        row += '    <td title=' + title + '>' + nomeProduto + '</td>';
        row += '    <td>' + nomeCategoria + '</td>';
        row += '    <td> <input type="text" id=' + txtQuantID + '>Unid. </td>';
        //row +='    <td> <input class="dtapickEncomenda" type="text" id='+txtDtaEntregID+' data-provide="datepicker" >  </td>';
        row += '</tr>';


    });

    $(tableListProdutosAEncomendar).append(row);

    //CONVERTER TABELA EM DATATABLE


    $('.dtapickEncomenda').daterangepicker({
        singleDatePicker: true,
        locale: {
            format: 'YYYY-MM-DD'
        }
    });

    dataTablePortuguese(tableListProdutosAEncomendar)
    //$(tableListProdutosAEncomendar).DataTable();


}


////////////////////////////////////////////////
//  CATEGORIAS IN PRODUTOS
////////////////////////////////////////////////

function getCategInProdutos() {
    $(dropdownCategFiltroProdutos).empty();

    var soapMsg = '';
    soapMsg += '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://services.mcm.ipg.pt/"> <soapenv:Header/>';
    soapMsg += '<soapenv:Body>';
    soapMsg += '<ser:get-all-categorias/>';
    soapMsg += '</soapenv:Body>';
    soapMsg += '</soapenv:Envelope>';

    wsCategorias(soapMsg, successGetCategInProdutos, tweak.errorCallBack)
}

function successGetCategInProdutos(data, status, req) {
    var json = $.xml2json(data);
    var categorias = tweak.forceArray(json.Body.get_all_categorias_response.response.categorias.categoria);

    var htmlDLLCat = '';

    $(dropdownCategFiltroProdutos).append('<option value="" selected> Seleccionar uma Categoria ! </option>');


    categorias.forEach(function (element, index, array) {
        var id = element.id;
        var nome = element.nome;
        var descript = element.descricao;

        htmlDLLCat = '<option value="' + id + '"> ' + nome + ' </option>';
        $(dropdownCategFiltroProdutos).append(htmlDLLCat);
    });


}

