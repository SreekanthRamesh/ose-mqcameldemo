/*
 * Copyright 2005-2015 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.abouchama.mqcameldemo;

import io.fabric8.annotations.Alias;
import io.fabric8.annotations.ServiceName;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.builder.RouteBuilder;
import javax.inject.Inject;

/**
 * Configures all our Camel routes, components, endpoints and beans
 */
@ContextName("myCdiCamelContext")
public class MyRoutes extends RouteBuilder {

    @Inject
    @ServiceName("amq62-amq-tcp")
    @Alias("jms")
    ActiveMQComponent activeMQComponent;

    @Override
    public void configure() throws Exception {
    	
    	/*restConfiguration().component("jetty")
        .dataFormatProperty("prettyPrint", "true")
        .port(9191);
    	
    	rest("/FIS").description("User rest service")
        .consumes("application/json").produces("application/json")
        .get("/{name}").to("direct:order");
    	
    	
    	from("direct:order")
        .setBody(simple("Bienvenue: ${headers.name} on Fuse Integration Service !"))
        .to("jms:queue:REST.FIS?exchangePattern=InOnly");*/
    	
        from("timer://foo?period=500")
                .setBody(constant("Everything is awesome!"))
                .to("jms:queue:TEST.FOO");

        from("jms:queue:TEST.FOO")
                .to("log:output");
    }
}
