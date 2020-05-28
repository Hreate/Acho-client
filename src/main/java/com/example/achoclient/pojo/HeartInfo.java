package com.example.achoclient.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class HeartInfo {
    @JSONField
    private String status;

    @JSONField
    private String name;

    @JSONField
    private String host;

    @JSONField
    private String port;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HeartInfo heartInfo = (HeartInfo) o;

        if (status != null ? !status.equals(heartInfo.status) : heartInfo.status != null) return false;
        if (name != null ? !name.equals(heartInfo.name) : heartInfo.name != null) return false;
        if (host != null ? !host.equals(heartInfo.host) : heartInfo.host != null) return false;
        return port != null ? port.equals(heartInfo.port) : heartInfo.port == null;
    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (host != null ? host.hashCode() : 0);
        result = 31 * result + (port != null ? port.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HeartInfo{" +
                "status=" + status +
                ", name='" + name + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
