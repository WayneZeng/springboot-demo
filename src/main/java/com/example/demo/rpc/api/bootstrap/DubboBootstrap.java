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
package com.example.demo.rpc.api.bootstrap;

//import com.example.demo.rpc.config.utils.ReferenceConfigCache;
import com.example.demo.rpc.model.ApplicationModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.SortedSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

/**
 * See {ApplicationModel} and {ExtensionLoader} for why this class is designed to be singleton.
 * <p>
 * The bootstrap class of Dubbo
 * <p>
 * Get singleton instance by calling static method {@link #getInstance()}.
 * Designed as singleton because some classes inside Dubbo, such as ExtensionLoader, are designed only for one instance per process.
 *
 * @since 2.7.5
 */
public class DubboBootstrap {

    public static final String DEFAULT_REGISTRY_ID = "REGISTRY#DEFAULT";

    public static final String DEFAULT_PROTOCOL_ID = "PROTOCOL#DEFAULT";

    public static final String DEFAULT_SERVICE_ID = "SERVICE#DEFAULT";

    public static final String DEFAULT_REFERENCE_ID = "REFERENCE#DEFAULT";

    public static final String DEFAULT_PROVIDER_ID = "PROVIDER#DEFAULT";

    public static final String DEFAULT_CONSUMER_ID = "CONSUMER#DEFAULT";

    private static final String NAME = DubboBootstrap.class.getSimpleName();

    private final Logger logger = LoggerFactory.getLogger(DubboBootstrap.class);

    private static DubboBootstrap instance;

    private final AtomicBoolean awaited = new AtomicBoolean(false);

    private final Lock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();

    private final ExecutorService executorService = newSingleThreadExecutor();

    // private final EventDispatcher eventDispatcher = EventDispatcher.getDefaultExtension();

    //private final ExecutorRepository executorRepository = ExtensionLoader.getExtensionLoader(ExecutorRepository.class).getDefaultExtension();

    // private final ConfigManager configManager;

    //private final Environment environment;

    //private ReferenceConfigCache cache;

    private volatile boolean referAsync;

    private AtomicBoolean initialized = new AtomicBoolean(false);

    private AtomicBoolean started = new AtomicBoolean(false);

    private AtomicBoolean destroyed = new AtomicBoolean(false);

    //private volatile ServiceInstance serviceInstance;

    //private volatile MetadataService metadataService;

    //private volatile MetadataServiceExporter metadataServiceExporter;

    //private List<ServiceConfigBase<?>> exportedServices = new ArrayList<>();

    private List<CompletableFuture<Object>> asyncReferringFutures = new ArrayList<>();

    /**
     * See { ApplicationModel} and { ExtensionLoader} for why DubboBootstrap is designed to be singleton.
     */
    public static synchronized DubboBootstrap getInstance() {
        if (instance == null) {
            instance = new DubboBootstrap();
        }
        return instance;
    }

    private DubboBootstrap() {
        System.out.println(">>>>:" + "DubboBootstrap");
        // configManager = ApplicationModel.getConfigManager();
        // environment = ApplicationModel.getEnvironment();
    }

    /**
     * Initialize
     */
    private void initialize() {
        if (!initialized.compareAndSet(false, true)) {
            return;
        }

        if (logger.isInfoEnabled()) {
            logger.info(NAME + " has been initialized!");
        }
    }

    /**
     * Start the bootstrap
     */
    public DubboBootstrap start() {
        if (started.compareAndSet(false, true)) {
            initialize();
            if (logger.isInfoEnabled()) {
                logger.info(NAME + " is starting...");
            }
            // 1. export Dubbo Services
            exportServices();
            // referServices();

            if (logger.isInfoEnabled()) {
                logger.info(NAME + " has started.");
            }
        }
        return this;
    }

    public DubboBootstrap stop() throws IllegalStateException {
        destroy();
        return this;
    }

    private void exportServices() {
        // configManager中获取，但是configmanager中的哪里来
//        configManager.getServices().forEach(sc -> {
//            ServiceConfig serviceConfig = (ServiceConfig) sc;
//            serviceConfig.setBootstrap(this);
//            sc.export();
//            exportedServices.add(sc);
//
//        });
        System.out.println(">>>>export from config manager");
    }

//    private void referServices() {
//        if (cache == null) {
//            cache = ReferenceConfigCache.getCache();
//        }
//
//        configManager.getReferences().forEach(rc -> {
//            // TODO, compatible with  ReferenceConfig.refer()
//            ReferenceConfig referenceConfig = (ReferenceConfig) rc;
//            referenceConfig.setBootstrap(this);
//
//            if (rc.shouldInit()) {
//                cache.get(rc);
//
//            }
//        });
//    }

    public void destroy() {
        // for compatibility purpose

        if (started.compareAndSet(true, false)
                && destroyed.compareAndSet(false, true)) {
            shutdown();
            release();
        }
    }

    private void release() {
        executeMutually(() -> {
            while (awaited.compareAndSet(false, true)) {
                if (logger.isInfoEnabled()) {
                    logger.info(NAME + " is about to shutdown...");
                }
                condition.signalAll();
            }
        });
    }

    private void shutdown() {
        if (!executorService.isShutdown()) {
            // Shutdown executorService
            executorService.shutdown();
        }
    }

    private void executeMutually(Runnable runnable) {
        try {
            lock.lock();
            runnable.run();
        } finally {
            lock.unlock();
        }
    }

}
