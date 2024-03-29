package com.bingoabin.proxy;

/**
 * @author xubin03
 * @date 2021/5/19 9:09 下午
 */
public class SumClass {
	/**
	 #JDK动态代理：利用反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用 InvokeHandler来处理。
	 #CGlib动态代理：利用ASM（开源的Java字节码编辑库，操作字节码）开源包，将代理对象 类的class文件加载进来，通过修改其字节码生成子类来处理。
	 #区别：JDK代理只能对实现接口的类生成代理；CGlib是针对类实现代理，对指定的类生成 一个子类，并覆盖其中的方法，这种通过继承类的实现方式，不能代理final修饰的类。

	 #总结：
	 1.JDK代理使用的是反射机制实现aop的动态代理，CGLIB代理使用字节码处理框架asm， 通过修改字节码生成子类。所以jdk动态代理的方式创建代理对象效率较高，执行效率较 低，cglib创建效率较低，执行效率高；
	 2.JDK动态代理机制是委托机制，具体说动态实现接口类，在动态生成的实现类里面委托 hanlder去调用原始实现类方法，CGLIB则使用的继承机制，具体说被代理类和代理类是继 承关系，所以代理类是可以赋值给被代理类的，如果被代理类有接口，那么代理类也可以赋 值给接口。

	 #JDK的动态代理（依赖于接口）
	 1. 在Java的动态代理机制中，有两个重要的类或接口，一个是InvocationHandler接 口，另一个是Proxy类。
	 2. InvocationHandler接口是给动态代理类实现的，负责处理被代理对象的操作
	 3. Proxy类是用来创建动态代理类实例对象的，只有得到这个对象，才能调用需要代 理的方法。
	 4. 动态代理的代理类是在静态代理类上进行修改，将动态代理类实现 InvocationHandler接口，重写Invoke方法，Invoke方法通过传入的被代理类方法和 参数来执行。

	 #JDK动态代理和Cglib动态代理的区别：
	 1. JDK动态代理是实现了被代理对象的接口，Cglib是继承了被代理对象。
	 2. Cglib因为是继承机制，所以无法代理被final修饰的方法。
	 3. JDK和Cglib都是在运行期间生产字节码，JDK是直接写class字节码，Cglib使用 ASM框架写class字节码；cglib代理实现更复杂，生成代理类比JDK效率低。
	 4. JDK调用代理方法，是通过反射实现机制调用，cglib是通过Fashclass机制直接调 用方法，效率更高。
	 #Fastcalss机制：为代理类和被代理类个生成一个class，这个class会为代理类或被代理类的方法分配一个 index。这个index当做一个入参，Fashclass就可以直接定位要调用的方法，并直接进行调用。这样 省去了反射调用，所以效率高。
	 */

	/**
	 #JDK动态代理和CGLIB动态代理的区别
	 #SpringAOP中的动态代理主要有两种方式，JDK动态代理和CGLIB动态代理：
	 1.JDK动态代理只提供接口的代理，不支持类的代理。核心InvocationHandler接口和Proxy类，InvocationHandler通过invoke()方法反射来调用目标类中的代码，动态地将横切逻辑和业务编织在一起；接着，Proxy利用InvocationHandler动态创建一个符合某一接口的的实例,生成目标类的代理对象。
	 2.如果代理类没有实现InvocationHandler接口，那么SpringAOP会选择使用CGLIB来动态代理目标类。CGLIB（CodeGenerationLibrary），是一个代码生成的类库，可以在运行时动态的生成指定类的一个子类对象，并覆盖其中特定方法并添加增强代码，从而实现AOP。CGLIB是通过继承的方式做的动态代理，因此如果某个类被标记为final，那么它是无法使用CGLIB做动态代理的。
	 #静态代理与动态代理区别在于生成AOP代理对象的时机不同，相对来说AspectJ的静态代理方式具有更好的性能，但是AspectJ需要特定的编译器进行处理，而SpringAOP则无需特定的编译器处理。
	 */

	/**
	 #动态代理为什么使用反射  不使用继承?
	 #这个问题有坑啊，动态代理可以用继承和反射都可以实现。
	 #JDK动态代理：利用反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用 InvokeHandler来处理。
	 #CGlib动态代理：利用ASM（开源的Java字节码编辑库，操作字节码）开源包，将代理对象 类的class文件加载进来，通过修改其字节码生成子类来处理。
	 #区别：JDK代理只能对实现接口的类生成代理；CGlib是针对类实现代理，对指定的类生成 一个子类，并覆盖其中的方法，这种通过继承类的实现方式，不能代理final修饰的类。

	 #总结：
	 1.JDK代理使用的是反射机制实现aop的动态代理，CGLIB代理使用字节码处理框架asm， 通过修改字节码生成子类。所以jdk动态代理的方式创建代理对象效率较高，执行效率较 低，cglib创建效率较低，执行效率高；
	 2.JDK动态代理机制是委托机制，具体说动态实现接口类，在动态生成的实现类里面委托 hanlder去调用原始实现类方法，CGLIB则使用的继承机制，具体说被代理类和代理类是继 承关系，所以代理类是可以赋值给被代理类的，如果被代理类有接口，那么代理类也可以赋 值给接口。

	 #JDK的动态代理（依赖于接口）
	 1. 在Java的动态代理机制中，有两个重要的类或接口，一个是InvocationHandler接 口，另一个是Proxy类。
	 2. InvocationHandler接口是给动态代理类实现的，负责处理被代理对象的操作
	 3. Proxy类是用来创建动态代理类实例对象的，只有得到这个对象，才能调用需要代 理的方法。
	 4. 动态代理的代理类是在静态代理类上进行修改，将动态代理类实现 InvocationHandler接口，重写Invoke方法，Invoke方法通过传入的被代理类方法和 参数来执行。

	 #JDK动态代理和Cglib动态代理的区别：
	 1. JDK动态代理是实现了被代理对象的接口，Cglib是继承了被代理对象。
	 2. Cglib因为是继承机制，所以无法代理被final修饰的方法。
	 3. JDK和Cglib都是在运行期间生产字节码，JDK是直接写class字节码，Cglib使用 ASM框架写class字节码；cglib代理实现更复杂，生成代理类比JDK效率低。
	 4. JDK调用代理方法，是通过反射实现机制调用，cglib是通过Fashclass机制直接调 用方法，效率更高。
	 #Fastcalss机制：为代理类和被代理类个生成一个class，这个class会为代理类或被代理类的方法分配一个 index。
	 #这个index当做一个入参，Fashclass就可以直接定位要调用的方法，并直接进行调用。这样 省去了反射调用，所以效率高。
	 */
}
