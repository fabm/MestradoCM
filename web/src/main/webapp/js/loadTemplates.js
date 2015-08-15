/**
 * Created by rafa on 17/05/2015.
 */

var liNovoUtilizador = '#liNovoUtilizador';

$(liNovoUtilizador).click(function(){

    alert('LI NOVO UTILIZADOR');

    var dados = new Dados();
    dados.callback = function(data){
        $("#page-wrapper").html(data.rendered);
    }
    $.ajax({
        url: "/web/templates/"
    }).done(dados.setTemplate);

});
