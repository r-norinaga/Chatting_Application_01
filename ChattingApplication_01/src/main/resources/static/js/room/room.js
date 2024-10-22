/**
 * 
 */
'use strict'

jQuery(function($){
    /** 登録ボタンを押したときの処理. */
    $('#submit_button').click(function(event) {
        // ユーザー登録
        createARoom();
    });
 });
 /** ユーザー登録処理 */
 function createARoom() {
    // バリデーション結果をクリア
    removeValidResult();
    // フォームの値を取得
    
	var formData = $('#create-room-form').serializeArray();
	
	console.log(formData);
	
	
    // ajax通信
    $.ajax({
        type : "POST",
        cache : false,
        url : '/room/createARoom/rest',
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
			alert('トークルームを作成しました');
			// ログイン画面にリダイレクト
//			window.location.href = '/message/messageList';
		}
    }).fail(function(jqXHR, textStatus, errorThrown){
		console.log(jqXHR);
		// ajax失敗時の処理
		alert('トークルームの作成に失敗しました。');
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
 
 	if(key == 'userIds'){
		// エラーメッセージ追加
		$('p[class=' + key + ']').addClass('is-invalid');
		$('p.' + key).after('<div class="invalid-feedback">' + value + '</div>');

/*
		$('input[name=' + key + ']').addClass('is-invalid');
		$('input[name=' + key + ']').after('<div class="invalid-feedback">' + value + '</div>');
		$('h3.'+key).after('<div class="invalid-feedback">' + value + '</div>');
*/
		$('ol[id="list"]').addClass('is-invalid');
		// エラーメッセージ追加
		$('ol[id="list"]').after('<div class="invalid-feedback">'+ value + '</div>'); 
	 }else {
		$('input[id=' + key + ']').addClass('is-invalid');
		// エラーメッセージ追加
		$('input[id=' + key + ']').after('<div class="invalid-feedback">' + value + '</div>');
		$('p[id="modalRoomName"]').addClass('is-invalid');
		// エラーメッセージ追加
		$('p[id="modalRoomName"]').after('<div class="invalid-feedback">'+ value + '</div>'); 
	 }

}