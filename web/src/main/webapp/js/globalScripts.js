/**
 * Created by rafa on 10/05/2015.
 */

//DADOS DA PESSOA COM LOGIN REALIZADo
var loginName = '';
var loginPassword = '';


var changeLineInTitle = ' &#013;';

//WEB SERVICEs
var wsURLAuthentication = "/services/Authentication";
var wsURLEncomendas = '/services/Encomenda';
var wsURLCategorias = '/services/Categoria';
var wsURLProdutos = '/services/Produto';
var wsURLUtilizador = '/services/Utilizador';
var wsURLCliente = '/services/Cliente';


//var wsURLUtilizador = 'http://rafaxps:8080/services/Utilizador';//'services/Utilizador';
//var wsURLLocalidade = 'http://rafa_msi:8080/web/pages/';

var basicSiteURL = '';

//////////////////////////////////////////////
// WEB AUTHENTICATION - HEADER ATHENTICATION
// USERNAME : PASSWORD
var base64Login = '';

var g_soapBuilder;


//////////////////////////////////////////////
// COOKIES
var cookieNameLogin64Base = "dataLogin64Base";
var cookieNameUsernameLogin = "dataUsernameLogin";


$(function () {

    g_soapBuilder = new SoapBuilder();
    tweak = new Tweak();

});


function idFTS(fts) {
    return '#' + fts;
}

function classFTS(fts) {
    return '.' + fts;
}

function getVal(fts) {
    return $(fts).val();
}


/////////////////////////////////////////////////
//      WS UTILIZADORES CALL
function wsUtilizador(soapMsg, success, error) {
    wsCall(wsURLUtilizador, soapMsg, success, error);
}

/////////////////////////////////////////////////
//      WS CLIENTE CALL
function wsCliente(soapMsg, success, error) {
    wsCall(wsURLCliente, soapMsg, success, error);
}

/////////////////////////////////////////////////
//      WS CATEGORIAS CALL
function wsCategorias(soapMsg, success, error) {
    wsCall(wsURLCategorias, soapMsg, success, error);
}

/////////////////////////////////////////////////
//      WS PRODUTOS CALL
function wsProdutos(soapMsg, success, error) {
    wsCall(wsURLProdutos, soapMsg, success, error);
}

/////////////////////////////////////////////////
//      WS LOCALIDADES CALL
function wsLocalidades(soapMsg, success, error) {
    wsCall(wsURLLocalidade, soapMsg, success, error);
}

/////////////////////////////////////////////////
//      WS ENCOMENDA CALL
function wsEncomendas(soapMsg, success, error) {
    wsCall(wsURLEncomendas, soapMsg, success, error);
}


/////////////////////////////////////////////////
//      WS CALL
function wsCall(url, data, success, error) {
    //console.log(new WebServiceCall(url, data, success, error));

    //base64Login = btoa("bruno:bruno");
    //base64Login = btoa("francisco:francisco");
    base64Login = getCookie('dataLogin64Base');

    $.ajax({
        type: "POST",
        contentType: "text/xml",
        dataType: "xml",
        async: false,
        url: url,
        headers: {
            "Authorization": "Basic " + base64Login
        },
        data: data,
        success: success,
        error: error
    });
}

function WebServiceCall(url, soapMsg, success, error) {
    this.url = url;
    this.soapMsg = soapMsg;
    this.successFunction = success;
    this.errorFunction = error;
}

/////////////////////////////////////////////////
/////////////////////////////////////////////////
//      SOAP BUILDER
function SoapBuilder() {
}

SoapBuilder.prototype.getSimpleEnvelope = function (bodyContent) {
    var soapMsg = "";
    soapMsg += "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://services.mcm.ipg.pt/\">\n";
    soapMsg += "\t<soapenv:Header/>\n";
    soapMsg += "\t<soapenv:Body>\n";
    soapMsg += bodyContent;
    soapMsg += "\t</soapenv:Body>\n";
    soapMsg += "</soapenv:Envelope>\n";
    //console.log("SOAP MSG:" + soapMsg);
    return soapMsg;
};


/////////////////////////////////////////////////
/////////////////////////////////////////////////
//      TWEAK

function Tweak() {
}

Tweak.prototype.forceArray = function (objects) {
    var list = objects;
    if (list !== null) {
        if (list !== undefined) {
            //var firstId = -1;

            if (list.hasOwnProperty('length') === false) {
                list = [];
                list.push(objects);
            }
        }
        else
            list = [];
    }
    else
        list = [];
    return list;
};

Tweak.prototype.errorCallBack = function (data, status, req) {
    console.log("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    console.log(" .response (ERROR)");
    console.log(data.responseText);
    console.log(status);
    console.log(req);
    console.log("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
};


/////////////////////////////////////////////////
/////////////////////////////////////////////////
//      URL TO OTHER PAGE
function goToPage(page) {

    $(location).attr('href', basicSiteURL + page);
}

/////////////////////////////////////////////////
/////////////////////////////////////////////////
//      SET / GET COOKIES
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1);
        if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
    }
    return "";
}


/////////////////////////////////////////////////
/////////////////////////////////////////////////
//      CONVERT TO DATATABLE

function dataTablePortuguese(htmlElement) {

    $(htmlElement).DataTable({
        language: {
            "sProcessing": "A processar...",
            "sLengthMenu": "Mostrar _MENU_ registos",
            "sZeroRecords": "Não foram encontrados resultados",
            "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registos",
            "sInfoEmpty": "Mostrando de 0 até 0 de 0 registos",
            "sInfoFiltered": "(filtrado de _MAX_ registos no total)",
            "sInfoPostFix": "",
            "sSearch": "Procurar:",
            "sUrl": "",
            "oPaginate": {
                "sFirst": "Primeiro",
                "sPrevious": "Anterior",
                "sNext": "Seguinte",
                "sLast": "Último"
            }
        }
    });

}


function dataTableSemPesquisa(htmlElement) {
    $(htmlElement).DataTable({
        bFilter: false,
        bInfo: false
    });
}