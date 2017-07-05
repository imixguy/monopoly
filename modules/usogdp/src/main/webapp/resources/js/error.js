/**
 * Created by sedler on 08.01.15.
 */
(function ($) {
    function Api() {
        this.info();
    };

    Api.prototype = {
        constructor: Api,
//        moduleName: _moduleName,
//        version: _version,
//        storage: _storage,
        info: function () {
//            console.log(this.moduleName);
//            console.log(this.version);
        },
        printError: printError
    };

    function printError(jqXHR, textStatus, message) {
//        $('#error').show();
//        $('#error').append('<div>'+'Request failed: ' + textStatus+' '+message+'</div>');
//        alert( "Request failed: " + textStatus+' '+message);
    }

    window.ajaxError = new Api();
    return ajaxError;
}(jQuery));