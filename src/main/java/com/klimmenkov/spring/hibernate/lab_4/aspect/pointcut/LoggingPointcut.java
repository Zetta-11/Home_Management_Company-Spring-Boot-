package com.klimmenkov.spring.hibernate.lab_4.aspect.pointcut;

import org.aspectj.lang.annotation.Pointcut;

public class LoggingPointcut {
    @Pointcut("execution (* com.klimmenkov.spring.hibernate.lab_4.dao.impl.*.get*(..))")
    private void allGetMethods() {
    }

    @Pointcut("execution (* com.klimmenkov.spring.hibernate.lab_4.dao.impl.*.save*(..))")
    private void allSaveMethods() {
    }

    @Pointcut("execution (* com.klimmenkov.spring.hibernate.lab_4.dao.impl.*.delete*(..))")
    private void allDeleteMethods() {
    }

    @Pointcut("allGetMethods() || allSaveMethods() || allDeleteMethods()")
    public void allShowAddDeleteMethods() {
    }
}
