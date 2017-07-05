<%--
  Created by IntelliJ IDEA.
  User: sedler
  Date: 26.02.15
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" charset="utf8" src="${ctx}/resources/js/usogdp.js?v=1"></script>
<style>
    #project-label {
        display: block;
        font-weight: bold;
        margin-bottom: 1em;
    }
    #project-icon {
        float: left;
        height: 32px;
        width: 32px;
    }
    #project-description {
        margin: 0;
        padding: 0;
    }
</style>
<script>
    $(function() {
        usogdp.hideLoader();
        var projects = [
            {
                value: "jquery",
                label: "jQuery",
                desc: "the write less, do more, JavaScript library",
                icon: "jquery_32x32.png"
            },
            {
                value: "jquery-ui",
                label: "jQuery UI",
                desc: "the official user interface library for jQuery",
                icon: "jqueryui_32x32.png"
            },
            {
                value: "sizzlejs",
                label: "Sizzle JS",
                label: "sizzlejs",
                desc: "a pure-JavaScript CSS selector engine",
                icon: "sizzlejs_32x32.png"
            }
        ];

        $( "#project" ).autocomplete({
            minLength: 0,
            source: projects,
//            focus: function( event, ui ) {
////                $( "#project" ).val( ui.item.label );
//                $( "#project" ).val( ui.item.value );
//                return false;
//            },
            select: function( event, ui ) {
                $( "#project" ).val( ui.item.label );
                $( "#project-id" ).val( ui.item.value );
                $( "#project-description" ).html( ui.item.desc );
                $( "#project-icon" ).attr( "src", "images/" + ui.item.icon );
                return false;
            }
        })
                .autocomplete( "instance" )._renderItem = function( ul, item ) {
            return $( "<li>" )
                    .append( "<a>" + item.label + "<br>" + item.desc + "</a>" )
                    .appendTo( ul );
        };
    });
</script>
</head>
<body>

<div id="project-label">Select a project (type "j" for a start):</div>
<img id="project-icon" src="images/transparent_1x1.png" class="ui-state-default" alt="">
<input id="project">
<input type="hidden" id="project-id">
<p id="project-description"></p>
