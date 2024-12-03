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
	
//	let modalRoomId = $('p[id="modalRoomId"]');
//	
//	console.log(modalRoomId.text());
	
	var formData = $('#entered-room-form').serializeArray();
	
	console.log(formData);

	$.ajax({
		type:"PUT",
		cache:false,
		url:'/room/enterARoom',
		data:formData,
		dataType:'json',
	}).done(function(data){
		
		
		alert('トークルームに入室しました。');
		
		window.location.href='/talk/roomList';
				
	}).fail(function(jqXHR, textStatus, errorThrown){
		alert('トークルームへの入室に失敗しました。');
	}).always(function(){
		
	});
	
	
	
	
}