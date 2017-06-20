/*
 *  Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.wso2.carbon.kernel.internal.runtime;

import org.wso2.carbon.kernel.runtime.exception.RuntimeServiceException;

/**
 * MBean interface for exposing CarbonRuntimeService functionalities.
 *
 * @since 5.1.0
 */
public interface CarbonRuntimeServiceMBean {

    /**
     * Users can call this method to start all registered runtime on the Runtime Manager.
     *
     * @throws RuntimeServiceException - on error while trying to starting registered runtimes
     */
    void startRuntimes() throws RuntimeServiceException;

    /**
     * Users can call this method to stop all registered runtime on the Runtime Manager.
     *
     * @throws RuntimeServiceException - on error while trying to stop registered runtimes
     */
    void stopRuntimes() throws RuntimeServiceException;

    /**
     * Users can call this method to put the Carbon server on Maintenance Mode and this will affect
     * all registered runtime into MAINTENANCE state.
     *
     * @throws RuntimeServiceException - on error while trying to start server Maintenance mode
     */
    void beginMaintenance() throws RuntimeServiceException;

    /**
     * Users can call this method to put the Carbon server back in normal state and this will affect
     * all registered runtime into INACTIVE state.
     *
     * @throws RuntimeServiceException - on error while trying to end server Maintenance mode
     */
    void endMaintenance() throws RuntimeServiceException;

}
