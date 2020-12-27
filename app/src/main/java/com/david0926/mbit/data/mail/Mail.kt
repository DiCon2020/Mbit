package com.david0926.mbit.data.mail

import com.david0926.mbit.data.UserModel

data class Mail(
    val my_text: Boolean, // 내가 보낸 메일인지
    val send_date: String, // 보낸 시각
    val text: String, // 내용
    val sender_info: UserModel // 전송자 정보
)
