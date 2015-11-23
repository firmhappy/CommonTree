# CommonTree
使用前需要做以下几项准备工作：  
	1、配置数据库，并创建数据表；  
	2、继承com.bean.Vo类，Vo类已经包含了插件运行所必须的一些参数，可以继承此类，并加入自定义的参数，同时设置getter和setter。除此之外，还需要创建PO类（可以通过Hibernate反向生成）。PO类的类名与表名相同，Vo类的类名为：PO类的类名+Vo；  
***
打开index.jsp页面后，点击load按钮，会提示输入类名，即PO类的类名和页面大小（节点分页的页面大小）；  
![index页面](http://i.imgur.com/2l5h1MV.png)

![输入类名和页面大小](http://i.imgur.com/gU7JvXF.png)

load成功后会显示根节点，并在右侧显示该节点的所有数据  

![Load成功](http://i.imgur.com/pwkzTrB.png)

在所有数据中，id（节点唯一标识）、count（子节点总数）、page（当前页，从1开始）、pageSize（页面大小）、path（到根节点路径，“，”分隔节点ID）、isParent（是否为父节点）、className（类名）几项不可更改，由插件维护；  
点击节点，会在右侧显示相应的节点数据。插件加载节点的方式为动态加载，即，只有在打开节点时，会在加载节点的子节点或在翻页时，加载下一页的节点；  

![节点数据展示](http://i.imgur.com/YE6FmsT.png)

插件提供对节点的增删改查功能，其中删除节点时会删除节点及其子节点。