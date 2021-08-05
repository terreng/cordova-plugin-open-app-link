/*global cordova, module*/

module.exports = {
    open: function (url, successCallback, errorCallback) {
        cordova.exec(function(opened) {if (opened == "true") {successCallback(true)} else {successCallback(false)}}, errorCallback, "OpenAppLink", "open", [url]);
    }
};