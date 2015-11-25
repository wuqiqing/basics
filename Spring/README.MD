#创建bean的四种方式
    无参构造、
    静态工厂（用静态方法 返回对象 factory-method）、
    实例工厂（bean ：返回的对象名称 factory-bean ：实体类的bean factory-method ：方法）、
    factorybean （底层用的）
#BeanFactory和FactoryBean的区别？ 
 BeanFactory：是一个工厂（其实是构建了一个spring上下文的环境，容器），用来管理和获取很多Bean对象，
 FactoryBean：是一个Bean生成工具，是用来获取一种类型对象的Bean，它是构造Bean实例的一种方式。
#依赖注入的三种方式
    第一种：构造器参数注入
    第二种：setter方法属性注入(setter方法的规范-JavaBean规范)
    第三种：接口注入
#aop 面向切面编程  是面向对象编程的延伸
   aop实现是基于动态代理实现的   jdk动态代理 跟cglib动态代理 
   1、Spring AOP 优先对接口进行代理 （使用Jdk动态代理）
   2、如果目标对象没有实现任何接口，才会对类进行代理 （使用cglib动态代理）
#动态代理和静态代理区别？ 
 动态代理：在虚拟机内部，运行的时候，动态生成代理类(运行时生成，runtime生成) ，并不是真正存在的类， Proxy$$ （Proxy$$Customer）
 静态代理：存在代理类 （例如：struts2 Action的代理类 ActionProxy）
#【AspectJ类型匹配的通配符】
 *：匹配任何数量字符；
 ..：匹配任何数量字符的重复，如在类型模式中匹配任何数量子包；而在方法参数模式中匹配任何数量参数。
 +：匹配指定类型的子类型；仅能作为后缀放在类型模式后边。