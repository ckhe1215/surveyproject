var questionSize = parseInt($('.questionSize').val());
var answerSize = parseInt($('.answerSize').val());
var questionIds = [];
var answerCnt = new Array(questionSize);
for (var i = 0; i < answerCnt.length; i++) {
    answerCnt[i] = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
}

for(var i = 0; i < questionSize; i++){
    questionIds.push($(".getId").eq(i).val());
}

for(var i = 0; i < questionSize; i++){
    for(var j = i * answerSize / questionSize; j < (i + 1) * answerSize / questionSize; j++) {
        $('.oneAnswer' + questionIds[i]).eq(j).css("display", "block");
        if ($('.oneAnswer' + questionIds[i]).eq(j).children(".answer1").val() == 'true')
            answerCnt[i][0]++;
        if ($('.oneAnswer' + questionIds[i]).eq(j).children(".answer2").val() == 'true')
            answerCnt[i][1]++;
        if ($('.oneAnswer' + questionIds[i]).eq(j).children(".answer3").val() == 'true')
            answerCnt[i][2]++;
        if ($('.oneAnswer' + questionIds[i]).eq(j).children(".answer4").val() == 'true')
            answerCnt[i][3]++;
        if ($('.oneAnswer' + questionIds[i]).eq(j).children(".answer5").val() == 'true')
            answerCnt[i][4]++;
        if ($('.oneAnswer' + questionIds[i]).eq(j).children(".answer6").val() == 'true')
            answerCnt[i][5]++;
        if ($('.oneAnswer' + questionIds[i]).eq(j).children(".answer7").val() == 'true')
            answerCnt[i][6]++;
        if ($('.oneAnswer' + questionIds[i]).eq(j).children(".answer8").val() == 'true')
            answerCnt[i][7]++;
        if ($('.oneAnswer' + questionIds[i]).eq(j).children(".answer9").val() == 'true')
            answerCnt[i][8]++;
        if ($('.oneAnswer' + questionIds[i]).eq(j).children(".answer10").val() == 'true')
            answerCnt[i][9]++;
    }
}

for(var i = 0; i < questionSize; i++){
    var ctx = document.getElementsByClassName('result' + questionIds[i]);
    var choiceCnt = $('.choiceCntOf'+questionIds[i]).val();
    var labelArr = [];
    var dataArr = [];
    for (var j = 0; j < 10; j++) {
        if (choiceCnt > j)
        {
            labelArr.push(j + 1 + '번');
            dataArr.push(answerCnt[i][j]);
        }
    }
   var result = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: labelArr,
            datasets: [{
                data: dataArr,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)',
                    'rgba(5, 0, 153, 0.2)',
                    'rgba(93, 93, 93, 0.2)',
                    'rgba(255, 0, 221, 0.2)',
                    'rgba(153, 112, 0, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)',
                    'rgba(5, 0, 153, 1)',
                    'rgba(93, 93, 93, 1)',
                    'rgba(255, 0, 221, 1)',
                    'rgba(153, 112, 0, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            responsive: false
        }
    });
}