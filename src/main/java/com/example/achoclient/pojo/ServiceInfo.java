package com.example.achoclient.pojo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 服务信息
 */

public class ServiceInfo {
    @JSONField
    private String name;

    @JSONField
    private String host;

    @JSONField
    private String port;

    @JSONField
    private Long aliveTime;

    @JSONField
    private Long renewTime;

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

    public Long getAliveTime() {
        return aliveTime;
    }

    public void setAliveTime(Long aliveTime) {
        this.aliveTime = aliveTime;
    }

    public Long getRenewTime() {
        return renewTime;
    }

    public void setRenewTime(Long renewTime) {
        this.renewTime = renewTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceInfo that = (ServiceInfo) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (host != null ? !host.equals(that.host) : that.host != null) return false;
        if (port != null ? !port.equals(that.port) : that.port != null) return false;
        if (aliveTime != null ? !aliveTime.equals(that.aliveTime) : that.aliveTime != null) return false;
        return renewTime != null ? renewTime.equals(that.renewTime) : that.renewTime == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (host != null ? host.hashCode() : 0);
        result = 31 * result + (port != null ? port.hashCode() : 0);
        result = 31 * result + (aliveTime != null ? aliveTime.hashCode() : 0);
        result = 31 * result + (renewTime != null ? renewTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceInfo{" +
                "name='" + name + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", aliveTime=" + aliveTime +
                ", renewTime=" + renewTime +
                '}';
    }
}
