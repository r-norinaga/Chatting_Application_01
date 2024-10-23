/**
 * 
 */
'use strict'

jQuery(function($){
	/** 更新ボタンを押したときの処理 */
	$('#submit_button').click(function(event){
		//ユーザー更新
		updateRoomUser();
	});	
});


/** ユーザー更新処理 */
function updateRoomUser(){
	
	let modalRoomId = $('p[id="modalRoomId"]');
	
	
	
	console.log(modalRoomId.text());
	
	var formData = $('#leave-room-form' + modalRoomId.text()).serializeArray();
	
	console.log(formData);
/*
	$('p[id="modalRoomId"]').text();
*/
	

	
	
	
	
	
	
/*
	formData.text("roomId",$('#modalRoomId').val);
	let roomId = formData.getElementById('roomId');
*/
	
	
	$.ajax({
		type:"PUT",
		cache:false,
		url:'/room/leaveARoom',
		data:formData,
		dataType:'json',
	}).done(function(data){
		
		
		alert('トークルームから退出しました。');
		
		window.location.href='/talk/roomList';
				
	}).fail(function(jqXHR, textStatus, errorThrown){
		alert('トークルームからの退出に失敗しました。');
	}).always(function(){
		
	});
	
	
	
	
}