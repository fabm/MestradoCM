/**
 * Created by rafa on 10/05/2015.
 */
$(document).ready(novoUtilizadorReady);

var ftsBtnRegistoNovoUtilizador = '#btnRegistoNovoUtilizador';


//ELEMENTOS REGISTO
var ftsTxtRegistoNome = 'txtRegistoNome';
var ftsTxtRegistoMorada = 'txtRegistoMorada';
var ftsTxtRegistoNporta = 'txtRegistoNporta';
var ftsTxtRegistoContribuinte = 'txtRegistoContribuinte';
var ftsTxtRegistoDataNascimento = 'txtRegistoDataNascimento';
var ftsTxtRegistoContacto = 'txtRegistoContacto';
var ftsTxtRegistoLocalidade = 'txtRegistoLocalidade';
var ftsTxtRegistoUsername = 'txtRegistoUsername';
var ftsTxtRegistoPassword = 'txtRegistoPassword';
var ftsTxtRegistoEmail = 'txtRegistoEmail';


function novoUtilizadorReady() {


    $('.dtapickDataNascimento').daterangepicker({
        singleDatePicker: true,
        locale: {
            format: 'YYYY-MM-DD'
        }
    });

    //format: 'DD/MM/YYYY'
    //startDate: moment().subtract(6, 'days')
    //$('.txtRegistoDataNascimento').datepicker();

    //click add new
    $(ftsBtnRegistoNovoUtilizador).click(insertClienteUtilizador);
}


///////////////////////////////////////////////////
///     REGISTAR NOVO UTILIZADOR
///////////////////////////////////////////////////
function insertClienteUtilizador() {


    var camposValidos = validaCampos(
        getVal('#' + ftsTxtRegistoNome),
        getVal('#' + ftsTxtRegistoMorada), getVal('#' + ftsTxtRegistoNporta),
        getVal('#' + ftsTxtRegistoContribuinte), getVal('#' + ftsTxtRegistoDataNascimento),
        getVal('#' + ftsTxtRegistoContacto), getVal('#' + ftsTxtRegistoLocalidade),
        getVal('#' + ftsTxtRegistoEmail),
        getVal('#' + ftsTxtRegistoUsername), getVal('#' + ftsTxtRegistoPassword));

    if (camposValidos == true) {


        //FORMATO DA DATA COMPLETO : 2000-01-25T00:00:00         --------- 2001-07-04T12:08:56.235-07:00

        var dataComplemento = 'T00:00:00';
        var dataOriginal = getVal('#' + ftsTxtRegistoDataNascimento);
        var dataFinal = dataOriginal + dataComplemento;
        var soapMsg = '';
        //soapMsg += '<ser:createUserCliente>';
        //soapMsg += '<cliente>';
        //soapMsg += '    <contribuinte>' + getVal('#' + ftsTxtRegistoContribuinte) + '</contribuinte>';
        //soapMsg += '    <nome> ' + getVal('#' + ftsTxtRegistoNome) + '</nome>';
        //soapMsg += '    <morada>' + getVal('#' + ftsTxtRegistoMorada) + '</morada>';
        //soapMsg += '    <porta>' + getVal('#' + ftsTxtRegistoNporta) + '</porta>';
        //soapMsg += '    <dataNascimento>' + dataFinal + '</dataNascimento>';
        //soapMsg += '    <email>' + getVal('#' + ftsTxtRegistoNome) + '</email>';
        //soapMsg += '    <contacto>' + getVal('#' + ftsTxtRegistoContacto) + '</contacto>';
        //soapMsg += '    <localidade>' + getVal('#' + ftsTxtRegistoLocalidade) + '</localidade>';
        //soapMsg += '    <login>' + getVal('#' + ftsTxtRegistoUsername) + '</login>';
        //soapMsg += '    <password>' + getVal('#' + ftsTxtRegistoPassword) + '</password>';
        //soapMsg += '</cliente>';
        //soapMsg += '</ser:createUserCliente>';


        soapMsg += '<ser:addClienteUtilizador>';
        soapMsg += '<request>';
        soapMsg += '    <contribuinte>'+ getVal('#' + ftsTxtRegistoContribuinte) +'</contribuinte>';
        soapMsg += '    <nome>'+ getVal('#' + ftsTxtRegistoNome) +'</nome>';
        soapMsg += '    <morada>'+ getVal('#' + ftsTxtRegistoMorada) +'</morada>';
        soapMsg += '    <porta>'+ getVal('#' + ftsTxtRegistoNporta) +'</porta>';
        soapMsg += '    <dataNascimento>'+ dataFinal +'</dataNascimento>';
        soapMsg += '    <email>'+ getVal('#' + ftsTxtRegistoNome) +'</email>';
        soapMsg += '    <contacto>'+ getVal('#' + ftsTxtRegistoContacto) +'</contacto>';
        soapMsg += '    <localidade>'+ getVal('#' + ftsTxtRegistoLocalidade) +'</localidade>';
        soapMsg += '    <email>' + getVal('#' + ftsTxtRegistoEmail)+ '</email>    ';
        soapMsg += '    <login>'+ getVal('#' + ftsTxtRegistoUsername) +'</login>';
        soapMsg += '    <password>'+ getVal('#' + ftsTxtRegistoPassword) +'</password>';
        soapMsg += '</request>';
        soapMsg += '</ser:addClienteUtilizador>';


        var soapInsertClientAndUtilizador = g_soapBuilder.getSimpleEnvelope(soapMsg);

        //wsUtilizador(soapInsertClientAndUtilizador, successInsUtilizadorAndCliente, tweak.errorCallBack);
        wsCliente(soapInsertClientAndUtilizador, successInsUtilizadorAndCliente, tweak.errorCallBack);


    }

}

