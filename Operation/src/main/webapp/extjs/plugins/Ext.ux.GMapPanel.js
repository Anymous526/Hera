/*
 * Ext JS Library 2.2
 * Copyright(c) 2006-2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */

/**
 * @author Shea Frederick
 */

Ext.namespace('Ext.ux');

var mapPanel;
/**
 *
 * @class GMapPanel
 * @extends Ext.Panel
 */
Ext.ux.GMapPanel = Ext.extend(Ext.Panel, {
	
    initComponent : function(){
        
        var defConfig = {
            plain: true,
            zoomLevel: 3,
            yaw: 180,
            pitch: 0,
            zoom: 0,
            gmapType: 'map',
            border: false,
            defaultCity : '',
            longitude : 0,
            latitude : 0,
            mapClick : true
        };
        
        Ext.applyIf(this,defConfig);
        
        Ext.ux.GMapPanel.superclass.initComponent.call(this);        

    },
    afterRender : function(){
        var wh = this.ownerCt.getSize();
        Ext.applyIf(this, wh);
        
        Ext.ux.GMapPanel.superclass.afterRender.call(this);    
        
        if (this.gmapType === 'map'){
            this.gmap = new GMap2(this.body.dom);
        }
        
        if (this.gmapType === 'panorama'){
            this.gmap = new GStreetviewPanorama(this.body.dom);
        }
        
        if (typeof this.addControl == 'object' && this.gmapType === 'map') {
            this.gmap.addControl(this.addControl);
        }
        if (typeof this.setCenter === 'object') {
            if (typeof this.setCenter.geoCodeAddr === 'string'){
                this.geoCodeLookup(this.setCenter.geoCodeAddr);
            }else{
                if (this.gmapType === 'map'){
                    var point = new GLatLng(this.setCenter.lat,this.setCenter.lng);
                    this.gmap.setCenter(point, this.zoomLevel);    
                }
                if (typeof this.setCenter.marker === 'object' && typeof point === 'object'){
                    this.addMarker(point,this.setCenter.marker,this.setCenter.marker.clear);
                }
            }
            if (this.gmapType === 'panorama'){
                this.gmap.setLocationAndPOV(new GLatLng(this.setCenter.lat,this.setCenter.lng), {yaw: this.yaw, pitch: this.pitch, zoom: this.zoom});
            }
        }
        GEvent.bind(this.gmap, 'load', this, function(){
            this.onMapReady();
        });
    },
    onMapReady : function(){
        this.addMapControls();
        this.addOptions();
        this.addListeners();
        this.addBaseIcon();
        this.showLocalMarkers();
    },
    onResize : function(w, h){

        if (typeof this.getMap() == 'object') {
            this.gmap.checkResize();
        }
        
        Ext.ux.GMapPanel.superclass.onResize.call(this, w, h);

    },
    setSize : function(width, height, animate){
        
        if (typeof this.getMap() == 'object') {
            this.gmap.checkResize();
        }
        Ext.ux.GMapPanel.superclass.setSize.call(this, width, height, animate);
        
    },
    getMap : function(){
        
        return this.gmap;
        
    },
    getCenter : function(){
        
        return this.getMap().getCenter();
        
    },
    setCity : function(city) {
    	this.defaultCity = city;
    },
    getCity : function() {
    	return this.defaultCity;
    },
    setLongitude : function(longitude) {
    	this.longitude = longitude;
    },
    getLongitude : function() {
    	return this.longitude;
    },
    setLatitude : function(latitude) {
    	this.latitude = latitude;
    },
    getLatitude : function() {
    	return this.latitude;
    },
    setMarkers : function(markers) {
    	this.setCenter.markers = markers;
    },
    getMarkers : function() {
    	return this.setCenter.markers;
    },
    getCenterLatLng : function(){
        
        var ll = this.getCenter();
        return {lat: ll.lat(), lng: ll.lng()};
        
    },
    fixedPosition : function(latitude, longitude) {
    	point = new GLatLng(latitude, longitude);
    	this.getMap().setCenter(point);
    },
    addListeners : function() {
    	mapPanel = this;
    	if (this.mapClick == true) {
    		GEvent.addListener(this.getMap(), "click", this.onMapClick);
    	}
    },
    onMapClick : function(marker,latlng) {
			var me = mapPanel;
      		// 如果用户单击的是标记，不再这里处理
  			if (marker) {
    			return;
    		}
    		if (me.getMap().getZoom() < 17) {
    			me.getMap().setCenter(latlng, me.getMap().getZoom() + 1);
    			return;
    		}
    		// 创建标记，并添加到链表中
    		var newMarker = new GMarker(latlng, {draggable: false});
    		//删除其他的标记
    		me.getMap().clearOverlays();
    		// 将创建的标记添加到地图中
    		me.getMap().addOverlay(newMarker);
    		me.setLongitude(newMarker.getLatLng().lng());
    		me.setLatitude(newMarker.getLatLng().lat());
    		// 为标记添加事件处理函数：单击标记时要显示信息窗口
    		GEvent.addListener(newMarker, "click", function() {
      			newMarker.openInfoWindow(me.showMarkerInfo(newMarker, '暂无'));
    		});
    	},
    showMarkerInfo : function(mark, addr) {
    	var me = this;
    	//输出坐标和比例等级
    	var div = document.createElement('div');
  		div.style.fontSize = '8.5pt';
  		div.style.width = '300px';
  		div.innerHTML = "坐标：" + me.formatLatLng(mark.getLatLng()) + "" +
  					    "<br> 地图比例：" + me.getMap().getZoom() + "级" +
  					    "<br>地址：" + addr;
  		//加一个线
  		var hr = document.createElement('hr');
  		hr.style.border = 'solid 1px #cccccc';
  		div.appendChild(hr);
  		//加一个删除按钮
  		var lnk = document.createElement('div');
  		lnk.innerHTML = '删除标记';
  		lnk.style.color = '#0000cc';
  		lnk.style.cursor = 'pointer';
  		lnk.style.textDecoration = 'underline';
  		lnk.onclick = function() {// 关闭显示信息栏，删除标记
  			me.getMap().closeInfoWindow();
    		me.getMap().removeOverlay(mark);
    		me.setLongitude('0');
    		me.setLatitude('0');
  		};
  			// 创建“删除”按钮
  		div.appendChild(lnk);
  		// 当用户关闭信息窗口时 Google 地图 API 会自动释放该对象 
  		return div;
    },
    showLocalMarkerInfo : function(mName, addr, poses) {
    	var me = this;
    	//商户
    	var div = document.createElement('div');
  		div.style.fontSize = '8.5pt';
  		div.style.width = '400px';
  		var showInfo = "商户名称：" + mName +
  						"<br>显示信息：" + addr;
  		div.innerHTML = showInfo;
  		//横线
  		var hr = document.createElement('hr');
  		hr.style.border = 'solid 1px #cccccc';
  		div.appendChild(hr);
  		//终端
  		var posDiv = document.createElement('div');
  		div.style.fontSize = '8.5pt';
  		var posInfo = "";
  		for (var i = 0; i < poses.length; i++) {
  			var pos = poses[i];
  			posInfo = posInfo + "<br>终端序列号：" + pos.serialNo;
  			posInfo = posInfo + "&nbsp&nbsp&nbsp&nbsp终端号：" + pos.businessNo;
  			posInfo = posInfo + "<br>设备状态：" + pos.allStatus;
  			posInfo = posInfo + "&nbsp&nbsp&nbsp&nbsp状态：" + pos.posRunStatus;
  			
  		}
  		if (posInfo == "") {
  			posInfo = "暂无终端信息";
  		}
  		posDiv.innerHTML = posInfo;
  		div.appendChild(posDiv);
  		
  		// 当用户关闭信息窗口时 Google 地图 API 会自动释放该对象 
  		return div;
    },
    addBaseIcon : function() {
    	var baseIcon = new GIcon();
        baseIcon.shadow = "http://www.google.com/mapfiles/shadow50.png";
        baseIcon.iconSize = new GSize(20, 34);
        baseIcon.shadowSize = new GSize(37, 34);
        baseIcon.iconAnchor = new GPoint(9, 34);
        baseIcon.infoWindowAnchor = new GPoint(9, 2);
        baseIcon.infoShadowAnchor = new GPoint(18, 25);
        this.baseIcon = baseIcon;
    },
    addMarker : function(point, marker, center, index, addr){
        
    	var me = this;
        Ext.applyIf(marker,G_DEFAULT_ICON);
		
        if (index > 3) {
        	return;
        }
        if (center === true && index == 0) {
            this.getMap().setCenter(point, this.zoomLevel);
        }
        
        var letter = String.fromCharCode("A".charCodeAt(0) + index);
		var letteredIcon = new GIcon(this.baseIcon);
        letteredIcon.image = "http://www.google.com/mapfiles/marker" + letter + ".png";
		
        markerOptions = { icon:letteredIcon };
		
        var mark = new GMarker(point,markerOptions);
		GEvent.addListener(mark, "click", function() {
      			mark.openInfoWindow(me.showMarkerInfo(mark, addr));
    		});
        this.getMap().addOverlay(mark);

    },
    addLocalMarker : function(point, index, image, mName, addr, poses){
        
    	var me = this;
        if (index === 1) {
            this.getMap().setCenter(point, this.zoomLevel);
        }
        var icon = new GIcon(G_DEFAULT_ICON);
        if (index <= 99) {
        	icon.image = "http://gmaps-samples.googlecode.com/svn/trunk/markers/" + image + "/marker" + index +".png";
        } else {
         	icon.image = "http://gmaps-samples.googlecode.com/svn/trunk/markers/" + image + "/blank.png";
        }
 		
        markerOptions = { icon : icon };
		
        var mark = new GMarker(point,markerOptions);
		GEvent.addListener(mark, "click", function() {
      			mark.openInfoWindow(me.showLocalMarkerInfo(mName, addr, poses));
    		});
        this.getMap().addOverlay(mark);

    },
    addMapControls : function(){
        
        if (this.gmapType === 'map') {
            if (Ext.isArray(this.mapControls)) {
                for(i=0;i<this.mapControls.length;i++){
                    this.addMapControl(this.mapControls[i]);
                }
            }else if(typeof this.mapControls === 'string'){
                this.addMapControl(this.mapControls);
            }else if(typeof this.mapControls === 'object'){
                this.getMap().addControl(this.mapControls);
            }
        }
        
    },
    addMapControl : function(mc){
        
        var mcf = window[mc];
        if (typeof mcf === 'function') {
            this.getMap().addControl(new mcf());
        }    
        
    },
    addOptions : function(){
        
        if (Ext.isArray(this.mapConfOpts)) {
            var mc;
            for(i=0;i<this.mapConfOpts.length;i++){
                this.addOption(this.mapConfOpts[i]);
            }
        }else if(typeof this.mapConfOpts === 'string'){
            this.addOption(this.mapConfOpts);
        }        
        
    },
    addOption : function(mc){
        
        var mcf = this.getMap()[mc];
        if (typeof mcf === 'function') {
            this.getMap()[mc]();
        }    
        
    },
    formatDegree : function(value) {
  		value = Math.abs(value);
  		var v1 = Math.floor(value);
  		var v2 = Math.floor((value - v1) * 60);
  		var v3 = Math.round((value - v1) * 3600 % 60);
  		return v1 + '°' + v2 + '\'' + v3 + '"';
	},
	formatLatLng : function(pt) {
  		var me = this;
  		var latName, lngName;
  		var lat = pt.lat();
  		var lng = pt.lng();
  		latName = lat >= 0 ? '北纬' : '南纬';
  		lngName = lng >= 0 ? '东经' : '西经';

  		return lngName + me.formatDegree(lng) + '，' 
    		+ latName + me.formatDegree(lat);
	},
	showLocalMarkers : function() {
		if (Ext.isArray(this.setCenter.markers)) {
			this.getMap().clearOverlays();
			for (i = 0; i < this.setCenter.markers.length; i++) {
				var marker = this.setCenter.markers[i];
				point = new GLatLng(marker.latitude, marker.longitude);
				this.addLocalMarker(point, i + 1, marker.colour, marker.merchantName, marker.showName, marker.posInfo);
			}
		}
	},
    geoCodeLookup : function(addr) {
        this.geocoder = new GClientGeocoder();
        this.geocoder.getLocations(this.getCity() + addr, this.addAddressToMap.createDelegate(this));
        
    },
    addAddressToMap : function(response) {
        if (!response || response.Status.code != 200) {
            Ext.MessageBox.show({
								title : '未找到',
								msg : '不能找到你提供的地址!',
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.WARNING
							});
        }else{
            place = response.Placemark[0];
            addressinfo = place.AddressDetails;
            accuracy = addressinfo.Accuracy;
            if (accuracy === 0) {
                Ext.MessageBox.alert('Unable to Locate Address', 'Unable to Locate the Address you provided');
            }else{
                if (accuracy < 2) {
                   Ext.MessageBox.show({
								title : '未找到',
								msg : '不能找到你提供的地址!',
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.WARNING
							});
                }else{
                	if (this.setCenter.marker.clear === true){
            			this.getMap().clearOverlays();
            			this.setLongitude('0');
    					this.setLatitude('0');
        			}
                	for (var i = 0; i < response.Placemark.length; i++) {
                		place = response.Placemark[i];
                    	point = new GLatLng(place.Point.coordinates[1], place.Point.coordinates[0]);
                    	if (typeof this.setCenter.marker === 'object' && typeof point === 'object'){
                        	this.addMarker(point,this.setCenter.marker,true, i, place.address);
                    	}
                	}
                }
            }
        }
        
    }
 
});

Ext.reg('gmappanel',Ext.ux.GMapPanel); 