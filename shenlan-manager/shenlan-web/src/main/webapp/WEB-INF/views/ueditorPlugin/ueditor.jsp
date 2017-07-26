<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<html>
<head>
    <meta charset="utf-8" />
    <title>KindEditor JSP</title>
    <link rel="stylesheet" href="${ctx }/public/kindEditor/themes/default/default.css" />
    <link rel="stylesheet" href="${ctx }/public/kindEditor/plugins/code/prettify.css" />
    <script charset="utf-8" src="${ctx }/public/kindEditor/kindeditor.js"></script>
    <script charset="utf-8" src="${ctx }/public/kindEditor/lang/zh_CN.js"></script>
    <script charset="utf-8" src="${ctx }/public/kindEditor/plugins/code/prettify.js"></script>
    <script>
        KindEditor.ready(function(K) {
            var editor1 = K.create('textarea[name="content1"]', {
                cssPath : '${ctx }/public/kindEditor/plugins/code/prettify.css',
                uploadJson : '${ctx }',
                fileManagerJson : '${ctx }/ueditor/fileManager',
                allowImageUpload : true,
                allowFileManager : true,
                showLocal:true,
                items : ['emoticons','source','preview','undo','redo','cut','copy','paste','plainpaste','wordpaste','selectall','justifyleft','justifycenter','justifyright','justifyfull','insertorderedlist','insertunorderedlist','indent','outdent','subscript','superscript','formatblock','fontname','fontsize','forecolor','hilitecolor','bold','italic','underline','strikethrough','removeformat','image','insertvideo','fullscreen','table','','hr','moticons','link','unlink','print','code','baidumap','lineheight','clearhtml','pagebreak','quickformat','insertfile','template','anchor'],
                afterCreate : function() {
                    var self = this;
                    K.ctrl(document, 13, function() {
                        self.sync();
                        document.forms['example'].submit();
                    });
                    K.ctrl(self.edit.doc, 13, function() {
                        self.sync();
                        document.forms['example'].submit();
                    });
                }
            });
            prettyPrint();
        });
    </script>
</head>
<body>
<form name="example" method="post" action="demo.jsp">
    <textarea name="content1" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"></textarea>
    <br />
    <input type="submit" name="button" value="提交内容" /> (提交快捷键: Ctrl + Enter)
</form>
</body>
</html>
<%!
    private String htmlspecialchars(String str) {
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\"", "&quot;");
        return str;
    }
%>