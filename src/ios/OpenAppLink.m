#import "OpenAppLink.h"
#import <Cordova/CDVPlugin.h>

@implementation OpenAppLink

- (void)open:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSString* url = [command.arguments objectAtIndex:0];

    if (url != nil && [url length] > 0) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:url];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end