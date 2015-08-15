/**
 * Created by rafa on 01/08/2015.
 */


var base64Login ;
var returnResult ;

function getBasicAuthentication(loginName, loginPassword){
    var soapMsg = '';

    soapMsg += '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://services.mcm.ipg.pt/">';
    soapMsg += '<soapenv:Header/>';
    soapMsg += '<soapenv:Body>';
    soapMsg += '<ser:login/>';
    soapMsg += '</soapenv:Body>';
    soapMsg += '</soapenv:Envelope>';
    var soapBasicAuthentication = soapMsg;//g_soapBuilder.getSimpleEnvelope(soapMsg);


    //if (getCookie(cookieNameLogin64Base).length != 0){
    //    base64Login = getCookie(cookieNameLogin64Base);
    //}else{
    //    base64Login= btoa(loginName +":"+loginPassword);
    //
    //}


    base64Login= btoa(loginName +":"+loginPassword);

    $.ajax
    ({
        type: "POST",
        url: wsURLAuthentication,
        contentType: "text/xml",
        dataType: 'xml',
        async: false,
        headers: {
            "Authorization": "Basic " + base64Login
        },
        data: soapBasicAuthentication,
        success: successAuthentication,
        error: function (){
            alert('authentication error!');
        }
    });

    return returnResult ;
}

function successAuthentication(data, status, req){

    var json = $.xml2json(data);
    var result = json.Body.loginResponse.return.retorno;

    if (result.codigo == '1'){
        setCookie(cookieNameLogin64Base, base64Login, 3);
        returnResult = result.codigo;
    }

    console.log('! Authentication Success !')

}