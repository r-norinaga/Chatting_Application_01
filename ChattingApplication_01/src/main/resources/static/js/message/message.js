/**
 * 
 */
'use strict'

jQuery(function($){
    /** 登録ボタンを押したときの処理. */
    $('#submit_button').click(function(event) {
        // ユーザー登録
        postMessage();
    });
 });
 /** ユーザー登録処理 */
 function postMessage() {
    // バリデーション結果をクリア
    removeValidResult();
    // フォームの値を取得
    
	var formData = $('#message-form').serializeArray();
	
	console.log(formData);
	
	
    // ajax通信
    $.ajax({
        type : "POST",
        cache : false,
        url : '/message/postMessage/rest',
        data: formData,
        dataType : 'json',
    }).done(function(data){
        // ajax成功時の処理
        console.log(data);
        
		if(data.result === 90) {
			// validationエラー時の処理
			$.each(data.errors, function(key, value) {
				reflectValidResult(key, value)
			});
		}else if(data.result === 0){
			alert('メッセージを投稿しました');
			// ログイン画面にリダイレクト
//			window.location.href = '/message/messageList';
		}
    }).fail(function(jqXHR, textStatus, errorThrown){
		// ajax失敗時の処理
		alert('メッセージの投稿に失敗しました。');
	}).always(function() {
 		// 常に実行する処理
	});
 }
 
 /** バリデーション結果をクリア */
 function removeValidResult() {
	 $('.is-invalid').removeClass('is-invalid');
	 $('.invalid-feedback').remove();
	 $('.text-danger').remove();
 }
 /** バリデーション結果の反映 */
 function reflectValidResult(key, value) {
 // エラーメッセージ追加
 // CSS適用
		$('textarea[id=' + key + ']').addClass('is-invalid');
		// エラーメッセージ追加
		$('textarea[id=' + key + ']').after('<div class="invalid-feedback">' + value + '</div>');
		
		$('p[id="modalContent"]').addClass('is-invalid');
		// エラーメッセージ追加
//		$('p[id="modalContent"]').after('<div class="invalid-feedback" th:text="{'+ key + 'は' +  value + '}" >' + '</div>');
		$('p[id="modalContent"]').after('<div class="invalid-feedback">'+ value + '</div>'); 

	 
/** 
 		const 	textarea = document.getElementById('content');
 		textarea.setAttribute("class", "is-invalid");
 		let new_element = document.createElement('div');
 		new_element.setAttribute("class", "invalid-feedback");
 		new_element.setAttribute("errors", "content");
 		textarea.after(new_element);
 		
// 		textarea.after('<div class="invalid-feedback" th:errors="*{content}">' + value + '</div>');
//		$('input[id=' + key + ']').setAttribute("th:errorclass", "is-invalid");
//		
 		
**/
	

 }
 
 jQuery(function($){
	/** 更新ボタンを押したときの処理 */
	$('#submit_button_message_deletion').click(function(event){
		//ユーザー更新
		deleteMyMessage();
	});	
});


/** 処理 */
function deleteMyMessage(){
	
	
	var formData = $('#delete-my-message-form').serializeArray();
	

	
	$.ajax({
		type:"PUT",
		cache:false,
		url:'/message/deleteMyMessage',
		data:formData,
		dataType:'json',
	}).done(function(data){
		
		
		alert('メッセージを削除しました。');
		
		window.location.href = '/message/messageList?roomId=' + data;
				
	}).fail(function(jqXHR, textStatus, errorThrown){
		alert('メッセージの削除に失敗しました。');
	}).always(function(){
		
	});
	
	
	
	
}