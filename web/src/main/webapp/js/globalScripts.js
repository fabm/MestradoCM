/**
 * Created by rafa on 10/05/2015.
 */

//DADOS DA PESSOA COM LOGIN REALIZADo
var loginName = '';
var loginPassword = '';

//WEB SERVICEs
var wsURLAuthentication = "/services/Authentication";
var wsURLEncomendas = '/services/rest/encomenda/minhas/0';
var wsURLCategorias =   '/services/Categoria';
var wsURLUtilizador = 'http://rafaxps:8080/services/Utilizador';//'services/Utilizador';
var wsURLLocalidade = 'http://rafa_msi:8080/web/pages/';

var basicSiteURL = '';

//////////////////////////////////////////////
// WEB AUTHENTICATION - HEADER ATHENTICATION
// USERNAME : PASSWORD
var base64Login = '';

var g_soapBuilder;


//////////////////////////////////////////////
// COOKIES
var cookieNameLogin64Base = "dataLogin64Base";
var cookieNameUsernameLogin = "dataUsernameLogin"





$(function () {

    g_soapBuilder = new SoapBuilder();
    tweak = new Tweak();

});


function idFTS (fts){
    return '#' + fts;
}

function classFTS (fts){
    return '.' + fts;
}

function getVal(fts){
    return $(fts).val();
}


/////////////////////////////////////////////////
//      WS UTILIZADORES CALL
function wsUtilizadores (soapMsg, success, error){
    wsCall(wsURLUtilizador, soapMsg, success, error);
}

/////////////////////////////////////////////////
//      WS CATEGORIAS CALL
function wsCategorias(soapMsg, success, error){
    wsCall(wsURLCategorias, soapMsg, success, error);
}

/////////////////////////////////////////////////
//      WS LOCALIDADES CALL
function wsLocalidades (soapMsg, success, error){
    wsCall(wsURLLocalidade, soapMsg, success, error);
}


/////////////////////////////////////////////////
//      WS CALL
function wsCall(url, data, success, error ){
    //console.log(new WebServiceCall(url, data, success, error));

    base64Login = btoa("francisco:francisco");

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
    soapMsg += "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservices.testaut.ii.pt/\">\n";
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
function goToPage(page){

    $(location).attr('href', basicSiteURL + page);
}

/////////////////////////////////////////////////
/////////////////////////////////////////////////
//      SET / GET COOKIES
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
    }
    return "";
}
