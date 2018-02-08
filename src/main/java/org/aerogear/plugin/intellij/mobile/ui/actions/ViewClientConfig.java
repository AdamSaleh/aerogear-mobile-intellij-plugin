package org.aerogear.plugin.intellij.mobile.ui.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.aerogear.plugin.intellij.mobile.api.CLIException;
import org.aerogear.plugin.intellij.mobile.api.CLIRunnerImpl;
import org.aerogear.plugin.intellij.mobile.api.MobileAPI;
import org.aerogear.plugin.intellij.mobile.services.AeroGearMobileConfiguration;
import org.aerogear.plugin.intellij.mobile.services.MobileNotificationsService;
import org.aerogear.plugin.intellij.mobile.ui.sdkconfig.ViewSDKConfigDialog;
;

public class ViewClientConfig extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        String clientSdkConfig = "";
        try {
            
            String clientName = AeroGearMobileConfiguration.getInstance(e.getProject()).getClientName();
            clientSdkConfig = new MobileAPI(new CLIRunnerImpl()).getClientConfig(clientName);
        } catch (CLIException ex) {
            MobileNotificationsService.getInstance().notifyError("Error from mobile plugin: " + ex.toString());
        }

        new ViewSDKConfigDialog(clientSdkConfig).show();
    }
}
