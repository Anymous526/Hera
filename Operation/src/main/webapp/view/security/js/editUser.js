var getRoleEditForm = function(id, mav, method, win){

    var model = mav.model;
    var commitUrl = '';
    
    var forShow = false;// 用于详情界面
    var forCreate = false;// 用于新增界面
    var forUpdate = false;// 用于修改界面
    if (method == 'show') {
        forShow = true;
        forUpdate = false;
        forCreate = false;
        win.setTitle('用户详情');
    }
    if (method == 'edit') {
        forShow = false;
        forUpdate = true;
        forCreate = false;
        win.setTitle('修改用户');
        commitUrl = contextPath + '/user.do?method=update';
    }
    if (method == 'create') {
        forShow = false;
        forUpdate = false;
        forCreate = true;
        win.setTitle('新增用户');
        commitUrl = contextPath + '/user.do?method=save';
    }
    
//    var sList = eval(model.statusList);
//    var statusCombo = new Ext.form.ComboBox({
//    
//        mode: 'local',// 默认为remote，需要proxy-MemoryProxy
//        id: 'statusist',
//        width: 200,
//        fieldLabel: '用户状态',
//        store: new Ext.data.SimpleStore({
//            fields: ["text", "value"],
//            data: sList
//        }),
//        hiddenName: 'status',// 真正存储选择值的隐藏参数名
//        displayField: 'text',
//        valueField: 'value',
//        value: model.status,// 默认值
//        readOnly: (forShow || model.spcsFlag),
//        allowBlank: false,
//        blankText: '必须设置用户状态',
//        emptyText: '请选择用户状态',
//        editable: false,
//        typeAhead: true,
//        forceSelection: true,
//        triggerAction: 'all',
//        selectOnFocus: true,
//        cls: forShow ? 'item-field-readonly' : '',
//        listeners : {
//        	render : function() { this.validate(); }
//        }
//    
//    });

    Ext.form.Field.prototype.msgTarget = 'side';
    var userEditForm = new Ext.form.FormPanel({
        id: 'user_edit-Form',
        labelWidth: 75, // label settings here cascade unless
        frame: true,
        bodyStyle: 'padding:5px 25px 0',
        width: 400,
        anchor: '100% 100% ',
        monitorValid: true,
        layout: 'form',
        defaults: {
            width: 200
        },
        items: [{
            xtype: 'textfield',
            fieldLabel: '用户名称',
            name: 'userName',
            value: model.userName,
            readOnly: (forShow || model.spcsFlag),
            allowBlank: false,
            blankText: '用户名不能为空',
            maxLength: 32,
            maxLengthText: '用户名称超过最大长度（32位）',
            regex: /^[a-zA-Z0-9\u4E00-\u9FA5]*$/,
            regexText: '用户名称应由数字、26个英文字母以及中文汉字组成',
            cls: forShow ? 'item-field-readonly' : '',
            plugins: [Ext.ux.plugins.RemoteValidator],
            rvOptions: {
                url: contextPath + '/user.do?method=' +
                (forCreate ? 'checkNameNew' : 'checkNameUpdate'),
                params: {
                    id: model.id
                },
                ignoreCheckOnBeginning: !forCreate
            },
            listeners : {
            	render : function() { this.validate(); }
            }
        }, {
            xtype: 'textfield',
            inputType: 'password',
            fieldLabel: '用户密码',
            name: 'password',
            value: model.password,
            readOnly: !forCreate,
            allowBlank: false,
            blankText: '密码不能为空',
            maxLength: 32,
            maxLengthText: '用户密码超过最大长度（32位）',
            minLength: 6,
            minLengthText: '用户密码不足最小长度（6位）',
            regex: /^[a-zA-Z0-9\u4E00-\u9FA5]*$/,
            regexText: '用户密码应由数字、26个英文字母以及中文汉字组成',
            hideMode: 'offsets',
            hidden: !forCreate,
            hideLabel: !forCreate,
            cls: !forCreate ? 'item-field-readonly' : '',
            listeners : {
            	render : function() { this.validate(); }
            }
        }, {
            xtype: 'textfield',
            fieldLabel: '真实姓名',
            name: 'realName',
            value: model.realName,
            readOnly: forShow,
            allowBlank: true,
            maxLength: 32,
            maxLengthText: '真实姓名超过最大长度（32位）',
            regex: /^[a-zA-Z0-9\u4E00-\u9FA5]*$/,
            regexText: '真实姓名应由数字、26个英文字母以及中文汉字组成',
            cls: forShow ? 'item-field-readonly' : ''
        }, {
            xtype: 'textfield',
            fieldLabel: '手机号',
            name: 'mobile',
            value: model.mobile,
            readOnly: (forShow || model.csFlag),
            allowBlank: true,
            maxLength: 32,
            maxLengthText: '手机号超过最大长度（32位）',
            regex: /^[0-9]*$/,
            regexText: '手机号应由数字组成',
            cls: forShow ? 'item-field-readonly' : ''
        }, {
            xtype: 'textfield',
            fieldLabel: '电子邮箱',
            name: 'email',
            value: model.email,
            readOnly: forShow,
            allowBlank: true,
            maxLength: 100,
            maxLengthText: '电子邮箱地址超过最大长度（100位）',
            regex: /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
            regexText: '请按电子邮箱地址格式输入',
            cls: forShow ? 'item-field-readonly' : ''
        }
//        , statusCombo
        ],
        
        buttons: [{
        
            text: '确定',
            formBind: true,
            hidden: method == 'show',
            handler: function(){
                userEditForm.getEl().mask('处理中....');
                userEditForm.form.doAction('submit', {
                    url: commitUrl,
                    method: 'post',
                    success: function(form, action){
                        var data = Ext.decode(action.response.responseText);
                        Ext.MessageBox.show({
                            title: data.success ? '成功' : '失败',
                            msg: data.msg,
                            buttons: Ext.MessageBox.OK,
                            icon: data.success ? Ext.MessageBox.INFO : Ext.MessageBox.ERROR,
                            fn: function(){
                                win.close();
                                
                            }
                            
                        });
                        userEditForm.getEl().unmask();
                        
                    },
                    failure: function(form, action){
                        var data = Ext.decode(action.response.responseText);
                        Ext.MessageBox.show({
                            title: '错误',
                            msg: data.msg,
                            buttons: Ext.MessageBox.OK,
                            icon: Ext.MessageBox.ERROR
                        });
                        userEditForm.getEl().unmask();
                    }
                });
            }
        }, {
            text: '返回',
            handler: function(){
                userEditForm.getEl().mask();
                win.close();
            }
        }]
    });
    
    if (forUpdate) {
        userEditForm.add(new Ext.form.TextField({
            xtype: 'textfield',
            name: 'id',
            value: model.id,
            hidden: true,
            hideLabel: true,
            hideMode: 'offsets'
        
        }));
    }
    
    return userEditForm;
    
};
