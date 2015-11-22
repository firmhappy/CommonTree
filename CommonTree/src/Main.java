import java.lang.reflect.Field;
import java.util.List;

import com.bean.Node;
import com.bean.NodeVo;
import com.bean.Vo;
import com.service.TreeService;
import com.service.TreeServiceImpl;


public class Main {
	public static void main(String[] args) {
		TreeService ts=new TreeServiceImpl();
		Vo vo=new NodeVo("superv","sv",0,0,"SuperV","0","false",null,null,0);
		vo=ts.addEntity(vo);
		System.out.println(vo.getId());
	}

}
