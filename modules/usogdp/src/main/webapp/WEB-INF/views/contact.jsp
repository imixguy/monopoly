<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
	*, html, body {
		margin: 0 ;
		padding: 0;
		font-size:100%;
	}

	h1 {
		font-family: arial, sans-serif;
		font-size: 23px;
		padding-top:60px;
		padding-bottom:30px;
		padding-left:80px;
		color: #4682B4
	}

	h2 {
		font-family: arial, sans-serif;
		font-size: 20px;
		padding-top:50px;
		padding-bottom:20px;
		padding-left:80px;
		color: #4682B4
	}

	table.first{
		width:300px}

	table.first td{
		padding:4px 10px;
		border:1px solid #FFFFFF;
	}

	table.stuff{
		border-collapse:separate;
		width:1200px}

	table.stuff td{
		padding:4px 10px;
		border:1px solid #FFFFFF;
	}

	p{
		text-align:center;
		line-height:1.5em;
		font-size: 14px;
	}

	p#secelement {
		line-height:1.5em;
		font-size:15px;
		text-align:left;
		padding-left:150px;
	}

	td {
		width:200px
	}
	.fm1{
		font-size: 18px;
		font-weight: bold;
		color: #2F4F4F}
	.fm {
		font-size: 15px;
		font-weight: bold;
		color: #2F4F4F
	}

	.foto{
		padding-left:80px
	}
	.foto1 {
		padding-left:110px
	}


</style>


<h1>Контакты ГЦ КТЦ</h1>

<center><table class="first">
	<tr><td><p class="foto1"><img src="${ctx}/resources/img/chl.png" width="80" height="105" align="left"  alt="Фото"></p> </td></tr >
	<tr><td><p class="fm1">Богуш</p>
		<p class="fm1">Надежда Петровна</p></td></tr >
	<tr><td><p id=firstelement>Начальник отдела развития, внедрения и сопровождения информационных технологий перевозочного процесса</p></td></tr >
	<tr><td><p class="fm">7-400 38-31</p>
		<p class="fm">МТС 029-537-22-08</p>
		<p class="fm">VEL 029-685-98-65</p>
		<p class="fm"><a href="mailto:nadja@ktc.rw">nadja@ktc.rw</a></p> </td>	</tr >
	</tr >
</table></center>

<center><table class="stuff">
	<tbody align="left" valign="top">
	<tr>
		<td> <p class="foto"><img src="${ctx}/resources/img/chl.png" width="80" height="105" align="left"  alt="Фото"></p> </td>
		<td> <p class="foto"><img src="${ctx}/resources/img/chl.png" width="80" height="105" align="left"  alt="Фото"></p> </td>
		<td> <p class="foto"><img src="${ctx}/resources/img/chl.png" width="80" height="105" align="left"  alt="Фото"></p> </td>
		<td> <p class="foto"><img src="${ctx}/resources/img/chl.png" width="80" height="105" align="left"  alt="Фото"></p> </td>
		<td> <p class="foto"><img src="${ctx}/resources/img/chl.png" width="80" height="105" align="left"  alt="Фото"></p> </td>
	</tr >
	<tr>
		<td><p class="fm1">Качановский </p>
			<p class="fm1">Михаил Викторович</p></td>
		<td><p class="fm1">Гавриленко </p>
			<p class="fm1">Геннадий Игоревич</p></td>
		<td><p class="fm1">Латошинская </p>
			<p class="fm1">Наталья Владимировна</p></td>
		<td><p class="fm1">Черноокий </p>
			<p class="fm1">Дмитрий Георгиевич </p></td>
		<td><p class="fm1">Седлер </p>
			<p class="fm1">Сергей Игоревич </p></td>

	</tr>
	<tr>
		<td><p>Главный инженер проекта</p></td>
		<td><p>Инженер-программист</p>	</td>
		<td><p>Инженер-программист</p>	</td>
		<td><p>Инженер-программист</p>	</td>
		<td><p>Инженер-программист</p>	</td>
	</tr>
	<tr>
		<td><p class="fm">7-400 38-07</p>
			<p class="fm"><a href="mailto:miha@ktc.rw">miha@ktc.rw</a></p> </td>
		<td><p class="fm">7-400 27-32</p>
			<p class="fm"><a href="mailto:gavrilenko@ktc.rw">gavrilenko@ktc.rw</a></p></td>
		<td><p class="fm">7-400 27-32</p>
			<p class="fm"><a href="mailto:natika@ktc.rw">natika@ktc.rw</a> </p></td>
		<td><p class="fm">7-400 27-32</p>
			<p class="fm"><a href="mailto:dima@ktc.rw">dima@ktc.rw</a></p> </td>
		<td><p class="fm">7-400 27-32</p>
			<p class="fm"><a href="mailto:sedler@ktc.rw">sedler@ktc.rw</a></p></td>
	</tr>
	</tbody>
</table></center>

<script type="text/javascript" charset="utf8" src="${ctx}/resources/js/usogdp.js"></script>

<script>
	usogdp.hideLoader();
</script>

