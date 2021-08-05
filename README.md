# cordova-plugin-open-app-link

**Android and iOS**

A Cordova plugin for opening a link only if there's an app installed to handle it. If there's no app that opens the link, it will fail. This is intended for use with cordova-plugin-safariviewcontroller, which has the unforunate side-effect of not respecting app deep links.

**This doesn't work in the latest version of iOS for some reason. I'm looking into it.**

## Install
`cordova plugin add cordova-plugin-open-app-link`

## Usage
```js
OpenAppLink.open(url, function(opened) {
    if (opened) {
        //Opened link in native app successfully.
    } else {
        //No app installed to handle the link. Here you should fallback to browser.
    }
}, function(error) {console.error(error)})
```

Note: If your app is configured to handle deep links, they *will* work with this.

## How this works

It's explained for Android here:

[https://developer.chrome.com/docs/android/custom-tabs/best-practices/](https://developer.chrome.com/docs/android/custom-tabs/best-practices/)

[https://gist.github.com/andreban/1780525015f6449867a3](https://gist.github.com/andreban/1780525015f6449867a3)