/*
---
description: This class gives you a method to upload files 'the ajax way'

license: MIT-style

authors:
- Arian Stolwijk

requires: 
  core/1.2.4: 
  - Class.Extras
  - Element.Event
  - Element.Style

provides: [Element.iFrameFormRequest, iFrameFormRequest]

...
*/

/**
 * @author Arian Stolwijk
 * Idea taken from http://www.webtoolkit.info/ajax-file-upload.html
 */

var iFrameFormRequest = new Class({

	Implements: [Options, Events],

	options: { /*
		onRequest: function(){},
		onComplete: function(data){},
		onFailure: function(){}, */
		eventName: 'submit'
	},

	initialize: function(form, options){
		this.setOptions(options);
		var frameId = this.frameId = 'f' + Math.floor(Math.random() * 99999);
		var loading = false;

		this.form = document.id(form);

		this.formEvent = function(){
			loading = true;
			this.fireEvent('request');
		}.bind(this);

		this.iframe = new IFrame({
			name: frameId,
			styles: {
				display: 'none'
			},
			src: 'about:blank',
			events: {
				load: function(){
					if (loading) {
						var doc = this.iframe.contentWindow.document;
						if (doc) {
							if (doc.location.href == 'about:blank') this.fireEvent('failure');
							else this.fireEvent('complete', doc.body.innerHTML);
						} else {
							this.fireEvent('failure');
						}
						loading = false;
					}
				}.bind(this)
			}
		}).inject(document.id(document.body));

		this.attach();
	},

	send: function(){
		this.form.submit();
	},

	attach: function(){
		this.form
			.store('iFrameFormRequest:formTarget', this.form.get('target'))
			.set('target', this.frameId)
			.addEvent(this.options.eventName, this.formEvent);
	},

	detach: function(){
		this.form
			.store('iFrameFormRequest:formTarget', this.form.get('target'))
			.set('target', this.form.retrieve('iFrameFormRequest:formTarget'))
			.removeEvent(this.options.eventName, this.formEvent);
	},

	toElement: function(){
		return this.iframe;
	}

});

Element.implement('iFrameFormRequest', function(options){
	this.store('iFrameFormRequest', new iFrameFormRequest(this,options));
	return this;
});

