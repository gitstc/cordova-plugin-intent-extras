# Cordova Plugin for accessing the Cordova Intent extras

## Usage

```js
window.plugins.intent.getExtras(function (extras) {
    console.log(extras);
}, function (error) {
    console.error(error);
});
```