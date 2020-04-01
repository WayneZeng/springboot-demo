package com.example.demo.rpc.config;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

/**
 * @author: yanuo
 * @create: 20200331 4:34 PM
 */
@Data
public class MethodConfig implements Serializable {
    protected static final Logger logger = LoggerFactory.getLogger(MethodConfig.class);
    private static final long serialVersionUID = 4267533505537413570L;

    /**
     * The method name
     */
    private String name;

    /**
     * Whether need to return
     */
    private Boolean isReturn;

    /**
     * The method arguments
     */
    private List<ArgumentConfig> arguments;

    /**
     * These properties come from MethodConfig's parent Config module, they will neither be collected directly from xml or API nor be delivered to url
     */
    private String service;
    private String serviceId;

}
