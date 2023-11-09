package com.lmh.mp.tenant.config;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.schema.Column;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MybatisInterceptorConfiguration {

    /**
     * 配置mp最外部的拦截器
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(tenantLineInnerInterceptor());
        return mybatisPlusInterceptor;
    }

    @Bean
    public TenantLineInnerInterceptor tenantLineInnerInterceptor() {
        TenantLineInnerInterceptor tenantLineInnerInterceptor = new TenantLineInnerInterceptor();
        tenantLineInnerInterceptor.setTenantLineHandler(new MyTenantLineHandler());
        return tenantLineInnerInterceptor;
    }

    static class MyTenantLineHandler implements TenantLineHandler {

        @Override
        public Expression getTenantId() {
            return new StringValue(LoginContext.getTenantId());
        }

        @Override
        public String getTenantIdColumn() {
            return "tenant_id";
        }

        @Override
        public boolean ignoreTable(String tableName) {
            TableInfo tableInfo = TableInfoHelper.getTableInfo(tableName);
            if (tableInfo != null) {
                return tableInfo.getFieldList().stream().noneMatch(f -> getTenantIdColumn().equals(f.getColumn()));
            }
            return false;
        }

        @Override
        public boolean ignoreInsert(List<Column> columns, String tenantIdColumn) {
            return false;
        }
    }
}
