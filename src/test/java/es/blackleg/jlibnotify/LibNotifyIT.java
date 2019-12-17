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
package es.blackleg.jlibnotify;

import es.blackleg.jlibnotify.core.DefaultLibNotifyLoader;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Hector Espert <hectorespertpardo@gmail.com>
 */
public class LibNotifyIT {
    
    private LibNotify libNotify;
    
    @Before
    public void setUp() {
        if (Objects.isNull(libNotify)) {
            libNotify = DefaultLibNotifyLoader.getInstance().load();
        }
        
        assertThat(libNotify.isAvailable()).isFalse();
        libNotify.init("LibNotifyIT");
        assertThat(libNotify.isAvailable()).isTrue();
    }
    
    @After
    public void tearDown() {
        assertThat(libNotify.isAvailable()).isTrue();
        libNotify.unInit();
        assertThat(libNotify.isAvailable()).isFalse();
    }
    
    
    @Test
    public void testAppName() {
        assertThat(libNotify.getAppName()).isEqualTo("LibNotifyIT");
        libNotify.setAppName("LibNotifyTest");
        assertThat(libNotify.getAppName()).isEqualTo("LibNotifyTest");
    }
    
    @Test
    public void testServerInfo() {
        ServerInfo serverInfo = libNotify.getServerInfo();
        assertThat(serverInfo).isNotNull();
        assertThat(serverInfo).extracting(ServerInfo::getName).isNotNull();
        assertThat(serverInfo).extracting(ServerInfo::getVendor).isNotNull();
        assertThat(serverInfo).extracting(ServerInfo::getVersion).isNotNull();
        assertThat(serverInfo).extracting(ServerInfo::getSpecVersion).isNotNull();
    }
    
    @Test
    public void testServerCapabilities() {
        Collection<String> capabilities = libNotify.getServerCapabilities();
        assertThat(capabilities).isNotEmpty();
        assertThat(capabilities).contains("actions", "body", "body-markup", "icon-static", "persistence", "sound");
    }

    @Test
    public void testNotification() throws InterruptedException {
        Notification notification = libNotify.createNotification("LibNotify IT", "LibNotify Integration test", "dialog-information");
        assertThat(notification).isNotNull();
        
        CountDownLatch latch = new CountDownLatch(1);
        
        
        libNotify.addAction(notification, new ActionCallback() {
            @Override
            public void onAction() {
                latch.countDown();
            }
            
        });
        
        libNotify.showNotification(notification);
        
        latch.await();
        
        libNotify.closeNotification(notification);
    }
    
}
