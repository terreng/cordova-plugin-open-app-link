//For ios, you need to set a swift version https://stackoverflow.com/a/52950615/6276471

@objc(OpenAppLink) class OpenAppLink : CDVPlugin {
    @objc(open:)
    func open(command: CDVInvokedUrlCommand) {
        let url_string = command.arguments[0] as? String ?? ""
        let url = URL(string: url_string)!

        if #available(iOS 10.0, *) {
            UIApplication.shared.open(url, options: [UIApplication.OpenExternalURLOptionsKey.universalLinksOnly : true]) { (success) in
                if (success) {
                    pluginResponse(message_string: "true")
                } else {
                    pluginResponse(message_string: "false")
                }
            }
        } else {
            pluginResponse(message_string: "false")
        }

        func pluginResponse(message_string: String) {
            let pluginResult = CDVPluginResult(
                status: CDVCommandStatus_OK,
                messageAs: message_string
            )

            self.commandDelegate!.send(
                pluginResult,
                callbackId: command.callbackId
            )
        }

    }
}
