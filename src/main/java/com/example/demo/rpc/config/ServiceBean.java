package com.example.demo.rpc.config;


import com.example.demo.rpc.common.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: yanuo
 * @create: 20200330 10:54 AM
 */

public class ServiceBean<T> implements Serializable {

    protected static final Logger logger = LoggerFactory.getLogger(ServiceBean.class);

    private static final long serialVersionUID = 4267533505537413570L;

    /**
     * The interface name of the exported service
     */
    protected String interfaceName;

    /**
     * The interface class of the exported service
     */
    protected Class<?> interfaceClass;

    /**
     * The reference of the interface implementation
     */
    protected T ref;

    /**
     * The service name
     */
    protected String path;

    /**
     * Local impl class name for the service interface
     */
    protected String local;

    /**
     * Local stub class name for the service interface
     */
    protected String stub;

    /**
     * The method configuration
     */
    private List<MethodConfig> methods;

    /**
     * The url of the reference service
     */
    protected final List<URL> urls = new ArrayList<URL>();




}