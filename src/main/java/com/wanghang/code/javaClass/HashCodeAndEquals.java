package com.wanghang.code.javaClass;

import lombok.Builder;
import lombok.Data;

/**
 * Class类自定义hashCode()和equals()方法
 */
@Data
@Builder
public class HashCodeAndEquals {
    /**
     * 包名
     */
    private String packageName;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 是否自动安装
     */
    private boolean autoInstall;

    /**
     * 租户冗余字段
     */
    private Long tenantId;

    /**
     * 旧包名兼容
     */
    private String oldPackageName;

    @Override
    public int hashCode() {
        return (tenantId + deviceType + packageName).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashCodeAndEquals that = (HashCodeAndEquals) o;
        return packageName.equals(that.packageName) &&
                deviceType.equals(that.deviceType) &&
                tenantId.equals(that.tenantId);
    }
}
