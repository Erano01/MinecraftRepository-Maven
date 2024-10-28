package me.erano.com.V1_18_R2.performance;

import me.erano.com.core.performance.TPSHandler;
import me.erano.com.core.performance.TPSHandlerFactory;

public class TPSHandlerFactoryImpl implements TPSHandlerFactory {
    @Override
    public TPSHandler createTPSHandler() {
        return new TPSHandlerImpl();
    }
}
