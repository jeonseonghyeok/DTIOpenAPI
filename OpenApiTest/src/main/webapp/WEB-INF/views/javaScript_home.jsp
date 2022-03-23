<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Open Home 한글</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
	<h1>오픈 API test</h1>
	<!-- <button class='move_to_url' targetUrl='arissue'>작성</button> -->
	<!-- <button class='move_to_url' targetUrl='rarrequest'>역발행 요청</button> -->
	<br>
	<select id='url_target' style="width: 200px"></select>
	<button id='btn_move_to'>이동</button>
	<script language="javascript">
		/* 
		 * url 마지막에 '/'가 없을 경우 location.href 결과가 달라 '/'없이 들어온 경우 추가
		 */
		$(window).load(function() {
			if (!location.href.endsWith('/')) {
				location.href = location.href + '/';
			}
			urlBtn_mapping();
		})
		
		function urlBtn_mapping() {
			const fileNameList = JSON.parse('${fileNameList}');
			for (key in fileNameList) {
				//console.log(key);
				$('#url_target').append(
						$("<option value='"+key+"_"+fileNameList[key]+"'>"
								+ fileNameList[key] + "</option>"));
			}
		}

		$('#btn_move_to').click(function() {
			location.href = './' + $('#url_target')[0].value + '.html';
		});
	</script>
</body>

</html>
