package kr.or.ddit.reflection;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

import kr.or.ddit.reflect.ReflectionTest;


/**
 * 리플렉션이란? (java.lang.reflect)
 * 	객체로부터 그 객체의 타입(유형), 속성, 행동에 관한 정보를 역으로 추적하는 과정.
 *
 */
public class ReflectionDesc {
	public static void main(String[] args) {
		Object instance = ReflectionTest.getObject();
		System.out.println(instance);
		
		Class <?> instanceType = instance.getClass();
		System.out.printf("클래스 정보 : %s\n", instanceType.getName());
		Field[] fields = instanceType.getDeclaredFields();
		for(Field fld : fields) {
			// 전역변수의 타입
			Class<?> fldType = fld.getType();
			String fldName = fld.getName();
			
			try {
				
				// private -> public 으로 강제 변환
//				fld.setAccessible(true);
				
//				Object propertyValue = fld.get(instance);
				
				// 자바빈 규약에 따라
				PropertyDescriptor pd = new PropertyDescriptor(fldName, instanceType);
				
				Method getter = pd.getReadMethod();
				
//				member.getMem_id();
				
				Object propertyValue = getter.invoke(instance);
				
				System.out.printf("%s %s = %s;\n", fldType.getName(), fldName, propertyValue);
				
			} catch (IllegalArgumentException | IllegalAccessException | IntrospectionException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		Method[] methods =  instanceType.getMethods();
		for(Method mtd : methods) {
			// signature reflection
			Parameter[] parameters = mtd.getParameters();
			String mtdName = mtd.getName();
			Class<?> returnType = mtd.getReturnType();
			System.out.printf("%s %s(%s);\n", returnType.getName(), mtdName, Arrays.toString(parameters));
		}
		
	}
}
