package me.erano.com.V1_19_R3.performance;

import me.erano.com.core.performance.TPSHandlerFactory;

public class TPSHandlerFactoryImpl implements TPSHandlerFactory {
    @Override
    public TPSHandlerImpl createTPSHandler() {
        return new TPSHandlerImpl();
    }
}
