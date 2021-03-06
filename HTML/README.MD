#switch 
    switch 选择语句 case后面只能跟常量值
        ps：jdk1.5 byte ,short ,int ,char
            jdk1.6 enum（枚举类型）
            jdk1.7 string 
#在静态方法中，不能存在this、super关键字(因为this，super 都是创建对象后的)
#静态方法中只能访问其它的静态方法或静态变量（非静态的成员在静态方法中不能使用）
#构造代码块的特点：
     是随着对象的创建而去执行的代码块。在创建对象时，会调用构造函数，而在执行构造函数中所书写的代码之前，存在隐式三步：
 1，执行super语句
 2，显式初始化堆空间中所有的非静态成员变量
 3，执行构造代码块
     注意：只要创建一次对象，就会执行一次构造代码块。 
 构造代码块的作用： 把多个构造函数中相同的代码提取出来，书写在构造代码块。
 
#类加载过程：
 1，JVM会先去方法区中找有没有相应类的.class存在。如果有，就直接使用；如果没有，则把相关类的.class加载到方法区
 2，在.class加载到方法区时，会分为两部分加载：先加载非静态内容，再加载静态内容
 3，加载非静态内容：把.class中的所有非静态内容加载到方法区下的非静态区域内
 4，加载静态内容：
 4.1、把.class中的所有静态内容加载到方法区下的静态区域内
 4.2、静态内容加载完成之后，对所有的静态变量进行默认初始化
 4.3、所有的静态变量默认初始化完成之后，再进行显式初始化
 4.4、当静态区域下的所有静态变量显式初始化完后，执行静态代码块
 5，当静态区域下的静态代码块，执行完之后，整个类的加载就完成了。
 
# 对象创建过程：
 1，在堆内存中开辟一块空间
 2，给开辟空间分配一个地址
 3，把对象的所有非静态成员加载到所开辟的空间下
 4，所有的非静态成员加载完成之后，对所有非静态成员变量进行默认初始化
 5，所有非静态成员变量默认初始化完成之后，调用构造函数
 6，在构造函数入栈执行时，分为两部分：先执行构造函数中的隐式三步，再执行构造函数中书写的代码
 6.1、隐式三步：
 1，执行super语句     （继承时讲解）
 2，对开辟空间下的所有非静态成员变量进行显式初始化
 3，执行构造代码块
    6.2、在隐式三步执行完之后，执行构造函数中书写的代码
 7，在整个构造函数执行完并弹栈后，把空间分配的地址赋值给一个引用对象
 
#方法(函数)的特点：
     1，重载： 发生在同一个类中
        方法名相同、参数列表不相同。和返回值没有关系
     2，重写： 发生在子父类中
           方法名相同、参数列表相同、返回值相同 （子父类中的方法一模一样）
           2，子类中重写方法的访问权限必须大于或等于父类中方法的访问权限
#抽象类具有特点：
     1，抽象类不能实例化（不能new对象）[抽象类的作用：仅仅就是为了不让父类创建对象]
     2，子类在继承抽象类后，需要把抽象类中所有的抽象方法全部重写完成之后，子类才可以创建对象
     3，抽象方法只能包含在抽象类中
# 为什么static不能和abstract一起使用？
       abstract表示方法没有方法体(没有具体的功能实现);
       static修饰的方法会随着类的加载而存在，可以使用类名调用。
       使用类名去调用没方法体的抽象方法，没有任何意义
  
#final修饰符：表示最终的
    final修饰类表示类不能被继承，abstract修饰的类必须被子类继承
    final修饰的方法表示不能被重写，而abstract修饰的方法必须被重写
    private修饰符：表示私有的
    private修饰方法表示该方法不会参与重写，而abstract修饰的方法必须被重写
#接口的多继承
    类与类之间是继承关系（需要符合：is-a关系）
    类和接口之间是实现关系（符合：like-a关系）
    接口和接口之间是继承关系（可以多继承）
    
#适配器模式（没有抽象方法的抽象类）： 
   在接口中可以定义N多个抽象方法，而实现了接口的子类，需要重写接口所有的方法。但是，如果子类中只能接口中的一个方法时，怎么办？
    使用适配器模式解决
    #interface A       class B implements A      class  C  extend B 
    
#接口和抽象类的区别：
1，接口中只能定义抽象方法；抽象类中除了具有抽象方法外，还可以定义其它方法
2，接口可以多实现；而抽象类只能单一继承
3，接口用来描述事物扩展功能；抽象用来描述事物的共性内容（描述不清楚）
4，接口中没有构造函数；抽象类中具有构造函数

