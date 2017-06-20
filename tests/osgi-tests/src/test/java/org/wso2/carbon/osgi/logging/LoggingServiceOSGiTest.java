/*
* Copyright 2015 WSO2, Inc. http://www.wso2.org
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
package org.wso2.carbon.osgi.logging;

import org.ops4j.pax.exam.ExamFactory;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.ops4j.pax.exam.testng.listener.PaxExam;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogEntry;
import org.osgi.service.log.LogReaderService;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.wso2.carbon.container.CarbonContainerFactory;
import org.wso2.carbon.kernel.CarbonRuntime;
import org.wso2.carbon.kernel.config.model.CarbonConfiguration;
import org.wso2.carbon.kernel.utils.CarbonServerInfo;

import java.util.Enumeration;
import javax.inject.Inject;

/**
* Logging Service OSGi test case is to verify the logging events are published correctly.
*
* @since 5.0.0
*/
@Listeners(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
@ExamFactory(CarbonContainerFactory.class)
public class LoggingServiceOSGiTest {
    private static final String START_UP_LOG_MESSAGE = " started in";
    private static final String CARBON_RUNTIME_SERVICE = CarbonRuntime.class.getName();

    @Inject
    private CarbonServerInfo carbonServerInfo;

    @Inject
    private LogReaderService logReaderService;

    @Inject
    private BundleContext bundleContext;

    @Test
    public void testServerLogStatus() {
        Assert.assertNotNull(carbonServerInfo, "Log reader service cannot be null");
        Assert.assertNotNull(logReaderService, "Log reader service cannot be null");
        Enumeration entries = logReaderService.getLog();

        Assert.assertEquals(entries.hasMoreElements(), true, "Log entry count should be greater than zero");
        boolean flag = false;
        //iterate and check whether the startup finalization log message is found.
        while (entries.hasMoreElements()) {
            if (((LogEntry) entries.nextElement()).getMessage().startsWith(getCarbonConfiguration().getName() +
                    START_UP_LOG_MESSAGE)) {
                flag = true;
            }
        }
        Assert.assertEquals(flag, true, "Carbon Startup log not found");
    }

    /**
     * @return Carbon Configuration reference
     */
    private CarbonConfiguration getCarbonConfiguration() {
        ServiceReference reference = bundleContext.getServiceReference(CARBON_RUNTIME_SERVICE);
        CarbonRuntime carbonRuntime = (CarbonRuntime) bundleContext.getService(reference);
        return carbonRuntime.getConfiguration();
    }
}
