package jvm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;

public class HelloClassLoader  extends ClassLoader{
	
	public static void main(String[] args) throws Exception {
		Object hello = new HelloClassLoader().findClass("Hello").newInstance();
		Method med = hello.getClass().getMethod("hello");
		med.invoke(hello);
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] data = this.handleData(getBytes());
		return defineClass(name, data, 0, data.length);
	}
	
	public byte[] handleData(byte[] data){
		
		int len = data.length;
		byte[] res = new byte[len];
		for(int i = 0;i<len;i++){
			res[i] = (byte) (255 - data[i]);
		}
		return res ;
	}
	
	public byte[] getBytes(){
		byte[] data = null;
		try {
			FileInputStream input = new FileInputStream("E:/jvm/src/jvm/Hello.xlass");
			int len = input.available();
			data = new byte[len];
			input.read(data);
			input.close();		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data ;
	}
}
