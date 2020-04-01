///*
// * Licensed to the Apache Software Foundation (ASF) under one or more
// * contributor license agreements.  See the NOTICE file distributed with
// * this work for additional information regarding copyright ownership.
// * The ASF licenses this file to You under the Apache License, Version 2.0
// * (the "License"); you may not use this file except in compliance with
// * the License.  You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package com.example.demo.rpc.context.configcontext;
//
//import com.example.demo.rpc.context.dubbocontext.FrameworkExt;
//import com.example.demo.rpc.context.dubbocontext.LifecycleAdapter;
//import com.example.demo.rpc.utils.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.*;
//import java.util.concurrent.Callable;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReadWriteLock;
//import java.util.concurrent.locks.ReentrantReadWriteLock;
//import java.util.stream.Collectors;
//
//import static com.example.demo.rpc.constants.CommonConstants.DEFAULT_KEY;
//import static com.example.demo.rpc.utils.StringUtils.isNotEmpty;
//import static java.lang.Boolean.TRUE;
//import static java.util.Collections.emptyMap;
//import static java.util.Collections.unmodifiableSet;
//import static java.util.Optional.ofNullable;
//
//public class ConfigManager extends LifecycleAdapter implements FrameworkExt {
//
//    private static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);
//
//    public static final String NAME = "config";
//
//    /**
//     * The suffix container
//     */
//    private static final String[] SUFFIXES = new String[]{"Config", "Bean", "ConfigBase"};
//
//
//    private final Map<String, Map<String, AbstractConfig>> configsCache = newMap();
//
//    private final ReadWriteLock lock = new ReentrantReadWriteLock();
//
//    public ConfigManager() {
//    }
//
//
//    public void addConfig(ServiceConfigBase config) {
//        addConfig(config, false);
//    }
//
//    protected void addConfig(ServiceConfigBase config, boolean unique) {
//        if (config == null) {
//            return;
//        }
//        write(() -> {
//            Map<String, ServiceConfigBase> configsMap = configsCache.computeIfAbsent(getTagName(config.getClass()), type -> newMap());
//            addIfAbsent(config, configsMap, unique);
//        });
//    }
//
//    private String getId(ServiceConfigBase config){
//        // todo:
//        return config.getClass().getSimpleName() + "#" + DEFAULT_KEY;
//    }
//
//    private static void checkDuplicate(ServiceConfigBase oldOne, ServiceConfigBase newOne) throws IllegalStateException {
//        if (oldOne != null && !oldOne.equals(newOne)) {
//            String configName = oldOne.getClass().getSimpleName();
//            logger.warn("Duplicate Config found for " + configName + ", you should use only one unique " + configName + " for one application.");
//        }
//    }
//
//    static <C extends ServiceConfigBase> void addIfAbsent(C config, Map<String, C> configsMap, boolean unique)
//            throws IllegalStateException {
//
//        if (config == null || configsMap == null) {
//            return;
//        }
//
//        if (unique) { // check duplicate
//            configsMap.values().forEach(c -> {
//                checkDuplicate(c, config);
//            });
//        }
//
//        String key = getId(config);
//
//        C existedConfig = configsMap.get(key);
//
//        if (existedConfig != null && !config.equals(existedConfig)) {
//            if (logger.isWarnEnabled()) {
//                String type = config.getClass().getSimpleName();
//                logger.warn(String.format("Duplicate %s found, there already has one default %s or more than two %ss have the same id, " +
//                        "you can try to give each %s a different id : %s", type, type, type, type, config));
//            }
//        } else {
//            configsMap.put(key, config);
//        }
//    }
//
//    public static String getTagName(Class<?> cls) {
//        String tag = cls.getSimpleName();
//        for (String suffix : SUFFIXES) {
//            if (tag.endsWith(suffix)) {
//                tag = tag.substring(0, tag.length() - suffix.length());
//                break;
//            }
//        }
//        return StringUtils.camelToSplitName(tag, "-");
//    }
//
//    public void addService(ServiceConfigBase<?> serviceConfig) {
//        addConfig(serviceConfig);
//    }
//
//    public void addServices(Iterable<ServiceConfigBase<?>> serviceConfigs) {
//        serviceConfigs.forEach(this::addService);
//    }
//
//    public Collection<ServiceConfigBase> getServices() {
//        return getConfigs(getTagName(ServiceConfigBase.class));
//    }
//
//    private <V> V write(Callable<V> callable) {
//        V value = null;
//        Lock writeLock = lock.writeLock();
//        try {
//            writeLock.lock();
//            value = callable.call();
//        } catch (RuntimeException e) {
//            throw e;
//        } catch (Throwable e) {
//            throw new RuntimeException(e.getCause());
//        } finally {
//            writeLock.unlock();
//        }
//        return value;
//    }
//
//    private void write(Runnable runnable) {
//        write(() -> {
//            runnable.run();
//            return null;
//        });
//    }
//
//    private <V> V read(Callable<V> callable) {
//        Lock readLock = lock.readLock();
//        V value = null;
//        try {
//            readLock.lock();
//            value = callable.call();
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        } finally {
//            readLock.unlock();
//        }
//        return value;
//    }
//
//    private static Map newMap() {
//        return new HashMap<>();
//    }
//}
