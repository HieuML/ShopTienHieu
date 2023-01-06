package com.example.shoptienhieu.utils;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

public class generatorIDHandler implements IdentifierGenerator {

        private String prefix;
        public Serializable a(){
            return prefix.toUpperCase() + new Date().getTime();
        }
        @Override
        public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
            return a();
        }
        @Override
        public void configure(Type type, Properties properties,
                              ServiceRegistry serviceRegistry) throws MappingException {
            prefix = properties.getProperty("prefix");
        }
    }

