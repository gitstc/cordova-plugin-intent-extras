function IntentExtrasPlugin() {
    'use strict';
}

IntentExtrasPlugin.prototype.getExtras = function(successCallback, failureCallback) {
    'use strict';

    return cordova.exec (
        successCallback,
        failureCallback,
        "IntentExtrasPlugin",
        "getExtras",
        []
    );
};

var intentInstance = new IntentExtrasPlugin();
module.exports = intentInstance;

// Make plugin work under window.plugins
if (!window.plugins) {
    window.plugins = {};
}
if (!window.plugins.IntentExtrasPlugin) {
    window.plugins.IntentExtrasPlugin = intentInstance;
}