function successInsUtilizadorAndCliente(data, status, req) {

    //  MENSAGEM QUE VEM DO WS (succ or error)

    var json = $.xml2json(data);
    var retorno = json.Body.addClienteUtilizadorResponse.return;

    var codeMessage = retorno.codigo;
    var message = retorno.mensagem;

    if (codeMessage == "1") {
        //$(messageSuccessInsertProduto).text(message);
        //$(messageSuccessInsertProduto).show()
        //$(modalConfirmDeleteProduto).modal('hide');
        //cleanProdutosFields()
        alert(message);
        clientCleanFields();
    } else {
        //$(messageErrorInsertProduto).text(message);
        //$(messageErrorInsertProduto).show()
        alert(message);


    }


}


///////////////////////////////////////////////////
///     validações
///////////////////////////////////////////////////
function validaCampos(nome, morada, nporta, nif, dataNascimento, contacto, localidade, email, username, password) {
    var isValid = true;

    if (nome === '') {
        $(classFTS(ftsTxtRegistoNome)).addClass('has-error');
        isValid = false;
    } else {
        $(classFTS(ftsTxtRegistoNome)).removeClass('has-error');
    }

    if (morada == '') {
        $(classFTS(ftsTxtRegistoMorada)).addClass('has-error');
        isValid = false;
    } else {
        $(classFTS(ftsTxtRegistoMorada)).removeClass('has-error');
    }

    if (nporta === '') {
        $(classFTS(ftsTxtRegistoNporta)).addClass('has-error');
        isValid = false;
    } else {
        $(classFTS(ftsTxtRegistoNporta)).removeClass('has-error');
    }

    if (nif === '') {
        $(classFTS(ftsTxtRegistoContribuinte)).addClass('has-error');
        isValid = false;
    } else {
        $(classFTS(ftsTxtRegistoContribuinte)).removeClass('has-error');
    }

    if (dataNascimento === '') {
        $(classFTS(ftsTxtRegistoDataNascimento)).addClass('has-error');
        isValid = false;
    } else {
        $(classFTS(ftsTxtRegistoDataNascimento)).removeClass('has-error');
    }

    if (contacto === '') {
        $(classFTS(ftsTxtRegistoContacto)).addClass('has-error');
        isValid = false;
    } else {
        $(classFTS(ftsTxtRegistoContacto)).removeClass('has-error');
    }

    if (localidade === '') {
        $(classFTS(ftsTxtRegistoLocalidade)).addClass('has-error');
        isValid = false;
    } else {
        $(classFTS(ftsTxtRegistoLocalidade)).removeClass('has-error');
    }

    if (email === '') {
        $(classFTS(ftsTxtRegistoEmail)).addClass('has-error');
        isValid = false;
    } else {
        $(classFTS(ftsTxtRegistoEmail)).removeClass('has-error');
    }

    if (username === '') {
        $(classFTS(ftsTxtRegistoUsername)).addClass('has-error');
        isValid = false;
    } else {
        $(classFTS(ftsTxtRegistoUsername)).removeClass('has-error');
    }

    if (password === '') {
        $(classFTS(ftsTxtRegistoPassword)).addClass('has-error');
        isValid = false;
    } else {
        $(classFTS(ftsTxtRegistoPassword)).removeClass('has-error');
    }


    return isValid;
}

///////////////////////////////////////////////////
///     CLEAN FIELDs
///////////////////////////////////////////////////
function clientCleanFields(){

    $('#' + ftsTxtRegistoNome).val('');
    $('#' + ftsTxtRegistoMorada).val('');
    $('#' + ftsTxtRegistoNporta).val('');
    $('#' + ftsTxtRegistoContribuinte).val('');
    $('#' + ftsTxtRegistoDataNascimento).val('');
    $('#' + ftsTxtRegistoContacto).val('');
    $('#' + ftsTxtRegistoLocalidade).val('');
    $('#' + ftsTxtRegistoEmail).val('');
    $('#' + ftsTxtRegistoUsername).val('');
    $('#' + ftsTxtRegistoPassword).val('');
}