var questionSize = parseInt($('.questionSize').val());
var answerSize = parseInt($('.answerSize').val());
var questionIds = [];

for(var i = 0; i < questionSize; i++){
    questionIds.push($(".getId").eq(i).val());
}

for(var i = 0; i < questionSize; i++){
    for(var j = i * answerSize / questionSize; j < (i + 1) * answerSize / questionSize; j++) {
        $('.oneAnswer' + questionIds[i]).eq(j).css("display", "block");
    }
}