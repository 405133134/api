package api;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import org.nutz.resource.Scans;

public class Test implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws ClassNotFoundException {
		
		Class<?> class1=Class.forName("api.Test");
		Field[] field = class1.getDeclaredFields();
		
		for (int i = 0; i < field.length; i++) {
            // 权限修饰符
            int mo = field[i].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = field[i].getType();
            System.out.println(priv + " " + type.getName() + " " + field[i].getName() + ";");
        }
	}

}
