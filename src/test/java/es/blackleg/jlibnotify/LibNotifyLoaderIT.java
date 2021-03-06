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

import es.blackleg.jlibnotify.core.DefaultLibNotify;
import es.blackleg.jlibnotify.core.DefaultLibNotifyLoader;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

/**
 *
 * @author Hector Espert <hectorespertpardo@gmail.com>
 */
public class LibNotifyLoaderIT {

    @Test
    public void testLoad() {
        LibNotifyLoader libNotifyLoader = DefaultLibNotifyLoader.getInstance();
        assertThat(libNotifyLoader).isNotNull();
        assertThat(libNotifyLoader).isInstanceOf(DefaultLibNotifyLoader.class);
        assertThat(libNotifyLoader.load()).isInstanceOf(DefaultLibNotify.class);
    }

}
