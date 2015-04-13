var com = com ? com : {};
com.comment = com.comment? com.comment: {};

com.comment.ReplyUI = new Class({
	Implements : Options,
	options : {
		forwoardType : 1
	},
	initialize : function(commentId, options) {
		this.commentId = commentId;
		this.setOptions(options);
		this.initDom();
	},
	initDom : function() {
		new Request.HTML({
					url : "/manager/comment/detail.htm?id=" + this.commentId,
					method : "get",
					async : false,
					onSuccess : function(responseTree,
							responseElements, responseHTML) {
						new POP.showBox("replydialog",responseHTML, 644,520,{
							fillType:"html",
							drag:false
						});
					}
				}).send();
	}
});

com.comment.replyDialog = function(commentId) {
 new com.comment.ReplyUI(commentId);
}


com.comment.send = function(commentId) {
	var replyContent = $("replyContent");
	var value = replyContent.value.trim();
	if (value.length > 140) {
		POP.alert(
				"<strong style='color:blue;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp回复内容最多140字符</strong>", {
					autoClose : false
				});
		return;
	}
	
	var queryString = "id=" + commentId + "&replyContent=" + value;
	var request = new Request.JSON({
				url : "/manager/comment/replyCommit.htm",				
				method : "post",
				onComplete : function(obj, responsetxt) {
					var data = JSON.decode(responsetxt);			
					if (data.success) {
					    alert(data.msg);
						POP.closeBox();
					}
					
				}
			}).send(queryString);
}