#什么是多态？
    多态其实就是一个事物以多种形式表现出来。
    多态时，子父类中存在一模一样的成员变量时，编译时以等号左边作为参考，运行时也是以等号左边作为参考
    多态时，子父类中存在一模一样的成员方法时，编译时以等号左边作为参考，运行时是以等号右边作为参考
    在使用多态时，子父类中存在一模一样的静态方法时：编译时期是以等号左边作为参考，运行时期也是以等号左边作为参考
#避免类型转换异常
在开发中，对于类型转换时，通常会使用一个关键字来避免ClassCastException异常
使用关键字instanceof避免类型转换异常：if（object1 onstanceof  object2）
#String类的方法总结
      String类中方法可以划分为：
      获取方法：
         int  length();  获取字符串的长度
         char  charAt(int index) 根据给定的角标获取字符串中对应角标下的字符
         int  indexOf(int ch)根据给定的字符获取字符在字符串第一次出现角标
         int  indexOf(String str)根据给定的字符串获取子字符串在字符串对象中第一次出现的角标
         int  indexOf(int ch,int fromindex)根据给定的字符，从指定的角标开始获取字符在字符串中第一次出现的角标
         int indexOf(String str,int index)
         int  lastIndexOf(String str) 根据给定的字符串获取子字符串在字符串对象中最后一次出现的角标
         int  lastIndexOf(int ch,int index) 根据给定的字符，从指定的角标开始获取字符在字符串中最后一次出现的角标
        
      判断方法：
       boolean  endsWith(String str)判断字符串对象是否以给定的字符串作为结尾
       boolean  startsWith(String  str) 判断字符串对象是否以给定的字符串作为开头
       boolean  equals(Object obj) 判断字符串对象是否和给定的字符串相同（以存储内容）
                                判断时区分大小写字母
       booolean equalsIgnoreCase(String str) 判断字符串对象是否和给定的字符串相同
                                 判断是不区分大小写字母
       boolean  isEmpty() 判断字符串对象是否为空。（空表示空字符串） 
                        可以使用length()==0代替  或 equals(“”)
       boolean  contains(String str)判断字符串对象中是否包含指定的字符或子字符串
      
      截取方法：
       String  substring(int index) 从指定的角标开始，截取字符串对象中的子字符串（截取到末尾）
       String  substring(int startIndex,int endIndex) 截取字符串对象中的子字符串，从指定的开始角标到指定的结束角标（包头不包尾）
 
      切割方法：
      String[]  split(String str) 根据给定的分隔符[字符串参数]把字符串对象进行分隔，分隔为多个字符串
 
      转换方法：
      String  toUpperCase() 把字符串转为大写
      String  toLowerCase() 把字符串转为小写
      把基本类型数据转为字符串（静态方法）
      String valueOf(基本类型数据[不包含byte])  （也可以是Object类型数据）
      把字符串对象转为字节数组：
      byte[]  getBytes()
      把字符串对象转为字符数组：
      char[]  toCharArray()
      把字节数组转为字符串：
      new String(byte[])  //使用构造方法转换
      把字符数组转为字符串：
      new String(char[])//使用构造方法转换
      String.valueOf(char[]) //使用静态方法valueOf
 
      其它方法：
     String  trim()  去除字符串对象两边的空格  例:”   N  BA  ”   trim()后：”N  BA”
     String  replace(char oldChar,char newChar) 指定字符串对象中的字符，使用一个新的字符替换
#Iterator迭代器中的常用方法：
 boolean  hasNext()  判断迭代器对象所指向的集合中是否还有下一个元素存在
 Object  next()   返回当前迭代器对象指向的集合中的元素
 void   remove() 删除当前迭代器对象指向的集合中的元素
 
#Collection集合（接口）
    |----List集合（接口）：存取元素有序、可以存储重复元素、可以存储null、有角标，可以精确控制集合中的每一个元素
         |-----ArrayList集合（类）：实现了List接口。方法都是围绕着角标操作的
                               底层使用可变数组
                               查询遍历效率比较高，增删的效率比较低
                               属于线程不安全的集合类
 
         |-----LinkedList集合（类）：实现了List接口。方法都是围绕着头和尾来设计的
                                底层使用链表结构
                                查询遍历效率比较低，增删的效率比较高
                                属于线程不安全的集合类
    |----Set集合（接口）：存取元素无序、不能存储重复元素、只能存储一个null、没有角标，只能通过迭代器遍历获取元素。该集合中的方法全部来自于Collection集合中
         |-----HashSet集合（类）：实现Set接口
                               底层使用哈希表结构
                               查询元素和添加不重复元素的效率比较快
                |-----LinkedHashSet集合（类）：HashSet集合类的子类。 存取元素有序
                                           底层使用链表+哈希表结构
                                          查询、增删效率比较高
         |-----TreeSet集合（类）：实现Set接口。
                               底层使用二叉树结构
                               存储的元素会按照一定的规则自动排序。

