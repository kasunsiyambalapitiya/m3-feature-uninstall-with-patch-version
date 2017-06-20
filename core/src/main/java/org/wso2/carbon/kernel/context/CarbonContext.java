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
package org.wso2.carbon.kernel.context;

import org.wso2.carbon.kernel.internal.context.CarbonContextHolder;

import java.security.Principal;

/**
 * This provides the API for thread local based programming for carbon based products. Each CarbonContext will utilize
 * an underlying {@link org.wso2.carbon.kernel.internal.context.CarbonContextHolder} instance, which will store the
 * actual data.
 *
 * @since 5.1.0
 */
public class CarbonContext {
    private CarbonContextHolder carbonContextHolder = null;

    /**
     * Making this private so that it will not be instantiated.
     */
    private CarbonContext() {
    }

    /**
     * Package local level constructor using a {@link CarbonContextHolder} instance in constructing this
     * CarbonContext instance.
     *
     * @param carbonContextHolder the carbonContextHolder instance to be used in creating a CarbonContext instance.
     */
    CarbonContext(CarbonContextHolder carbonContextHolder) {
        this.carbonContextHolder = carbonContextHolder;
    }

    /**
     * Returns the current carbon context holder associated with this CarbonContext instance.
     *
     * @return the carbon context holder instance.
     */
    CarbonContextHolder getCarbonContextHolder() {
        return carbonContextHolder;
    }

    /**
     * Returns the carbon context instance which is stored at current thread local space.
     *
     * @return the carbon context instance.
     */
    public static CarbonContext getCurrentContext() {
        return new CarbonContext(CarbonContextHolder.getCurrentContextHolder());
    }

    /**
     * The current jass user principal set with this carbon context instance. If no principal is set, a null value will
     * be returned.
     *
     * @return the jass user principal in the carbon context, null if no value is already set.
     */
    public Principal getUserPrincipal() {
        return getCarbonContextHolder().getUserPrincipal();
    }

    /**
     * Method the lookup currently stored property with the carbon context instance using the given property key name.
     *
     * @param name property key name to lookup.
     * @return the value stored using the given key, or null if no value is already set.
     */
    public Object getProperty(String name) {
        return getCarbonContextHolder().getProperty(name);
    }
}
