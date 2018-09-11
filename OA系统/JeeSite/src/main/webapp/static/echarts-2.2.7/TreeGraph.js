function treeMenu(a){
    //列表map形式
    this.tree=a||[];
    this.groups={};
	//存放id与对应的name映射
	this.nameMap={}
	//得到每个点对应的层次,为了后期进行布局
	this.levelMap={}
	//样式设计
	this.style={"symbolSize":[60,50,40,30,20],"value":[8,6,4,2,1]}
};
treeMenu.prototype={
    init:function(pid){
        this.group();
		this.MapNamebyId();
		this.setIdLevel(pid);
        return this.rescusive(pid);
    },
    group:function(){
        for(var i=0;i<this.tree.length;i++){
            //存在该grops则直接添加
            if(this.groups[this.tree[i].pId]){
                this.groups[this.tree[i].pId].push(this.tree[i]);
            }else{
                this.groups[this.tree[i].pId]=[];
                this.groups[this.tree[i].pId].push(this.tree[i]);
            }
        }
    },
	//得到每个点的层次
	setIdLevel:function(pid){
		var level=1;
		this.levelMap[pid]=level;
		var gs=this.groups[pid];
		//str=JSON.stringify(gs)
		//alert("json:"+str)
		var temp=[]
		while(gs){
			level++;
			if(gs==null||gs==undefined||gs.length==0)
				break;
			temp=[]
			for(var i=0;i<gs.length;i++){
				var myid=gs[i]["id"];
				this.levelMap[myid]=level;
				subgs=this.groups[myid];
				if(subgs instanceof Array &&subgs!=null){
				for(var j=0;j<subgs.length;j++){
				temp.push(subgs[j]);
				}
				}
			}
			gs=temp;
		}
		
	},
	//根据所在层次设计不同大小的样式
	getStyleById:function(id){
		var level=this.levelMap[id]
		if(level>=5)
			level=5;
		var symbolize=0
		var value=0
		symbolize=this.style['symbolSize'][level-1]
		value=this.style['value'][level-1]
		var styleValue={}
		styleValue['symbolSize']=symbolize
		styleValue['value']=value
		return styleValue
	},
    MapNamebyId:function(){
		for(var i=0;i<this.tree.length;i++){
			map=this.tree[i]
			this.nameMap[map["id"]]=map["name"]
		}
	},
	//设置节点属性
	setNode:function(node,name,symbolize,value,children){
		    node['name']=name;
			node['symbolSize']=symbolize;
			node['value']=value
			node['children']=children
			return node;
	},
	//<span style="color:#ff0000;">
	rescusive:function (number){//这里是构建数据源的重点</span>
		var data=[]
		var node={}
		var styleValue={}
		//某个节点下的子节点
		var a=this.groups[number];
		var nodeName=this.nameMap[number];
		if(a==null||a==undefined){
			styleValue=this.getStyleById(number)
			//设置节点
			this.setNode(node,nodeName,styleValue['symbolSize'],styleValue['value'],[])
			return node;
		}
		for(var i=0;i<a.length;i++){
			children=this.rescusive(a[i].id);
			data.push(children);
		}
		styleValue=this.getStyleById(number)
		this.setNode(node,nodeName,styleValue['symbolSize'],styleValue['value'],data)
		return node;
	},
	//创建组织结构图
	createTreeVisual:function(myChart,title,data){
		var option = {
        title : {
        text: title
       },
        tooltip : {
        trigger: 'item',
        formatter: "{b}"
        },
      toolbox: {
        show : true,
        feature : {
            saveAsImage : {show: true}
         }
      },
      calculable : false,
       series : [
         {
            name:'树图',
            type:'tree',
            orient: 'vertical',  // vertical horizontal
            rootLocation: {x: 'center',y: 10}, // 根节点位置  {x: 'center',y: 10}
            nodePadding: 80,
            symbol: 'circle',
            data:data
        }]//series
	}	
     myChart.setOption(option);
	}
}
//得到数据
function getData(zNodes){
	var mytree=new treeMenu(zNodes)
	treeData=mytree.init(0)
	data=[]
	data.push(treeData)
	return data;
	//str=JSON.stringify(menu);
	//alert("responsing json:"+str)
}
function createTreeV(myChart,title,znodes){
	var mytree=new treeMenu(znodes)
	treeData=mytree.init(7)
	data=[]
	data.push(treeData)
	mytree.createTreeVisual(myChart,title,data)
}


