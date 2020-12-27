package com.david0926.mbit.network.mail

import com.david0926.mbit.data.comment.CommonResponse
import com.david0926.mbit.data.mail.Mail
import com.david0926.mbit.data.mail.MailGetRequest
import com.david0926.mbit.data.mail.MailItem
import com.david0926.mbit.data.mail.MailSendRequest
import com.david0926.mbit.data.post.*
import com.david0926.mbit.network.RemoteDataSource
import com.david0926.mbit.network.RemoteDataSourceImpl

class MailManager {
    private val retrofitRemoteDataSource: RemoteDataSource = RemoteDataSourceImpl()
    /**
     *  누군가와의 메일들을 불러올 때 사용함
     **/
    fun getMails(token: String, mailGetRequest: MailGetRequest, onResponse : (CommonResponse, ArrayList<Mail>?) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.getMails(token, mailGetRequest, onResponse, onFailure)
    }

    /**
     *  자신의 메일들을 불러올 때 사용함
     *  MailItem 객체에는 제플린 처럼 메일 목록에 들어갈 정보만 제공 ( 상대방 정보, 마지막 대화 정보 )
     *  MailItem 객체의 Mail 변수에는 text와 sendDate만 들어있음
     **/
    fun getMyMails(token: String, onResponse : (CommonResponse, ArrayList<MailItem>?) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.getMailList(token, onResponse, onFailure)
    }

    /**
     *  메일 보낼 때 사용함
     *  Mail 배열에서 그 사람과의 메일정보가 새로고침된 상태로 온다고함
     **/
    fun sendMail(token: String, mailSendRequest: MailSendRequest, onResponse : (CommonResponse, ArrayList<Mail>?) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.sendMail(token, mailSendRequest, onResponse, onFailure)
    }
}