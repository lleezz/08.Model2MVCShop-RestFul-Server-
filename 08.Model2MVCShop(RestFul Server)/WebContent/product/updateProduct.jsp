<%@ page contentType="text/html; charset=euc-kr" %>

<html>
<head>
<title>��ǰ����</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript" src="/javascript/calendar.js"></script>

<script type="text/javascript">

function fncUpdateProduct(){
	
	var prodNo = document.detailForm.prodNo.value;
	
	var prodName=document.detailForm.prodName.value;
	var prodDetail=document.detailForm.prodDetail.value;
	var manuDate=document.detailForm.manuDate.value;
	var price=document.detailForm.price.value;
	
	var fileName = document.detailForm.fileName.value;
	var fileNameNew = document.detailForm.fileNameNew.value;
	
	if( fileNameNew!= null || fileNameNew.length>0){
		fileName = fileNameNew;
	}
	
	if(prodName == null || prodName.length <1){
		alert("��ǰ���� �ݵ�� �Է��ϼž� �մϴ�.");
		return;
	}
	if(prodDetail == null || prodDetail.length <1){
		alert("��ǰ��������  �ݵ�� �Է��ϼž� �մϴ�.");
		return;
	}
	if(manuDate == null || manuDate.length <1){
		alert("�������ڴ�  �ݵ�� �Է��ϼž� �մϴ�.");
		return;
	}
	if(price == null || price.length <1){
		alert("������  �ݵ�� �Է��ϼž� �մϴ�.");
		return;
	}
	

	document.detailForm.action='/product/updateProduct';
	document.detailForm.submit();
}


function resetData() {
	document.detailForm.reset();
}

</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<form name="detailForm"  method="post" >

<input type="hidden" name="prodNo" value="${product.prodNo}">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">��ǰ����</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr> 
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:13px;">
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			��ǰ�� <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="prodName" value="${product.prodName}" class="ct_input_g" 
							style="width:100px; height:19px"  maxLength="100" minLength="5"  />
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			��ǰ������ <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="prodDetail" value="${product.prodDetail}" class="ct_input_g" 
							style="width:600px; height:50px"  maxLength="200" minLength="5"  />
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			�������� <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
							
			<input 	type="text" name="manuDate" value="${product.manuDate}" class="ct_input_g" 
							style="width:100px; height:19px"  maxLength="10" minLength="6"  /> 
			<a href="javascript:show_calendar('document.detailForm.manuDate', document.detailForm.manuDate.value);">
				<img src="/images/ct_icon_date.gif" width="18" height="18" align="absmiddle"/>
			</a>
				
							
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			����<img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="price" value="${product.price}" class="ct_input_g" 
							style="width:100px; height:19px"  maxLength="10" >
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			��ǰ�̹���</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
		 	<img src="/images/uploadFiles/${product.fileName}"/><br/>
			<input type="hidden" name="fileName" value="${product.fileName}">
			<input type="file" name="fileNameNew" class="ct_input_g" 
							style="width:400px; height:19px"  maxLength="100" >
		</td>
	</tr>

	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td width="53%"></td>
		<td align="right">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncUpdateProduct();">����</a>
						
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
					</td>
					<td width="30"></td>					
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:resetData();">���</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

</form>

<script type="text/javascript">
document.getElementById("btnCmfID").focus();
</script>

</body>
</html>
