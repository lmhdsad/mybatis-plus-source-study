package com.lmh.mp.tenant.config;

public class LoginContext {

    private static final ThreadLocal<String> tenantThreadLocal = new ThreadLocal<>();

    public static void setTenantId(String tenantId) {
        tenantThreadLocal.set(tenantId);
    }

    public static String getTenantId() {
        return tenantThreadLocal.get();
    }

    public static void removeTenantId() {
        tenantThreadLocal.remove();
    }
}
