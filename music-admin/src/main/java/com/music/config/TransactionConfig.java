package com.music.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置事务
 *
 * @author xpq2013@aliyun.com
 * @version 1.0.0
 * @date 2018/4/24
 */
@Aspect
@Configuration
public class TransactionConfig {

    /**
     * 定义切入点
     */
    private static final String POINTCUT_EXPRESSION = "execution (* com.music.service.*.*.*(..))";

    private final PlatformTransactionManager transactionManager;

    @Autowired
    public TransactionConfig(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Bean
    public TransactionInterceptor txAdvice() {
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        RuleBasedTransactionAttribute readOnly = new RuleBasedTransactionAttribute();
        readOnly.setReadOnly(true);
        readOnly.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
        RuleBasedTransactionAttribute required = new RuleBasedTransactionAttribute();
        required.setRollbackRules(
                Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        required.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        required.setTimeout(10);
        // 定义方法前缀
        Map<String, TransactionAttribute> txMap = new HashMap<>(10);
        txMap.put("add*", required);
        txMap.put("generate*", required);
        txMap.put("save*", required);
        txMap.put("insert*", required);
        txMap.put("update*", required);
        txMap.put("edit*", required);
        txMap.put("delete*", required);
        txMap.put("remove*", required);
        txMap.put("get*", readOnly);
        txMap.put("select*", readOnly);
        txMap.put("find*", readOnly);
        source.setNameMap(txMap);
        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
