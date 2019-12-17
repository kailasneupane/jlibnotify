/*
 * Copyright 2019 Hector Espert <hectorespertpardo@gmail.com>.
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
package es.blackleg.jlibnotify.jna;

import com.sun.jna.Library;
import com.sun.jna.Pointer;

/**
 *
 * @author Hector Espert <hectorespertpardo@gmail.com>
 */
public interface NativeLibNotify extends Library {
    
    GBoolean notify_init(String app_name);
    
    GBoolean notify_is_initted();
    
    void notify_uninit();
    
    String notify_get_app_name();
    
    void notify_set_app_name(String app_name);
    
    GBoolean notify_get_server_info(String[] ret_name, String[] ret_vendor, String[] ret_version, String[] ret_spec_version);
    
    Pointer notify_get_server_caps();
    
    Pointer notify_notification_new(String summary, String body, String icon);
    
    GBoolean notify_notification_show(Pointer notification, Pointer error);
    
    GBoolean notify_notification_close(Pointer notification, Pointer error);
    
    void notify_notification_add_action(Pointer notification, String action, String label, NotifyActionCallback callback, Pointer user_data, Object free_func);
    
}
