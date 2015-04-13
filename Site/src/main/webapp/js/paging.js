Boat = {};
Boat.UI = {};

//翻页控件
Boat.UI.Paging = new Class({
    /*Events
        beforeLoad 读取数据前执行
        afterLoad 读取数据后执行
    */
    Implements: [Events, Options],    
    options:{
        url:'', //后台URL        
	    className:'paging',
	    startParamName : "start",
	    limitParamName : "limit",
	    total:'total', //数据源中记录数属性
	    limit:10, //每页记录数
	    //el:控件容器,showNumber:是否显示数字按钮,showText:是否显示页码
	    head:{el:document.body,showNumber: false,showText:true},
	    foot:{el:null,showNumber: true,showText:false}
    },
    
    initialize: function(options){        
        this.setOptions(options);
        this.index = 0; //当前页
        this.limit = this.options.limit;      
        this.startParamName = this.options.startParamName;
        this.limitParamName = this.options.limitParamName;
        this.head = new Element('span',{'class':this.options.className});
		$(this.options.head.el).empty();  
        this.head.injectInside($(this.options.head.el));
        this.loader = new Request.JSON({async:false,url:this.options.url,onComplete:this.onComplete.bind(this)});
        this.param = {}; //load参数
        if(this.options.foot.el){
            this.foot = this.head.clone();
            this.foot.injectInside($(this.options.foot.el));
        }
    },
    
    onComplete: function(data,text){    
        data = data||{};
        if(this.index==0){
        	
        	var pagingObjNames = this.options.total.split(".");
        	 
        	if(pagingObjNames.length == 1)
        		this.total = data[this.options.total]||0;
        	else {
        		var pagingObjName = pagingObjNames[0];
        		
        		var pagingObj = data[pagingObjName]||{};
        		this.total = pagingObj[pagingObjNames[1]]||0; //总记录数
        	}
            
        }        
        this.page = Math.ceil(this.total/this.limit); //总页数 
        
        this.create(this.head,this.options.head);
        
        if(this.foot){
            this.create(this.foot,this.options.foot);
        }
        
        this.fireEvent('afterLoad',[data,text]);
    },
  
    create: function(panel,options){
        panel.empty();
		var currentStartCount = this.index*this.limit+1;
		var currentEndCount =  (this.index+1)*this.limit;
		if (currentEndCount >= this.total) currentEndCount = this.total;
		var title = new Element("span", {"html":"显示&nbsp;"+currentStartCount+"-"+currentEndCount+"&nbsp;条&nbsp;&nbsp;&nbsp;共 "+this.total+" 条&nbsp;&nbsp;"});
		//上一页
		//panel.grab(title);
        if(this.index>0){
            var prev = new Element('a',{'html':'上一页','href':'javascript:void(null)','events':{'click':this.click.bind(this,this.index-1)}});
            panel.grab(prev);
        }else{
        	var prev = new Element('span',{'class':'disabled','html':'上一页'});
        	panel.grab(prev);
        }  
        
        if(options.showNumber){  
            var beginInx = this.index-2<0?0:this.index-2;
            var endIdx = this.index+2>this.page?this.page:this.index+2;
            
            if(beginInx>0) panel.grab(this.createNumber(0));
            if(beginInx>1) panel.grab(this.createNumber(1));
            if(beginInx>2) panel.grab(this.createSplit());        
            
            for(var i=beginInx;i<endIdx;i++){
                panel.grab(this.createNumber(i));
            }
            
            if(endIdx<this.page-2) panel.grab(this.createSplit());
            if(endIdx<this.page-1) panel.grab(this.createNumber(this.page-2));
            if(endIdx<this.page) panel.grab(this.createNumber(this.page-1));
        }   
        //下一页
        if(this.index<this.page-1){
            var next = new Element('a',{'html':'下一页','href':'javascript:void(null)','events':{'click':this.click.bind(this,this.index+1)}});
            panel.grab(next);
        }else{
        	var next = new Element('span',{'class':'disabled','html':'下一页'});
        	panel.grab(next);
        }  
        
        if(options.showText) panel.grab(this.createText());        
    },
    
    createNumber: function(i){
        var a = new Element('a',{'html':i+1,'href':'javascript:void(null)','events':{'click':this.click.bind(this,i)}});
        if(i==this.index){
            a.addClass('current');
        }        
        return a;
    },
    
    createSplit: function(){
        var split = new Element('span',{'class':'break','html':'...'});
        return split;
    },
    
    createText: function(){
        var text = new Element('span',{'html':'&nbsp;第'+(this.index+1)+'/'+(this.page)+'页'});
        return text;
    },
    
    click: function(index){
        this.index = index;
        this.load();
    },
    
    load: function(param){
        this.fireEvent('beforeLoad');
        // this.param.start = this.index*this.limit;
        //this.param.limit = this.limit;
        
        this.param[this.startParamName] = this.index+1;
        this.param[this.limitParamName] = this.limit;
        if(param) this.param = $merge(this.param,param);
        this.loader.get(this.param);
    },
    
    reload: function(param){
        this.index = 0;
        this.load(param);
    },
    
    setLimit: function(limit){
        this.limit = limit;
        this.reload();
    }
});