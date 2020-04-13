package com.test.kafka.config;

/**
 * zheng hongda
 */
public class KafkaEnv {

    private static String servers;
    private static boolean enabled;
    private static String  profile;
    private static boolean saslEnabled = true;

    public static String getServers() {
        return servers;
    }

    public static void setServers(String servers) {
        KafkaEnv.servers = servers;
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static void setEnabled(boolean enabled) {
        KafkaEnv.enabled = enabled;
    }

    public static String getProfile() {
        return profile;
    }

    public static void setProfile(String profile) {
        KafkaEnv.profile = profile;
    }

    public static boolean isSaslEnabled() {
        return saslEnabled;
    }

    public static void setSaslEnabled(boolean saslEnabled) {
        KafkaEnv.saslEnabled = saslEnabled;
    }
}
