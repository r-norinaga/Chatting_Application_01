/**
 * 
 */
'use strict'

jQuery(function($){
	/** 更新ボタンを押したときの処理 */
	$('#btn-update').click(function(event){
		//ユーザー更新
		updateUser();
	});	
});


/** ユーザー更新処理 */
function updateUser(){
	
	
	var formData = $('#user-detail-form').serializeArray();
	
	$.ajax({
		type:"PUT",
		cache:false,
		url:'/user/update',
		data:formData,
		dataType:'json',
	}).done(function(data){
		
		
		alert('ユーザーを更新しました。');
		
		window.location.href='/menu/userMenu';
				
	}).fail(function(jqXHR, textStatus, errorThrown){
		alert('ユーザー更新に失敗しました。');
	}).always(function(){
		
	});
	
	
	
	
}