$(function(){
    getDueTime();
});

//매칭 시간 표시 
function getDueTime(){
    //매칭: 오전 10시 ~ 오후 4시(16시)
    let nowTime = new Date();
    const startTime = new Date(nowTime.getFullYear(), nowTime.getMonth(), nowTime.getDate(), 10, 0, 0);
    const endTime = new Date(nowTime.getFullYear(), nowTime.getMonth(), nowTime.getDate(), 16, 0, 0);
    let timeStr = ``;

    if(nowTime.getTime() < startTime.getTime()){
        //매칭시간 시작 이전
        timeStr = `매칭은 10시부터 가능합니다.`;
        
    }else if(nowTime.getTime() > endTime.getTime()){
        //매칭시간 종료 이후
        timeStr = `매칭이 종료되었습니다.`;

    }else{
        //매칭 시간
        setInterval(()=>{
            nowTime = new Date();
            let dueTime = endTime.getTime() - nowTime.getTime();
            let timeStr = "";
    
            if(dueTime > 0){
                timeStr = 
                `남은 매칭 시간: 
                ${Math.floor(dueTime/1000/60/60)}시간 
                ${Math.floor(dueTime/1000/60%60)}분 
                ${Math.floor(dueTime/1000%60%60)}초`;
    
            }else{
                timeStr = "매칭이 종료되었습니다.";
                clearInterval();
            }
            $(".dueTime").text(timeStr);
        },1000);
    }
    $(".dueTime").text(timeStr);

}