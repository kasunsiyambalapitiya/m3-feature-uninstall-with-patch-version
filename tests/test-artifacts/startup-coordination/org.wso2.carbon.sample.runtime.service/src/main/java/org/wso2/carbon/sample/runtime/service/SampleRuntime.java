/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.carbon.sample.runtime.service;

import org.osgi.service.component.annotations.Component;
import org.wso2.carbon.kernel.runtime.Runtime;
import org.wso2.carbon.kernel.runtime.RuntimeState;
import org.wso2.carbon.kernel.runtime.exception.RuntimeServiceException;

/**
 * Sample Runtime Service Component class.
 *
 * @since 5.1.0
 */
@Component(
        name = "org.wso2.carbon.sample.runtime.service.SampleRuntime",
        immediate = true,
        service = Runtime.class
)
public class SampleRuntime implements Runtime {

    @Override
    public void init() throws RuntimeServiceException {

    }

    @Override
    public void start() throws RuntimeServiceException {

    }

    @Override
    public void stop() throws RuntimeServiceException {

    }

    @Override
    public void beginMaintenance() throws RuntimeServiceException {

    }

    @Override
    public void endMaintenance() throws RuntimeServiceException {

    }

    @Override
    public Enum<RuntimeState> getState() {
        return null;
    }

    @Override
    public void setState(RuntimeState runtimeState) {

    }
}
