<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Post Blog</title>
<%@ include file="header.jsp"%>

<style type="text/css">
#toolBar1 select { font-size:10px; }
#textBox {
  width: 640px;
  height: 300px;
  border: 1px #000000 solid;
  padding: 12px;
  overflow: scroll;
  background-color:#FFF;
}
#textBox #sourceText {
  padding: 0;
  margin: 0;
  min-width: 498px;
  min-height: 200px;
}
#editMode label { cursor: pointer; }
.blogform {
	width: 90%;
	margin-left: 100px;
	margin-top: 100px;
	min-height: 400px;
	border-style: groove;
	padding: 10px;
}
</style>


<script type="text/javascript">
	
<%@ include file="js/wysiwyg.js" %>

$(document).ready(function(){
	$("#tombolform").click(function(){
	$("#uploadform").attr("style","display:none");
	if($("#judul").val()=="")
		return;
	if($("#kategori").val()=="")
		return;
	if($("#katakunci").val()=="")
		return;
	if(document.getElementById("textBox").innerHTML == "")
		return;
		
	var judulpost = $("#judul").val();
	var kategoripost = $("#kategori").val();
	var katakuncipost = $("#katakunci").val();
	var bodypost = document.getElementById("textBox").innerHTML;
	alert($("#idpost").val()+judulpost+kategoripost+katakuncipost+bodypost);
	$.ajax({
				url : "simpaneditpost",
				type : "post",
				data : {
					judul : judulpost,
					kategori : kategoripost,
					katakunci : katakuncipost,
					konten : bodypost,
					idpost: $("#idpost").val()
				},
				success : function(responseText) {
				
					if(responseText == "oke"){
					var id = $("#idpost").val();
						$(".blogform").html("<br><h1 align='center' class='oke'>Post berhasil diUpdate <a href='catatan?action=baca&id="+id+"'>Lihat</a></h1>");
					}else if(responseText == "gagal"){
						$(".blogform").html("Post gagal");
					}else if(responseText == "tdklengkap"){
						$( "<p align='center' class='error'>Data tdk lengkap</h1>" ).insertBefore( "form" );

					}
				},error : function(){
					alert("edit posting gagal");
				}
		});
	
	});
	
	$("#uploadimage").click(function(){
		$("#uploadform").attr("style","display:block");
	});
	
	$("#closeuploadform").click(function(){
		$("#uploadform").attr("style","display:none");
	});
	
	$("#uploadbutton").click(function(){
			
			var file_data = $('#file').prop('files')[0]; 
			var form_data = new FormData();                  
    		form_data.append('file', file_data);
    		alert(form_data);  
			$.ajax({
				url : "upload",
				type: "post",
				enctype : 'multipart/form-data',
             	data : form_data,
	            processData : false,
	            contentType : false,
	            cache : false,
				
				success :function(data){
					 if(data=="gagal"){
						alert("upload: "+data);
					}else{
					/* $("#displaypict").empty();
					$("#displaypict").append("<img src='${pageContext.request.contextPath }/image/"+data+"'/>");
					 */
						var html = "<img style='max-width:500px' src='${pageContext.request.contextPath }/image/"+data+"'/>";
						alert("UPLOAD SUKSES"+data);
						window.stop();
						insertHtmlAfterSelection(html);
					}
				},
				error:function(){
					alert("gagal upload");
				}
				
			});
		});
		
		
function insertHtmlAfterSelection(html) {
    var sel, range, expandedSelRange, node;
    if (window.getSelection) {
        sel = window.getSelection();
        if (sel.getRangeAt && sel.rangeCount) {
            range = window.getSelection().getRangeAt(0);
            expandedSelRange = range.cloneRange();
            range.collapse(false);

            // Range.createContextualFragment() would be useful here but is
            // non-standard and not supported in all browsers (IE9, for one)
            var el = document.createElement("div");
            el.innerHTML = html;
            var frag = document.createDocumentFragment(), node, lastNode;
            while ( (node = el.firstChild) ) {
                lastNode = frag.appendChild(node);
            }
            range.insertNode(frag);

            // Preserve the selection
            if (lastNode) {
                expandedSelRange.setEndAfter(lastNode);
                sel.removeAllRanges();
                sel.addRange(expandedSelRange);
            }
        }
    } /* else if (document.selection && document.selection.createRange) {
        range = document.selection.createRange();
        expandedSelRange = range.duplicate();
        range.collapse(false);
        range.pasteHTML(html);
        expandedSelRange.setEndPoint("EndToEnd", range);
        expandedSelRange.select();
    } */
}
	
});
</script>
</head>
<body onload="initDoc();">
	<div class="container">
		<div class="header"></div>
		<div class="content">
		<table><tr><td width="300px">
			<div class="blogform">
			<h2 align="center">Edit Posting</h2>
				<form method="post" name="compForm" id="myform">
					<table>
						<tr><input type="hidden" id="idpost" value="${post.idpost }"/>
							<td>Judul<br> <input type="text" id="judul" value='<c:out value="${post.judul }"></c:out>' /></td>
						</tr>
						<tr>
							<td>Konten<br>

								<div id="toolBar1">
									<select onchange="formatDoc('formatblock',this[this.selectedIndex].value);this.selectedIndex=0;">
										<option selected>- formatting -</option>
										<option value="h1">Title 1 &lt;h1&gt;</option>
										<option value="h2">Title 2 &lt;h2&gt;</option>
										<option value="h3">Title 3 &lt;h3&gt;</option>
										<option value="h4">Title 4 &lt;h4&gt;</option>
										<option value="h5">Title 5 &lt;h5&gt;</option>
										<option value="h6">Subtitle &lt;h6&gt;</option>
										<option value="p">Paragraph &lt;p&gt;</option>
										<option value="pre">Preformatted &lt;pre&gt;</option>
									</select>
									<select onchange="formatDoc('fontname',this[this.selectedIndex].value);this.selectedIndex=0;">
										<option class="heading" selected>- font -</option>
										<option>Arial</option>
										<option>Arial Black</option>
										<option>Courier New</option>
										<option>Times New Roman</option>
									</select>
									<select onchange="formatDoc('fontsize',this[this.selectedIndex].value);this.selectedIndex=0;">
										<option class="heading" selected>- size -</option>
										<option value="1">Very small</option>
										<option value="2">A bit small</option>
										<option value="3">Normal</option>
										<option value="4">Medium-large</option>
										<option value="5">Big</option>
										<option value="6">Very big</option>
										<option value="7">Maximum</option>
									</select>
									<select onchange="formatDoc('forecolor',this[this.selectedIndex].value);this.selectedIndex=0;">
										<option class="heading" selected>- color -</option>
										<option value="red">Red</option>
										<option value="blue">Blue</option>
										<option value="green">Green</option>
										<option value="black">Black</option>
									</select>
									<select onchange="formatDoc('backcolor',this[this.selectedIndex].value);this.selectedIndex=0;">
										<option class="heading" selected>- background -</option>
										<option value="red">Red</option>
										<option value="green">Green</option>
										<option value="black">Black</option>
									</select>
								</div>
								
								<div id="toolBar2">
									<img class="intLink" title="Clean"
										onclick="if(validateMode()&&confirm('Are you sure?')){oDoc.innerHTML=sDefTxt};"
										src="${pageContext.request.contextPath }/image/clean.gif" /> <img class="intLink"
										title="Print" onclick="printDoc();" src="${pageContext.request.contextPath }/image/print.png">
									<img class="intLink" title="Undo" onclick="formatDoc('undo');"
										src="${pageContext.request.contextPath }/image/undo.gif" /> <img class="intLink" title="Redo"
										onclick="formatDoc('redo');" src="${pageContext.request.contextPath }/image/redo.gif" /> <img
										class="intLink" title="Remove formatting"
										onclick="formatDoc('removeFormat')"
										src="${pageContext.request.contextPath }/image/formating.png"> <img class="intLink"
										title="Bold" onclick="formatDoc('bold');"
										src="${pageContext.request.contextPath }/image/bold.gif" /> <img class="intLink"
										title="Italic" onclick="formatDoc('italic');"
										src="${pageContext.request.contextPath }/image/italic.gif" /> <img class="intLink"
										title="Underline" onclick="formatDoc('underline');"
										src="${pageContext.request.contextPath }/image/underline.gif" /> <img class="intLink"
										title="Left align" onclick="formatDoc('justifyleft');"
										src="${pageContext.request.contextPath }/image/ratakiri.gif" /> <img class="intLink"
										title="Center align" onclick="formatDoc('justifycenter');"
										src="${pageContext.request.contextPath }/image/ratatengah.gif" /> <img class="intLink"
										title="Right align" onclick="formatDoc('justifyright');"
										src="${pageContext.request.contextPath }/image/ratakanan.gif" /> <img class="intLink"
										title="Numbered list"
										onclick="formatDoc('insertorderedlist');"
										src="${pageContext.request.contextPath }/image/number.gif" /> <img class="intLink"
										title="Dotted list"
										onclick="formatDoc('insertunorderedlist');"
										src="${pageContext.request.contextPath }/image/bullet.gif" /> <img class="intLink"
										title="Quote" onclick="formatDoc('formatblock','blockquote');"
										src="${pageContext.request.contextPath }/image/petik.gif" /> <img class="intLink"
										title="Add indentation" onclick="formatDoc('outdent');"
										src="${pageContext.request.contextPath }/image/removetab.gif" /> <img class="intLink"
										title="Delete indentation" onclick="formatDoc('indent');"
										src="${pageContext.request.contextPath }/image/tab.gif" /> <img class="intLink"
										title="Hyperlink"
										onclick="var sLnk=prompt('Write the URL here','http:\/\/');if(sLnk&&sLnk!=''&&sLnk!='http://'){formatDoc('createlink',sLnk)}"
										src="${pageContext.request.contextPath }/image/link.gif" /> <img class="intLink" title="Cut"
										onclick="formatDoc('cut');" src="${pageContext.request.contextPath }/image/cut.gif" /> <img
										class="intLink" title="Copy" onclick="formatDoc('copy');"
										src="${pageContext.request.contextPath }/image/copy.gif" /> <img class="intLink"
										title="Paste" onclick="formatDoc('paste');"
										src="${pageContext.request.contextPath }/image/paste.gif" /> <img class="intLink"
										title="Insert Image" onclick="Confirm.render()"
										src="${pageContext.request.contextPath }/image/img.png" height="17" />
										<img class="intLink"
										title="Upload Image" id="uploadimage"
										src="${pageContext.request.contextPath }/image/imgupload.png" height="17" />
								</div>
							
								<div id="textBox" name="tulisan" contenteditable="true">${post.konten}</div>
								<p id="editMode">
									<input type="checkbox" name="switchMode" id="switchBox"
										onchange="setDocMode(this.checked);" /> <label
										for="switchBox">Show HTML</label>
								</p>
						</tr>
						<tr>
							<td>Kategori<select id="kategori" style="width: 100px">
							<option value="1" selected="selected">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							</select></td><td><input type="button" id="tombolform" value="simpan" /></td>
						</tr>
						<tr><td>Katakunci <input type="text" id="katakunci" value="${post.katakunci }" /></td></tr>
					</table>

				</form>
				</div>
				</td>
			<td>
				<div id='uploadform' style='display: none;width:200px; height:150px; background-color:buttonface; margin-top:120px;  margin-left: 10% '>
					<div align='right'><button id='closeuploadform'>tutup</button></div>
					<form  id='formupload' style='margin-top:50px; margin-left:50px; width:200px; height: 150px; border-style:inset' method='post' enctype='multipart/form-data'>
							<input type='file' id='file' name='file' size='50' /> <br />
							<input type='button' id='uploadbutton' value='Upload File' />
					</form>
					<div id="displaypict"></div>
				</div>
			</td>
			</tr>
			</table>
				<br><a href="post?action=daftarposting">kembali</a>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</div>

</body>
</html>