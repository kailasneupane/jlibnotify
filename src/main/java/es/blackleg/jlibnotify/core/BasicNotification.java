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
package es.blackleg.jlibnotify.core;

import com.sun.jna.Pointer;
import es.blackleg.jlibnotify.Notification;
import es.blackleg.jlibnotify.Notification;

/**
 *
 * @author Hector Espert <hectorespertpardo@gmail.com>
 */
public class BasicNotification implements Notification {
    
    private final Pointer pointer;
    
    private final String title; 
    
    private final String detailsText;
    
    private final String icon;

    public BasicNotification(Pointer pointer, String title, String detailsText, String icon) {
        this.pointer = pointer;
        this.title = title;
        this.detailsText = detailsText;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public String getDetailsText() {
        return detailsText;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public Pointer getPointer() {
        return pointer;
    }

}
