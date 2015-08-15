/**
 * Created by rafa on 28/07/2015.
 */
$(document).ready(readyTestBasicAuthentication);

var username = "francisco";
var password = "francisco2";
var ftsBtnRegistoNovoUtilizador = '#btnRegistoNovoUtilizador';


function readyTestBasicAuthentication(){

    //$(ftsBtnRegistoNovoUtilizador).click(testBasicAuthentication);
}


function testBasicAuthentication(){
    var soapMsg = '';
    soapMsg += '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://services.mcm.ipg.pt/">';
    soapMsg += '<soapenv:Header/>';
    soapMsg += '<soapenv:Body>';
    soapMsg += '<ser:login/>';
    soapMsg += '</soapenv:Body>';
    soapMsg += '</soapenv:Envelope>';


    var base64Login = btoa(username + ":" + password);
    console.log('----> HEADER BASE 64 : ' + base64Login);


    $.ajax
    ({
        type: "POST",
        url: wsURLAuthentication,
        contentType: "text/xml",
        dataType: 'xml',
        headers: {
            "Authorization": "Basic " + base64Login
        },
        data: soapMsg,
        success: successAuthenticationTeste,
        error: function (){
            alert('authentication error!');
        }
    });
}

function successAuthenticationTeste(data, status, req){
    console.log('! Authentication Success !')

}