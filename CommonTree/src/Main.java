import java.lang.reflect.Field;
import java.util.List;

import com.bean.Node;
import com.bean.NodeVo;
import com.service.TreeService;
import com.service.TreeServiceImpl;


public class Main {
	public static void main(String[] args) {
		TreeService ts=new TreeServiceImpl();
		NodeVo nodevo=new NodeVo();
		nodevo.setId(0);
		List list=ts.getChildren(nodevo);
		for(Object node:list){
			try {
				Field nnf=node.getClass().getDeclaredField("name");
				nnf.setAccessible(true);
				System.out.println(nnf.get(node));
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
