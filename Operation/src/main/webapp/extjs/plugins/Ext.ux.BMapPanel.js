/*
 * ! Ext JS 3.2.1 Plugin Copyright(c) 2006-2011 Just in Mobile, Inc.
 * http://www.jusinmobile.com/
 */
/**
 * @class Ext.ux.BMapPanel
 * @extends Ext.Panel
 * @author Wei Zhang
 */
Ext.ux.BMapPanel = Ext.extend(Ext.Panel, {
	initComponent : function() {
		var defConfig = {
			isSingleMarker : true,
			zoomLevel : 14
		};
		Ext.applyIf(this, defConfig);

		Ext.ux.BMapPanel.superclass.initComponent.call(this);

	},
	afterRender : function() {
		var wh = this.ownerCt.getSize();
		Ext.applyIf(this, wh);
		Ext.ux.BMapPanel.superclass.afterRender.call(this);
		this.initBMap();
	},
	initBMap : function() {
		this.bmap = new BMap.Map(this.body.dom);
		this.bmap.parentCt = this;

		this.bmap.addControl(new BMap.NavigationControl());

		this.locateTo(this.address);

		this.bmap.addEventListener('click', function(e) {
			if (this.parentCt.isSingleMarker) {
				this.clearOverlays();
			}
			this.parentCt.lastLng = e.point.lng;
			this.parentCt.lastLat = e.point.lat;
			var infoTip = this.parentCt.tip;
			var address = this.parentCt.address;
			var point = e.point;
			var marker = new BMap.Marker(e.point);
			this.addOverlay(marker);
			marker.addEventListener('mouseover', function(e) {
				var info = '';
				if (typeof infoTip != 'undefined'
						&& infoTip != '') {
					info = '<p><b>' + infoTip + '</b></p>';
				}
				if (typeof address != 'undefined'
						&& address != '') {
					info = info + '<p>地址：' + address + '</p>';
				}
				info = info + '<p>经度：' + point.lng
						+ '</p><p>纬度：' + point.lat + '</p>';
				var iw = new BMap.InfoWindow(info);
				this.openInfoWindow(iw);
			});
			marker.addEventListener('mouseout', function(e) {
				this.closeInfoWindow();
			});
		});
	},
	locateTo : function(address) {
		var defaultCenterPoint = new BMap.Point(114.056332, 22.552675);

		if ((typeof this.lng != 'undefined' && this.lng != '') && (typeof this.lng != 'undefined' && this.lat != '')) {
			var point = new BMap.Point(this.lng, this.lat);
			var infoTip = this.tip;
			var address = this.address;
			if (point) {
				this.bmap.centerAndZoom(point, this.zoomLevel);
				this.lastLng = point.lng;
				this.lastLat = point.lat;
				var marker = new BMap.Marker(point);
				this.bmap.addOverlay(marker);
				marker.addEventListener('mouseover', function(e) {
					var info = '';
					if (typeof infoTip != 'undefined' && infoTip != '') {
						info = '<p><b>' + infoTip + '</b></p>';
					}
					if (typeof address != 'undefined' && address != '') {
						info = info + '<p>地址：' + address + '</p>';
					}
					info = info + '<p>经度：' + point.lng + '</p><p>纬度：'
							+ point.lat + '</p>';
					var iw = new BMap.InfoWindow(info);
					this.openInfoWindow(iw);
				});
				marker.addEventListener('mouseout', function(e) {
					this.closeInfoWindow();
				});
			}
			return;
		}

		if ( (typeof this.address === 'undefined' || this.address == '') && (typeof this.city === 'undefined' || this.city == '') ) {
			this.bmap.centerAndZoom(defaultCenterPoint, this.zoomLevel);
			return;
		}

		var geo = new BMap.Geocoder();
		var zoomLevel = this.zoomLevel;
		var map = this.bmap;
		var win = this;
		var infoTip = this.tip;
		var address = this.address;
		var city = this.city;

		geo.getPoint(this.address, function(point) {
			if (point) {
				map.centerAndZoom(point, zoomLevel);
				win.lastLng = point.lng;
				win.lastLat = point.lat;
				var marker = new BMap.Marker(point);
				map.addOverlay(marker);
				marker.addEventListener('mouseover', function(e) {
					var info = '';
					if (typeof infoTip != 'undefined' && infoTip != '') {
						info = '<p><b>' + infoTip + '</b></p>';
					}
					if (typeof address != 'undefined' && address != '') {
						info = info + '<p>地址：' + address + '</p>';
					}
					info = info + '<p>经度：' + point.lng + '</p><p>纬度：'
							+ point.lat + '</p>';
					var iw = new BMap.InfoWindow(info);
					this.openInfoWindow(iw);
				});
				marker.addEventListener('mouseout', function(e) {
					this.closeInfoWindow();
				});
			} else {
				map.centerAndZoom(defaultCenterPoint, this.zoomLevel);
				var local = new BMap.LocalSearch(map, {renderOptions:{map: map}});
				local.enableFirstResultSelection();
				local.search(city);
			}
		}, city);
	}
});

Ext.reg('bmappanel', Ext.ux.BMapPanel);