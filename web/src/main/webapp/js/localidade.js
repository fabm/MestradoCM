/**
 * Created by rafa on 27/07/2015.
 */

///////////////////////////////////
//  GET ALL LOCALIDADES
function loadLocalidades() {

    var soapMsg = '';
    soapMsg + '<ser:get-all-localidades/> ';

    var soapLocalidades = g_soapBuilder.getSimpleEnvelope(soapMsg);

    wsLocalidades(soapLocalidades, successGetLocalidades, errorGetLocalidades);

}

function successGetLocalidades(data, status, req) {
    alert("SUCCES GET ALL LOCALIDADES");
}


function errorGetLocalidades(data, status, req) {
    alert("ERROR GET ALL LOCALIDADES");
}

///////////////////////////////////////////
//  GET LOCALIDADES COM PAGINAS
function getLocalidadesWithPages(page) {
    var soapMsg = '';
    soapMsg += '<ser:getLocalidadesComPagina>';
    soapMsg += '<pagina>'+page+'</pagina>';
    soapMsg += '</ser:getLocalidadesComPagina>';

    var soapLocalidadesWithPages = g_soapBuilder.getSimpleEnvelope(soapMsg);

    wsLocalidades(soapLocalidadesWithPages, successGetLocalidadesWithPages, errorGetLocalidadesWithPages);

}

function successGetLocalidadesWithPages(data, status, req){
    alert("SUCCESS GET LOCALIDADES WITH PAGES");
}

function errorGetLocalidadesWithPages(data, status, req){
    alert("SUCCESS GET LOCALIDADES WITH PAGES");
}


///////////////////////////////////////////
//  GET LOCALIDADES COM FILTROS E PAGINAS
function loadLocalidadeWithFilterAndPage(filter, page) {
    var soapMsg = '';

    soapMsg += '<ser:getLocalidadesComFiltroEPagina>';

    if (page !== null || page.equals('')) {
        soapMsg += '<filtro>' + filter + '</filtro>';
    } else {
        soapMsg += '<filtro>' + filter + '</filtro>';
        soapMsg += '<pagina>' + page + '</pagina>';
    }

    soapMsg += '</ser:getLocalidadesComFiltroEPagina>';
    var soapLocalidadesWithFilterAndPage = g_soapBuilder.getSimpleEnvelope(soapMsg);

    wsLocalidades(soapLocalidadesWithFilterAndPage, successGetLocalidadesWithFilterAndPage, errorGetLocalidadesWithFilterAndPage);


}


function successGetLocalidadesWithFilterAndPage(data, status, req) {
    alert("SUCCESS GET LOCALIDADES WITH FILTER AND PAGE");
}

function errorGetLocalidadesWithFilterAndPage(data, status, req) {
    alert("ERROR GET LOCALIDADES WITH FILTER AND PAGE");

}