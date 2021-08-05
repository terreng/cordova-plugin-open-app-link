#import "OpenAppLink.h"
#import <Cordova/CDVPlugin.h>

@implementation OpenAppLink

- (void)open:(CDVInvokedUrlCommand*)command
{
    NSString* url_string = [command.arguments objectAtIndex:0];
    NSURL *url = [NSURL URLWithString:url_string];

    //if ([[UIApplication sharedApplication] canOpenURL:url]) {
        [[UIApplication sharedApplication] openURL:url
                                            options:@{UIApplicationOpenURLOptionUniversalLinksOnly: @YES}
                                    completionHandler:^(BOOL success){
                                        CDVPluginResult* pluginResult = nil;
                                        if (success) {
                                            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"true"];
                                        } else {
                                            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"false"];
                                        }
                                        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
                                    }];
    //} else {
    //    CDVPluginResult* pluginResult = nil;
    //    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"NOPE1"];
    //    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    //}
}

@end

/*
    <config-file target="*-Info.plist" parent="LSApplicationQueriesSchemes">
        <array>
            <string>http</string>
            <string>https</string>
        </array>
    </config-file>
*/