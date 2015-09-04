/**
 * Created by rafa on 04/09/2015.
 */
$(document).ready(novoPadeiroReady);


var txtLoginPadeiro = 'txtLoginPadeiro';
var txtPasswordPadeiro = 'txtPasswordPadeiro';
var txtNomePadeiro = 'txtNomePadeiro';

var btnVerCamposNovoPadeiro = '#btnVerCamposNovoPadeiro';
var btnRegistoNovoPadeiro = '#btnRegistoNovoPadeiro';

var divNovoPadeiro = '.divNovoPadeiro';

function novoPadeiroReady() {


    $(btnVerCamposNovoPadeiro).click(function () {

        //ABRIR MODAL DOS CAMPOS
        cleanPadeiroFields();
        $(divNovoPadeiro).show();

    });

    $(btnRegistoNovoPadeiro).click(function(){
        novoPadeiro();
    });

}


function novoPadeiro() {


    var camposValidos = validaCamposNovoPadeiro(getVal('#' + txtLoginPadeiro), getVal('#' + txtPasswordPadeiro), getVal('#' + txtNomePadeiro))


    if (camposValidos === true) {
        var soapMsg = '';

        soapMsg += '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://services.mcm.ipg.pt/">';
        soapMsg += '<soapenv:Header/>';
        soapMsg += '<soapenv:Body>';
        soapMsg += '<ser:addUtilizadorPadeiro>';
        soapMsg += '<req-add-utilizador>';
        soapMsg += '<login>' + getVal('#' + txtLoginPadeiro) + '</login>';
        soapMsg += '<password>' + getVal('#' + txtPasswordPadeiro) + '</password>';
        soapMsg += '<nome>' + getVal('#' + txtNomePadeiro) + '</nome>';
        soapMsg += '</req-add-utilizador>';
        soapMsg += '</ser:addUtilizadorPadeiro>';
        soapMsg += '</soapenv:Body>';
        soapMsg += '</soapenv:Envelope>';

        wsUtilizador(soapMsg, succNovoPadeiro, tweak.errorCallBack );

    }

}


function succNovoPadeiro(data, status, req){

}


/////////////////////////////////////////////
//      ~VALIDA CAMPOS (se estao preenchidos)
function validaCamposNovoPadeiro(login, pass, nome) {
    var isValid = true;

    if (login == "" || login == null) {
        $(classFTS(txtLoginPadeiro)).addClass('has-error');
        isValid = false
    } else {
        $(classFTS(txtLoginPadeiro)).removeClass('has-error');

    }

    if (pass == "" || pass == null) {
        $('.' + txtPasswordPadeiro).addClass('has-error');
        isValid = false
    } else {
        $('.' + txtPasswordPadeiro).removeClass('has-error');

    }

    if (nome == "" || nome == null || nome == undefined) {
        $('.' + txtNomePadeiro).addClass('has-error');
        isValid = false
    } else {
        $('.' + txtNomePadeiro).removeClass('has-error');

    }


    return isValid;
}


function cleanPadeiroFields() {

    $('#' + txtLoginPadeiro).val('');
    $('#' + txtPasswordPadeiro).val('');
    $('#' + txtNomePadeiro).val('');

}