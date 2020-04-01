package com.example.demo.rpc.config;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author: yanuo
 * @create: 20200331 4:37 PM
 */
@Data
public class ArgumentConfig implements Serializable {

    protected static final Logger logger = LoggerFactory.getLogger(ArgumentConfig.class);
    private static final long serialVersionUID = -2165482463925213595L;

    /**
     * The argument index: index -1 represents not set
     */
    private Integer index = -1;

    /**
     * Argument type
     */
    private String type;

    private String name;

}
