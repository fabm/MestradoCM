/**
 * Created by rafa on 06/09/2015.
 */


var btnLogout = '.btnLogout';

$(document).ready(function () {
    $(btnLogout).click(function () {
        alert('CLICK REMOVE COOKIE');
        //removeCookie('dataLogin64Base');
        setCookie('dataLogin64Base', '', 0);
    })
});






