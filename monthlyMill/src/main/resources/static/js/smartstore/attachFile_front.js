//셀 누르면 전체가 보이도록 (toggle)
$(document).on("click", ".tdata", function(){
    this.parentNode.querySelectorAll(".tdata").forEach((cell)=>{
        cell.classList.toggle("expand");
    });
})
/*============= */


//리사이즈 드래그 기능추가
document.querySelectorAll(".thead").forEach((head)=>{
    const div = createTableResizeDiv();
    head.appendChild(div);
    dragResize(div);
});

//이벤트 범위생성
function createTableResizeDiv(){
    const div = document.createElement("div");
    div.className = "resizeLine";
    return div;
}

//드래그 이벤트 액션
function dragResize(div){
    let curCol,nextCol,pageX,curColWidth,nextColWidth,tbWidth;

    //드래그 시작
    div.addEventListener("mousedown", function(e){
        curCol  = e.target.parentElement;
        nextCol = curCol.nextElementSibling;
        pageX = e.pageX;
        curColWidth = curCol.offsetWidth;
        if(nextCol){
            nextColWidth = nextCol.offsetWidth;
        }
        tbWidth = curCol.parentElement.parentElement.parentElement.offsetWidth;

        //드래그 중
        document.addEventListener("mousemove",onMouseMove);
    });

    function onMouseMove(event){
        if(curCol){
            let diffX = event.pageX - pageX;
            
            //너비 변경, 최대 너비 제한 풀기
            curCol.style.width = (curColWidth + (diffX))+'px';
            curCol.style.maxWidth = 'none';

            document.querySelector(".table").style.width = (tbWidth + (diffX))+'px';

            if(document.querySelectorAll(".trow").length>0){
                document.querySelectorAll(".trow").forEach((cell)=>{
                    cell.children[curCol.cellIndex].style.width = (curColWidth + (diffX))+'px';
                    cell.children[curCol.cellIndex].style.maxWidth = 'none'; 
                });
            }

        }
    };


    //드래그 종료
    document.addEventListener("mouseup", function(e){
        if(curCol){
            // console.log(e.pageX);
            curCol = undefined;
            nxtCol = undefined;
            pageX = undefined;
            nxtColWidth = undefined;
            curColWidth = undefined;

            //이벤트 종료
            document.removeEventListener("mousemove", onMouseMove);
        }
    });   
}

