/**
 * Created by rafa on 03/09/2015.
 */

$(document).ready(verEncomendasReady);


var tableListMinhasEncomendas = '.tableListMinhasEncomendas';
var divMostraProdutosEncomendados = '.divMostraProdutosEncomendados';

var tableListProdEncomendados = '.tableListProdEncomendados';

var listProdutosEncomendados = [];
var listAllProdutos = [];


function verEncomendasReady() {
    getProdutosToMinhasEncomendas();
    listMinhasEncomendas();

}


/////////////////////////////////
//      VER MINHAS ENCOMENDAS

function listMinhasEncomendas() {

    var soapMsg = '';

    //soapMsg += '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://services.mcm.ipg.pt/">';
    //soapMsg += '<soapenv:Header/>';
    //soapMsg += '<soapenv:Body>';
    //soapMsg += '<ser:getMinhasEncomendas>';
    //soapMsg += '<idSync>0</idSync>';
    //soapMsg += '</ser:getMinhasEncomendas>';
    //soapMsg += '</soapenv:Body>';
    //soapMsg += '</soapenv:Envelope>';


    soapMsg += '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://services.mcm.ipg.pt/">';
    soapMsg += '<soapenv:Header/>';
    soapMsg += '<soapenv:Body>';
    soapMsg += '<ser:getMinhasEncomendasDetalhe>';
    soapMsg += '<idSync>0</idSync>';
    soapMsg += '</ser:getMinhasEncomendasDetalhe>';
    soapMsg += '</soapenv:Body>';
    soapMsg += '</soapenv:Envelope>';

    wsEncomendas(soapMsg, successListMEncomendas, tweak.errorCallBack);

    $(tableListProdEncomendados + ' tbody').empty();

    $('.rbClassSeeProdutosEnco').change(function () {
        alert($(this).val());
        var idEncFind = $(this).val();

        var produtosEncontrados = listProdutosEncomendados.filter(function (obj) {
            return obj.idencomenda == idEncFind;
        });

        var row = '';


        for (var i = 0; i < produtosEncontrados.length; i++) {
            var nomeproduto = produtosEncontrados[i].nomeproduto;
            var quantidade = produtosEncontrados[i].quantidade;
            var precoUnitario = produtosEncontrados[i].preco;

            row += '<tr>';
            row += '<td>' + nomeproduto + '</td>';
            row += '<td>' + quantidade + '</td>';
            row += '<td>' + precoUnitario + '</td>';
            row += '</tr>';

        }


        $(tableListProdEncomendados).append(row);
        //$(tableListProdEncomendados).html(row);

        //dataTableSemPesquisa(tableListProdEncomendados);

       $(tableListProdEncomendados).DataTable({
            bFilter: false,
            bInfo: false
        }).ajax.reload();                 //api().ajax.reload();             //ajax.reload();



        //dataTable().fnReloadAjax();




    });


    dataTablePortuguese(tableListMinhasEncomendas);


}

function successListMEncomendas(data, status, req) {
    var json = $.xml2json(data);
    var filterProdutos = tweak.forceArray(json.Body.getMinhasEncomendasDetalheResponse.return.listaEncomendasDetalheXmls);

    var row = '';

    $(tableListMinhasEncomendas + ' tbody').empty();

    filterProdutos.forEach(function (element, index, array) {
        var idEncomenda = element.id;
        var dataCriacao = element.dataCriacao;
        var dataEntrega = element.dataEntrega


        var valorTotalEncomenda = '';

        console.log('para aqui');

        var groupProdutosEncomendados = tweak.forceArray(element.produtosEncomendados);


        if (groupProdutosEncomendados.length !== 0) {

            groupProdutosEncomendados.forEach(function (element, index, array) {
                var idProduto = element.idProduto;
                var quantidade = element.quantidade;
                var preco = element.preco;

                var valor = quantidade * preco;
                valorTotalEncomenda = +valorTotalEncomenda + +valor;


                //NOME PRODUTO
                var findNomeProduto = listAllProdutos.filter(function (obj) {
                    if (obj.idproduto == idProduto) {
                        return obj.nomeproduto;
                    }
                });


                var nomeEncontrado = findNomeProduto[0].nomeproduto;

                var list = {idencomenda: idEncomenda, nomeproduto: nomeEncontrado, quantidade: quantidade, preco: preco}
                console.log(list);
                listProdutosEncomendados.push(list);


            });

        }


        row += '<tr>';
        if (dataEntrega == undefined) {
            row += '    <td> ---- </td>';
        } else {
            row += '    <td>' + dataEntrega.substring(0, 10) + '</td>';
        }

        row += '    <td>' + valorTotalEncomenda + '</td>';
        row += '    <td> <input class="rbClassSeeProdutosEnco" type="radio" name="rbSeeProdutosEncomGroup" id="" value=' + idEncomenda + ' data-idEncomenda=' + idEncomenda + '></td>';
        row += '</tr>';

    });

    $(tableListMinhasEncomendas).append(row);

    //dataTablePortuguese(tableListMinhasEncomendas);

}


function getProdutosToMinhasEncomendas() {
    var soapMsg = '';
    soapMsg += '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://services.mcm.ipg.pt/">';
    soapMsg += '<soapenv:Header/>';
    soapMsg += '<soapenv:Body>';
    soapMsg += '<ser:getProdutosDeSync>';
    soapMsg += '<versao>0</versao>';
    soapMsg += '</ser:getProdutosDeSync>';
    soapMsg += '</soapenv:Body>';
    soapMsg += '</soapenv:Envelope>';


    wsProdutos(soapMsg, successGetProdutosToMinhasEncomendas, tweak.errorCallBack);


}


function successGetProdutosToMinhasEncomendas(data, status, req) {
    var json = $.xml2json(data);
    var produtos = tweak.forceArray(json.Body.getProdutosDeSyncResponse.return.resGetProdutos);

    produtos.forEach(function (element, index, array) {

        var idProd = element.id;
        var nomeProd = element.nome;
        var precoUnitProd = element.preco_unitario;
        var categoriaID = element.categoria;

        var listProduto = {idproduto: idProd, nomeproduto: nomeProd};

        listAllProdutos.push(listProduto);


    });


}


