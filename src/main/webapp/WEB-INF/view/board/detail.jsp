<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>

        <div class="container my-3">
            <c:if test="${principal.id == boardDto.userId}">
                <div class="mb-3">
                    <a href="/board/${boardDto.boardId}/updateForm" class="btn btn-warning">수정</a>
                    <button onclick="boardDeleteById(`${boardDto.boardId}`)" id="btn-delete"
                        class="btn btn-danger">삭제</button>
                </div>
            </c:if>



            <div class="mb-2">
                글 번호 : <span id="id"><i>${boardDto.boardId} </i></span> 작성자 : <span class="me-3"><i>${dto.username}
                    </i></span>

                <i id="heart" class="fa-regular fa-heart my-xl my-cursor" value="no"></i>
            </div>

            <div>
                <h3>${boardDto.title}</h3>
            </div>
            <hr />
            <div>
                <div>${boardDto.content}</div>
            </div>
            <hr />

            <div class="card">
                <form>
                    <input id="boardId" type="hidden" name="boardId" value="${boardDto.boardId}">
                    <div class="card-body">
                        <textarea id="comment" class="form-control" rows="1"></textarea>
                    </div>
                    <div class="card-footer">
                        <button onclick="insertReply()" type="button" id="btn-reply-save" class="btn btn-primary">등록</button>
                    </div>
                </form>
            </div>
            <br />
            <div class="card">
                <div class="card-header">댓글 리스트</div>
                    <ul id="reply-box" class="list-group">
                <c:forEach items="${replyDtos}" var="replyDtos">
                        <li id="reply-${replyDtos.replyId}" class="list-group-item d-flex justify-content-between">
                            <div>${replyDtos.comment}</div>
                            <div class="d-flex">
                                <div class="font-italic">작성자 : ${replyDtos.username} &nbsp;</div>
                                <button onClick="replyDeleteById(${replyDtos.replyId})" class="badge bg-secondary">삭제</button>
                            </div>
                        </li>
                </c:forEach>
                    </ul>

            </div>
        </div>

        <script>
            function insertReply() {
                let comment = $("#comment").val()
                let boardId = $("#boardId").val()

                let reply = {
                    comment: comment,
                    boardId: boardId
                }

                $.ajax({
                    type: "put",
                    url: "/reply/",
                    data: JSON.stringify(reply),
                    contentType: "application/json; charset=uft-8",
                    dataType: "json"
                }).done((res) => {
                    alert(res.msg);
                    location.href = "/board/" + boardId;
                }).fail((err) => {
                    alert(err.responseJSON.msg);
                });
            }

            function replyDeleteById(id) {
                $.ajax({
                    type: "delete",
                    url: "/reply/" + id,
                    dataType: "json"
                }).done((res) => {
                    alert(res.msg);
                    // location.href = "/board/"+${boardDto.boardId};
                    $("#reply-"+id).remove();
                }).fail((err) => {
                    alert(err.responseJSON.msg);
                });
            }

            function boardDeleteById(id) {
                $.ajax({
                    type: "delete",
                    url: "/board/" + id,
                    dataType: "json"
                }).done((res) => {
                    alert(res.msg);
                    location.href = "/";
                }).fail((err) => {
                    alert(err.responseJSON.msg);
                });
            }
        </script>

        <%@ include file="../layout/footer.jsp" %>