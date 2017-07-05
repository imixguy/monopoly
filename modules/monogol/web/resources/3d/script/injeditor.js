/**
 * Created by miha on 02.09.2014.
 */

var injeditor={
    init:function(){
        var txtEd=$('.textEdit');
        if(txtEd.length>0){
            this.loadCKEditor();
        }
    },
    loadCKEditor:function(){
        if(document.getElementById('scriptckeditor')==null){
            var script = document.createElement('script');
            script.type = 'text/javascript';
            script.src = '/resources/script/ckeditor4/ckeditor.js';
            script.id = 'scriptckeditor';
            document.getElementsByTagName('head')[0].appendChild(script);
        }else{
            ckeditorload();
        }
    }
}

//При замене версии CKEDITOR в конец файла ckeditor.js поместить выpов этой функции. ckeditorload();
function ckeditorload(){
    CKEDITOR.basePath = '/resources/script/ckeditor4/';
    CKEDITOR.config.allowedContent=true;
    $('.textEdit').each(function(ind,el){
        //<![CDATA[
        CKEDITOR.replace($(el).attr('id'));
        //]]>
    });
}

$(function(){
    injeditor.init();
});