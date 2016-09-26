<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="../js/autocomplete.css"/>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>

<script type="text/javascript" src="../js/autocomplete.js"></script>
    <script type="text/javascript">
    $(function(){
        var data = "the People's Republic of China".split(" ");
        alery(data)
        $("#autocomplete").autocomplete(data,{minChars:0}).result(function(event,data,formatted){
            alert(data);
        });
    });
    </script>
  </head>
  <body>
    <input type="text" id="autocomplete"/>
  </body>
</html>