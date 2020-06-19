package org.amix.test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

import org.amix.entity.User;
import org.amix.service.UserService;

/**
 * 1、Reflection和MethodHandle机制本质上都是在模拟方法调用，但是Reflection是在模拟Java代码层次的方法调用，而MethodHandle是在模拟字节码层次的方法调用
 * 2、Reflection中的Method对象远比MethodHandle机制中的MethodHandle对象所包含的信息要多。前者是方法在Java一端的全面映像，包含了方法的签名、描述符以及方法属性表中各种属性的Java端表示方式，还包含有执行权限等的运行期信息。而后者仅仅包含着与执行该方法相关的信息。通俗的话说，Reflection是重量级，而MethodHandle是轻量级
 * 3、由于MethodHandle是对字节码的方法指令调用的模拟，那理论上虚拟机在这方面做的各种优化（如方法内联），在MethodHandle上也应当可以采用类似思路去支持（但目前实现还不完善）。而通过反射去调用方法则不行
 * 4、Reflection
 * API的设计目标是只为Java语言服务的，而MethodHandle则设计为可服务于所有Java虚拟机之上的语言，其中也包括了Java语言
 * 
 * @author Administrator
 *
 */
public class testMethodhandle {
	public static void main(String[] args) throws Throwable {

		Tuser tuser = new Tuser();

		/** 此乃反射调用 */
		Class cls = Tuser.class;
		//	getMethod只能调用public声明的方法，而getDeclaredMethod基本可以调用任何类型声明的方法
		Method testMethod = cls.getMethod("show", String.class);
		testMethod.invoke(tuser, "111");
		/** 此乃反射调用 结束 */

		/** 以下是句柄调用 */
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		/**
		 * 方法签名不可变对象，是对方法的一个映射，包含返回值和参数类型。在lookup时也是通过它来寻找的。
		 * 每个方法句柄都有一个MethodType实例，用来指明方法的返回类型和参数类型。 参数为返回值类型、参数类型 单个参数，也可以多个参数
		 */
		MethodType mt = MethodType.methodType(User.class, Long.class);
		try {
			// 声明定义方法句柄，通过lookup对象得到方法句柄，参数为方法所在的类、方法的名称、所匹配的方法签名
			MethodHandle mh = lookup.findVirtual(Tuser.class, "show", mt);
			// 通过句柄调用方法，invoke允许更加松散的调用方式，invokeExact为全匹配
			User user = (User) mh.invokeExact(tuser, "11");
			System.out.print(user.getName());

			// 查找静态方法
			MethodHandle aStatic = MethodHandles.lookup().findStatic(User.class, "show", mt);

		} catch (NoSuchMethodException | IllegalAccessException e) {
			e.printStackTrace();
		}
		/** 以下是句柄调用结束 */
	}

	
}
