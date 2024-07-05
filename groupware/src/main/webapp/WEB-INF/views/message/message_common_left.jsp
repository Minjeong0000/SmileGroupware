<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div><h1><i class="fa-regular fa-message"></i>  쪽지</h1></div>
<div class="msg-menu">
    <div>
        <a href="http://127.0.0.1:8080/message/received"><i class="fa-solid fa-envelope">받은 쪽지함</i></a>
            <ul>
                <a href="http://127.0.0.1:8080/message/important"><li><i class="fa-solid fa-star"></i> 중요 쪽지함</li></a>
                <a href="http://127.0.0.1:8080/message/trash"><li> <i class="fa-solid fa-trash-can"></i> 휴지통</li></a>
            </ul>
    </div>
    <div><a href=""><i class="fa-regular fa-envelope-open"></i> 읽은 쪽지함</a></div>
    <div><a href="http://127.0.0.1:8080/message/sent"><i class="fa-regular fa-paper-plane"></i> 보낸 쪽지함</a></div>


    <div><button id="openModal">쪽지 보내기</button></div>


</div>