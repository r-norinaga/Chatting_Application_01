/**
 * 
 */
'use strict'

jQuery(function($){
    /** 登録ボタンを押したときの処理. */
    $('#submit_button').click(function(event) {
        // ユーザー登録
        leaveARoom();
    });
 });
 /** ユーザー登録処理 */
 function leaveARoom() {
    // バリデーション結果をクリア
    removeValidResult();
    // フォームの値を取得
    

	var formData = $('#leave-room-form').serializeArray();
	console.log(formData);

	
    // ajax通信
    $.ajax({
        type : "POST",
        cache : false,
        url : '/room/leaveARoom/rest',
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