/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.arquillian.persistence.client;

import org.jboss.arquillian.container.test.spi.RemoteLoadableExtension;
import org.jboss.arquillian.container.test.spi.client.deployment.AuxiliaryArchiveAppender;
import org.jboss.arquillian.persistence.container.RemotePersistenceExtension;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

/**
 * 
 * Creates <code>arquillian-persistence.jar</code> archive
 * containing all required dependencies to run Persistence Extension and
 * extension SPI itself.
 * 
 * @author <a href="mailto:bartosz.majsak@gmail.com">Bartosz Majsak</a>
 * 
 */
public class PersistenceArchiveAppender implements AuxiliaryArchiveAppender
{

   @Override
   public Archive<?> createAuxiliaryArchive()
   {
      return ShrinkWrap.create(JavaArchive.class, "arquillian-persistence.jar")
            // TODO Maven resolver
            .addPackages(
                  true,
                  Filters.exclude(".*/package-info.*"),
                  "org.dbunit")
            .addAsServiceProvider(RemoteLoadableExtension.class, RemotePersistenceExtension.class)
            // TODO resolve all datasets
            .addAsManifestResource("datasets/single-user.xml");
   }
}
