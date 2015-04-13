function limit(){
	var txtNote;//文本框   
    var txtLimit;//提示字数的input   
    var limitCount;//限制的字数   
    var isbyte;//是否使用字节长度限制（1汉字=2字符）   
    var txtlength;//到达限制时，字符串的长度  
    var txtByte;  
    this.init=function(){  
        txtNote=this.txtNote;  
        txtLimit=this.txtLimit;  
        limitCount=this.limitCount;  
        isbyte=this.isbyte;  
        txtNote.onkeydown=function(){wordsLimit()};txtNote.onkeyup=function(){wordsLimit()};  
        txtLimit.value=limitCount;        
   }     
   function wordsLimit(){  
        var noteCount=0;          
        if(isbyte){
        	var tempstr=txtNote.value.replace(/[^\x00-\xff]/g, "**");
        	noteCount=tempstr.length;
        }else{
        	noteCount=txtNote.value.length;
        }  
        if(noteCount>limitCount){  
           if(isbyte){  
               txtNote.value=txtNote.value.substring(0,txtlength+Math.floor((limitCount-txtByte)/2));  
               var tstr=txtNote.value.replace(/[^\x00-\xff]/g, "**");
               txtByte=tstr.length;           
               txtLimit.value=limitCount-txtByte;  
            }else{  
               txtNote.value=txtNote.value.substring(0,limitCount);  
               txtLimit.value=0;  
          }     
       }else{  
          txtLimit.value=limitCount-noteCount;  
        }  
       txtlength=txtNote.value.length;//记录每次输入后的长度   
       txtByte=txtNote.value.replace(/[^\x00-\xff]/g, "**").length;  
    }  
}  
