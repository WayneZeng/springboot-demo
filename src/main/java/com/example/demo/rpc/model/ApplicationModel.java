/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.demo.rpc.model;

import com.example.demo.rpc.context.configcontext.ConfigManager;
import com.example.demo.rpc.context.dubbocontext.FrameworkExt;
import com.example.demo.rpc.extension.ExtensionLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * {@link ExtensionLoader}, {@code DubboBootstrap} and this class are at present designed to be
 * singleton or static (by itself totally static or uses some static fields). So the instances
 * returned from them are of process scope. If you want to support multiple dubbo servers in one
 * single process, you may need to refactor those three classes.
 *
 * Represent a application which is using Dubbo and store basic metadata info for using
 * during the processing of RPC invoking.
 * <p>
 * ApplicationModel includes many ProviderModel which is about published services
 * and many Consumer Model which is about subscribed services.
 * <p>
 *
 */

public class ApplicationModel {
    protected static final Logger LOGGER = LoggerFactory.getLogger(ApplicationModel.class);
    public static final String NAME = "application";

    private static AtomicBoolean INIT_FLAG = new AtomicBoolean(false);

//    public static void init() {
//        if (INIT_FLAG.compareAndSet(false, true)) {
//            ExtensionLoader<ApplicationInitListener> extensionLoader = ExtensionLoader.getExtensionLoader(ApplicationInitListener.class);
//            Set<String> listenerNames = extensionLoader.getSupportedExtensions();
//            for (String listenerName : listenerNames) {
//                extensionLoader.getExtension(listenerName).init();
//            }
//        }
//    }


    private static final ExtensionLoader<FrameworkExt> LOADER = ExtensionLoader.getExtensionLoader(FrameworkExt.class);


    public static ConfigManager getConfigManager() {
        return (ConfigManager) LOADER.getExtension(ConfigManager.NAME);
    }



}
