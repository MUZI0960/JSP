<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<table>
   <thead>
      <tr>
         <th>테이블명</th>
         <th>테이블스페이스명</th>
         <th>레코드수</th>
      </tr>
   </thead>
   <tbody id="tableTbody">
   
   </tbody>
</table>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
         <div class="modal-body">
            <table>
               <thead>
                  <tr>
                     <th>테이블명</th>
                     <th>컬럼명</th>
                     <th>데이터타입</th>
                     <th>데이터길이</th>
                     <th>null여부</th>
                  </tr>
               </thead>
               <tbody id="colTbody">
                  
               </tbody>
            </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
//descendants 구조 아래와 동일 하다. 하지만 차이는 처음부터 다보는데, 밑에껀 tableTbody요거부터 찾고 이벤트가 발생하면 다음을 찾는 구조
// 아래의 모양을 잘 기억해두고 저런식으로 자주 활용하자.
//    $("#tableTbody tr").on("click" , function () {
         
//    });
//    let tableTbody = $("#tableTbody").on("click","tr" ,function () {
      
//       let tableSchemaVO = $(this).data("source"); //돌아오는 타입 테이블 스키마 VO
//    });
   
   
   //--------------------------------------------------------------
   let tableTbody = $("#tableTbody").data("propNames",["tableName", "tablespaceName", "numRows"]);
   let colTbody = $("#colTbody").data("propNames",["tableName", "columnName", "dataType", "dataLength", "nullable"]);
   
   let makeCommonTR = function (sourceObj, listTbody) {
      //위의 배열을 받아옴.
      let propNames = listTbody.data("propNames");
      let tdTags = [];
      $(propNames).each(function (idx, name) {
         tdTags.push( $("<td>").html(sourceObj[name]) );
      })
      return $("<tr>").append(tdTags).data("source", sourceObj);
      
   }
   
   let makeColTR = function (ColSchemaVO) {
      return $("<tr>").append(
               $("<td>").html(ColSchemaVO.tableName)
               , $("<td>").html(ColSchemaVO.columnName)
               , $("<td>").html(ColSchemaVO.dataType)
               , $("<td>").html(ColSchemaVO.dataLength)
               , $("<td>").html(ColSchemaVO.nullable)
            
            ).data("csource", ColSchemaVO);
   }
   
   //--------------------------------------------------------------

   let makeTableTR = function (tableSchemaVO) {
      return $("<tr data-bs-toggle='modal' data-bs-target='#exampleModal'>").append(
               $("<td>").html(tableSchemaVO.tableName)
               , $("<td>").html(tableSchemaVO.tableSpaceName)
               , $("<td>").html(tableSchemaVO.numRows)
            
            ).data("source", tableSchemaVO);
   }
   //--------------------------------------------------------------
   
   
   let exampleModal = $("#exampleModal").on("show.bs.modal", function (event) {
      let tr = event.relatedTarget; //이벤트안에 relatedTarget프로퍼티를 통해 클릭이벤트의
      let tableSchemaVO = $(tr).data("source");
      
      
      
      $.getJSON("<%=request.getContextPath()%>/schema/columnSchema"
            , {
               what:tableSchemaVO.tableName
            }
            )
         .done(function (resp) {
            //셋어트리뷰트로 보낸걸 이렇게 꺼낸것임.
            let trTags= [];
            $(resp.columnList).each(function (idx, ColSchemaVO) {
               trTags.push(makeCommonTR(ColSchemaVO,colTbody));
            });
            colTbody.empty();
            colTbody.append(trTags);
         });
      
      
   }).on("hidden.bs.modal",function(event){
      $(this).find("tbody").empty();
   });
   
   

   

   
   $.getJSON("<%=request.getContextPath()%>/schema/tableSchema")
      .done(function(resp) {
         //resp 응답데이터
         let trTags =[];
         
         $(resp.tableList).each(function (idx, tableSchemaVO) {
            let jqTr = makeCommonTR(tableSchemaVO, tableTbody);
//             data-bs-toggle='modal' data-bs-target='#exampleModal'>
            jqTr.attr({
               "data-bs-toggle" : "modal"
               ,"data-bs-target" : "#exampleModal"
               
            });
            trTags.push( jqTr );
         });
         tableTbody.empty();
         tableTbody.append(trTags);
      });


</script>


<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>