/**
 * Created by rafa on 03/09/2015.
 */

$(document).ready(verEncomendasReady);


var tableListMinhasEncomendas = '.tableListMinhasEncomendas';


function verEncomendasReady() {
    listMinhasEncomendas();
}


/////////////////////////////////
//      VER MINHAS ENCOMENDAS

function listMinhasEncomendas() {

    var soapMsg = '';

    soapMsg += '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://services.mcm.ipg.pt/">';
    soapMsg += '<soapenv:Header/>';
    soapMsg += '<soapenv:Body>';
    soapMsg += '<ser:getMinhasEncomendas>';
    soapMsg += '<idSync>0</idSync>';
    soapMsg += '</ser:getMinhasEncomendas>';
    soapMsg += '</soapenv:Body>';
    soapMsg += '</soapenv:Envelope>';

    wsEncomendas(soapMsg, successListMEncomendas, tweak.errorCallBack);

}

function successListMEncomendas(data, status, req) {
    var json = $.xml2json(data);
    var filterProdutos = tweak.forceArray(json.Body.getMinhasEncomendasResponse.return.minhasEncomendasList);

    var row = '';

    filterProdutos.forEach(function (element, index, array) {
        var idEncomenda = element.id;
        var precoProduto = element.preco;
        var dataPrevistaOriginal = element.dataPrevista;


        row += '<tr>';
        row += '    <td>' + idEncomenda + '</td>';
        row += '    <td>' + precoProduto + '</td>';

        if (dataPrevistaOriginal == undefined) {
            row += '    <td> ---- </td>';
        } else {
            row += '    <td>' + dataPrevistaOriginal.substring(0, 10) + '</td>';
        }

        row += '</tr>';

    });

    $(tableListMinhasEncomendas).append(row);

    dataTablePortuguese(tableListMinhasEncomendas);

}