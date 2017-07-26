/*******************************************************************************
 * KindEditor – WYSIWYG HTML Editor for Internet
 * Copyright (C) 2006-2011 kindsoft.net
 *
 * @author Roddy <luolonghao@gmail.com>
 * @site http://www.kindsoft.net/
 * @licence http://www.kindsoft.net/license.php
 *******************************************************************************/

KindEditor.plugin('insertvideo', function(K) {
    var self = this, name = 'insertvideo',
        allowinsertvideoUpload = K.undef(self.allowinsertvideoUpload, true),
        allowinsertvideoRemote = K.undef(self.allowinsertvideoRemote, true),
        formatUploadUrl = K.undef(self.formatUploadUrl, true),
        allowFileManager = K.undef(self.allowFileManager, false),
        uploadJson = K.undef(self.uploadJson, self.basePath + 'php/upload_json.php'),
        insertvideoTabIndex = K.undef(self.insertvideoTabIndex, 0),
        imgPath = self.pluginsPath + 'insertvideo/insertvideos/',
        extraParams = K.undef(self.extraFileUploadParams, {}),
        filePostName = K.undef(self.filePostName, 'imgFile'),
        fillDescAfterUploadinsertvideo = K.undef(self.fillDescAfterUploadinsertvideo, false),
        lang = self.lang(name + '.');

    self.plugin.insertvideoDialog = function(options) {
        var insertvideoUrl = options.insertvideoUrl,
            insertvideoWidth = K.undef(options.insertvideoWidth, ''),
            insertvideoHeight = K.undef(options.insertvideoHeight, ''),
            insertvideoTitle = K.undef(options.insertvideoTitle, ''),
            insertvideoAlign = K.undef(options.insertvideoAlign, ''),
            showRemote = K.undef(options.showRemote, true),
            showLocal = K.undef(options.showLocal, true),
            tabIndex = K.undef(options.tabIndex, 1),
            clickFn = options.clickFn;
        var target = 'kindeditor_upload_iframe_' + new Date().getTime();
        var hiddenElements = [];
        for(var k in extraParams){
            hiddenElements.push('<input type="hidden" name="' + k + '" value="' + extraParams[k] + '" />');
        }
        var html = [
            '<div style="padding:20px;">',
            //tabs
            '<div class="tabs"></div>',
            //remote insertvideo – start
            '<div class="tab1" style="display:none;">',
            //url
            '<div class="ke-dialog-row">',
            '<label for="remoteUrl" style="width:80px;">' + lang.remoteUrl + '</label>',
            '<input type="text" id="remoteUrl" class="ke-input-text" name="url" value="" style="width:200px;" /> &nbsp;',
            '<span class="ke-button-common ke-button-outer">',
            '<input type="button" class="ke-button-common ke-button" name="viewServer" value="' + lang.viewServer + '" />',
            '</span>',
            '</div>',

            //size
            '<div class="ke-dialog-row">',
            '<label for="remoteWidth" style="width:60px;">' + lang.size + '</label>',
            lang.width + ' <input type="text" id="remoteWidth" class="ke-input-text ke-input-number" name="width" value="" maxlength="4" /> ',
            lang.height + ' <input type="text" class="ke-input-text ke-input-number" name="height" value="" maxlength="4" /> ',
            //'<img class="ke-refresh-btn" src="' + imgPath + 'refresh.png" width="16" height="16" alt="" style="cursor:pointer;" title="' + lang.resetSize + '" />',
            '</div>',
            ////align
            //'<div class="ke-dialog-row">',
            //'<label style="width:60px;">' + lang.align + '</label>',
            //'<input type="radio" name="align" class="ke-inline-block" value="" checked="checked" /> <img name="defaultImg" src="' + imgPath + 'align_top.gif" width="23" height="25" alt="" />',
            //' <input type="radio" name="align" class="ke-inline-block" value="left" /> <img name="leftImg" src="' + imgPath + 'align_left.gif" width="23" height="25" alt="" />',
            //' <input type="radio" name="align" class="ke-inline-block" value="right" /> <img name="rightImg" src="' + imgPath + 'align_right.gif" width="23" height="25" alt="" />',
            //'</div>',
            //title
            //'<div class="ke-dialog-row">',
            //'<label for="remoteTitle" style="width:60px;">' + lang.imgTitle + '</label>',
            //'<input type="text" id="remoteTitle" class="ke-input-text" name="title" value="" style="width:200px;" />',
            //'</div>',
            '</div>',
            //remote insertvideo – end
            //local upload – start
            '<div class="tab2" style="display:none;">',
            '<iframe name="' + target + '" style="display:none;"></iframe>',
            '<form class="ke-upload-area ke-form" method="post" enctype="multipart/form-data" target="' + target + '" action="'+uploadJson+'/ueditor/uploadfile">',
            '<input type="hidden" name="fileDir" value="kindeditor_video">',
            //file
            '<div class="ke-dialog-row">',
            hiddenElements.join(''),
            '<label style="width:60px;">' + lang.localUrl + '</label>',
            '<input type="text" name="localUrl" class="ke-input-text" tabindex="-1" style="width:200px;" readonly="true" /> &nbsp;',
            '<input type="button" class="ke-upload-button" value="' + lang.upload + '" />',
            '</div>',
            '</form>',
            '</div>',
            //local upload – end
            '</div>'
        ].join('');
        var dialogWidth = showLocal || allowFileManager ? 450 : 400,
            dialogHeight = showLocal && showRemote ? 300 : 250;
        var dialog = self.createDialog({
                name : name,
                width : dialogWidth,
                height : dialogHeight,
                title : self.lang(name),
                body : html,
                yesBtn : {
                    name : self.lang('yes'),
                    click : function(e) {
// Bugfix: http://code.google.com/p/kindeditor/issues/detail?id=319
                        function check(str){
                            var pos = str.lastIndexOf('.mp4');
                            if(pos === -1){
                                return false;
                            }else{
                                return pos + 4 === str.length;
                            }
                        }
                        if(K.trim(urlBox.val()).length>7&&!check(K.trim(urlBox.val()))){
                            alert('上传文件类型有误');
                            return false
                        }

                        if(K.trim(urlBox.val()).length==7&&!check(K.trim(localUrlBox.val()))){
                            alert('上传文件类型有误');
                            return false
                        }
                        if (dialog.isLoading) {
                            return;
                        }
// insert local insertvideo
                        if (showLocal && showRemote && tabs && tabs.selectedIndex === 1 || !showRemote) {
                            if (uploadbutton.fileBox.val() == '') {
                                alert(self.lang('pleaseSelectFile'));
                                return;
                            }
                            dialog.showLoading(self.lang('uploadLoading'));
                            uploadbutton.submit();
                            localUrlBox.val('');
                            return;
                        }
// insert remote insertvideo
                        var url = K.trim(urlBox.val()),
                            width = widthBox.val(),
                            height = heightBox.val(),
                            title = titleBox.val(),
                            align = '';
                        alignBox.each(function() {
                            if (this.checked) {
                                align = this.value;
                                return false;
                            }
                        });
                        if (url == 'http://' || K.invalidUrl(url)) {
                            alert(self.lang('invalidUrl'));
                            urlBox[0].focus();
                            return;
                        }
                        if (!/^\d*$/.test(width)) {
                            alert(self.lang('invalidWidth'));
                            widthBox[0].focus();
                            return;
                        }
                        if (!/^\d*$/.test(height)) {
                            alert(self.lang('invalidHeight'));
                            heightBox[0].focus();
                            return;
                        }
                        clickFn.call(self, url, title, width, height, 0, align);
                    }
                },
                beforeRemove : function() {
                    viewServerBtn.unbind();
                    widthBox.unbind();
                    heightBox.unbind();
                    refreshBtn.unbind();
                }
            }),
            div = dialog.div;

        var urlBox = K('[name="url"]', div),
            localUrlBox = K('[name="localUrl"]', div),
            viewServerBtn = K('[name="viewServer"]', div),
            widthBox = K('.tab1 [name="width"]', div),
            heightBox = K('.tab1 [name="height"]', div),
            refreshBtn = K('.ke-refresh-btn', div),
            titleBox = K('.tab1 [name="title"]', div),
            alignBox = K('.tab1 [name="align"]', div);

        var tabs;
        if (showRemote && showLocal) {
            tabs = K.tabs({
                src : K('.tabs', div),
                afterSelect : function(i) {}
            });
            tabs.add({
                title : lang.remoteinsertvideo,
                panel : K('.tab1', div)
            });
            tabs.add({
                title : lang.localinsertvideo,
                panel : K('.tab2', div)
            });
            tabs.select(tabIndex);
        } else
        if (showRemote) {
            K('.tab1', div).show();
        } else if (showLocal) {
            K('.tab2', div).show();
        }

        var uploadbutton = K.uploadbutton({
            button : K('.ke-upload-button', div)[0],
            fieldName : filePostName,
            form : K('.ke-form', div),
            target : target,
            width: 60,
            afterUpload : function(data) {
                dialog.hideLoading();
                if (data.error == 0) {
                    var url = data.url;
                    if (formatUploadUrl) {
                        url = K.formatUrl(url, 'absolute');
                    }
                    if (self.afterUpload) {
                        self.afterUpload.call(self, url, data, name);
                    }
                    if (!fillDescAfterUploadinsertvideo) {
                        clickFn.call(self, url, data.title, data.width, data.height, data.border, data.align);
                    } else {
                        K(".ke-dialog-row #remoteUrl", div).val(url);
                        K(".ke-tabs-li", div)[0].click();
                        K(".ke-refresh-btn", div).click();
                    }
                } else {
                    alert(data.message);
                }
            },
            afterError : function(html) {
                dialog.hideLoading();
                self.errorDialog(html);
            }
        });
        uploadbutton.fileBox.change(function(e) {
            localUrlBox.val(uploadbutton.fileBox.val());
        });
        if (allowFileManager) {
            viewServerBtn.click(function(e) {
                self.loadPlugin('filemanager', function() {
                    self.plugin.filemanagerDialog({
                        viewType : 'VIEW',
                        dirName : 'insertvideo',
                        clickFn : function(url, title) {
                            if (self.dialogs.length > 1) {
                                K('[name="url"]', div).val(url);
                                if (self.afterSelectFile) {
                                    self.afterSelectFile.call(self, url);
                                }
                                self.hideDialog();
                            }
                        }
                    });
                });
            });
        } else {
            viewServerBtn.hide();
        }
        var originalWidth = 0, originalHeight = 0;
        function setSize(width, height) {
            widthBox.val(width);
            heightBox.val(height);
            originalWidth = width;
            originalHeight = height;
        }
        refreshBtn.click(function(e) {
            var tempImg = K('<video src="' + urlBox.val() + '" />', document).css({
                position : 'absolute',
                visibility : 'hidden',
                top : 0,
                left : '-1000px'
            });

            tempImg.bind('load', function() {
                setSize(tempImg.width(), tempImg.height());
                tempImg.remove();
            });
            K(document.body).append(tempImg);
        });
        widthBox.change(function(e) {
            if (originalWidth > 0) {
                heightBox.val(Math.round(originalHeight / originalWidth * parseInt(this.value, 10)));
            }
        });
        heightBox.change(function(e) {
            if (originalHeight > 0) {
                widthBox.val(Math.round(originalWidth / originalHeight * parseInt(this.value, 10)));
            }
        });
        urlBox.val(options.insertvideoUrl);
        setSize(options.insertvideoWidth, options.insertvideoHeight);
        titleBox.val(options.insertvideoTitle);
        alignBox.each(function() {
            if (this.value === options.insertvideoAlign) {
                this.checked = true;
                return false;
            }
        });

        if (showRemote && tabIndex == 0) {
            urlBox[0].focus();
            urlBox[0].select();
        }

        if (showLocal && tabIndex == 1) {
            localUrlBox[0].focus();
            localUrlBox[0].select();
        }

        return dialog;
    };
    self.plugin.insertvideo = {
        edit : function() {
            var img = self.plugin.getSelectedinsertvideo();
            self.plugin.insertvideoDialog({
                insertvideoUrl : img ? img.attr('data-ke-src') : 'http://',
                insertvideoWidth : img ? img.width() : '',
                insertvideoHeight : img ? img.height() : '',
                insertvideoTitle : img ? img.attr('title') : '',
                insertvideoAlign : img ? img.attr('align') : '',
                showRemote : allowinsertvideoRemote,
                showLocal : allowinsertvideoUpload,
                tabIndex: img ? 0 : insertvideoTabIndex,
                clickFn : function(url, title, width, height, border, align) {
                    if (img) {
                        img.attr('src', url);
                        img.attr('data-ke-src', url);
                        img.attr('width', width);
                        img.attr('height', height);
                        img.attr('title', title);
                        img.attr('align', align);
                        img.attr('alt', title);
                    } else {
                        self.exec('insertinsertvideo', url, title, width, height, border, align);
                    }
// Bugfix: [Firefox] 上传图片后，总是出现正在加载的样式，需要延迟执行hideDialog
                    setTimeout(function() {
                        self.hideDialog().focus();
                    }, 0);
                }
            });
        },
        'delete' : function() {
            var target = self.plugin.getSelectedinsertvideo();
            if (target.parent().name == 'a') {
                target = target.parent();
            }
            target.remove();
// [IE] 删除图片后立即点击图片按钮出错
            self.addBookmark();
        }
    };
    self.clickToolbar(name, self.plugin.insertvideo.edit);
});
