#校验的目标属性实体类pojo一定提供getXXX方法
	全局校验 （校验当前Action 所有方法）	
	在Action类所在包，创建 Action类名-validation.xml 
#局部校验器
	名称  Action类名-action标签对应name属性值名称-validation.xml	
	动态方法访问的不可以使用xm局部校验器