#在使用通配符时通配符存在一些限定：
 上限：<? extends 父类/接口>  表示?只能是父类或接口的子类 （包含父类）
 下限：<? super 子类> 表示?只能是子类的父类或接口对象 （包含子类）
#Map集合：
 |----Hashtable类：底层使用哈希表结构。存储的key-value不能为null(不能使用null作为key或value的值)。 现被HashMap代替。
       |-----Properties类：用来存储key-valur的键值属性集。（IO流中讲解）
 可以通过IO流来操作该属性集中的key或value
     |----HashMap类：底层使用哈希表结构。存取元素无序、可以存储null(key对象只能有一个null)
         |----LinkedHashMap类：底层使用链表+哈希表结构。存取元素有序
     |----TreeMap类：底层使用二叉树结构。存储的元素会自动排序。
 两种排序：自然排序、比较器排序
 
#wait()和sleep()方法的区别：
  wait()属于对象锁的；sleep方法属于线程的
  
  sleep()方法中必须指定毫秒值；wait()方法中可以指定毫秒值也可以不指定
  
  sleep()只是让运行的线程暂时的等待，当时间一到，就自动转为运行状态或阻塞状态
  wait()会让运行的线程一直处于等待状态，只有唤醒后，才会转为运行状态或阻塞状态
  
  wait()在程序中只能书写在同步代码块中；sleep()可以书写在同步代码块中也可以书写在外部
  
#OSI网络模型有七层：
 1.物理层：主要定义物理设备标准，如网线的接口类型、光纤的接口类型、各种传输介质的传输速率等。它的主要作用是传输比特流（就是由1、0转化为电流强弱来进行传输,到达目的地后在转化为1、0，也就是我们常说的数模转换与模数转换）。这一层的数据叫做比特。 
 2.数据链路层：主要将从物理层接收的数据进行MAC地址（网卡的地址）的封装与解封装。常把这一层的数据叫做帧。在这一层工作的设备是交换机，数据通过交换机来传输。 
 3.网络层：主要将从下层接收到的数据进行IP地址（例192.168.0.1)的封装与解封装。在这一层工作的设备是路由器，常把这一层的数据叫做数据包。 
 4.传输层：定义了一些传输数据的协议和端口号（WWW端口80等），如：TCP（传输控制协议，传输效率低，可靠性强，用于传输可靠性要求高，数据量大的数据），UDP（用户数据报协议，与TCP特性恰恰相反，用于传输可靠性要求不高，数据量小的数据，如QQ聊天数据就是通过这种方式传输的）。 主要是将从下层接收的数据进行分段和传输，到达目的地址后再进行重组。常常把这一层数据叫做段。 
 5.会话层：通过传输层（端口号：传输端口与接收端口）建立数据传输的通路。主要在你的系统之间发起会话或者接受会话请求（设备之间需要互相认识可以是IP也可以是MAC或者是主机名） 
 6.表示层：主要是进行对接收的数据进行解释、加密与解密、压缩与解压缩等（也就是把计算机能够识别的东西转换成人能够能识别的东西（如图片、声音等）。 
 7.应用层： 主要是一些终端的应用，比如说FTP（各种文件下载），WEB（IE浏览），QQ之类的（可以把它理解成我们在电脑屏幕上可以看到的东西．就是终端应用）。
 
#1、UDP协议（称为：数据报文协议）
 属于面向无连接，传输速度比较快，数据不安全，在传输数据时有数据大小限定：在64K以下
 适用于对传递数据没有要求，传输数据量比较小的
 
# 2、TCP协议（称为：数据传输协议【目前最常用的一种协议】）
 属于面向有连接，需要经过三次握手之后建立了连接通道后才可以传输数据。
 特点：传输速度比较慢、数据安全，传输数据时没有大小限定
#解析xml的两种方式
    dom4j ： 需要先获取到xml的跟标签root 然后再一次获取
    xpath ： 可以快速定位到某一标签
#两种约束
    DTD约束，Schme约束
    
    

    