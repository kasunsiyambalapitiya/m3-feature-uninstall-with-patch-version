/*
 *  Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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
package org.wso2.carbon.kernel.internal.transports;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;
import org.wso2.carbon.kernel.transports.CarbonTransport;
import org.wso2.carbon.kernel.transports.TransportManager;

import java.util.Map;

/**
 * Provides OSGi console commands to manage Carbon transports.
 *
 * @since 5.0.0
 */
public class TransportMgtCommandProvider implements CommandProvider {

    private TransportManager transportManager;

    public TransportMgtCommandProvider(TransportManager transportManager) {
        this.transportManager = transportManager;
    }

    @Override
    public String getHelp() {
        return "---Transport Management---\n" +
                "\tstartTransport <transportName> - Start the specified transport with <transportName>.\n" +
                "\tstopTransport <transportName> - Stop the specified transport with <transportName>\n" +
                "\tstartTransports - Start all transports\n" +
                "\tstopTransports - Stop all transports\n" +
                "\tbeginMaintenance - Activate maintenance mode of all transports\n" +
                "\tendMaintenance - Deactivate maintenance mode of all transports\n" +
                "\tlistTransports - List all the available transports\n";

    }

    public void _startTransport(CommandInterpreter ci) {
        String transportName = ci.nextArgument();

        if (transportName == null || transportName.equals("")) {
            throw new IllegalArgumentException("Invalid transport.");
        }

        transportManager.startTransport(transportName);
    }

    public void _stopTransport(CommandInterpreter ci) {
        String transportName = ci.nextArgument();

        if (transportName == null || transportName.equals("")) {
            throw new IllegalArgumentException("Invalid transport.");
        }

        transportManager.stopTransport(transportName);
    }

    public void _startTransports(CommandInterpreter ci) {
        transportManager.startTransports();
    }

    public void _stopTransports(CommandInterpreter ci) {
        transportManager.stopTransports();
    }

    public void _beginMaintenance(CommandInterpreter ci) {
        transportManager.beginMaintenance();
    }

    public void _endMaintenance(CommandInterpreter ci) {
        transportManager.endMaintenance();
    }

    public void _listTransports(CommandInterpreter ci) {
        Map<String, CarbonTransport> map = transportManager.getTransports();
        map.forEach((key, value) -> System.out.println("Transport Name: " + key + "\t" + " State: " +
                                                       value.getState().toString()));
    }
}
