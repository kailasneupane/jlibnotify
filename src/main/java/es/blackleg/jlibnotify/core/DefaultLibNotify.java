/*
 * Copyright 2019 Hector Espert.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.blackleg.jlibnotify.core;

import com.sun.jna.Pointer;
import es.blackleg.jlibnotify.ActionCallback;
import es.blackleg.jlibnotify.LibNotify;
import es.blackleg.jlibnotify.Notification;
import es.blackleg.jlibnotify.ServerInfo;
import es.blackleg.jlibnotify.jna.GBoolean;
import es.blackleg.jlibnotify.jna.NativeLibNotify;
import es.blackleg.jlibnotify.jna.NotifyActionCallback;
import java.util.Collection;

/**
 *
 * @author Hector Espert
 */
public class DefaultLibNotify implements LibNotify {

    private final NativeLibNotify nativeLibNotify;
    
    private final ServerCapabilitiesReader serverCapabilitiesReader;

    public DefaultLibNotify(NativeLibNotify libNotify, ServerCapabilitiesReader serverCapabilitiesReader) {
        this.nativeLibNotify = libNotify;
        this.serverCapabilitiesReader = serverCapabilitiesReader;
    }

    @Override
    public void init(String appName) {
        if (this.nativeLibNotify.notify_init(appName) == GBoolean.FALSE) {
            throw new RuntimeException("Error when init libnotify");
        }
    }

    @Override
    public boolean isAvailable() {
        return nativeLibNotify.notify_is_initted() == GBoolean.TRUE;
    }

    @Override
    public void unInit() {
        nativeLibNotify.notify_uninit();
    }
    
    @Override
    public String getAppName() {
        return nativeLibNotify.notify_get_app_name();
    }

    @Override
    public void setAppName(String appName) {
        nativeLibNotify.notify_set_app_name(appName);
    }
    
    @Override
    public ServerInfo getServerInfo() {
        String[] name = new String[1];
        String[] vendor = new String[1];
        String[] version = new String[1];
        String[] specVersion = new String[1];
        
        if (nativeLibNotify.notify_get_server_info(name, vendor, version, specVersion)== GBoolean.FALSE) {
            throw new RuntimeException("Error when get server info");
        }
        
        return new BasicServerInfo(name[0], vendor[0], version[0], specVersion[0]);
    }
    
    @Override
    public Collection<String> getServerCapabilities() {
        return serverCapabilitiesReader.getServerCapabilitiesFromPointer(nativeLibNotify.notify_get_server_caps());
    }

    @Override
    public Notification createNotification(String summary, String body, String icon) {
        Pointer pointer = nativeLibNotify.notify_notification_new(summary, body, icon);
        return new BasicNotification(pointer, summary, body, icon);
    }

    @Override
    public void showNotification(Notification notification) {
        if (nativeLibNotify.notify_notification_show(notification.getPointer(), Pointer.NULL) == GBoolean.FALSE) {
            throw new RuntimeException("Error when show notification");
        }
    }

    @Override
    public void closeNotification(Notification notification) {
        if (nativeLibNotify.notify_notification_close(notification.getPointer(), Pointer.NULL) == GBoolean.FALSE) {
            throw new RuntimeException("Error when show notification");
        }
    }

    @Override
    public void addAction(Notification notification, ActionCallback actionCallback) {
        NotifyActionCallback notifyActionCallback = new NotifyActionCallback() {
            @Override
            public void callback(Pointer notification, String action, Pointer user_data) {
                System.out.println("asdas");
            }
        };
        nativeLibNotify.notify_notification_add_action(notification.getPointer(), "action_click", "Reply to Message", notifyActionCallback, null, null);
    }

}
