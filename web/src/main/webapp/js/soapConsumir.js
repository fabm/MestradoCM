/**
 * Created by rafa on 15/06/2015.
 */

function soapInsertNovoUtilizador (nome, morada, porta, dataNascimento, email, contacto, localidade, login, password){

    var soapMsg = '';
    soapMsg += '<ser:createUserCliente>';
    soapMsg += '<cliente>';
    soapMsg += '    <contribuinte>?</contribuinte>';
    soapMsg += '    <nome> + nome + </nome>';
    soapMsg += '    <morada>'+morada+'</morada>';
    soapMsg += '    <porta>'+porta+'</porta>';
    soapMsg += '    <dataNascimento>'+dataNascimento+'</dataNascimento>';
    soapMsg += '    <email>'+email+'</email>';
    soapMsg += '    <contacto>'+contacto+'</contacto>';
    soapMsg += '    <localidade>'+localidade+'</localidade>';
    soapMsg += '    <login>'+login+'</login>';
    soapMsg += '    <password>'+password+'</password>';
    soapMsg += '</cliente>';
    soapMsg += '</ser:createUserCliente>';

    var soapFinal =  g_soapBuilder.getSimpleEnvelope(soapMsg);
    return soapFinal;
}