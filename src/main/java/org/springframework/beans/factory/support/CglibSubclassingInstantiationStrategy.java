package org.springframework.beans.factory.support;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * 使用 cglib 进行 Bean 实例化
 *
 * @author derekyi
 * @date 2020/11/23
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    /**
     * 使用CGLIB动态生成子类
     *
     * @param beanDefinition bean定义
     * @return 代理类
     * @throws BeansException 代理创建异常
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback((MethodInterceptor) (obj, method, argsTemp, proxy) -> proxy.invokeSuper(obj, argsTemp));
        return enhancer.create();
    }
}
