/**
 * Created by sedler on 30.12.14.
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
        init: init
    };

    function init() {
        alert('hello');
    }

    window.queue = new Api();
    return queue;
}(jQuery));