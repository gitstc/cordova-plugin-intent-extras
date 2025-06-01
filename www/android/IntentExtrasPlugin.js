function IntentPlugin() {
    'use strict';
}

IntentPlugin.prototype.getExtras = function(successCallback, failureCallback) {
    'use strict';

    return cordova.exec (
        successCallback,
        failureCallback,
        "IntentPlugin",
        "getExtras",
        []
    );
};

var intentInstance = new IntentPlugin();
module.exports = intentInstance;

// Make plugin work under window.plugins
if (!window.plugins) {
    window.plugins = {};
}
if (!window.plugins.intent) {
    window.plugins.intent = intentInstance;
}
