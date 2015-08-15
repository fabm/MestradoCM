/**
 * Created by rafa on 10/05/2015.
 */
$(document).ready(novoUtilizadorReady);

//var ftsBtnRegistoNovoUtilizador = '#btnRegistoNovoUtilizador';


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
$(ftsBtnRegistoNovoUtilizador).click(registarNvoUtiliz);
}




///////////////////////////////////////////////////
///     REGISTAR NOVO UTILIZADOR
///////////////////////////////////////////////////
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

function registarNvoUtiliz(){

    var mandatoyFields =  validaCampos(
                            getVal(ftsTxtRegistoNome),
                            getVal(ftsTxtRegistoMorada), getVal(ftsTxtRegistoNporta),
                            getVal(ftsTxtRegistoContribuinte), getVal(ftsTxtRegistoDataNascimento),
                            getVal(ftsTxtRegistoContacto), getVal(ftsTxtRegistoLocalidade),
                            getVal(ftsTxtRegistoUsername), getVal(ftsTxtRegistoPassword) );

    if (mandatoyFields !== false){
        alert('BOTA INSERIR UM CAMPOS ?');

        var soapNewUtilizador = soapInsertNovoUtilizador(getVal(ftsTxtRegistoNome),
            getVal(ftsTxtRegistoMorada), getVal(ftsTxtRegistoNporta),
            getVal(ftsTxtRegistoContribuinte), getVal(ftsTxtRegistoDataNascimento),
            getVal(ftsTxtRegistoContacto), getVal(ftsTxtRegistoLocalidade),
            getVal(ftsTxtRegistoUsername), getVal(ftsTxtRegistoPassword) );


        wsUtilizadores(soapNewUtilizador, successInsertNovoUtilizador, alert('erro insert'));


    }else{
        alert('CAMPOS NULOS  !!!');
    }
}



function successInsertNovoUtilizador(data, status, req){
    alert('*** successInsertNovoUtilizador');
    console.log(data);
}




///////////////////////////////////////////////////
///     validações
///////////////////////////////////////////////////
function validaCampos(nome, morada, nporta, nif, dataNascimento, contacto, localidade, username, password){
    var isValid =  true;
    //var nomeIsValid, moradaIsValid, nportaIsValid, nifIsValid, dataNascimentoIsValid, contactoIsValid, localidadeIsValid, usernameIsValid, passwordIsValid = true;

    if (nome === ''){
        $(classFTS(ftsTxtRegistoNome)).addClass('has-error');
        isValid = false;
    }

    if (morada === ''){
        $(classFTS(ftsTxtRegistoMorada)).addClass('has-error');
        isValid = false;
    }

    if (nporta === ''){
        $(classFTS(ftsTxtRegistoNporta)).addClass('has-error');
        isValid = false;
    }

    if (nif === ''){
        $(classFTS(ftsTxtRegistoContribuinte)).addClass('has-error');
        isValid = false;
    }

    if (dataNascimento === ''){
        $(classFTS(ftsTxtRegistoDataNascimento)).addClass('has-error');
        isValid = false;
    }

    if (contacto === ''){
        $(classFTS(ftsTxtRegistoContacto)).addClass('has-error');
        isValid = false;
    }

    if (localidade === ''){
        $(classFTS(ftsTxtRegistoLocalidade)).addClass('has-error');
        isValid = false;
    }

    if (username === ''){
        $(classFTS(ftsTxtRegistoUsername)).addClass('has-error');
        isValid = false;
    }

    if (password === ''){
        $(classFTS(ftsTxtRegistoPassword)).addClass('has-error');
        isValid = false;
    }

    //if (nomeIsValid == false || moradaIsValid == false || nportaIsValid== false || nifIsValid == false || dataNascimentoIsValid == false || contactoIsValid == false || localidadeIsValid == false || usernameIsValid == false || passwordIsValid == false )
    //    isValid = false;

    return isValid;
}