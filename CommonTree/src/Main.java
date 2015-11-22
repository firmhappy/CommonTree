import java.lang.reflect.Field;
import java.util.List;

import com.bean.Node;
import com.bean.NodeVo;
import com.service.TreeService;
import com.service.TreeServiceImpl;


public class Main {
	public static void main(String[] args) {
		TreeService ts=new TreeServiceImpl();
		NodeVo nodevo=new NodeVo(1011,"VVTEST","vtest","0",0,"V");
		NodeVo vo=(NodeVo) ts.updateEntity(nodevo);
		System.out.println("ADD:"+vo.getName());
	}

}
