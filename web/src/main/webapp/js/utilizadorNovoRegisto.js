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

function novoUtilizadorReady (){

    //click add new
$(ftsBtnRegistoNovoUtilizador).click(insertUtilizador);
}




///////////////////////////////////////////////////
///     REGISTAR NOVO UTILIZADOR
///////////////////////////////////////////////////
function insertUtilizador(){


    var camposValidos =  validaCampos(
        getVal('#' + ftsTxtRegistoNome),
        getVal('#' + ftsTxtRegistoMorada), getVal('#' + ftsTxtRegistoNporta),
        getVal('#' + ftsTxtRegistoContribuinte), getVal('#' + ftsTxtRegistoDataNascimento),
        getVal('#' + ftsTxtRegistoContacto), getVal('#' + ftsTxtRegistoLocalidade),
        getVal('#' + ftsTxtRegistoUsername), getVal('#' + ftsTxtRegistoPassword) );

    if (camposValidos == true){
        var soapMsg = '';
        soapMsg += '<ser:createUserCliente>';
        soapMsg += '<cliente>';
        soapMsg += '    <contribuinte>'+ getVal('#' + ftsTxtRegistoContribuinte) +'</contribuinte>';
        soapMsg += '    <nome> '+ getVal('#' + ftsTxtRegistoNome) + '</nome>';
        soapMsg += '    <morada>'+ getVal('#' + ftsTxtRegistoMorada) +'</morada>';
        soapMsg += '    <porta>'+ getVal('#' + ftsTxtRegistoNporta) +'</porta>';
        soapMsg += '    <dataNascimento>'+ getVal('#' + ftsTxtRegistoDataNascimento) +'</dataNascimento>';
        soapMsg += '    <email>'+ getVal('#' + ftsTxtRegistoNome)  +'</email>';
        soapMsg += '    <contacto>'+ getVal('#' + ftsTxtRegistoContacto) +'</contacto>';
        soapMsg += '    <localidade>'+ getVal('#' + ftsTxtRegistoLocalidade) +'</localidade>';
        soapMsg += '    <login>'+ getVal('#' + ftsTxtRegistoUsername) +'</login>';
        soapMsg += '    <password>'+ getVal('#' + ftsTxtRegistoPassword)  +'</password>';
        soapMsg += '</cliente>';
        soapMsg += '</ser:createUserCliente>';


        var soapInsertClientAndUtilizador = g_soapBuilder.getSimpleEnvelope(soapMsg);

        wsUtilizador(soapInsertClientAndUtilizador, successInsUtilizadorAndCliente, tweak.errorCallBack);



    }

}

function successInsUtilizadorAndCliente(data, status, req){

    //  MENSAGEM QUE VEM DO WS (succ or error)
    var json = $.xml2json(data);
    var retorno = json.Body.add_categoriaResponse.response.retorno;
    var codeMessage = retorno.code;
    var message = retorno.mensagem;





}




///////////////////////////////////////////////////
///     validações
///////////////////////////////////////////////////
function validaCampos(nome, morada, nporta, nif, dataNascimento, contacto, localidade, username, password){
    var isValid =  true;

    if (nome === ''){
        $(classFTS(ftsTxtRegistoNome)).addClass('has-error');
        isValid = false;
    }else{
        $(classFTS(ftsTxtRegistoNome)).removeClass('has-error');
    }

    if (morada == ''){
        $(classFTS(ftsTxtRegistoMorada)).addClass('has-error');
        isValid = false;
    }else{
        $(classFTS(ftsTxtRegistoMorada)).removeClass('has-error');
    }

    if (nporta === ''){
        $(classFTS(ftsTxtRegistoNporta)).addClass('has-error');
        isValid = false;
    }else{
        $(classFTS(ftsTxtRegistoNporta)).removeClass('has-error');
    }

    if (nif === ''){
        $(classFTS(ftsTxtRegistoContribuinte)).addClass('has-error');
        isValid = false;
    }else{
        $(classFTS(ftsTxtRegistoContribuinte)).removeClass('has-error');
    }

    if (dataNascimento === ''){
        $(classFTS(ftsTxtRegistoDataNascimento)).addClass('has-error');
        isValid = false;
    }else{
        $(classFTS(ftsTxtRegistoDataNascimento)).removeClass('has-error');
    }

    if (contacto === ''){
        $(classFTS(ftsTxtRegistoContacto)).addClass('has-error');
        isValid = false;
    }else{
        $(classFTS(ftsTxtRegistoContacto)).removeClass('has-error');
    }

    if (localidade === ''){
        $(classFTS(ftsTxtRegistoLocalidade)).addClass('has-error');
        isValid = false;
    }else{
        $(classFTS(ftsTxtRegistoLocalidade)).removeClass('has-error');
    }

    if (username === ''){
        $(classFTS(ftsTxtRegistoUsername)).addClass('has-error');
        isValid = false;
    }else{
        $(classFTS(ftsTxtRegistoUsername)).removeClass('has-error');
    }

    if (password === ''){
        $(classFTS(ftsTxtRegistoPassword)).addClass('has-error');
        isValid = false;
    }else{
        $(classFTS(ftsTxtRegistoPassword)).removeClass('has-error');
    }


    return isValid;
}