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

import es.blackleg.jlibnotify.LibNotify;
import es.blackleg.jlibnotify.jna.NativeLibNotify;
import es.blackleg.jlibnotify.test.NativeLibNotifyMock;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Hector Espert <hectorespertpardo@gmail.com>
 */
public class BasicLibNotifyTest {
    
    private LibNotify libNotify;
    
    @Before
    public void setUp() {
        NativeLibNotify nativeLibNotify = new NativeLibNotifyMock();
        libNotify = new DefaultLibNotify(nativeLibNotify, null);
    }

    @Test
    public void testInit() {
        libNotify.init("test-init");
    }

}
