package com.justayar.springboot.configuration;


public class AppConfiguration {

    private Redis redis = new Redis();

    public Redis getRedis() {
        return redis;
    }

    private String activeRedisMode;

    public String getActiveRedisMode() {
        return activeRedisMode;
    }

    public void setActiveRedisMode(String activeRedisMode) {
        this.activeRedisMode = activeRedisMode;
    }

    public void setRedis(Redis redis) {
        this.redis = redis;
    }

    public static class Redis {

        private Config standalone = new Config();
        private Config sentinel = new Config();
        private Config cluster = new Config();

        public Config getStandalone() {
            return standalone;
        }

        public void setStandalone(Config standalone) {
            this.standalone = standalone;
        }

        public Config getSentinel() {
            return sentinel;
        }

        public void setSentinel(Config sentinel) {
            this.sentinel = sentinel;
        }

        public Config getCluster() {
            return cluster;
        }

        public void setCluster(Config cluster) {
            this.cluster = cluster;
        }
    }


    public static class Config {

        private String host;
        private int port;
        private int timeout;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public int getTimeout() {
            return timeout;
        }

        public void setTimeout(int timeout) {
            this.timeout = timeout;
        }

    }

}
