/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
    // Define changes to default configuration here.
    // For the complete reference:
    // http://docs.ckeditor.com/#!/api/CKEDITOR.config

    // The toolbar groups arrangement, optimized for two toolbar rows.
    config.toolbarGroups = [
        { name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
        { name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
        { name: 'links' },
        { name: 'insert' },
        { name: 'forms' },
        { name: 'tools' },
        { name: 'document',	   groups: [ 'mode', 'document', 'doctools' ] },
        { name: 'others' },
        '/',
        { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
        { name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
        { name: 'styles' },
        { name: 'colors' },
        { name: 'about' }
    ];

    // Remove some buttons, provided by the standard plugins, which we don't
    // need to have in the Standard(s) toolbar.
    config.removeButtons = 'Underline,Subscript,Superscript';

    // Se the most common block elements.
    config.format_tags = 'p;h1;h2;h3;pre';

    // Make dialogs simpler.
    config.removeDialogTabs = 'image:advanced;link:advanced';

    config.filebrowserUploadUrl ="/resources/script/ckfinder/core/connector/java/connector.java"
    //config.defaultLanguage = "ru";
    config.filebrowserImageBrowseUrl = "/resources/script/ckfinder/ckfinder.html?type=Images";
    config.filebrowserImageUploadUrl = "/resources/script/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images";

    //config.toolbar = "Full";
};

CKEDITOR.dialog.add( 'mydialogIm', function( api ){
    var dialogDefinition =
    {
        title : 'CKEditor',
        minWidth : 800,
        minHeight : 440,
        contents : [
            {
                id : 'tab1',
                label : 'Label',
                title : 'Title',
                expand : true,
                padding : 0,
                elements :
                    [
                        {
                            type : 'html',
                            html : '<iframe name="CKFINDER" src="/resources/script/ckfinder/ckfinder.html?type=Images" style="width:800px;height:440px;"></iframe>'
                        }
                    ]
            }
        ],
        startFireCurDIM:function(file,ckCD){
            try{
                ckCD.selectPage('info');
                ckCD.getContentElement('info', 'txtUrl').focus();
                ckCD.setValueOf('info', 'txtUrl', file.folder.getResourceType().url+file.name);
            }catch (e){
                e="1";
            }
        },
        buttons : [ CKEDITOR.dialog.okButton, CKEDITOR.dialog.cancelButton ],
        onOk : function() {
            var m=window.frames["CKFINDER"].api.getSelectedFiles();
            if(m.length>1){
                alert("Выделено более одного файла определитесь с выделением");
                return;
            }else{
                this.definition.startFireCurDIM(window.frames["CKFINDER"].api.getSelectedFile(),this.ckCD);
            }
        }
    };
    return dialogDefinition;
} );

CKEDITOR.on('instanceReady', function(){
    CKEDITOR.on( 'dialogDefinition', function( ev ) {
        var onAC=function(){
            var ckCD = CKEDITOR.dialog.getCurrent();
            var kc=CKEDITOR.instances.editor1.openDialog('mydialogIm');
            kc.ckCD=ckCD;
        };
        var dialogName = ev.data.name;
        var dialogDefinition = ev.data.definition;
        if ( dialogName == 'image' ) {
            var linkTab = dialogDefinition.getContents( 'Link' );
            var urlField2 = linkTab.get( 'browse');
            urlField2.filebrowser.url="" ;
            urlField2.onClick=onAC
            var infoTab = dialogDefinition.getContents( 'info' );
            var urlField = infoTab.get( 'browse');
            urlField.filebrowser.url="" ;
            urlField.onClick=onAC;
        }
    });
});