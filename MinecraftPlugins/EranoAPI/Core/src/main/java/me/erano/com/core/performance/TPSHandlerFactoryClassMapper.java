package me.erano.com.core.performance;

import java.util.HashMap;
import java.util.Map;

public class TPSHandlerFactoryClassMapper {

    private static final Map<String, String> versionToClassMap = new HashMap<>();

    /**
     *
     * @return NMSHandlerImpl appropriate to the version of the server.
     */
    public static TPSHandlerFactory getTPSHandlerFactory(String version) {
        String tpsHandlerClassName = getHandlerClassName(version);
        try {
            Class<? extends TPSHandlerFactory> clazz = (Class<? extends TPSHandlerFactory>) Class.forName(tpsHandlerClassName);
            return clazz.getConstructor().newInstance();
        } catch (ReflectiveOperationException exception) {
            throw new RuntimeException("Error creating TPSHandler for Minecraft version " + version, exception);
        }
    }

    static {
        versionToClassMap.put("1.17.1", "me.erano.com.v1_17_R1.performance.TPSHandlerFactoryImpl");
        versionToClassMap.put("1.18.1", "me.erano.com.v1_18_R1.performance.TPSHandlerFactoryImpl");
        versionToClassMap.put("1.18.2", "me.erano.com.v1_18_R2.performance.TPSHandlerFactoryImpl");
        versionToClassMap.put("1.19.1", "me.erano.com.V1_19_R1.performance.TPSHandlerFactoryImpl");
        versionToClassMap.put("1.19.2", "me.erano.com.V1_19_R1.performance.TPSHandlerFactoryImpl");
        versionToClassMap.put("1.19.3", "me.erano.com.v1_19_R2.performance.TPSHandlerFactoryImpl");
        versionToClassMap.put("1.19.4", "me.erano.com.v1_19_R3.performance.TPSHandlerFactoryImpl");
        versionToClassMap.put("1.20.1", "me.erano.com.v1_20_R1.performance.TPSHandlerFactoryImpl");
        versionToClassMap.put("1.20.2", "me.erano.com.v1_20_R2.performance.TPSHandlerFactoryImpl");
        versionToClassMap.put("1.20.3", "me.erano.com.v1_20_R3.performance.TPSHandlerFactoryImpl");
        versionToClassMap.put("1.20.4", "me.erano.com.v1_20_R3.performance.TPSHandlerFactoryImpl");
        versionToClassMap.put("1.20.5", "me.erano.com.v1_20_R3.performance.TPSHandlerFactoryImpl");
        versionToClassMap.put("1.20.6", "me.erano.com.v1_20_R3.performance.TPSHandlerFactoryImpl");
        versionToClassMap.put("1.21.1", "me.erano.com.v1_21_R1.performance.TPSHandlerFactoryImpl");
        versionToClassMap.put("1.21.2", "me.erano.com.v1_21_R2.performance.TPSHandlerFactoryImpl");
        versionToClassMap.put("1.21.3", "me.erano.com.v1_21_R2.performance.TPSHandlerFactoryImpl");
    }

    private static String getHandlerClassName(String version) {
        String handlerClassName = versionToClassMap.get(version);
        if (handlerClassName == null) {
            throw new RuntimeException("Unsupported Minecraft version: " + version);
        }
        return handlerClassName;
    }
}
